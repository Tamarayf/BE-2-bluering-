package com.example.Assesment.Controller;


import com.example.Assesment.Apiresponse;
import com.example.Assesment.DTO.LeaveRequestDTO;
import com.example.Assesment.DTO.LeaveTypeDTO;
import com.example.Assesment.DTO.LeaveeDTO;
import com.example.Assesment.DTO.PaginationRequest;
import com.example.Assesment.Entity.LeaveTypeEntity;
import com.example.Assesment.Entity.LeaveeEntity;
import com.example.Assesment.Repository.LeaveeRepository;
import com.example.Assesment.Services.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LeaveController {


    private final LeaveService service2;

    @Autowired
    public LeaveController(LeaveService service2) {
        this.service2 = service2;
    }

    @Autowired
    private LeaveeRepository leaveRepository;




    @PostMapping("/leavesType/")
    public Apiresponse DefineLeaveType(@RequestBody LeaveTypeDTO leaveTypeDTO) {
        LeaveTypeEntity definedLeaveType = service2.DefineLeaveType(leaveTypeDTO);
        return new Apiresponse(true, "LeaveType created successfully", definedLeaveType.getIdLeaveType());
    }

    @PostMapping("/Leave/")
    public Apiresponse submitLeaveRequest(@RequestBody LeaveeDTO leaveDTO) {
        try {
            LeaveeEntity savedLeave = service2.submitLeaveRequest(leaveDTO);
            return new Apiresponse(true, "Leave request submitted successfully", savedLeave);
        } catch (Exception e) {
            return new Apiresponse(false, "Failed to submit leave request: " + e.getMessage(), null);
        }

    }
    @GetMapping("/leaves/employee-date-range")
    public Apiresponse getLeavesByEmployeeAndDateRange(@RequestBody LeaveRequestDTO requestDTO) {
        try {
            List<LeaveeDTO> leaves = service2.getLeavesByEmployeeAndDateRange(
                    requestDTO.getEmployeeId(),
                    requestDTO.getFromDate(),
                    requestDTO.getToDate()
            );

            if (leaves.isEmpty()) {
                System.out.println("No leave requests found for employee " + requestDTO.getEmployeeId() + " between " + requestDTO.getFromDate() + " and " + requestDTO.getToDate());
            }

            return new Apiresponse(true, "Leave requests retrieved successfully", leaves);
        } catch (Exception e) {
            System.out.println("Failed to retrieve leave requests: " + e.getMessage());
            return new Apiresponse(false, "Failed to retrieve leave requests: " + e.getMessage(), null);
        }
    }



    @GetMapping("/leaves/paginated")
    public Apiresponse getLeavesByTypeAndEmployee(@RequestBody PaginationRequest request) {
        try {
            List<LeaveeDTO> leaves = service2.getLeavesByTypeAndEmployee(request.getLeaveTypeId(), request.getEmployeeId(), request.getPage(), request.getSize());
            return new Apiresponse(true, "Leave requests retrieved successfully", leaves);
        } catch (Exception e) {
            return new Apiresponse(false, "Failed to retrieve leave requests: " + e.getMessage(), null);
        }
    }


//    @GetMapping("/leaves/employee-date-range")
//    public Apiresponse getLeavesByEmployeeAndDateRange(@RequestBody LeaveRequestDTO requestDTO) {
//        try {
//            List<LeaveeDTO> leaves = service2.getLeavesByEmployeeAndDateRange(
//                    requestDTO.getEmployeeId(),
//                    requestDTO.getFromDate(),
//                    requestDTO.getToDate()
//            );
//            return new Apiresponse(true, "Leave requests retrieved successfully", leaves);
//        } catch (Exception e) {
//            return new Apiresponse(false, "Failed to retrieve leave requests: " + e.getMessage(), null);
//        }
//    }














//        @GetMapping("/leaves/{employeeId}/{leaveType}/{page}/{size}")
//        public Apiresponse getLeavesByTypeAndEmployee (
//                @PathVariable Integer employeeId,
//                @PathVariable String leaveType,
//        @PathVariable int page,
//        @PathVariable int size){
//            try {
//                Pageable pageable = PageRequest.of(page, size);
//                Page<LeaveeDTO> leaves = service2.getLeavesByTypeAndEmployee(employeeId, leaveType, pageable);
//                return new Apiresponse(true, "Leaves retrieved successfully", leaves);
//            } catch (Exception e) {
//                return new Apiresponse(false, "Failed to retrieve leave requests: " + e.getMessage(), null);
//            }
//        }


    }






