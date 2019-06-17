package com.qmdx00.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.qmdx00.entity.Account;
import com.qmdx00.entity.Record;
import com.qmdx00.service.AccountService;
import com.qmdx00.service.RecordService;
import com.qmdx00.util.MapUtil;
import com.qmdx00.util.ResultUtil;
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
 * @date 19/06/17 08:39
 * @description 机器操作记录 Controller
 */
@Slf4j
@RestController
@RequestMapping("/api/record")
public class RecordController extends BaseController {

    private final TokenUtil tokenUtil;
    private final AccountService accountService;
    private final RecordService recordService;

    @Autowired
    public RecordController(RecordService recordService, TokenUtil tokenUtil, AccountService accountService) {
        this.recordService = recordService;
        this.tokenUtil = tokenUtil;
        this.accountService = accountService;
    }

    /**
     * 获取操作记录列表
     *
     * @param request 请求
     * @return Response
     */
    @GetMapping
    public Response getRecordList(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (!VerifyUtil.checkString(token)) {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        } else {
            try {
                // 解析token
                Claim claim = tokenUtil.getClaim(token, "account_id");
                Account account = accountService.findAccountById(claim.asString());
                // 判断角色是否有权限
                if (account != null && account.getRole() == Role.ADMIN) {
                    List<Record> records = recordService.getAllRecord();
                    if (records != null) {
                        log.info("record list: {}", records);
                        return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS, records);
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
     * 通过ID获取操作记录
     *
     * @param request 请求
     * @param id      操作记录 ID
     * @return Response
     */
    @GetMapping("/{id}")
    public Response getRecordById(HttpServletRequest request, @PathVariable String id) {
        String token = request.getHeader("token");
        if (!VerifyUtil.checkString(token)) {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        } else {
            try {
                // 解析token
                Claim claim = tokenUtil.getClaim(token, "account_id");
                Account account = accountService.findAccountById(claim.asString());
                // 判断角色是否有权限
                if (account != null && account.getRole() == Role.ADMIN) {
                    Record record = recordService.getRecordById(id);
                    if (record != null) {
                        log.info("get record: {}", record);
                        return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS, record);
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
     * 通过 ID 删除操作记录
     *
     * @param request 请求
     * @param id      记录 ID
     * @return Response
     */
    @DeleteMapping("/{id}")
    public Response deleteRecordById(HttpServletRequest request, @PathVariable String id) {
        String token = request.getHeader("token");
        if (!VerifyUtil.checkString(token)) {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        } else {
            try {
                // 解析token
                Claim claim = tokenUtil.getClaim(token, "account_id");
                Account account = accountService.findAccountById(claim.asString());
                // 判断角色是否有权限
                if (account != null && account.getRole() == Role.ADMIN) {
                    Integer row = recordService.deleteRecordById(id);
                    log.info("delete record: {}", row);
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


}
