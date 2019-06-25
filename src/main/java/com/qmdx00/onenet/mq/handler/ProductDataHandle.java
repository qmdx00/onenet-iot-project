package com.qmdx00.onenet.mq.handler;

import com.qmdx00.entity.productData.FirstData;
import com.qmdx00.entity.productData.FourthData;
import com.qmdx00.entity.productData.SecondData;
import com.qmdx00.entity.productData.ThirdData;
import com.qmdx00.repository.productData.FirstDataRepository;
import com.qmdx00.repository.productData.FourthDataRepository;
import com.qmdx00.repository.productData.SecondDataRepository;
import com.qmdx00.repository.productData.ThirdDataRepository;
import com.qmdx00.util.MessageUtil;
import com.qmdx00.util.TimeUtil;
import com.qmdx00.util.UUIDUtil;
import com.qmdx00.util.model.Message;
import com.qmdx00.util.model.ProductData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author yuanweimin
 * @date 19/06/19 11:44
 * @description 生产过程数据处理类
 */
@Slf4j
@Component
public class ProductDataHandle implements MessageHandler {

    private final FirstDataRepository firstDataRepository;
    private final SecondDataRepository secondDataRepository;
    private final ThirdDataRepository thirdDataRepository;
    private final FourthDataRepository fourthDataRepository;
    private ExecutorService service = Executors.newCachedThreadPool();

    @Autowired
    public ProductDataHandle(FirstDataRepository firstDataRepository, SecondDataRepository secondDataRepository, ThirdDataRepository thirdDataRepository, FourthDataRepository fourthDataRepository) {
        this.firstDataRepository = firstDataRepository;
        this.secondDataRepository = secondDataRepository;
        this.thirdDataRepository = thirdDataRepository;
        this.fourthDataRepository = fourthDataRepository;
    }

    @Override
    public void handle(long msgId, String msgBody) {
        // 解析接收的数据
        Message msg = MessageUtil.analysis(msgId, msgBody);
        ProductData data = translate(msg.getTimestamp(), msg.getBody());
        log.info("receive data: {}", data);
        // 判断数据所属第几道工序并将工序数据存储到数据库中
        service.execute(() -> {
            if (data.getProductId().startsWith("1")) {
                firstDataRepository.save(FirstData.builder()
                        .dataId(UUIDUtil.getUUID())
                        .workerId(data.getWorkId())
                        .productId(data.getProductId().split("=")[0])
                        .copper(data.getCopper())
                        .tin(data.getTin())
                        .zinc(data.getZinc())
                        .createTime(data.getCreateTime())
                        .build());
            } else if (data.getProductId().startsWith("2")) {
                secondDataRepository.save(SecondData.builder()
                        .dataId(UUIDUtil.getUUID())
                        .workerId(data.getWorkId())
                        .productId(data.getProductId().split("=")[0])
                        .previous(data.getProductId().split("=")[1])
                        .diameter(data.getDiameter())
                        .weight(data.getWeight())
                        .length(data.getLength())
                        .createTime(data.getCreateTime())
                        .build());
            } else if (data.getProductId().startsWith("3")) {
                thirdDataRepository.save(ThirdData.builder()
                        .dataId(UUIDUtil.getUUID())
                        .workerId(data.getWorkId())
                        .productId(data.getProductId().split("=")[0])
                        .previous(data.getProductId().split("=")[1])
                        .diameter(data.getDiameter())
                        .weight(data.getWeight())
                        .length(data.getLength())
                        .createTime(data.getCreateTime())
                        .build());
            } else if (data.getProductId().startsWith("4")) {
                fourthDataRepository.save(FourthData.builder()
                        .dataId(UUIDUtil.getUUID())
                        .workerId(data.getWorkId())
                        .productId(data.getProductId().split("=")[0])
                        .previous(data.getProductId().split("=")[1])
                        .diameter(data.getDiameter())
                        .weight(data.getWeight())
                        .length(data.getLength())
                        .tensile(data.getTensile())
                        .createTime(data.getCreateTime())
                        .build());
            }
        });
    }

    /**
     * 将数据解析成 ProductData 对象
     *
     * @param timestamp 时间戳
     * @param body      数据体
     * @return FirstData
     */
    private ProductData translate(String timestamp, String body) {
        // 解析 body 字符串到 Map 中
        Map<String, String> map = Arrays.stream(body.split("-"))
                .map(et -> et.split("@"))
                .filter(et -> et.length == 2 && !et[1].equals("#"))
                .collect(Collectors.toMap(et -> et[0], et -> et[1]));
        // 将 Map 映射到 ProductData 对象中
        return ProductData.builder()
                .workId(map.get("WID"))
                .productId(map.get("PID"))
                .length(map.get("LEN"))
                .weight(map.get("WET"))
                .diameter(map.get("DIA"))
                .copper(map.get("COC"))
                .tin(map.get("TOC"))
                .zinc(map.get("ZOC"))
                .tensile(map.get("PST"))
                .createTime(TimeUtil.toDate(timestamp))
                .build();
    }
}
