package com.example.Assesment.Repository;

import com.example.Assesment.Entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;


    public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Integer> {
    }


