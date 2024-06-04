package com.example.Assesment.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "ExpenseType", schema = "Assesment", catalog = "")
public class ExpenseTypeEntity {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_type_id")
    private int expenseTypeId;
    @Basic@Column(name = "expense_type_name")
    private String expenseTypeName;


}
