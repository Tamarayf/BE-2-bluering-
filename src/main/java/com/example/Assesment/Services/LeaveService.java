package com.example.Assesment.Services;


import com.example.Assesment.DTO.LeaveRequestDTO;
import com.example.Assesment.DTO.LeaveTypeDTO;
import com.example.Assesment.DTO.LeaveeDTO;
import com.example.Assesment.Entity.LeaveTypeEntity;
import com.example.Assesment.Entity.LeaveeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface LeaveService {

    LeaveTypeEntity DefineLeaveType(LeaveTypeDTO leaveTypeDTO);
    LeaveeEntity submitLeaveRequest(LeaveeDTO leaveeDTO);
    List<LeaveeDTO> getLeavesByEmployeeAndDateRange(Integer employeeId, LocalDate fromDate, LocalDate toDate);
    List<LeaveeDTO> getLeavesByTypeAndEmployee(Integer typeId, Integer employeeId, int page, int size);
//   Page<LeaveeDTO> getLeavesByTypeAndEmployee(Integer employeeId, String leaveType, Pageable pageable);


}
