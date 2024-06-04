package com.example.Assesment.Repository;

import com.example.Assesment.DTO.LeaveeDTO;
import com.example.Assesment.Entity.LeaveeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public interface LeaveeRepository extends JpaRepository<LeaveeEntity,Integer> {


    List<LeaveeEntity> findByEmployeeIdAndFromDateBetween(Integer employeeId, LocalDate fromDate, LocalDate toDate);


    Page<LeaveeEntity> findByLeaveTypeIdAndEmployeeId(Integer leaveTypeId, Integer employeeId, Pageable pageable);


//    Page<LeaveeEntity> findByEmployeeIdAndLeaveTypeId(Integer employeeId,String leaveType, Pageable pageable);

}