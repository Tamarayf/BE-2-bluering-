package com.example.Assesment.DTO;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeDTO {
    private Integer empId;
    private String empName;
    private String empEmail;
    private String empAddress;
    private Integer departmentId;
    private String departmentName;
//    List<ExpenseTypeDTO> expenseTypeDTOList;
}
