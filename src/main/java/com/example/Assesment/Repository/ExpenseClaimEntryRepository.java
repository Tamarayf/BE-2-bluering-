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

    @Query(value = "SELECT DISTINCT ExpClaimEntry.expense_type_id FROM ExpenseClaimEntry ExpClaimEntry JOIN ExpenseClaim ExpClaim ON ExpClaimEntry.expense_claim_id = ExpClaim.expense_claim_id JOIN Employee Emp ON ExpClaim.employee_id = Emp.emp_id  WHERE ExpClaimEntry.expense_Claim_Entry_Id = ?1", nativeQuery = true)
    Double getTotalAmountByTypeIdAndEmployee(Integer expenseTypeId, Integer employeeId);

    List<ExpenseClaimEntryEntity> findAllByExpenseClaimId(Integer expenseClaimId);



//
//    List<ExpenseClaimEntity> findByExpenseTypeIdAndEmployeeId(Integer expenseTypeId, Integer employeeId);
//}
}






