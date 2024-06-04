package com.example.Assesment.Entity;

import com.mysql.cj.MysqlConnection;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "expenseclaim", schema = "Assesment", catalog = "")
public class ExpenseClaimEntity {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_claim_id")
    private int expenseClaimId;
    @Basic
    @Column(name = "dates")
    private LocalDate dates;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "total")
    private Double total;
    @Basic
    @Column(name = "statuss")
    private String statuss;
    @Basic
    @Column(name = "employee_id")
    private Integer employeeId;



}