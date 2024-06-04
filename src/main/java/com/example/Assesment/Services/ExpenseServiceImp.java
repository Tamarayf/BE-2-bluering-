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


    public ExpenseClaimEntity createExpenseClaim(ExpenseClaimDTO dto) {
        ExpenseClaimEntity expenseClaim = ExpenseClaimMapper.INSTANCE.expenseClaimDTOToExpenseClaimEntity(dto);
        expenseClaim = expenseClaimrepository.save(expenseClaim);

        List<ExpenseClaimEntryEntity> entries = dto.getEntries().stream()
                .map(ExpenseClaimEntryMapper.INSTANCE::expenseClaimEntryDTOToExpenseClaimEntryEntity)

                .collect(Collectors.toList());

        for (ExpenseClaimEntryEntity entry : entries) {
            entry.setExpenseClaimId(expenseClaim.getExpenseClaimId());
            expenseClaimEntryrepository.save(entry);
        }

        double totalAmount = entries.stream()
                .mapToDouble(ExpenseClaimEntryEntity::getTotal)
                .sum();
        expenseClaim.setTotal(totalAmount);

        ExpenseClaimEntity r = expenseClaimrepository.save(expenseClaim);

        return r;
    }

    //    public ClaimTypeTotalDTO getTotalClaimsByTypeForEmployee(ExpenseClaimRequestDTO request) {
//        List<ExpenseClaim> claims = expenseClaimRepository.findByEmployeeId(request.getEmployeeId());
//
//        Double totalAmount = claims.stream()
//                .flatMap(claim -> claim.getEntries().stream())
//                .filter(entry -> entry.getExpenseTypeId().equals(request.getExpenseTypeId()))
//                .mapToDouble(entry -> entry.getTotal())
//                .sum();
//
//        return new ClaimTypeTotalDTO(request.getExpenseTypeId(), totalAmount);
//    }


//    public List<EmployeeDTO> getExpenseClaimsPerEmployeePerType(Integer id) {
//        List<EmployeeEntity> employees = employeeRepository.findAll();
//        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
//        for (EmployeeEntity employee : employees) {
//            EmployeeDTO employeeDTO = mapper.employeeEntityToEmployeeDTO(employee);
//            List<Integer> expenseTypes = expenseClaimEntryrepository.getExpensesForEmployee(employee.getEmpId());
//            List<ExpenseTypeDTO> expensetypeDTOS = new ArrayList<>();
//            for (Integer expenseType : expenseTypes) {
//                ExpenseTypeDTO expensetypeDTO = expenseTypeMapper
//                        .expenseTypeEntityToExpenseTypeDTO(expenseTyperepository.getReferenceById(expenseType));
//                expensetypeDTO.setExpenseClaimEntries(expenseClaimEntryrepository.findByExpenseTypeId(expenseType)
//                        .stream()
//                        .map(expenseClaimEntryMapper::expenseClaimEntryEntityToExpenseClaimEntryDTO)
//                        .collect(Collectors.toList()));
//                expensetypeDTOS.add(expensetypeDTO);
//            }
//            employeeDTO.setExpenseTypeDTOList(expensetypeDTOS);
//            employeeDTOs.add(employeeDTO);
//        }
//        return employeeDTOs;
//    }


//    public Double getTotalClaimsPerTypePerEmployee(Integer employeeId, Integer expenseClaimTypeId) {
//        List<ExpenseClaimEntryEntity> expenseClaims = expenseClaimEntryrepository.findByEmployeeIdAndExpenseClaimTypeId(employeeId, expenseClaimTypeId);
//
//        Double totalClaims = 0.0;
//        for (ExpenseClaimEntryEntity expenseClaim : expenseClaims) {
//            totalClaims += expenseClaim.getTotal();
//        }
//
//        return totalClaims;
//}

//    public Double getTotalAmountByTypeIdAndEmployee(Integer expenseTypeId, Integer employeeId) {
//        System.out.println("Fetching total amount for expenseTypeId: " + expenseTypeId + " and employeeId: " + employeeId);
//
//        List<ExpenseClaimEntity> expenses = expenseClaimrepository.findByExpenseTypeIdAndEmployeeId(expenseTypeId, employeeId);
//        if (expenses == null || expenses.isEmpty()) {
//            System.out.println("No expenses found for the given criteria.");
//            return null;
//        }
//
//        Double totalAmount = expenses.stream().mapToDouble(ExpenseClaimEntity::getTotal).sum();
//        System.out.println("Total amount calculated: " + totalAmount);
//        return totalAmount;
//    }

    public Double getTotalAmountByTypeIdAndEmployee(Integer expenseTypeId, Integer employeeId) {
        return expenseClaimEntryrepository.getTotalAmountByTypeIdAndEmployee(expenseTypeId, employeeId);
    }
//public List<TotalClaimPerTypePerEmployeeDTO> getTotalClaimsPerTypePerEmployee() {
//    List<Object[]> results = expenseClaimrepository.getTotalClaimsPerTypePerEmployee();
//
//    List<TotalClaimPerTypePerEmployeeDTO> dtos = new ArrayList<>();
//    for (Object[] result : results) {
//        TotalClaimPerTypePerEmployeeDTO dto = new TotalClaimPerTypePerEmployeeDTO();
//        dto.setEmployeeId((Integer) result[0]);
//        dto.setExpenseTypeId((Integer) result[1]);
//        dto.setTotalAmount((Double) result[2]);
//        dtos.add(dto);
//    }
//
//    return dtos;
//}

}