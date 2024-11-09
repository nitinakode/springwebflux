package com.springwebflux.school.service;

import com.springwebflux.school.entity.Status;
import com.springwebflux.school.entity.Department;
import com.springwebflux.school.repo.DepartmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public Mono<Department> saveDepartment(Department department) {
        return Mono.fromCallable(() -> departmentRepository.save(department));
    }

    @Transactional
    public Mono<Department> getDepartment(Long id) {
        return Mono.justOrEmpty(departmentRepository.findById(id));
    }

    //check
    public Flux getActiveDepartments() {
        return Flux.fromIterable(departmentRepository.findByStatus(Status.ACTIVE));
    }

    public Mono<Department> updateDepartment(Long id, Department departmentDetails) {
        return Mono.justOrEmpty(departmentRepository.findById(id))
                   .map(existingDept -> {
                       existingDept.setName(departmentDetails.getName());
                       existingDept.setStatus(departmentDetails.getStatus());
                       return departmentRepository.save(existingDept);
                   });
    }

    public Mono<Void> deactivateDepartment(Long id) {
        return Mono.justOrEmpty(departmentRepository.findById(id))
                   .map(department -> {
                       department.setStatus(Status.INACTIVE);
                       departmentRepository.save(department);
                       return department;
                   }).then();
    }

    public Mono<Void> deleteDepartment(Long id) {
        return Mono.justOrEmpty(departmentRepository.findById(id))
                   .map(department -> {
                       departmentRepository.delete(department);
                       return department;
                   }).then();
    }
}
