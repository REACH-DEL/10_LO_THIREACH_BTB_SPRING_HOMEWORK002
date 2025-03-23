package org.hrd.homework002.service.implementation;

import org.hrd.homework002.model.dto.request.InstructorRequest;
import org.hrd.homework002.model.entity.Instructor;
import org.hrd.homework002.repository.InstructorRepository;
import org.hrd.homework002.service.InstructorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceImp implements InstructorService {

    private final InstructorRepository instructorRepository;

    public InstructorServiceImp(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public List<Instructor> getAllInstructors(Integer offset, Integer limit) {
        return instructorRepository.findAllIntructors(offset, limit);
    }

    @Override
    public Instructor getInstructorById(Integer instructorId) {
        return instructorRepository.findInstructorById(instructorId);
    }

    @Override
    public Instructor updateInstructorById(Integer instructorId, InstructorRequest instructorRequest) {
        return instructorRepository.modifyInstructorById(instructorId, instructorRequest);
    }

    @Override
    public Instructor addInstructor(InstructorRequest instructorRequest) {
        return instructorRepository.insertInstructor(instructorRequest);
    }

    @Override
    public Instructor deleteInstructorById(Integer instructorId) {
        return instructorRepository.removeInstructor(instructorId);
    }
}
