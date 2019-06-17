package com.qmdx00.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.qmdx00.entity.Account;
import com.qmdx00.entity.Producible;
import com.qmdx00.service.AccountService;
import com.qmdx00.service.ProducibleService;
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
 * @date 19/06/17 08:40
 * @description 产品 Controller
 */
@Slf4j
@RestController
@RequestMapping("/api/producible")
public class ProducibleController extends BaseController {

    private final TokenUtil tokenUtil;
    private final AccountService accountService;
    private final ProducibleService producibleService;

    @Autowired
    public ProducibleController(ProducibleService producibleService, TokenUtil tokenUtil, AccountService accountService) {
        this.producibleService = producibleService;
        this.tokenUtil = tokenUtil;
        this.accountService = accountService;
    }

    /**
     * 获取所有可生产的产品列表
     *
     * @return Response
     */
    @GetMapping
    public Response getProducibleList() {
        List<Producible> list = producibleService.findAllProducible();
        if (list != null) {
            log.info("get producible list: {}", list);
            return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS, list);
        } else {
            return ResultUtil.returnStatus(ResponseStatus.NOT_FOUND);
        }
    }

    /**
     * 获取指定可生产产品信息
     *
     * @param id 产品 ID
     * @return Response
     */
    @GetMapping("/{id}")
    public Response getProducibleById(@PathVariable String id) {
        if (!VerifyUtil.checkString(id)) {
            Producible producible = producibleService.findProducibleById(id);
            if (producible != null) {
                log.info("get producible: {}", producible);
                return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS, producible);
            } else {
                return ResultUtil.returnStatus(ResponseStatus.NOT_FOUND);
            }
        } else {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        }
    }

    /**
     * 创建一条可生产产品信息
     *
     * @param request 请求
     * @param name    名称
     * @param desc    描述
     * @param type    类型
     * @param image   图片地址
     * @return Response
     */
    @PostMapping
    public Response createProducible(HttpServletRequest request,
                                     @RequestParam("name") String name,
                                     @RequestParam("desc") String desc,
                                     @RequestParam("type") String type,
                                     @RequestParam("image") String image) {

        String token = request.getHeader("token");
        if (!VerifyUtil.checkString(token, name, desc, type, image)) {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        } else {
            try {
                // 解析token
                Claim claim = tokenUtil.getClaim(token, "account_id");
                Account account = accountService.findAccountById(claim.asString());
                // 判断角色是否有权限
                if (account != null && account.getRole() == Role.ADMIN) {
                    String producibleId = UUIDUtil.getUUID();
                    Producible producible = Producible.builder()
                            .producibleId(producibleId)
                            .name(name)
                            .producibleDesc(desc)
                            .type(type)
                            .image(image)
                            .build();
                    log.info("create producible: {}", producible);
                    return ResultUtil.returnStatusAndData(producibleService.saveProducible(producible),
                            MapUtil.create("id", producibleId + ""));
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
     * 通过 ID 删除可生产产品信息
     *
     * @param id 产品 ID
     * @return Response
     */
    @DeleteMapping("/{id}")
    public Response deleteProducible(HttpServletRequest request,
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
                    Integer row = producibleService.deleteProducibleById(id);
                    log.info("delete producible: {}", row);
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
