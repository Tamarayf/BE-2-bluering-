package com.example.Assesment.Mapper;


import com.example.Assesment.DTO.ExpenseClaimEntryDTO;

import com.example.Assesment.Entity.ExpenseClaimEntryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExpenseClaimEntryMapper {


    ExpenseClaimEntryMapper INSTANCE = Mappers.getMapper(ExpenseClaimEntryMapper.class);

    ExpenseClaimEntryDTO expenseClaimEntryEntityToExpenseClaimEntryDTO(ExpenseClaimEntryEntity expenseClaimEntryEntity);
    ExpenseClaimEntryEntity expenseClaimEntryDTOToExpenseClaimEntryEntity(ExpenseClaimEntryDTO expenseClaimEntryDTO);

}
