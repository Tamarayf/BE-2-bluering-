package com.example.Assesment.DTO;


import lombok.Data;

@Data
public class TotalClaimPerTypePerEmployeeDTO {
    private Integer employeeId;
    private Integer expenseTypeId;
    private Double totalAmount;

    public Integer getExpenseClaimTypeId() {
        return expenseTypeId;
    }
}
