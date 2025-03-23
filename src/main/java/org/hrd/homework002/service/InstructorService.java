package org.hrd.homework002.service;

import org.hrd.homework002.model.dto.request.InstructorRequest;
import org.hrd.homework002.model.entity.Instructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InstructorService {

    List<Instructor> getAllInstructors(Integer offset, Integer limit);

    Instructor getInstructorById(Integer instructorId);

    Instructor updateInstructorById(Integer instructorId, InstructorRequest instructorRequest);

    Instructor addInstructor(InstructorRequest instructorRequest);

    Instructor deleteInstructorById(Integer instructorId);
}
