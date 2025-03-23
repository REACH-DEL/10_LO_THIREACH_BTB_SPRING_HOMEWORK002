package org.hrd.homework002.service.implementation;

import org.hrd.homework002.model.dto.request.CourseRequest;
import org.hrd.homework002.model.entity.Course;
import org.hrd.homework002.repository.CourseRepository;
import org.hrd.homework002.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImp implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImp(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourse(Integer offset, Integer limit) {
        return courseRepository.findAllCourse(offset, limit);
    }

    @Override
    public Course getCourseById(Integer courseId) {
        return courseRepository.findCourseById(courseId);
    }

    @Override
    public Course updateCourseById(Integer courseId, CourseRequest courseRequest) {
        return courseRepository.modifyCourseById(courseId, courseRequest);
    }

    @Override
    public Course deleteCourseById(Integer courseId) {
        return courseRepository.removeCourseById(courseId);
    }

    @Override
    public Course addCourseById(CourseRequest courseRequest) {
        return courseRepository.insertCourseById(courseRequest);
    }

}
