package com.example.Assesment.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Data
@Entity
@Table(name = "Employee", schema = "Assesment", catalog = "")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private int empId;
    @Basic@Column(name = "emp_name")
    private String empName;
    @Basic@Column(name = "emp_email")
    private String empEmail;
    @Basic@Column(name = "emp_address")
    private String empAddress;
    @Basic@Column(name = "department_id")
    private Integer departmentId;


}
