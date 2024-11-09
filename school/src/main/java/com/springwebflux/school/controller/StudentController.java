package com.springwebflux.school.controller;

import com.springwebflux.school.dto.StudentDTO;
import com.springwebflux.school.entity.Student;
import com.springwebflux.school.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public Mono<Student> saveStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.saveStudent(studentDTO);
    }

    @GetMapping("/{id}")
    public Mono<Student> getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @GetMapping("/department/{departmentId}")
    public Flux<Student> getAllStudentsInDepartment(@PathVariable Long departmentId) {
        return studentService.getAllStudentsInDepartment(departmentId);
    }

    @GetMapping("/enrollment")
    public Flux<Student> getAllStudentsByEnrollmentDate(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return studentService.getAllStudentsByEnrollmentDate(startDate, endDate);
    }

    @GetMapping("/active")
    public Flux<Student> getAllActiveStudents() {
        return studentService.getAllActiveStudents();
    }

    @PutMapping("/{id}")
    public Mono<Student> updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        return studentService.updateStudent(id, studentDTO);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }
}
