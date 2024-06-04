package com.example.Assesment.Mapper;


import com.example.Assesment.DTO.ExpenseTypeDTO;
import com.example.Assesment.Entity.ExpenseTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExpenseTypeMapper {


    ExpenseTypeMapper INSTANCE = Mappers.getMapper(ExpenseTypeMapper.class);

    ExpenseTypeDTO expenseTypeEntityToExpenseTypeDTO(ExpenseTypeEntity expenseTypeEntity);

    ExpenseTypeEntity expenseTypeDTOToExpenseTypeEntity(ExpenseTypeDTO expenseTypeDTO);

}
