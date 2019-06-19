package com.qmdx00.repository;

import com.qmdx00.entity.MachineStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MachineStatusRepository extends JpaRepository<MachineStatus, String> {

    /**
     * 查询过去时间到现在的状态数据
     *
     * @param last 过去时间点
     * @return List
     */
    @Query(value = "select * from t_machine_status as m where to_days(m.create_time) between to_days(?1) and to_days(now())", nativeQuery = true)
    List<MachineStatus> findLastToNow(Date last);

    /**
     * 查询时间间隔中的所有状态数据
     *
     * @param start 开始时间戳
     * @param end   结束时间戳
     * @return List
     */
    @Query(value = "select * from t_machine_status as m where to_days(m.create_time) between to_days(?1) and to_days(?2)", nativeQuery = true)
    List<MachineStatus> findStartToEnd(Date start, Date end);
}
