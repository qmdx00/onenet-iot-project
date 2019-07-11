package com.qmdx00.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.qmdx00.entity.Account;
import com.qmdx00.entity.TaskProduct;
import com.qmdx00.entity.TaskStatus;
import com.qmdx00.repository.TaskProductRepository;
import com.qmdx00.repository.TaskStatusRepository;
import com.qmdx00.service.AccountService;
import com.qmdx00.service.TaskService;
import com.qmdx00.util.*;
import com.qmdx00.util.enums.ResponseStatus;
import com.qmdx00.util.enums.Role;
import com.qmdx00.util.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author yuanweimin
 * @date 19/06/21 09:52
 * @description 生产任务 Controller
 */
@Slf4j
@RestController
@RequestMapping("/api/task")
public class TaskController extends BaseController {

    private final TokenUtil tokenUtil;
    private final AccountService accountService;
    private final TaskService taskService;
    private final TaskProductRepository taskProductRepository;

    @Autowired
    public TaskController(TokenUtil tokenUtil, AccountService accountService, TaskProductRepository taskProductRepository, TaskService taskService) {
        this.tokenUtil = tokenUtil;
        this.accountService = accountService;
        this.taskProductRepository = taskProductRepository;
        this.taskService = taskService;
    }

    /**
     * 创建生产任务
     *
     * @param request  请求
     * @param taskId   任务 ID
     * @param orderId  订单 ID
     * @param priority 任务优先级
     * @param first    第一道工序任务数
     * @param second   第二道工序任务数
     * @param third    第三道工序任务数
     * @param deadline 任务截止日期时间戳
     * @return Response
     */
    @PostMapping
    public Response commandControl(HttpServletRequest request,
                                   @RequestParam("taskId") String taskId,
                                   @RequestParam("orderId") String orderId,
                                   @RequestParam("priority") String priority,
                                   @RequestParam("first") String first,
                                   @RequestParam("second") String second,
                                   @RequestParam("third") String third,
                                   @RequestParam("deadline") String deadline) {

        String token = request.getHeader("token");
        if (!VerifyUtil.checkString(token, taskId, orderId, orderId, priority, first, second, third, deadline)
                || deadline.length() != 13) {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        } else {
            try {
                // 解析token
                Claim claim = tokenUtil.getClaim(token, "account_id");
                Account account = accountService.findAccountById(claim.asString());
                // 判断角色是否有权限
                if (account != null && account.getRole() == Role.ADMIN) {
                    TaskProduct saved = taskProductRepository.save(TaskProduct.builder()
                            .taskId(taskId)
                            .orderId(orderId)
                            .priority(priority)
                            .first(first)
                            .second(second)
                            .third(third)
                            .creatTime(new Date())
                            .deadline(TimeUtil.toDate(deadline))
                            .build());
                    if (saved != null) {
                        log.info("create task: {}", saved);
                        return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS,
                                MapUtil.create("taskId", saved.getTaskId()));
                    } else {
                        return ResultUtil.returnStatus(ResponseStatus.FAILED);
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
     * 管理员获取生产任务列表
     *
     * @param request 请求
     * @return Response
     */
    @GetMapping
    public Response getTaskList(HttpServletRequest request) {
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
                    List<TaskProduct> list = taskProductRepository.findAll();
                    if (list != null) {
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
     * 通过任务 ID 获取任务生产进度
     *
     * @param request 请求
     * @param taskId  任务 ID
     * @return Response
     */
    @GetMapping("/{taskId}/process")
    public Response getTaskProcess(HttpServletRequest request, @PathVariable String taskId) {
        String token = request.getHeader("token");
        if (!VerifyUtil.checkString(taskId, token)) {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        } else {
            try {
                // 解析token
                Claim claim = tokenUtil.getClaim(token, "account_id");
                Account account = accountService.findAccountById(claim.asString());
                // 判断角色是否有权限
                if (account != null && account.getRole() == Role.ADMIN) {
                    Map<String, Object> status = taskService.getStatus(taskId);
                    log.info("get status: {}", status);
                    return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS, status);
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

    @GetMapping("/{taskId}")
    public Response getTaskDetail(HttpServletRequest request, @PathVariable String taskId) {
        String token = request.getHeader("token");
        if (!VerifyUtil.checkString(taskId, token)) {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        } else {
            try {
                // 解析token
                Claim claim = tokenUtil.getClaim(token, "account_id");
                Account account = accountService.findAccountById(claim.asString());
                // 判断角色是否有权限
                if (account != null && account.getRole() == Role.ADMIN) {
                    Optional<TaskProduct> optional = taskProductRepository.findById(taskId);
                    if (optional.isPresent()) {
                        TaskProduct task = optional.get();
                        log.info("get task: {}", task);
                        return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS, task);
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
