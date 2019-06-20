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
    @Query(value = "select * from t_machine_status as m where unix_timestamp(m.create_time) between unix_timestamp(?1) and unix_timestamp(now()) order by m.create_time", nativeQuery = true)
    List<MachineStatus> findLastToNow(Date last);

    /**
     * 查询时间间隔中的所有状态数据
     *
     * @param start 开始时间戳
     * @param end   结束时间戳
     * @return List
     */
    @Query(value = "select * from t_machine_status as m where unix_timestamp(m.create_time) between unix_timestamp(?1) and unix_timestamp(?2) order by m.create_time", nativeQuery = true)
    List<MachineStatus> findStartToEnd(Date start, Date end);
}
