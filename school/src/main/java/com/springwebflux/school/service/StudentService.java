package com.springwebflux.school.service;

import com.springwebflux.school.dto.StudentDTO;
import com.springwebflux.school.entity.Department;
import com.springwebflux.school.entity.Status;
import com.springwebflux.school.entity.Student;
import com.springwebflux.school.repo.DepartmentRepository;
import com.springwebflux.school.repo.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;

    public Mono<Student> saveStudent(StudentDTO studentDTO) {
        // Fetch department by ID (assuming you have a standard repository or method)
        Optional<Department> department = departmentRepository.findById(studentDTO.getDepartmentId());

        if (department.get()==null) {
            // Handle case where department is not found
            throw new RuntimeException("Department not found");
        }

        // Create and populate the student object
        Student student = new Student();
        student.setDepartment(department.get());
        student.setStatus(studentDTO.getStatus());
        student.setEnrollmentDate(LocalDate.now());
        student.setEmail(studentDTO.getEmail());
        return Mono.fromCallable(() -> studentRepository.save(student));
        // Save the student (assuming the save method is synchronous)
    }





    public Mono<Student> getStudent(Long id) {
        return Mono.justOrEmpty(studentRepository.findById(id));
    }

    public Flux<Student> getAllStudentsInDepartment(Long departmentId) {
        return Flux.fromIterable(studentRepository.findByDepartmentId(departmentId));
    }

    public Flux<Student> getAllStudentsByEnrollmentDate(LocalDate startDate, LocalDate endDate) {
        return Flux.fromIterable(studentRepository.findByEnrollmentDateBetween(startDate, endDate));
    }

    public Flux<Student> getAllActiveStudents() {
        return Flux.fromIterable(studentRepository.findByStatus(Status.ACTIVE));
    }

    public Mono<Student> updateStudent(Long id, StudentDTO studentDTO) {
        return Mono.justOrEmpty(studentRepository.findById(id))
                   .map(student -> {
                       student.setFirstName(studentDTO.getFirstName());
                       student.setLastName(studentDTO.getLastName());
                       student.setEmail(studentDTO.getEmail());
                       student.setStatus(studentDTO.getStatus());
                       student.setEnrollmentDate(studentDTO.getEnrollmentDate());
                       return studentRepository.save(student);
                   });
    }

    public Mono<Void> deleteStudent(Long id) {
        return Mono.justOrEmpty(studentRepository.findById(id))
                   .map(student -> {
                       studentRepository.delete(student);
                       return student;
                   }).then();
    }
}
