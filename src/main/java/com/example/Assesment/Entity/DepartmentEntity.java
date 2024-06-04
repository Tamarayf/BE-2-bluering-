package com.example.Assesment.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Data
@Entity
@Table(name = "Department", schema = "Assesment", catalog = "")
public class DepartmentEntity {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dep_id")
    private int depId;
    @Basic@Column(name = "dep_name")
    private String depName;


}
