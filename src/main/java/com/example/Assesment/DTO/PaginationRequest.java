package com.example.Assesment.DTO;

import com.example.Assesment.Entity.LeaveTypeEntity;
import lombok.Data;

import java.util.List;

@Data
public class PaginationRequest {


        private List<LeaveeDTO> items;
//        private int currentPage;
        private long totalItems;
//        private int totalPages;



}
