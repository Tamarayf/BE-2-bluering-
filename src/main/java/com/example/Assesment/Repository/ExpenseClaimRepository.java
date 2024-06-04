package com.example.Assesment.Repository;

import com.example.Assesment.DTO.TotalClaimPerTypePerEmployeeDTO;
import com.example.Assesment.Entity.ExpenseClaimEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;




@Repository
public interface ExpenseClaimRepository extends JpaRepository<ExpenseClaimEntity, Integer> {



}





