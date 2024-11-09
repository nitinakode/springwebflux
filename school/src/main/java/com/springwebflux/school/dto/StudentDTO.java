package com.springwebflux.school.dto;

import com.springwebflux.school.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Status status;
    private LocalDate enrollmentDate;
    private Long departmentId;
}
