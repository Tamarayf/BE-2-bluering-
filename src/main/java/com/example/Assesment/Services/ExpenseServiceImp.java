package com.example.Assesment.Services;

import com.example.Assesment.DTO.*;
import com.example.Assesment.Entity.*;
import com.example.Assesment.Mapper.*;
import com.example.Assesment.Repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.security.KeyStore;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImp implements ExpenseService {

    @Autowired
    private ExpenseTypeMapper expenseTypeMapper;

    @Autowired
    private ExpenseTypeRepository expenseTyperepository;


    @Autowired
    private ExpenseClaimMapper expenseClaimMapper;

    @Autowired
    private ExpenseClaimRepository expenseClaimrepository;


    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeMapper mapper;

    @Autowired
    private ExpenseClaimEntryMapper expenseClaimEntryMapper;

    @Autowired
    private ExpenseClaimEntryRepository expenseClaimEntryrepository;

    public ExpenseTypeEntity DefineExpenseType(ExpenseTypeDTO expenseTypeDTO) {

        ExpenseTypeEntity expenseTypeEntity = expenseTypeMapper.expenseTypeDTOToExpenseTypeEntity(expenseTypeDTO);
        return expenseTyperepository.save(expenseTypeEntity);

    }
    public  List<ExpenseClaimDTO> getExpense()
    {
        List<ExpenseClaimEntity> expenseClaimEntities = expenseClaimrepository.findAll();
        return expenseClaimEntities.stream()
                .map(expenseClaimEntity  -> expenseClaimMapper.expenseClaimEntityToExpenseClaimDTO(expenseClaimEntity ))
                .collect(Collectors.toList());
    }
    public ExpenseClaimEntity createExpenseClaim(ExpenseClaimDTO dto) {
        // Convert DTO to Entity
        ExpenseClaimEntity expenseClaim = ExpenseClaimMapper.INSTANCE.expenseClaimDTOToExpenseClaimEntity(dto);
        expenseClaim = expenseClaimrepository.save(expenseClaim);

        // Convert DTO entries to Entity entries
        List<ExpenseClaimEntryEntity> entries = dto.getEntries().stream()
                .map(ExpenseClaimEntryMapper.INSTANCE::expenseClaimEntryDTOToExpenseClaimEntryEntity)
                .collect(Collectors.toList());

        // Save entries and set the claim ID
        for (ExpenseClaimEntryEntity entry : entries) {
            entry.setExpenseClaimId(expenseClaim.getExpenseClaimId());
            expenseClaimEntryrepository.save(entry);
        }

        // Calculate total amount or set to 0 if no entries
        double totalAmount = entries.isEmpty() ? 0 : entries.stream()
                .mapToDouble(ExpenseClaimEntryEntity::getTotal)
                .sum();
        expenseClaim.setTotal(totalAmount);

        // Save and return the updated expense claim
        return expenseClaimrepository.save(expenseClaim);
    }

//    public ExpenseClaimEntity createExpenseClaim(ExpenseClaimDTO dto) {
//        ExpenseClaimEntity expenseClaim = ExpenseClaimMapper.INSTANCE.expenseClaimDTOToExpenseClaimEntity(dto);
//        expenseClaim = expenseClaimrepository.save(expenseClaim);
//
//        List<ExpenseClaimEntryEntity> entries = dto.getEntries().stream()
//                .map(ExpenseClaimEntryMapper.INSTANCE::expenseClaimEntryDTOToExpenseClaimEntryEntity)
//
//                .collect(Collectors.toList());
//
//        for (ExpenseClaimEntryEntity entry : entries) {
//            entry.setExpenseClaimId(expenseClaim.getExpenseClaimId());
//            expenseClaimEntryrepository.save(entry);
//        }
//
//        double totalAmount = entries.stream()
//                .mapToDouble(ExpenseClaimEntryEntity::getTotal)
//                .sum();
//        expenseClaim.setTotal(totalAmount);
//
//        ExpenseClaimEntity r = expenseClaimrepository.save(expenseClaim);
//
//        return r;
//    }


    public List<ExpenseClaimEntryDTO> getExpenseClaimEntriesByExpenseClaim(Integer expenseClaimId) {
        List<ExpenseClaimEntryEntity> entries = expenseClaimEntryrepository.findAllByExpenseClaimId(expenseClaimId);
        return entries.stream()
                .map(entry -> {
                    ExpenseClaimEntryDTO entryDTO = expenseClaimEntryMapper.expenseClaimEntryEntityToExpenseClaimEntryDTO(entry);
                    // Assuming you have a method to get the related data, similar to getEmployeesByDepartment

                    return entryDTO;
                })
                .collect(Collectors.toList());
    }

public Double getTotalAmountByTypeIdAndEmployee(Integer expenseTypeId, Integer employeeId) {
    List<ExpenseClaimEntryEntity> entries = expenseClaimEntryrepository.findByExpenseTypeIdAndEmployeeId(expenseTypeId, employeeId);

    if (entries.isEmpty()) {
        System.out.println("No entries found for expenseTypeId: " + expenseTypeId + " and employeeId: " + employeeId);
        return null;
    }

    double total = entries.stream()
            .mapToDouble(ExpenseClaimEntryEntity::getTotal)
            .sum();
    System.out.println("Total amount for expenseTypeId: " + expenseTypeId + " and employeeId: " + employeeId + " is " + total);

    return total;
}


}