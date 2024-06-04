package com.example.Assesment.DTO;

import lombok.Data;


import java.time.LocalDate;

@Data
public class ExpenseClaimEntryDTO {
    private Integer expenseClaimEntryId;
    private LocalDate dateEntry;
    private Integer expenseTypeId;
    private Integer expenseClaimId;
    private String description;
    private Double total;

}
