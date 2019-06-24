package com.qmdx00.controller;

import com.alibaba.fastjson.JSONObject;
import com.qmdx00.util.OkHttpUtil;
import com.qmdx00.util.ResultUtil;
import com.qmdx00.util.VerifyUtil;
import com.qmdx00.util.enums.Cmd;
import com.qmdx00.util.enums.ResponseStatus;
import com.qmdx00.util.model.Command;
import com.qmdx00.util.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author yuanweimin
 * @date 19/06/21 09:52
 * @description 设备控制，命令下发 Controller
 */
@SuppressWarnings("SpellCheckingInspection")
@Slf4j
@RestController
@RequestMapping("/api/cmd")
public class CommandController {

    private final OkHttpUtil okHttpUtil;

    @Autowired
    public CommandController(OkHttpUtil okHttpUtil) {
        this.okHttpUtil = okHttpUtil;
    }

    /**
     * 控制命令下发
     *
     * @param cmd 命令字符串
     * @return Response
     */
    @GetMapping("/{cmd}")
    public Response test(@PathVariable String cmd) {
        if (!VerifyUtil.checkString(cmd)) {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        } else {
            // 构建 Url 参数
            HashMap<String, String> urlParams = new HashMap<>();
            urlParams.put("imei", "869662034519832");
            urlParams.put("obj_id", "3341");
            urlParams.put("obj_inst_id", "0");
            urlParams.put("mode", "2");
            String url = parseUrl(urlParams);
            // 构建 request body 中的 json 字符串
            HashMap<String, List> map = new HashMap<>();
            List<Command> commands = new ArrayList<>();
            commands.add(new Command(5527, getCmd(cmd).getCode()));
            map.put("data", commands);
            String params = JSONObject.toJSONString(map);
            // 发送控制指令请求到平台，返回响应的 json 字符串
            String response = okHttpUtil.postJson(url, params);
            // 解析响应的字符串，判断是否下发成功
            JSONObject jsonObject = JSONObject.parseObject(response);
            String code = jsonObject.getString("errno");
            if (code != null && code.equals("0")) {
                return ResultUtil.returnStatus(ResponseStatus.SUCCESS);
            } else {
                return ResultUtil.returnStatus(ResponseStatus.FAILED);
            }
        }
    }

    /**
     * 将 Map 映射到 Url 参数中
     *
     * @param params 参数 Map
     * @return String
     */
    private String parseUrl(HashMap<String, String> params) {
        StringBuilder sb = new StringBuilder("http://api.heclouds.com/nbiot?");
        for (String key : params.keySet()) {
            sb.append(key).append("=").append(params.get(key)).append("&");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    /**
     * 将 请求的命令映射到枚举中
     *
     * @param cmd 命令字符串
     * @return Cmd
     */
    private Cmd getCmd(String cmd) {
        // 电机相关
        if (cmd.equalsIgnoreCase("motor-stop")) return Cmd.MOTOR_STOP;
        if (cmd.equalsIgnoreCase("motor-forward")) return Cmd.MOTOR_FORWARD;
        if (cmd.equalsIgnoreCase("motor-reverse")) return Cmd.MOTOR_REVERSE;
        if (cmd.equalsIgnoreCase("motor-slow")) return Cmd.MOTOR_SLOW;
        if (cmd.equalsIgnoreCase("motor-middle")) return Cmd.MOTOR_MIDDLE;
        if (cmd.equalsIgnoreCase("motor-fast")) return Cmd.MOTOR_FAST;
        // 滑台相关
        if (cmd.equalsIgnoreCase("slide-close")) return Cmd.SLIDE_CLOSE;
        if (cmd.equalsIgnoreCase("slide-open")) return Cmd.SLIDE_OPEN;
        if (cmd.equalsIgnoreCase("slide-slow")) return Cmd.SLIDE_SLOW;
        if (cmd.equalsIgnoreCase("slide-middle")) return Cmd.SLIDE_MIDDLE;
        if (cmd.equalsIgnoreCase("slide-fast")) return Cmd.SLIDE_FAST;
        // 推杆相关
        if (cmd.equalsIgnoreCase("rod-dis-0")) return Cmd.ROD_DIS_0;
        if (cmd.equalsIgnoreCase("rod-dis-5")) return Cmd.ROD_DIS_5;
        if (cmd.equalsIgnoreCase("rod-dis-15")) return Cmd.ROD_DIS_15;
        if (cmd.equalsIgnoreCase("rod-dis-20")) return Cmd.ROD_DIS_20;
        // 风扇相关
        if (cmd.equalsIgnoreCase("fan-close")) return Cmd.FAN_CLOSE;
        if (cmd.equalsIgnoreCase("fan-open")) return Cmd.FAN_OPEN;
        else return null;
    }
}
