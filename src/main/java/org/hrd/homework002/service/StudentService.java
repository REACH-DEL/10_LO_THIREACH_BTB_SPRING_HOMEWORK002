package org.hrd.homework002.service;

import org.hrd.homework002.model.dto.request.StudentRequest;
import org.hrd.homework002.model.entity.Student;
import org.hrd.homework002.model.entity.StudentCourse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    List<Student> getAllStudents(Integer offset, Integer limit);

    Student getStudentById(Integer studentId);

    Student updateStudentById(Integer studentId, StudentRequest studentRequest);

    Student addStudent(StudentRequest studentRequest);

    Student deleteStudentById(Integer studentId);
}
