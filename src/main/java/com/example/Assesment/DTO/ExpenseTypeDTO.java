package com.example.Assesment.DTO;


import lombok.Data;

import java.util.List;

@Data
public class ExpenseTypeDTO {
    private Integer expenseTypeId;
    private String expenseTypeName;
    List<ExpenseClaimEntryDTO> expenseClaimEntries;
}
