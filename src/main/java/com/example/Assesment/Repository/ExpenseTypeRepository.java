package com.example.Assesment.Repository;


import com.example.Assesment.Entity.ExpenseTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseTypeRepository extends JpaRepository<ExpenseTypeEntity,Integer> {
}
