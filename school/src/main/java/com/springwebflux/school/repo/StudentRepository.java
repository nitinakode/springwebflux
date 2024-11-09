package com.springwebflux.school.repo;

import com.springwebflux.school.entity.Status;
import com.springwebflux.school.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByDepartmentId(Long departmentId);
    List<Student> findByEnrollmentDateBetween(LocalDate startDate, LocalDate endDate);
    List<Student> findByStatus(Status status);
}
