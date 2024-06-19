package com.example.Assesment.Services;

import com.example.Assesment.DTO.*;

import com.example.Assesment.Entity.ExpenseClaimEntity;
import com.example.Assesment.Entity.ExpenseClaimEntryEntity;
import com.example.Assesment.Entity.ExpenseTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface ExpenseService {



    ExpenseTypeEntity DefineExpenseType(ExpenseTypeDTO expenseTypeDTO);
    ExpenseClaimEntity createExpenseClaim(ExpenseClaimDTO dto);

    List<ExpenseClaimDTO> getExpense();
    List<ExpenseClaimEntryDTO> getExpenseClaimEntriesByExpenseClaim(Integer expenseClaimId);

    Double getTotalAmountByTypeIdAndEmployee(Integer expenseTypeId, Integer employeeId);

}
