package org.hrd.homework002.repository;

import org.apache.ibatis.annotations.*;
import org.hrd.homework002.model.dto.request.InstructorRequest;
import org.hrd.homework002.model.entity.Instructor;

import java.util.List;

@Mapper
public interface InstructorRepository {

    @Select("""
        select * from instructors offset (#{offset}-1)*#{limit} limit #{limit}
    """)
    @Results(id = "instructorMapper", value = {
            @Result(property = "instructorId", column = "instructor_id"),
            @Result(property = "instructorName", column = "instructor_name"),
            @Result(property = "email", column = "email")
    })
    List<Instructor> findAllIntructors(Integer offset, Integer limit);

    @ResultMap("instructorMapper")
    @Select("""
        select * from instructors where instructor_id = #{instructorId}
    """)
    Instructor findInstructorById(Integer instructorId);

    @ResultMap("instructorMapper")
    @Select("""
        update instructors set instructor_name = #{instructor.instructorName}, email = #{instructor.email} where instructor_id = #{instructorId} returning *
    """)
    Instructor modifyInstructorById(Integer instructorId, @Param("instructor") InstructorRequest instructorRequest);

    @ResultMap("instructorMapper")
    @Select("""
        insert into instructors(instructor_name, email) values (#{instructor.instructorName}, #{instructor.email}) returning *
    """)
    Instructor insertInstructor(@Param("instructor") InstructorRequest instructorRequest);

    @ResultMap("instructorMapper")
    @Select("""
        delete from instructors where instructor_id = #{instructorId} returning *
    """)
    Instructor removeInstructor(Integer instructorId);
}
