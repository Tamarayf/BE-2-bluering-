package com.example.Assesment.Mapper;

import com.example.Assesment.DTO.EmployeeDTO;
import com.example.Assesment.Entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeDTO employeeEntityToEmployeeDTO(EmployeeEntity employeeEntity);
    EmployeeEntity employeeDTOToEmployeeEntity (EmployeeDTO employeeDTO);

}

