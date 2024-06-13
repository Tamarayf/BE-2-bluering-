package com.example.Assesment.Repository;

import com.example.Assesment.DTO.LeaveeDTO;
import com.example.Assesment.Entity.LeaveTypeEntity;
import com.example.Assesment.Entity.LeaveeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public interface LeaveeRepository extends JpaRepository<LeaveeEntity,Integer> {


    List<LeaveeEntity> findByEmployeeIdAndFromDateBetween(Integer employeeId, LocalDate fromDate, LocalDate toDate);


//    Page<LeaveeEntity> findByLeaveTypeIdAndEmployeeId(Integer leaveTypeId, Integer employeeId, Pageable pageable);


    Page<LeaveeEntity> findAll(Pageable pageable);

    Page<LeaveeEntity> findByLeaveTypeId(Integer leaveTypeId, Pageable pageable);

    Page<LeaveeEntity> findByEmployeeId(Integer employeeId, Pageable pageable);

    Page<LeaveeEntity> findByLeaveTypeIdAndEmployeeId(Integer leaveTypeId, Integer employeeId, Pageable pageable);

}