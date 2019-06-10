package com.qmdx00.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author yuanweimin
 * @date 19/06/10 11:11
 * @description 工人实体类
 */
@Entity
@Table(name = "t_worker")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Worker {
    @Id
    private String worker_id;
}
