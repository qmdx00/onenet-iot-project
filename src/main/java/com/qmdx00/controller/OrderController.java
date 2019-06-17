package com.qmdx00.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.qmdx00.entity.Account;
import com.qmdx00.entity.Order;
import com.qmdx00.service.AccountService;
import com.qmdx00.service.OrderService;
import com.qmdx00.util.*;
import com.qmdx00.util.enums.ResponseStatus;
import com.qmdx00.util.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author yuanweimin
 * @date 19/06/17 08:43
 * @description 订单 Controller
 */
@Slf4j
@RestController
@RequestMapping("/api/order")
public class OrderController extends BaseController {

    private final TokenUtil tokenUtil;
    private final AccountService accountService;
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService, TokenUtil tokenUtil, AccountService accountService) {
        this.orderService = orderService;
        this.tokenUtil = tokenUtil;
        this.accountService = accountService;
    }

    /**
     * 获取当前用户的所有订单
     *
     * @param request 请求
     * @return Response
     */
    @GetMapping
    public Response getAllOrder(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (!VerifyUtil.checkString(token)) {
            return ResultUtil.returnStatus(ResponseStatus.NOT_LOGIN);
        } else {
            try {
                // 解析token
                Claim claim = tokenUtil.getClaim(token, "account_id");
                String customerId = claim.asString();
                Account account = accountService.findAccountById(customerId);
                if (account != null) {
                    List<Order> orders = orderService.findAllOrder(customerId);
                    if (orders != null) {
                        log.info("get orders: {}", orders);
                        return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS, orders);
                    } else {
                        return ResultUtil.returnStatus(ResponseStatus.NOT_FOUND);
                    }
                } else {
                    return ResultUtil.returnStatus(ResponseStatus.VISITED_FORBID);
                }
            } catch (JWTVerificationException e) {
                // 解析失败，token无效
                log.error("{}", e);
                return ResultUtil.returnStatus(ResponseStatus.NOT_LOGIN);
            }
        }
    }

    /**
     * 通过订单 ID 获取订单信息
     *
     * @param request 请求
     * @param id      订单 ID
     * @return Response
     */
    @GetMapping("/{id}")
    public Response getOrderById(HttpServletRequest request,
                                 @PathVariable String id) {
        String token = request.getHeader("token");
        if (!VerifyUtil.checkString(token, id)) {
            return ResultUtil.returnStatus(ResponseStatus.NOT_LOGIN);
        } else {
            try {
                // 解析token
                Claim claim = tokenUtil.getClaim(token, "account_id");
                String customerId = claim.asString();
                Account account = accountService.findAccountById(customerId);
                if (account != null) {
                    Order order = orderService.findOrderById(id, customerId);
                    if (order != null) {
                        return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS, order);
                    } else {
                        return ResultUtil.returnStatus(ResponseStatus.NOT_FOUND);
                    }
                } else {
                    return ResultUtil.returnStatus(ResponseStatus.VISITED_FORBID);
                }
            } catch (JWTVerificationException e) {
                // 解析失败，token无效
                log.error("{}", e);
                return ResultUtil.returnStatus(ResponseStatus.NOT_LOGIN);
            }
        }
    }

    /**
     * 创建一条订单
     *
     * @param request      请求
     * @param producibleId 可生产产品ID
     * @param number       产品数量
     * @param diameter     直径
     * @param length       长度
     * @param weight       重量
     * @return Response
     */


    @PostMapping
    public Response createOrder(HttpServletRequest request,
                                @RequestParam("producible_id") String producibleId,
                                @RequestParam("number") Integer number,
                                @RequestParam("diameter") Double diameter,
                                @RequestParam("length") Double length,
                                @RequestParam("weight") Double weight) {

        String token = request.getHeader("token");
        if (!VerifyUtil.checkString(token, producibleId) || !VerifyUtil.checkInteger(number) || !VerifyUtil.checkDouble(diameter, length, weight)) {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        } else {
            try {
                // 解析token
                Claim claim = tokenUtil.getClaim(token, "account_id");
                String customerId = claim.asString();
                Account account = accountService.findAccountById(customerId);
                if (account != null) {
                    String orderId = UUIDUtil.getUUID();
                    Order order = Order.builder()
                            .orderId(orderId)
                            .customerId(customerId)
                            .producibleId(producibleId)
                            .number(number)
                            .diameter(diameter)
                            .length(length)
                            .weight(weight)
                            .createTime(new Date())
                            .updateTime(new Date())
                            .build();
                    log.info("create order: {}", order);
                    return ResultUtil.returnStatusAndData(orderService.saveOrder(order),
                            MapUtil.create("id", orderId));
                } else {
                    return ResultUtil.returnStatus(ResponseStatus.VISITED_FORBID);
                }
            } catch (JWTVerificationException e) {
                // 解析失败，token无效
                log.error("{}", e);
                return ResultUtil.returnStatus(ResponseStatus.NOT_LOGIN);
            }
        }
    }

    @PutMapping("/{id}")
    public Response updateOrder(HttpServletRequest request,
                                @PathVariable String id,
                                @RequestParam("producible_id") String producibleId,
                                @RequestParam("number") Integer number,
                                @RequestParam("diameter") Double diameter,
                                @RequestParam("length") Double length,
                                @RequestParam("weight") Double weight) {

        String token = request.getHeader("token");
        if (!VerifyUtil.checkString(id, token, producibleId) || !VerifyUtil.checkInteger(number) || !VerifyUtil.checkDouble(diameter, length, weight)) {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        } else {
            try {
                // 解析token
                Claim claim = tokenUtil.getClaim(token, "account_id");
                String customerId = claim.asString();
                Account account = accountService.findAccountById(customerId);
                if (account != null) {
                    Order order = orderService.findOrderById(id, customerId);
                    if (order != null) {
                        Integer row = orderService.updateOrder(Order.builder()
                                .orderId(order.getOrderId())
                                .customerId(order.getCustomerId())
                                .producibleId(producibleId)
                                .number(number)
                                .diameter(diameter)
                                .length(length)
                                .weight(weight)
                                .updateTime(new Date())
                                .createTime(order.getCreateTime())
                                .build());
                        log.info("update order: {}", row);
                        return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS, "row", row + "");
                    } else {
                        return ResultUtil.returnStatus(ResponseStatus.NOT_FOUND);
                    }
                } else {
                    return ResultUtil.returnStatus(ResponseStatus.VISITED_FORBID);
                }
            } catch (JWTVerificationException e) {
                // 解析失败，token无效
                log.error("{}", e);
                return ResultUtil.returnStatus(ResponseStatus.NOT_LOGIN);
            }
        }
    }
}
