package com.example.Assesment.Mapper;

;

import com.example.Assesment.DTO.LeaveeDTO;
import com.example.Assesment.Entity.LeaveeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LeaveeMapper {

    LeaveeMapper INSTANCE = Mappers.getMapper(LeaveeMapper.class);

    LeaveeDTO leaveeEntityToLeaveeDTO(LeaveeEntity leaveeEntity);

    LeaveeEntity leaveeDTOToLeaveeEntity(LeaveeDTO leaveeDTO);


}