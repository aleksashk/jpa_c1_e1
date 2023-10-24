package by.flameksandr.jpa_c1_e1.dto;

import by.flameksandr.jpa_c1_e1.entities.Enrollment;
import by.flameksandr.jpa_c1_e1.entities.Student;

public record EnrolledStudent(
        Student student,
        Enrollment enrollment
) {
}
