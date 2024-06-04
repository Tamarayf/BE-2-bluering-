package com.example.Assesment.DTO;

import lombok.Data;

@Data
public class PaginationRequest {

        private Integer employeeId;
        private Integer leaveTypeId;
        private Integer page;
        private Integer size;



}
