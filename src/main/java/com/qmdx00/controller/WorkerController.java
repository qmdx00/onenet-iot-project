package com.qmdx00.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.qmdx00.entity.Account;
import com.qmdx00.entity.Worker;
import com.qmdx00.service.AccountService;
import com.qmdx00.service.WorkerService;
import com.qmdx00.util.*;
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
 * @date 19/06/17 08:43
 * @description 工人 Controller
 */
@Slf4j
@RestController
@RequestMapping("/api/worker")
public class WorkerController extends BaseController {

    private final TokenUtil tokenUtil;
    private final AccountService accountService;
    private final WorkerService workerService;

    @Autowired
    public WorkerController(WorkerService workerService, TokenUtil tokenUtil, AccountService accountService) {
        this.workerService = workerService;
        this.tokenUtil = tokenUtil;
        this.accountService = accountService;
    }

    /**
     * 获取工人信息列表
     *
     * @param request 请求
     * @return Response
     */
    @GetMapping
    public Response getAllWorker(HttpServletRequest request) {
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
                    List<Worker> workers = workerService.getAllWorker();
                    if (workers != null) {
                        log.info("worker list: {}", workers);
                        return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS, workers);
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
     * 通过 ID 获取工人信息
     *
     * @param id 工人 ID
     * @return Response
     */
    @GetMapping("/{id}")
    public Response getAllWorker(@PathVariable String id) {
        if (!VerifyUtil.checkString(id)) {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        } else {
            Worker worker = workerService.getWorkerById(id);
            if (worker != null) {
                log.info("get worker: {}", worker);
                return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS, worker);
            } else {
                return ResultUtil.returnStatus(ResponseStatus.NOT_FOUND);
            }
        }
    }

    /**
     * 创建一条工人信息
     *
     * @param request    请求
     * @param name       姓名
     * @param phone      手机
     * @param department 所属部门
     * @param post       职位
     * @return Response
     */
    @PostMapping
    public Response createWorker(HttpServletRequest request,
                                 @RequestParam("name") String name,
                                 @RequestParam("phone") String phone,
                                 @RequestParam("department") String department,
                                 @RequestParam("post") String post) {

        String token = request.getHeader("token");
        if (!VerifyUtil.checkString(token, name, phone, department, post)) {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        } else {
            try {
                // 解析token
                Claim claim = tokenUtil.getClaim(token, "account_id");
                Account account = accountService.findAccountById(claim.asString());
                // 判断角色是否有权限
                if (account != null && account.getRole() == Role.ADMIN) {
                    String id = UUIDUtil.getUUID();
                    Worker worker = Worker.builder()
                            .workerId(id)
                            .name(name)
                            .phone(phone)
                            .department(department)
                            .post(post)
                            .build();
                    log.info("save worker: {}", worker);
                    return ResultUtil.returnStatusAndData(workerService.saveWorker(worker),
                            MapUtil.create("id", id));
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
     * 通过 ID 修改工人信息
     *
     * @param request    请求
     * @param id         工人 ID
     * @param name       姓名
     * @param phone      手机
     * @param department 所属部门
     * @param post       职位
     * @return Response
     */
    @PutMapping("/{id}")
    public Response updateWorker(HttpServletRequest request,
                                 @PathVariable String id,
                                 @RequestParam("name") String name,
                                 @RequestParam("phone") String phone,
                                 @RequestParam("department") String department,
                                 @RequestParam("post") String post) {

        String token = request.getHeader("token");
        if (!VerifyUtil.checkString(id, token, name, phone, department, post)) {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        } else {
            try {
                // 解析token
                Claim claim = tokenUtil.getClaim(token, "account_id");
                Account account = accountService.findAccountById(claim.asString());
                // 判断角色是否有权限
                if (account != null && account.getRole() == Role.ADMIN) {
                    Worker worker = workerService.getWorkerById(id);
                    if (worker != null) {
                        Integer row = workerService.updateWorker(Worker.builder()
                                .workerId(worker.getWorkerId())
                                .name(name)
                                .phone(phone)
                                .department(department)
                                .post(post)
                                .build());
                        log.info("update worker: {}", row);
                        return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS,
                                MapUtil.create("row", row + ""));
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

    @DeleteMapping("/{id}")
    public Response deleteWorkerById(HttpServletRequest request,
                                     @PathVariable String id) {

        String token = request.getHeader("token");
        if (!VerifyUtil.checkString(id, token)) {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        } else {
            try {
                // 解析token
                Claim claim = tokenUtil.getClaim(token, "account_id");
                Account account = accountService.findAccountById(claim.asString());
                // 判断角色是否有权限
                if (account != null && account.getRole() == Role.ADMIN) {
                    Integer row = workerService.deleteWorkerById(id);
                    log.info("delete worker: {}", row);
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
