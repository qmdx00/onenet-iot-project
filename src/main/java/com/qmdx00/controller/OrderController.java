package com.qmdx00.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.qmdx00.entity.Account;
import com.qmdx00.entity.Order;
import com.qmdx00.entity.OrderStatus;
import com.qmdx00.service.AccountService;
import com.qmdx00.service.OrderService;
import com.qmdx00.service.OrderStatusService;
import com.qmdx00.util.*;
import com.qmdx00.util.enums.ResponseStatus;
import com.qmdx00.util.enums.Role;
import com.qmdx00.util.enums.Status;
import com.qmdx00.util.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author yuanweimin
 * @date 19/06/17 08:43
 * @description 订单 Controller
 */
@SuppressWarnings({"unchecked", "Duplicates"})
@Slf4j
@RestController
@RequestMapping("/api/order")
public class OrderController extends BaseController {

    private final TokenUtil tokenUtil;
    private final AccountService accountService;
    private final OrderService orderService;
    private final OrderStatusService orderStatusService;

    @Autowired
    public OrderController(OrderService orderService, TokenUtil tokenUtil, AccountService accountService, OrderStatusService orderStatusService) {
        this.orderService = orderService;
        this.tokenUtil = tokenUtil;
        this.accountService = accountService;
        this.orderStatusService = orderStatusService;
    }

    /**
     * 管理员获取所有订单
     *
     * @param request 请求
     * @return Response
     */
    @GetMapping("/admin")
    public Response getAllOrderByAdmin(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (!VerifyUtil.checkString(token)) {
            return ResultUtil.returnStatus(ResponseStatus.NOT_LOGIN);
        } else {
            try {
                // 解析token
                Claim claim = tokenUtil.getClaim(token, "account_id");
                String customerId = claim.asString();
                Account account = accountService.findAccountById(customerId);
                if (account != null && account.getRole() == Role.ADMIN) {
                    List<Order> orders = orderService.findAllOrderByAdmin();
                    if (orders != null) {
                        List<HashMap> list = new LinkedList<>();
                        for (Order order : orders) {
                            HashMap in = new HashMap();
                            in.put("order", order);
                            in.put("status", orderStatusService.getStatusById(order.getOrderId()));
                            list.add(in);
                        }
                        log.info("get orders: {}", list);
                        return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS, list);
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
     * 获取当前用户的所有订单
     *
     * @param request 请求
     * @return Response
     */
    @GetMapping
    public Response getAllOrderByCustomer(HttpServletRequest request) {
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
                    List<Order> orders = orderService.findAllOrderByCustomer(customerId);
                    if (orders != null) {
                        List<HashMap> list = new LinkedList<>();
                        for (Order order : orders) {
                            HashMap in = new HashMap();
                            in.put("order", order);
                            in.put("status", orderStatusService.getStatusById(order.getOrderId()));
                            list.add(in);
                        }
                        log.info("get orders: {}", list);
                        return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS, list);
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
                        HashMap in = new HashMap();
                        in.put("order", order);
                        in.put("status", orderStatusService.getStatusById(order.getOrderId()));
                        return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS, in);
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
                                @RequestParam("number") String number,
                                @RequestParam("diameter") String diameter,
                                @RequestParam("length") String length,
                                @RequestParam("weight") String weight) {

        String token = request.getHeader("token");
        if (!VerifyUtil.checkString(token, producibleId, number, diameter, length, weight)) {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        } else {
            try {
                // 解析token
                Claim claim = tokenUtil.getClaim(token, "account_id");
                String customerId = claim.asString();
                Account account = accountService.findAccountById(customerId);
                if (account != null) {
                    // 创建订单记录
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
                    // 同时创建一条订单状态的记录
                    OrderStatus status = orderStatusService.saveStatus(OrderStatus.builder()
                            .orderId(orderId)
                            .orderStatus(Status.CREATE)
                            .build());
                    log.info("create order: {}", order);
                    log.info("create status: {}", status);
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

    /**
     * 通过 ID 修改订单信息
     *
     * @param request      请求
     * @param id           订单 ID
     * @param producibleId 可生产的产品信息
     * @param number       数量
     * @param diameter     直径
     * @param length       长度
     * @param weight       重量
     * @return Response
     */
    @PutMapping("/{id}")
    public Response updateOrder(HttpServletRequest request,
                                @PathVariable String id,
                                @RequestParam("producible_id") String producibleId,
                                @RequestParam("number") String number,
                                @RequestParam("diameter") String diameter,
                                @RequestParam("length") String length,
                                @RequestParam("weight") String weight) {

        String token = request.getHeader("token");
        if (!VerifyUtil.checkString(id, token, producibleId, number, diameter, length, weight)) {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        } else {
            try {
                // 解析token
                Claim claim = tokenUtil.getClaim(token, "account_id");
                String customerId = claim.asString();
                Account account = accountService.findAccountById(customerId);
                if (account != null) {
                    OrderStatus status = orderStatusService.getStatusById(id);
                    Order order = orderService.findOrderById(id, customerId);
                    if (order != null) {
                        if (status.getOrderStatus() == Status.CREATE) {
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
                            return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS,
                                    MapUtil.create("row", row + ""));
                        } else {
                            return ResultUtil.returnStatus(ResponseStatus.UPDATE_FAILED, "订单已被处理，无法修改");
                        }
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
