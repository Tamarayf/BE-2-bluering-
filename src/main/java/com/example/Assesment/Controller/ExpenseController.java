package com.example.Assesment.Controller;


import com.example.Assesment.Apiresponse;
import com.example.Assesment.DTO.*;
import com.example.Assesment.Entity.DepartmentEntity;
import com.example.Assesment.Entity.ExpenseClaimEntity;
import com.example.Assesment.Entity.ExpenseClaimEntryEntity;
import com.example.Assesment.Entity.ExpenseTypeEntity;
import com.example.Assesment.Services.ExpenseService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ExpenseController {

    private final ExpenseService service3;

    @Autowired
    public ExpenseController(ExpenseService service3) {
        this.service3 = service3;
    }


    @PostMapping("/expenseTypes/")
    public Apiresponse defineExpenseType(@RequestBody ExpenseTypeDTO expenseTypeDTO ) {
        ExpenseTypeEntity createdExpenseType = service3.DefineExpenseType(expenseTypeDTO);
        return new Apiresponse(true, "ExpenseType created successfully", createdExpenseType.getExpenseTypeId());
    }

    @GetMapping("/expense")
    public Apiresponse getExpenses() {
        List<ExpenseClaimDTO> expenseDTOs = service3.getExpense();
        return new Apiresponse(true, "Expenses retrieved successfully", expenseDTOs);
    }
    @PostMapping("/expenseClaim/")
    public Apiresponse defineExpenseClaim(@RequestBody ExpenseClaimDTO expenseClaimDTO ) {
        ExpenseClaimEntity createdExpenseClaim = service3.createExpenseClaim(expenseClaimDTO);
        return new Apiresponse(true, "ExpenseClaim created successfully", createdExpenseClaim);
    }

    @GetMapping("/expense-entries/{expenseClaimId}")
    public Apiresponse getExpenseClaimEntriesByExpenseClaim(@PathVariable Integer expenseClaimId ) {
        List<ExpenseClaimEntryDTO> expenseDTOs = service3.getExpenseClaimEntriesByExpenseClaim(expenseClaimId);
        if (!expenseDTOs.isEmpty()) {
            return new Apiresponse(true, "Expenses for department retrieved successfully", expenseDTOs);
        } else {
            return new Apiresponse(false, "No expenses found for department", null);
        }
    }






    @PostMapping("/total")
    public Apiresponse getTotalAmount(@RequestBody Map<String, Integer> requestBody) {
        if (!requestBody.containsKey("expenseTypeId") || !requestBody.containsKey("employeeId")) {
            return new Apiresponse(false, "Missing required parameters: expenseTypeId and employeeId", null);
        }

        Integer expenseTypeId = requestBody.get("expenseTypeId");
        Integer employeeId = requestBody.get("employeeId");

        if (expenseTypeId == null || employeeId == null) {
            return new Apiresponse(false, "expenseTypeId and employeeId cannot be null", null);
        }

        Double totalAmount = service3.getTotalAmountByTypeIdAndEmployee(expenseTypeId, employeeId);

        if (totalAmount == null) {
            return new Apiresponse(false, "No expenses found for the given expenseTypeId and employeeId", null);
        }

        return new Apiresponse(true, "Total amount retrieved successfully", totalAmount);
    }
}





