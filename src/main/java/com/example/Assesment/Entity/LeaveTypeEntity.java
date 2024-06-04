package com.example.Assesment.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "leave_type", schema = "Assesment", catalog = "")
public class LeaveTypeEntity {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leave_type_id")
    private int idLeaveType;
    @Basic@Column(name = "name_leave_type")
    private String nameLeaveType;

}
