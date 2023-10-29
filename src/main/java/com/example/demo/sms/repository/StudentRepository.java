package com.example.demo.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.sms.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>
{

}
