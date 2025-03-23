package org.hrd.homework002.service.implementation;

import lombok.Builder;
import org.hrd.homework002.model.dto.request.StudentRequest;
import org.hrd.homework002.model.entity.Student;
import org.hrd.homework002.model.entity.StudentCourse;
import org.hrd.homework002.repository.StudentRepository;
import org.hrd.homework002.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Builder
public class StudentServiceImp implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImp(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents(Integer offset, Integer limit) {
        return studentRepository.findAllStudents(offset, limit);
    }

    @Override
    public Student getStudentById(Integer studentId) {
        return studentRepository.findStudentById(studentId);
    }

    @Override
    @Transactional
    public Student updateStudentById(Integer studentId, StudentRequest studentRequest) {
        studentRepository.deleteStudentCourseByStudentId(studentId);
        for (Integer courseId : studentRequest.getCourseIds()) {
            studentRepository.insertStudentCourse(studentId, courseId);
        }
        return studentRepository.modifyStudentById(studentId, studentRequest);
    }

    @Override
    @Transactional
    public Student addStudent(StudentRequest studentRequest) {
        Integer studentId = studentRepository.insertStudent(studentRequest);
        for (Integer courseId : studentRequest.getCourseIds()){
            studentRepository.insertStudentCourse(studentId, courseId);
        }
        return studentRepository.findStudentById(studentId);
    }

    @Override
    @Transactional
    public Student deleteStudentById(Integer studentId) {
        studentRepository.deleteStudentCourseByStudentId(studentId);
        return studentRepository.deleteStudentById(studentId);
    }
}
