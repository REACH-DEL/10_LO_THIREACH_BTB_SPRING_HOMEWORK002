package org.hrd.homework002.controller;

import org.apache.ibatis.annotations.Update;
import org.hrd.homework002.model.dto.request.StudentRequest;
import org.hrd.homework002.model.dto.response.ApiResponse;
import org.hrd.homework002.model.entity.Student;
import org.hrd.homework002.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<Student>>> getAllStudents(@RequestParam(value = "offset", defaultValue = "1") Integer offset, @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        List<Student> students = studentService.getAllStudents(offset, limit);
        ApiResponse<List<Student>> response = ApiResponse.<List<Student>>builder()
                .message("The student has been successfully added.")
                .status(HttpStatus.OK)
                .payload(students)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/{student-id}")
    public ResponseEntity<ApiResponse<Student>> getStudentById(@PathVariable("student-id") Integer studentId){
        Student student = studentService.getStudentById(studentId);
        if (student == null){
            ApiResponse<Student> response = ApiResponse.<Student>builder()
                    .message("Student ID " + studentId + " not found.")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .message("Students has been successfully fetched.")
                .payload(student)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Student>> addStudent(@RequestBody StudentRequest studentRequest){
        Student student = studentService.addStudent(studentRequest);
        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .message("Students has been successfully fetched.")
                .payload(student)
                .status(HttpStatus.CREATED)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @DeleteMapping("/{student-id}")
    public ResponseEntity<ApiResponse<Student>> deleteStudentById(@PathVariable("student-id") Integer studentId){
        Student student = studentService.deleteStudentById(studentId);
        if (student == null){
            ApiResponse<Student> response = ApiResponse.<Student>builder()
                    .message("Delete fail. Students ID " +studentId+ " not found.")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .message("Students has been successfully deleted.")
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PutMapping("/{student-id}")
    public ResponseEntity<ApiResponse<Student>> updateStudentById(@PathVariable("student-id") Integer studentId,  @RequestBody StudentRequest studentRequest){
        Student student = studentService.updateStudentById(studentId, studentRequest);
        if (student == null){
            ApiResponse<Student> response = ApiResponse.<Student>builder()
                    .message("Update fail. Students ID " +studentId+ " not found.")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .message("Students has been successfully updated.")
                .payload(student)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
