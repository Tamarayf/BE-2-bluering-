package com.example.Assesment.Services;



import com.example.Assesment.DTO.*;
import com.example.Assesment.Entity.DepartmentEntity;
import com.example.Assesment.Entity.EmployeeEntity;
import com.example.Assesment.Entity.LeaveTypeEntity;
import com.example.Assesment.Entity.LeaveeEntity;
import com.example.Assesment.Mapper.LeaveTypeMapper;
import com.example.Assesment.Mapper.LeaveeMapper;
import com.example.Assesment.Repository.EmployeeRepository;
import com.example.Assesment.Repository.LeaveTypeRepository;
import com.example.Assesment.Repository.LeaveeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveServiceImp implements LeaveService {

    @Autowired
    private LeaveeMapper leaveMapper;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private LeaveeRepository leaveRepository;

    @Autowired
    private LeaveTypeMapper leaveTypeMapper;

    @Autowired
    private LeaveTypeRepository leaveTypeRepository;


    public LeaveTypeEntity DefineLeaveType(LeaveTypeDTO leaveTypeDTO) {
        LeaveTypeEntity leaveTypeEntity = leaveTypeMapper.leaveTypeDTOToLeaveTypeEntity(leaveTypeDTO);
        return leaveTypeRepository.save(leaveTypeEntity);
    }

    public LeaveeEntity submitLeaveRequest(LeaveeDTO leaveeDTO) {
        if (leaveeDTO.getFromDate() == null || leaveeDTO.getToDate() == null) {
            throw new IllegalArgumentException("Both fromDate and toDate must be provided");
        }

        // Calculate number of days between fromDate and toDate
        long numberOfDays = leaveeDTO.getFromDate().until(leaveeDTO.getToDate(), ChronoUnit.DAYS);
        leaveeDTO.setNumberOfDays((int) numberOfDays);

        LeaveeEntity leav1 = leaveMapper.leaveeDTOToLeaveeEntity(leaveeDTO);
        return leaveRepository.save(leav1);
    }


    public List<LeaveeDTO> getLeaves() {
        List<LeaveeEntity> leaveEntities = leaveRepository.findAll();
        return leaveEntities.stream()
                .map(leaveEntity -> {
                    LeaveeDTO leaveDTO = leaveMapper.leaveeEntityToLeaveeDTO(leaveEntity);
                    EmployeeEntity employeeEntity = employeeRepository.findById(leaveEntity.getEmployeeId()).orElse(null);
                    if (employeeEntity != null) {
                       leaveDTO.setEmployeeId(employeeEntity.getEmpId());
                    }
                    return leaveDTO;
                })
                .collect(Collectors.toList());
    }
    public List<LeaveeDTO> getLeavesByEmployeeAndDateRange(Integer employeeId, LocalDate from, LocalDate to) {
        List<LeaveeEntity> leaves = leaveRepository.findByEmployeeIdAndFromDateBetween(employeeId, from, to);
        return leaves.stream()
                .map(leaveMapper::leaveeEntityToLeaveeDTO)
                .peek(dto -> dto.setEmployeeId(employeeId))
                .collect(Collectors.toList());
    }


    public List<LeaveTypeDTO> getLeaveType() {
        List<LeaveTypeEntity> leaveTypeEntities = leaveTypeRepository.findAll();
        return leaveTypeEntities.stream()
                .map(leavesTypeEntity -> leaveTypeMapper.leaveTypeEntityToLeaveTypeDTO(leavesTypeEntity))
                .collect(Collectors.toList());
    }



    //    public List<LeaveeDTO> getLeavesByTypeAndEmployee(Integer typeId, Integer employeeId, int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<LeaveeEntity> leavePage = leaveRepository.findByLeaveTypeIdAndEmployeeId(typeId, employeeId, pageable);
//
//        return leavePage.getContent().stream()
//                .map(leaveMapper::leaveeEntityToLeaveeDTO)
//                .collect(Collectors.toList());
//    }
//public PaginationRequest getLeavesByTypeAndEmployee(Integer typeId, Integer employeeId, int page, int size) {
//    Pageable pageable = PageRequest.of(page, size);
//    Page<LeaveeEntity> leavePage = leaveRepository.findByLeaveTypeIdAndEmployeeId(typeId, employeeId, pageable);
//
//    PaginationRequest response = new PaginationRequest();
//    response.setItems(leavePage.getContent().stream()
//            .map(leaveMapper::leaveeEntityToLeaveeDTO)
//            .collect(Collectors.toList()));
//    response.setCurrentPage(page);
//    response.setTotalItems(leavePage.getTotalElements());
//    response.setTotalPages(leavePage.getTotalPages());
//
//    return response;
//}
//
//
//
//}



    public PaginationRequest getLeavesByTypeAndEmployee(Integer leaveTypeId, Integer employeeId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<LeaveeEntity> leavePage;

        if (leaveTypeId == null && employeeId == null) {
            // Fetch all leaves if no filters are applied
            leavePage = leaveRepository.findAll(pageable);
        } else if (leaveTypeId != null && employeeId == null) {
            // Fetch leaves by leave type
            leavePage = leaveRepository.findByLeaveTypeId(leaveTypeId, pageable);
        } else if (leaveTypeId == null && employeeId != null) {
            // Fetch leaves by employee
            leavePage = leaveRepository.findByEmployeeId(employeeId, pageable);
        } else {
            // Fetch leaves by both leave type and employee
            leavePage = leaveRepository.findByLeaveTypeIdAndEmployeeId(leaveTypeId, employeeId, pageable);
        }

        PaginationRequest response = new PaginationRequest();
        List<LeaveeDTO> leaveDTOs = leavePage.getContent().stream()
                .map(leaveMapper::leaveeEntityToLeaveeDTO)
                .collect(Collectors.toList());
        response.setItems(leaveDTOs);
        response.setTotalItems(leavePage.getTotalElements());

        return response;
    }

}
//    public Page<LeaveeDTO> getLeavesByTypeAndEmployee(Integer employeeId, String leaveType, Pageable pageable) {
//        Page<LeaveeEntity> leaveEntities = leaveRepository.findByEmployeeIdAndLeaveType(employeeId, leaveType, pageable);
//        return leaveEntities.map(leaveMapper::leaveeEntityToLeaveeDTO);
//    }








