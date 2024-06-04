package com.example.Assesment.DTO;

import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class ExpenseClaimDTO {
    private Integer expenseClaimId;
    private LocalDate dates;
    private String description;
    private Double total;
    private String statuss;
    private Integer employeeId;
//    private Integer expenseTypeId;
    private List<ExpenseClaimEntryDTO> entries=new ArrayList<>();

}
