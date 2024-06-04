package com.example.Assesment.DTO;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import lombok.Data;


import java.time.LocalDate;

@Data
public class LeaveeDTO {
    private Integer leaveId;
    private Integer leaveTypeId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Integer numberOfDays;
    private String note;
    private Integer employeeId;
}
