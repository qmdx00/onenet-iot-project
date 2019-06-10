package com.qmdx00.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author yuanweimin
 * @date 19/06/10 16:15
 * @description 设备操作记录实体
 */
@Entity
@Table(name = "t_record")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Record {
    @Id
    private String record_id;
    private String machine_id;
    private String worker_id;
    private Date start_time;
    private Date end_time;
}
