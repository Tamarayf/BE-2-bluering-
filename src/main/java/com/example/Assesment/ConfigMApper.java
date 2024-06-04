package com.example.Assesment;

import com.example.Assesment.Entity.ExpenseTypeEntity;
import com.example.Assesment.Mapper.*;

//import com.example.Assesment.Mapper.LeaveTypeMapper;
//import com.example.Assesment.Mapper.LeaveeMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
@Mapper
class MapperConfig {

    @Bean
    public DepartmentMapper departmentMapper() {
        return Mappers.getMapper(DepartmentMapper.class);
    }

    @Bean
    public EmployeeMapper employeeMapper() {
        return Mappers.getMapper(EmployeeMapper.class);
    }
    @Bean
    public LeaveeMapper leaveeMapper() {
        return Mappers.getMapper(LeaveeMapper.class);
    }
    @Bean
    public ExpenseTypeMapper expenseTypeMapper() {
        return Mappers.getMapper( ExpenseTypeMapper.class);
    }

    @Bean
    public ExpenseClaimEntryMapper expenseClaimEntryMapper() {
        return Mappers.getMapper(ExpenseClaimEntryMapper.class);
    }
    @Bean
    public ExpenseClaimMapper expenseClaimMapper() {
        return Mappers.getMapper(ExpenseClaimMapper.class);
    }

}