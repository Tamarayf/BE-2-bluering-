package com.example.Assesment.Mapper;

import com.example.Assesment.DTO.DepartmentDTO;

import com.example.Assesment.Entity.DepartmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    DepartmentDTO departmentEntityToDepartmentDTO(DepartmentEntity departmentEntity);

    DepartmentEntity departmentDTOToDepartmentEntity(DepartmentDTO departmentDTO);
}

