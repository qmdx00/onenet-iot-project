package com.qmdx00.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yuanweimin
 * @date 19/07/09 09:24
 * @description 任务状态记录实体（根据上传的数据生成）
 */
@Entity
@Table(name = "t_task_status")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskStatus implements Serializable {
    // 任务状态记录 ID
    @Id
    private String recordId;
    // 任务单编号
    private String taskId;
    // 所属哪一道工序
    private String belong;
    // 完成的数量
    private String doneNum;
}
