package org.hrd.homework002.repository;

import org.apache.ibatis.annotations.*;
import org.hrd.homework002.model.dto.request.CourseRequest;
import org.hrd.homework002.model.entity.Course;

import java.util.List;

@Mapper
public interface CourseRepository {

    @Results(id = "courseMapper", value = {
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "description", column = "description"),
            @Result(property = "instructor", column = "instructor_id",
                one = @One(select = "org.hrd.homework002.repository.InstructorRepository.findInstructorById"))

    })
    @Select("""
        select * from courses offset (#{offset}-1)* #{limit} limit #{limit}
    """)
    List<Course> findAllCourse(Integer offset, Integer limit);

    @ResultMap("courseMapper")
    @Select("""
        select * from courses where course_id = #{courseId}
    """)
    Course findCourseById(Integer instructorId);

    @ResultMap("courseMapper")
    @Select("""
        update courses set course_name = #{course.courseName}, description = #{course.description}, instructor_id = #{course.instructorId} where course_id = #{courseId} returning *
    """)
    Course modifyCourseById(Integer courseId, @Param("course") CourseRequest courseRequest);

    @ResultMap("courseMapper")
    @Select("""
        delete from courses where course_id = #{courseId} returning *
    """)
    Course removeCourseById(Integer courseId);

    @ResultMap("courseMapper")
    @Select("""
        insert into courses (course_name, description, instructor_id) values (#{course.courseName}, #{course.description}, #{course.instructorId}) returning *
    """)
    Course insertCourseById(@Param("course") CourseRequest courseRequest);
}
