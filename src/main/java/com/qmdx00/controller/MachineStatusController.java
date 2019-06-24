package com.qmdx00.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.qmdx00.entity.Account;
import com.qmdx00.entity.MachineStatus;
import com.qmdx00.service.AccountService;
import com.qmdx00.service.MachineStatusService;
import com.qmdx00.util.ResultUtil;
import com.qmdx00.util.TimeUtil;
import com.qmdx00.util.TokenUtil;
import com.qmdx00.util.VerifyUtil;
import com.qmdx00.util.enums.ResponseStatus;
import com.qmdx00.util.enums.Role;
import com.qmdx00.util.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author yuanweimin
 * @date 19/06/24 08:49
 * @description 获取设备历史数据 Controller
 */
@Slf4j
@RestController
@RequestMapping("/api/status")
public class MachineStatusController extends BaseController {

    private final TokenUtil tokenUtil;
    private final AccountService accountService;
    private final MachineStatusService machineStatusService;

    @Autowired
    public MachineStatusController(MachineStatusService machineStatusService, TokenUtil tokenUtil, AccountService accountService) {
        this.machineStatusService = machineStatusService;
        this.tokenUtil = tokenUtil;
        this.accountService = accountService;
    }

    /**
     * 获取历史时间到现在的设备状态数据
     *
     * @param request   请求
     * @param timestamp 13位的时间戳
     * @return Response
     */
    @GetMapping("/{timestamp}")
    public Response getStatusFromLast(HttpServletRequest request,
                                      @PathVariable String timestamp) {

        String token = request.getHeader("token");
        if (!VerifyUtil.checkString(token, timestamp) || timestamp.length() != 13) {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        } else {
            try {
                // 解析token
                Claim claim = tokenUtil.getClaim(token, "account_id");
                Account account = accountService.findAccountById(claim.asString());
                // 判断角色是否有权限
                if (account != null && account.getRole() == Role.ADMIN) {
                    List<MachineStatus> statuses = machineStatusService
                            .findStatusToNow(TimeUtil.toDate(timestamp));
                    log.info("get status: {}", statuses.size());
                    return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS, statuses);
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

    @GetMapping("/{start}/{end}")
    public Response getStatusByTime(HttpServletRequest request,
                                    @PathVariable String start,
                                    @PathVariable String end) {
        String token = request.getHeader("token");
        if (!VerifyUtil.checkString(token, start, end) || start.length() != 13 || end.length() != 13) {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        } else {
            try {
                // 解析token
                Claim claim = tokenUtil.getClaim(token, "account_id");
                Account account = accountService.findAccountById(claim.asString());
                // 判断角色是否有权限
                if (account != null && account.getRole() == Role.ADMIN) {
                    List<MachineStatus> statuses = machineStatusService.findStatusByTime(
                            TimeUtil.toDate(start),
                            TimeUtil.toDate(end));
                    log.info("get status: {}", statuses.size());
                    return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS, statuses);
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
