package com.qmdx00.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.qmdx00.entity.Account;
import com.qmdx00.entity.Machine;
import com.qmdx00.service.AccountService;
import com.qmdx00.service.MachineService;
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
 * @date 19/06/17 08:37
 * @description 设备 Controller
 */
@Slf4j
@RestController
@RequestMapping("/api/machine")
public class MachineController extends BaseController {

    private final TokenUtil tokenUtil;
    private final AccountService accountService;
    private final MachineService machineService;

    @Autowired
    public MachineController(MachineService machineService, TokenUtil tokenUtil, AccountService accountService) {
        this.machineService = machineService;
        this.tokenUtil = tokenUtil;
        this.accountService = accountService;
    }

    /**
     * 获取机器设备列表
     *
     * @param request 请求
     * @return Response
     */
    @GetMapping
    public Response getMachineList(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (!VerifyUtil.checkString(token)) {
            return ResultUtil.returnStatus(ResponseStatus.NOT_LOGIN);
        } else {
            try {
                // 解析token
                Claim claim = tokenUtil.getClaim(token, "account_id");
                Account account = accountService.findAccountById(claim.asString());
                // 判断角色是否有权限
                if (account.getRole() == Role.ADMIN) {
                    List<Machine> machines = machineService.findAllMachine();
                    log.info("machines: {}", machines);
                    return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS, machines);
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
     * 创建机器设备信息
     *
     * @param request 请求
     * @param name    机器名称
     * @param desc    机器描述
     * @param type    机器类型
     * @return Response
     */
    @PostMapping
    public Response createMachine(HttpServletRequest request,
                                  @RequestParam("name") String name,
                                  @RequestParam("desc") String desc,
                                  @RequestParam("type") String type) {

        String token = request.getHeader("token");
        if (!VerifyUtil.checkString(token, name, desc, type)) {
            return ResultUtil.returnStatus(ResponseStatus.NOT_LOGIN);
        } else {
            try {
                // 解析token
                Claim claim = tokenUtil.getClaim(token, "account_id");
                Account account = accountService.findAccountById(claim.asString());
                // 判断角色是否有权限
                if (account != null && account.getRole() == Role.ADMIN) {
                    String id = UUIDUtil.getUUID();
                    Machine machine = Machine.builder()
                            .machineId(id)
                            .name(name)
                            .type(type)
                            .machineDesc(desc)
                            .build();
                    log.info("saved machine: {}", machine);
                    return ResultUtil.returnStatusAndData(machineService.saveMachine(machine),
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
     * 通过 ID 获取设备信息
     *
     * @param request 请求
     * @param id      设备 ID
     * @return Response
     */
    @GetMapping("/{id}")
    public Response getMachine(HttpServletRequest request,
                               @PathVariable String id) {

        String token = request.getHeader("token");
        if (!VerifyUtil.checkString(token, id)) {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        } else {
            try {
                // 解析token
                Claim claim = tokenUtil.getClaim(token, "account_id");
                Account account = accountService.findAccountById(claim.asString());
                // 判断角色是否有权限
                if (account != null && account.getRole() == Role.ADMIN) {
                    Machine machine = machineService.findMachineById(id);
                    if (machine != null) {
                        return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS, machine);
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
     * 通过 ID 修改设备信息
     *
     * @param request 请求
     * @param id      设备 ID
     * @param name    名称
     * @param type    类型
     * @param desc    描述
     * @return Response
     */
    @PutMapping("/{id}")
    public Response updateMachine(HttpServletRequest request,
                                  @PathVariable String id,
                                  @RequestParam("name") String name,
                                  @RequestParam("type") String type,
                                  @RequestParam("desc") String desc) {

        String token = request.getHeader("token");
        if (!VerifyUtil.checkString(token, id, name, type, desc)) {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        } else {
            try {
                // 解析token
                Claim claim = tokenUtil.getClaim(token, "account_id");
                Account account = accountService.findAccountById(claim.asString());
                // 判断角色是否有权限
                if (account != null && account.getRole() == Role.ADMIN) {
                    Integer row = machineService.updateMachine(Machine.builder()
                            .machineId(id)
                            .name(name)
                            .type(type)
                            .machineDesc(desc)
                            .build());
                    log.info("update machine: {}", row);
                    return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS, MapUtil.create("row", row + ""));
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
    public Response deleteMachine(HttpServletRequest request,
                                  @PathVariable String id) {

        String token = request.getHeader("token");
        if (!VerifyUtil.checkString(token, id)) {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        } else {
            try {
                // 解析token
                Claim claim = tokenUtil.getClaim(token, "account_id");
                Account account = accountService.findAccountById(claim.asString());
                // 判断角色是否有权限
                if (account != null && account.getRole() == Role.ADMIN) {
                    Integer row = machineService.deleteMachineById(id);
                    log.info("delete machine: {}", row);
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
