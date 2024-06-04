package com.example.Assesment.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "expenseclaimentry", schema = "Assesment", catalog = "")
public class ExpenseClaimEntryEntity {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_claim_entry_id")
    private int expenseClaimEntryId;
    @Basic@Column(name = "date_entry")
    private LocalDate dateEntry;
    @Basic@Column(name = "expense_type_id")
    private Integer expenseTypeId;
    @Basic@Column(name = "expense_claim_id")
    private Integer expenseClaimId;
    @Basic@Column(name = "description")
    private String description;
    @Basic@Column(name = "total")
    private Double total;



}
