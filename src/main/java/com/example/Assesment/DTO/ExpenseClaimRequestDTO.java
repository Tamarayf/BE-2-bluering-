package com.example.Assesment.DTO;


import lombok.Data;

@Data
public class ExpenseClaimRequestDTO {
    private Integer employeeId;
    private Integer expenseTypeId;
}
