package com.example.Assesment.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveRequestDTO {

        private Integer employeeId;
        private LocalDate fromDate;
        private LocalDate toDate;


}
