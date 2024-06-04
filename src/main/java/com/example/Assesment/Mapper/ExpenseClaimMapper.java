package com.example.Assesment.Mapper;


import com.example.Assesment.DTO.ExpenseClaimDTO;
import com.example.Assesment.Entity.ExpenseClaimEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExpenseClaimMapper {

    ExpenseClaimMapper INSTANCE=Mappers.getMapper(ExpenseClaimMapper.class);
    ExpenseClaimDTO expenseClaimEntityToExpenseClaimDTO(ExpenseClaimEntity expenseClaimEntity);
    ExpenseClaimEntity expenseClaimDTOToExpenseClaimEntity(ExpenseClaimDTO expenseClaimDTO);
}
