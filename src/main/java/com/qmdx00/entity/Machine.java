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
 * @date 19/06/10 16:11
 * @description 机器设备实体
 */
@Entity
@Table(name = "t_machine")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Machine {
    @Id
    private String machine_id;
    private String name;
    private String describe;
    private String type;
    private Date buy_date;
}
