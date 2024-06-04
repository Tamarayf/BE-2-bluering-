package com.example.Assesment.Services;

import com.example.Assesment.DTO.DepartmentDTO;
import com.example.Assesment.DTO.EmployeeDTO;
import com.example.Assesment.Entity.DepartmentEntity;
import com.example.Assesment.Entity.EmployeeEntity;
import com.example.Assesment.Mapper.DepartmentMapper;
import com.example.Assesment.Mapper.EmployeeMapper;
import com.example.Assesment.Repository.DepartmentRepository;
import com.example.Assesment.Repository.EmployeeRepository;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EmployeeServiceImp  implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeMapper mapper;

    @Autowired
    private DepartmentRepository repositoryDepartment;

    @Autowired
    private DepartmentMapper departmentMapper;


    public DepartmentEntity DefineDepartment(DepartmentDTO departmentDTO) {
        DepartmentEntity departmentEntity = departmentMapper.departmentDTOToDepartmentEntity(departmentDTO);
        return repositoryDepartment.save(departmentEntity);
    }



    public EmployeeEntity createEmployee(EmployeeDTO employeeDTO) {

        EmployeeEntity employeeEntity = mapper.employeeDTOToEmployeeEntity(employeeDTO);
        EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);
        return savedEmployee;
    }

    public void updateEmployee(Integer empId, Map<String, Object> entityDto) {
        EmployeeEntity entityToUpdate = employeeRepository.findById(empId).orElseThrow(() -> new ResourceNotFoundException("User not found with number: " + empId));
        updateEntity(entityDto, entityToUpdate, EmployeeEntity.class);
        employeeRepository.saveAndFlush(entityToUpdate);
    }


    public Integer deleteEmployee(Integer empId) {
        EmployeeEntity employeeToDelete = employeeRepository.findById(empId).orElse(null);
        if (employeeToDelete != null) {
            employeeRepository.delete(employeeToDelete);
            return empId;
        } else {
            return null;
        }
    }

    public void updateDepartment(Integer depId, Map<String, Object> entityDto) {
        DepartmentEntity entityToUpdate = repositoryDepartment.findById(depId).orElseThrow(() -> new ResourceNotFoundException("User not found with number: " + depId));
        updateEntity(entityDto, entityToUpdate, DepartmentEntity.class);
        repositoryDepartment.saveAndFlush(entityToUpdate);
    }

    public void updateEntity(Map<String, Object> entityDTO, Object entityToUpdate, Class entityToUpdateClass) {
        entityDTO.forEach((k, v) -> {
            try {
                Field field = FieldUtils.getField(entityToUpdateClass, k, true);
                if (field != null) {
                    field.setAccessible(true);
                    field.set(entityToUpdate, v);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }


    public Integer deleteDepartment(Integer depId) {
        DepartmentEntity dempartmentToDelete = repositoryDepartment.findById(depId).orElse(null);
        if (dempartmentToDelete != null) {
            repositoryDepartment.delete(dempartmentToDelete);
            return depId;
        } else {
            return null;
        }
    }


    public EmployeeDTO getOneEmployee(Integer empId) {
        Optional<EmployeeEntity> employeeEntityOptional = employeeRepository.findById(empId);
        if (employeeEntityOptional.isPresent()) {
            EmployeeEntity employeeEntity = employeeEntityOptional.get();
            EmployeeDTO employeeDTO = mapper.employeeEntityToEmployeeDTO(employeeEntity);

            Integer departmentId = employeeEntity.getDepartmentId();
            if (departmentId != null) {
                DepartmentEntity departmentEntity = repositoryDepartment.findById(departmentId).orElse(null);
                if (departmentEntity != null) {
                    employeeDTO.setDepartmentName(departmentEntity.getDepName());
                }
            }
            return employeeDTO;
        } else {
            return null;
        }
    }



    public List<EmployeeDTO> getEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities.stream()
                .map(employeeEntity -> {
                    EmployeeDTO employeeDTO = mapper.employeeEntityToEmployeeDTO(employeeEntity);
                    DepartmentEntity departmentEntity = repositoryDepartment.findById(employeeEntity.getDepartmentId()).orElse(null);
                    if (departmentEntity != null) {
                        employeeDTO.setDepartmentName(departmentEntity.getDepName());
                    }
                    return employeeDTO;
                })
                .collect(Collectors.toList());
    }


    public  List<DepartmentDTO> getDepartments()
    {
        List<DepartmentEntity> departmentEntities = repositoryDepartment.findAll();
        return departmentEntities.stream()
                .map(departmentEntity  -> departmentMapper.departmentEntityToDepartmentDTO(departmentEntity ))
                .collect(Collectors.toList());
    }
    public DepartmentDTO getOneDepartment(Integer depId) {
        Optional<DepartmentEntity> departmentEntityOptional = repositoryDepartment.findById(depId);
        if (departmentEntityOptional.isPresent()) {
            DepartmentEntity departmentEntity = departmentEntityOptional.get();
            return departmentMapper.departmentEntityToDepartmentDTO(departmentEntity);
        } else {
            return null;
        }
    }
    public List<EmployeeDTO> getEmployeesByDepartment(Integer departmentId) {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities.stream()
                .filter(employeeEntity -> employeeEntity.getDepartmentId().equals(departmentId))
                .map(employeeEntity -> {
                    EmployeeDTO employeeDTO = mapper.employeeEntityToEmployeeDTO(employeeEntity);
                    DepartmentEntity departmentEntity = repositoryDepartment.findById(employeeEntity.getDepartmentId()).orElse(null);
                    if (departmentEntity != null) {
                        employeeDTO.setDepartmentName(departmentEntity.getDepName());
                    }
                    return employeeDTO;
                })
                .collect(Collectors.toList());
    }

}
