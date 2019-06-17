package com.qmdx00.repository;

import com.qmdx00.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author yuanweimin
 * @date 19/06/17 08:55
 * @description 工人信息持久化
 */
@Repository
public interface WorkerRepository extends JpaRepository<Worker, String> {
    /**
     * 通过 ID 删除工人信息
     *
     * @param id 工人 ID
     * @return Integer
     */
    Integer deleteWorkerByWorkerId(String id);

    /**
     * 修改工人信息
     *
     * @param worker 工人信息
     * @return Integer
     */
    /**
     * 修改工人信息
     *
     * @param id         工人 ID
     * @param name       姓名
     * @param phone      手机
     * @param department 所属部门
     * @param post       职位
     * @return Integer
     */
    @Modifying
    @Query(value = "update t_worker as w set w.name = ?2, w.phone = ?3, w.department = ?4, w.post = ?5 where w.worker_id = ?1", nativeQuery = true)
    Integer updateWorkerById(String id, String name, String phone, String department, String post);
}
