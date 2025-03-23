package org.hrd.homework002.model.entity;

import lombok.Data;

import java.util.List;

@Data
public class Student {
    private Integer studentId;
    private String studentName;
    private String email;
    private String phoneNumber;
    private List<StudentCourse> studentCourse;
}
