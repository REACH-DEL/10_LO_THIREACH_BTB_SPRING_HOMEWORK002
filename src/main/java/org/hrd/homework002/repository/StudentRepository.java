package org.hrd.homework002.repository;

import org.apache.ibatis.annotations.*;
import org.hrd.homework002.model.dto.request.StudentRequest;
import org.hrd.homework002.model.entity.Course;
import org.hrd.homework002.model.entity.Student;
import org.hrd.homework002.model.entity.StudentCourse;

import java.util.List;

@Mapper
public interface StudentRepository {
    @Results(id = "studentMapper", value = {
            @Result(property = "studentId", column = "student_id"),
            @Result(property = "studentName", column = "student_name"),
            @Result(property = "email", column = "email"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "studentCourse", column = "student_id",
            many = @Many(select = "findStudentCourse"))
    })
    @Select("""
        select * from students offset (#{offset}-1)*#{limit} limit #{limit}
    """)
    List<Student> findAllStudents(Integer offset, Integer limit);

    @Result(property = "course", column = "course_id",
    one = @One(select = "org.hrd.homework002.repository.CourseRepository.findCourseById"))
    @Select("""
        select * from student_course where student_id = #{studentId}
    """)
    List<StudentCourse> findStudentCourse(Integer studentId);

    @ResultMap("studentMapper")
    @Select("""
        select * from students where student_id = #{studentId}
    """)
    Student findStudentById(Integer studentId);

    @ResultMap("studentMapper")
    @Select("""
        update students set student_name = #{st.studentName}, email = #{st.email}, phone_number = #{st.phoneNumber} where student_id = #{studentId} returning *
    """)
    Student modifyStudentById(Integer studentId, @Param("st") StudentRequest studentRequest);

    @Select("""
        insert into students (student_name, email, phone_number) values (#{st.studentName}, #{st.email}, #{st.phoneNumber}) returning student_id
    """)
    Integer insertStudent(@Param("st") StudentRequest studentRequest);

    @Insert("""
        insert into student_course (student_id, course_id) values (#{studentId}, #{courseId})
    """)
    void insertStudentCourse(Integer studentId, Integer courseId);

    @ResultMap("studentMapper")
    @Select("""
        delete from students where student_id = #{studentId} returning *
    """)
    Student deleteStudentById(Integer studentId);

    @Delete("""
        delete from student_course where student_id = #{studentId}
    """)
    void deleteStudentCourseByStudentId(Integer studentId);
}
