package com.example.Assesment.DTO;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeDTO {
    private int empId;
    private String firstName;
    private String lastName;
    private String phonenumber;
    private String address;
    private int age;
    private Integer departmentId;
    private Object gender;
    private String departmentName;
//    List<ExpenseTypeDTO> expenseTypeDTOList;
}
