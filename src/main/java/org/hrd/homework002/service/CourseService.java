package org.hrd.homework002.service;

import org.hrd.homework002.model.dto.request.CourseRequest;
import org.hrd.homework002.model.entity.Course;
import org.hrd.homework002.model.entity.Instructor;

import java.util.List;

public interface CourseService {

    List<Course> getAllCourse(Integer offset, Integer limit);

    Course getCourseById(Integer courseId);


    Course updateCourseById(Integer courseId, CourseRequest courseRequest);

    Course deleteCourseById(Integer courseId);

    Course addCourseById(CourseRequest courseRequest);
}
