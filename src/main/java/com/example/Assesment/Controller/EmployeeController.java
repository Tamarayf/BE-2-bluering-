package com.example.Assesment.Controller;

import com.example.Assesment.Apiresponse;
import com.example.Assesment.DTO.DepartmentDTO;
import com.example.Assesment.DTO.EmployeeDTO;
import com.example.Assesment.Entity.DepartmentEntity;
import com.example.Assesment.Entity.EmployeeEntity;
import com.example.Assesment.Services.EmployeeService;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping("/api")
public class EmployeeController {


    private final EmployeeService service1;

    @Autowired
    public EmployeeController(EmployeeService service1Imp) {
        this.service1 = service1Imp;
    }

    @PostMapping("/departmentss/")
    public Apiresponse defineDepartment(@RequestBody DepartmentDTO departmentDTO) {
        DepartmentEntity createdDepartment = service1.DefineDepartment(departmentDTO);
        return new Apiresponse(true, "Department created successfully", createdDepartment.getDepId());
    }


    @PostMapping("/employees/")
    public Apiresponse addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        try {
            EmployeeEntity savedEmployee = service1.createEmployee(employeeDTO);
            return new Apiresponse(true, "Employee created successfully", savedEmployee.getEmpId());
        } catch (DataIntegrityViolationException ex) {
            return new Apiresponse(false, "Department not found", null);
        }
    }

    @PatchMapping("/employees/{empId}")
    public Apiresponse updateEmployee(@PathVariable Integer empId, @RequestBody Map<String, Object> updateFields) {
        try {
            service1.updateEmployee(empId, updateFields);
            return new Apiresponse(true, "Employee updated successfully", empId);
        } catch (ResourceNotFoundException e) {
            return new Apiresponse(false, "Employee not found with id: " + empId, null);
        }
    }

    @PatchMapping("/departments/{depId}")
    public Apiresponse updateDepartment(@PathVariable Integer depId, @RequestBody Map<String, Object> updateFields) {
        try {
            service1.updateDepartment(depId, updateFields);
            return new Apiresponse(true, "Department updated successfully", depId);
        } catch (ResourceNotFoundException e) {
            return new Apiresponse(false, "Department not found with id: " + depId, null);
        }
    }
    @DeleteMapping("/employees/{empId}")
    public Apiresponse deleteEmployee(@PathVariable Integer empId) {
        Integer deletedEmployeeId =service1.deleteEmployee(empId);
        if (deletedEmployeeId != null) {
            return new Apiresponse(true, "Employee deleted successfully", deletedEmployeeId);
        } else {
            return new Apiresponse(false, "Employee not found with id: " + empId);
        }
    }

    @DeleteMapping("/departments/{depId}")
    public Apiresponse deleteDepartment(@PathVariable Integer depId) {
        Integer deleteddepartmentId = service1.deleteDepartment(depId);
        if (deleteddepartmentId != null) {
            return new Apiresponse(true, "Department deleted successfully", deleteddepartmentId);
        } else {
            return new Apiresponse(false, "Department not found with id: " + depId);
        }
    }

    @GetMapping("/employees")
    public Apiresponse getEmployees() {
        List<EmployeeDTO> employeeDTOs = service1.getEmployees();
        return new Apiresponse(true, "Employees retrieved successfully", employeeDTOs);
    }

    @GetMapping("/employees/{empId}")
    public Apiresponse getOneEmployee(@PathVariable Integer empId) {
        EmployeeDTO employeeDTO = service1.getOneEmployee(empId);
        if (employeeDTO != null) {
            return new Apiresponse(true, "Employee retrieved successfully", employeeDTO);
        } else {
            return new Apiresponse(false, "Employee not found", null);
        }
    }

    @GetMapping("/departments")
    public Apiresponse getDepartments() {
        List<DepartmentDTO> departmentsDTOs = service1.getDepartments();
        return new Apiresponse(true, "Departments retrieved successfully", departmentsDTOs);
    }


    @GetMapping("/departments/{depId}")
    public Apiresponse getOneDepartment(@PathVariable Integer depId) {
        DepartmentDTO departmentDTO = service1.getOneDepartment(depId);
        if (departmentDTO != null) {
            return new Apiresponse(true, "Department retrieved successfully", departmentDTO);
        } else {
            return new Apiresponse(false, "Department not found", null);
        }
    }


    @GetMapping("/employeesALL/{departmentId}")
    public Apiresponse getEmployeesByDepartment(@PathVariable  Integer departmentId) {
        List<EmployeeDTO> employeeDTOs = service1.getEmployeesByDepartment(departmentId);
        if (!employeeDTOs.isEmpty()) {
            return new Apiresponse(true, "Employees for department retrieved successfully", employeeDTOs);
        } else {
            return new Apiresponse(false, "No employees found for department", null);
        }
    }
}
