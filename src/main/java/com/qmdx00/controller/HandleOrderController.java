package com.qmdx00.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.qmdx00.entity.Account;
import com.qmdx00.entity.Handle;
import com.qmdx00.entity.OrderStatus;
import com.qmdx00.service.AccountService;
import com.qmdx00.service.HandleService;
import com.qmdx00.service.OrderStatusService;
import com.qmdx00.util.MapUtil;
import com.qmdx00.util.ResultUtil;
import com.qmdx00.util.TokenUtil;
import com.qmdx00.util.VerifyUtil;
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
 * @date 19/06/22 10:58
 * @description 管理员处理订单 Controller
 */
@SuppressWarnings("unchecked")
@Slf4j
@RestController
@RequestMapping("/api/handle")
public class HandleOrderController extends BaseController {

    private final TokenUtil tokenUtil;
    private final AccountService accountService;
    private final HandleService handleService;
    private final OrderStatusService orderStatusService;

    @Autowired
    public HandleOrderController(HandleService handleService, TokenUtil tokenUtil, AccountService accountService, OrderStatusService orderStatusService) {
        this.handleService = handleService;
        this.tokenUtil = tokenUtil;
        this.accountService = accountService;
        this.orderStatusService = orderStatusService;
    }

    /**
     * 获取所有订单处理信息
     *
     * @param request 请求
     * @return Response
     */
    @GetMapping
    public Response getAllHandle(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (!VerifyUtil.checkString(token)) {
            return ResultUtil.returnStatus(ResponseStatus.NOT_LOGIN);
        } else {
            try {
                // 解析token
                Claim claim = tokenUtil.getClaim(token, "account_id");
                String adminId = claim.asString();
                Account account = accountService.findAccountById(adminId);
                if (account != null && account.getRole() == Role.ADMIN) {
                    List<Handle> handles = handleService.getAllHandle();
                    if (handles != null) {
                        List<HashMap> list = new LinkedList<>();
                        for (Handle handle : handles) {
                            HashMap in = new HashMap();
                            in.put("handle", handle);
                            in.put("status", orderStatusService.getStatusById(handle.getOrderId()));
                            list.add(in);
                        }
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
     * 管理员通过订单号处理订单
     *
     * @param request 请求
     * @param id      订单号码
     * @param status  处理状态
     * @return Response
     */
    @PutMapping("/{id}")
    public Response handleOrder(HttpServletRequest request,
                                @PathVariable String id,
                                @RequestParam("status") String status) {

        String token = request.getHeader("token");
        if (!VerifyUtil.checkString(id, token, status)) {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        } else {
            try {
                // 解析token
                Claim claim = tokenUtil.getClaim(token, "account_id");
                String adminId = claim.asString();
                Account account = accountService.findAccountById(adminId);
                if (account != null && account.getRole() == Role.ADMIN) {
                    // 添加订单处理记录
                    Handle handle = handleService.insertHandle(Handle.builder()
                            .orderId(id)
                            .adminId(adminId)
                            .handleTime(new Date())
                            .handleResult(Objects.requireNonNull(getStatus(status)).name())
                            .build());
                    // 修改订单状态
                    Integer row = orderStatusService.updateStatus(OrderStatus.builder()
                            .orderId(id)
                            .orderStatus(getStatus(status))
                            .build());
                    log.info("handle order: {}", handle);
                    log.info("update status: {}", row);
                    return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS,
                            MapUtil.create("row", row + ""));
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

    private Status getStatus(String status) {
        if (status.equalsIgnoreCase("CREATE")) return Status.CREATE;
        if (status.equalsIgnoreCase("ACCEPT")) return Status.ACCEPT;
        if (status.equalsIgnoreCase("REFUSE")) return Status.REFUSE;
        else return null;
    }
}
