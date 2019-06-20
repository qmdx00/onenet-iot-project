package com.qmdx00.onenet.mq.handler;

import com.qmdx00.entity.Machine;
import com.qmdx00.entity.MachineStatus;
import com.qmdx00.util.model.Message;
import com.qmdx00.service.MachineService;
import com.qmdx00.service.MachineStatusService;
import com.qmdx00.util.MessageUtil;
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
        // 封装成 Message 实体
        Message msg = MessageUtil.analysis(msgId, msgBody);
        // 映射成 MachineStatus 实体类对象
        MachineStatus status = translate(msg.getDeviceId(), msg.getTimestamp(), msg.getBody());
        log.info("generate status: {}", status);
        // 保存状态数据到数据库中
        service.execute(() -> log.info("save status: {}", machineStatusService.saveStatus(status)));
        // 转发到前端 websocket 中
        template.convertAndSend("/topic/msg", status);
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
        // 数据串解析为 Map
        Map<String, String> map = Arrays.stream(body.split("-"))
                .map(et -> et.split("@"))
                .filter(et -> et.length == 2)
                .collect(Collectors.toMap(et -> et[0], et -> et[1]));
        // 构建 MachineStatus 对象
        return MachineStatus.builder()
                .statusId(UUIDUtil.getUUID())
                .machineId(deviceId)
                .createTime(TimeUtil.toDate(timeStamp))
                .temperature(map.get("TM"))
                .temperatureWarn(map.get("TR"))
                .fan(map.get("FA"))
                .humidity(map.get("HU"))
                .humidityWarn(map.get("HR"))
                .voltage(map.get("VO"))
                .electric(map.get("CU"))
                .power(map.get("PO"))
                .weight(map.get("WE"))
                .weightWarn(map.get("WR"))
                .motorOpen(map.get("MW"))
                .motorSpeed(map.get("MP"))
                .motorDir(map.get("MI"))
                .slideOpen(map.get("TW"))
                .slideDir(map.get("TD"))
                .slideSpeed(map.get("TS"))
                .rodDistance(map.get("PD"))
                .machineError(map.get("ME"))
                .reservedA(map.get("RA"))
                .reservedB(map.get("RB"))
                .reservedC(map.get("RC"))
                .reservedD(map.get("RD"))
                .build();
    }
}
