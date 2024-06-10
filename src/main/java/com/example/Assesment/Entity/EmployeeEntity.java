package com.example.Assesment.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "Employee", schema = "Assesment", catalog = "")
public class EmployeeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private int empId;
    @Basic@Column(name = "first_name")
    private String firstName;
    @Basic@Column(name = "last_name")
    private String lastName;
    @Basic@Column(name = "phone_number")
    private String phonenumber;
    @Basic@Column(name = "Address")
    private String address;
    @Basic@Column(name = "age")
    private int age;
    @Basic@Column(name = "dep_id")
    private Integer departmentId;
    @Basic@Column(name = "gender")
    private Object gender;


}
