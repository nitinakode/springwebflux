package com.springwebflux.school.repo;

import com.springwebflux.school.entity.Status;
import com.springwebflux.school.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByStatus(Status status);

}
