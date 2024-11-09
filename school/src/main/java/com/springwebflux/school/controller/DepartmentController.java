package com.springwebflux.school.controller;

import com.springwebflux.school.entity.Department;
import com.springwebflux.school.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public Mono<Department> saveDepartment(@RequestBody Department department) {
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/{id}")
    public Mono<Department> getDepartment(@PathVariable Long id) {
        return departmentService.getDepartment(id);
    }

    @GetMapping("/active")
    public Flux<Department> getActiveDepartments() {
        return departmentService.getActiveDepartments();
    }

    @PutMapping("/{id}")
    public Mono<Department> updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        return departmentService.updateDepartment(id, department);
    }

    @PutMapping("/{id}/deactivate")
    public Mono<Void> deactivateDepartment(@PathVariable Long id) {
        return departmentService.deactivateDepartment(id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteDepartment(@PathVariable Long id) {
        return departmentService.deleteDepartment(id);
    }
}
