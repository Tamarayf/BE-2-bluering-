package com.example.Assesment.Repository;

import com.example.Assesment.Entity.ExpenseClaimEntity;
import com.example.Assesment.Entity.ExpenseClaimEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface ExpenseClaimEntryRepository extends JpaRepository<ExpenseClaimEntryEntity, Integer> {



    @Query("SELECT e FROM ExpenseClaimEntryEntity e JOIN ExpenseClaimEntity c ON e.expenseClaimId = c.expenseClaimId WHERE e.expenseTypeId = :expenseTypeId AND c.employeeId = :employeeId")
    List<ExpenseClaimEntryEntity> findByExpenseTypeIdAndEmployeeId(@Param("expenseTypeId") Integer expenseTypeId, @Param("employeeId") Integer employeeId);

    List<ExpenseClaimEntryEntity> findAllByExpenseClaimId(Integer expenseClaimId);


}






