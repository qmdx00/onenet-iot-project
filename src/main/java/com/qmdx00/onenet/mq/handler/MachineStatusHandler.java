package com.qmdx00.onenet.mq.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qmdx00.entity.Machine;
import com.qmdx00.entity.MachineStatus;
import com.qmdx00.service.MachineService;
import com.qmdx00.service.MachineStatusService;
import com.qmdx00.util.TimeUtil;
import com.qmdx00.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author yuanweimin
 * @date 19/06/13 10:54
 * @description 机器设备实时数据的消息处理类
 */
@Slf4j
@Component
public class MachineStatusHandler implements MessageHandler {

    private ExecutorService service = Executors.newCachedThreadPool();
    private final MachineStatusService machineStatusService;
    private final MachineService machineService;
    private final SimpMessagingTemplate template;

    @Autowired
    public MachineStatusHandler(SimpMessagingTemplate template, MachineService machineService, MachineStatusService machineStatusService) {
        this.template = template;
        this.machineService = machineService;
        this.machineStatusService = machineStatusService;
    }

    @Override
    public synchronized void handle(long msgId, String msgBody) {
        // 映射成 MachineStatus 实体类对象
        JSONObject msg = JSON.parseObject(msgBody);
        JSONObject prop = msg.getJSONObject("appProperty");
        String timestamp = prop.getString("dataTimestamp");
        String deviceId = prop.getString("deviceId");
        String body = msg.getString("body");
        MachineStatus status = translate(deviceId, timestamp, body);
        log.info("generate status: {}", status);
        // 保存状态数据到数据库中
//        service.execute(() -> log.info("save status: {}", machineStatusService.saveStatus(status)));
        // 转发到前端 websocket 中
//        template.convertAndSend("/topic/msg", status);
    }

    /**
     * 将一条数据流转换成状态数据实体,并生成机器设备信息
     *
     * @param deviceId  设备 ID
     * @param timeStamp 数据上传的时间戳
     * @param body      类型的值
     * @return MachineStatus
     */
    private MachineStatus translate(String deviceId, String timeStamp, String body) {
        // 如果设备信息不存在则根据deviceID创建，需要修改其它描述信息
        Machine machine = machineService.findMachineById(deviceId);
        if (machine == null) {
            machineService.saveMachine(Machine.builder()
                    .machineId(deviceId)
                    .build());
        }

        Map map = Arrays.stream(body.split("-"))
                .map(et -> et.split("@"))
                .filter(et -> et.length == 2)
                .collect(Collectors.toMap(et -> et[0], et -> et[1]));
        System.out.println(map);

        return MachineStatus.builder()
                .statusId(UUIDUtil.getUUID())
                .machineId(deviceId)
                .createTime(TimeUtil.toDate(timeStamp))
                .build();
    }
}
