package by.flameksandr.jpa_c1_e1.dto;

import by.flameksandr.jpa_c1_e1.entities.Student;

public record CountedEnrollmentForStudent(
        Student student,
        Long count
) {
}
