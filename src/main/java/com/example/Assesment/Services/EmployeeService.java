package com.example.Assesment.Services;

import com.example.Assesment.DTO.DepartmentDTO;
import com.example.Assesment.DTO.EmployeeDTO;
import com.example.Assesment.Entity.DepartmentEntity;
import com.example.Assesment.Entity.EmployeeEntity;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    DepartmentEntity DefineDepartment(DepartmentDTO departmentDTO);
    EmployeeEntity createEmployee(EmployeeDTO employeeDTO);
    void updateEmployee(Integer empId, Map<String, Object> entityDto);
    void updateDepartment(Integer depId, Map<String, Object> entityDto);
    Integer deleteEmployee(Integer empId);
    Integer deleteDepartment(Integer depId);
    EmployeeDTO getOneEmployee(Integer empId);
    List<EmployeeDTO> getEmployees();
    List<DepartmentDTO> getDepartments();
    DepartmentDTO getOneDepartment(Integer depId) ;
    List<EmployeeDTO> getEmployeesByDepartment(Integer departmentId);

}
