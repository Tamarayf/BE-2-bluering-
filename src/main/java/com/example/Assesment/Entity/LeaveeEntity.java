package com.example.Assesment.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;


@Getter
@Setter
@Entity
@Table(name = "Leavee", schema = "Assesment", catalog = "")
public class LeaveeEntity {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leave_id")
    private int leaveId;
    @Basic@Column(name = "leave_type_id")
    private Integer leaveTypeId;
    @Basic@Column(name = "from_date")
    private LocalDate fromDate;
    @Basic@Column(name = "to_date")
    private LocalDate toDate;
    @Basic@Column(name = "number_of_days")
    private Integer numberOfDays;
    @Basic@Column(name = "note")
    private String note;
    @Basic@Column(name = "employee_id")
    private Integer employeeId;


}
