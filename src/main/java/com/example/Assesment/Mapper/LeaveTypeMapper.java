package com.example.Assesment.Mapper;

import com.example.Assesment.DTO.LeaveTypeDTO;
import com.example.Assesment.Entity.LeaveTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LeaveTypeMapper {

    LeaveTypeMapper INSTANCE = Mappers.getMapper(LeaveTypeMapper.class);


    LeaveTypeDTO leaveTypeEntityToLeaveTypeDTO(LeaveTypeEntity leaveTypeEntity);

    LeaveTypeEntity leaveTypeDTOToLeaveTypeEntity(LeaveTypeDTO leaveTypeDTO);
//
//    default String map(Object value) {
//        if (value instanceof String) {
//            return ((String) value).toString();
//        }
//        return null;
//    }
}