package org.hrd.homework002.controller;

import org.hrd.homework002.model.dto.request.CourseRequest;
import org.hrd.homework002.model.dto.response.ApiResponse;
import org.hrd.homework002.model.entity.Course;
import org.hrd.homework002.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourse(@RequestParam(value = "offset", defaultValue = "1") Integer offset, @RequestParam(value = "limit", defaultValue = "10") Integer limit){
        List<Course> courses = courseService.getAllCourse(offset, limit);
        ApiResponse<List<Course>> apiResponse = ApiResponse.<List<Course>>builder()
                .message("All Course have been successfully fetched.")
                .payload(courses)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/{course-id}")
    public ResponseEntity<ApiResponse<Course>> getCourseById(@PathVariable("course-id") Integer courseId){
        Course course = courseService.getCourseById(courseId);
        if (course == null){
            ApiResponse<Course> response = ApiResponse.<Course>builder()
                    .message("Course ID " + courseId + " not found.")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .message("Course has been successfully fetched.")
                .payload(course)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{course-id}")
    public ResponseEntity<ApiResponse<Course>> updateCourseById(@PathVariable("course-id") Integer courseId, @RequestBody CourseRequest courseRequest){
        Course course = courseService.updateCourseById(courseId, courseRequest);
        if (course == null){
            ApiResponse<Course> response = ApiResponse.<Course>builder()
                    .message("Course ID " + courseId + " not found.")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .message("Course has been successfully updated.")
                .payload(course)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @DeleteMapping("/{course-id}")
    public ResponseEntity<ApiResponse<Course>> deleteCourseById(@PathVariable("course-id") Integer courseId){
        Course course = courseService.deleteCourseById(courseId);
        if (course == null){
            ApiResponse<Course> response = ApiResponse.<Course>builder()
                    .message("Course ID " + courseId + " not found.")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        else {
            ApiResponse<Course> response = ApiResponse.<Course>builder()
                    .message("Course has been successfully deleted.")
                    .status(HttpStatus.OK)
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Course>> addCourseById(@RequestBody CourseRequest courseRequest){
        Course course = courseService.addCourseById(courseRequest);
        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .message("Course has been successfully added.")
                .payload(course)
                .status(HttpStatus.CREATED)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
