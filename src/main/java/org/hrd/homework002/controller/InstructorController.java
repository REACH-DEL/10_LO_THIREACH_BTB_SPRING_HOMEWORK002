package org.hrd.homework002.controller;

import org.hrd.homework002.model.dto.request.InstructorRequest;
import org.hrd.homework002.model.dto.response.ApiResponse;
import org.hrd.homework002.model.entity.Instructor;
import org.hrd.homework002.service.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequestMapping("/api/v1/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Instructor>>> getAllInstructor(@RequestParam(value = "offset", defaultValue = "1") Integer offset, @RequestParam(value = "limit", defaultValue = "10") Integer limit){
        List<Instructor> instructors = instructorService.getAllInstructors(offset, limit);
        ApiResponse<List<Instructor>> apiResponse = ApiResponse.<List<Instructor>>builder()
                .message("All instructors have been successfully fetched.")
                .payload(instructors)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/{instructor-id}")
    public ResponseEntity<ApiResponse<Instructor>> getInstructorById(@PathVariable("instructor-id") Integer instructorId){
        Instructor instructor = instructorService.getInstructorById(instructorId);
        if(instructor == null){
            ApiResponse<Instructor> response = ApiResponse.<Instructor>builder()
                    .message("Instructor ID "+ instructorId+" not found.")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        ApiResponse<Instructor> response = ApiResponse.<Instructor>builder()
                .message("Instructor has been successfully fetched.")
                .payload(instructor)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{instructor-id}")
    public ResponseEntity<ApiResponse<Instructor>> updateInstructorById(@PathVariable("instructor-id") Integer instructorId, @RequestBody InstructorRequest instructorRequest){
        Instructor instructor = instructorService.updateInstructorById(instructorId, instructorRequest);
        if(instructor == null){
            ApiResponse<Instructor> response = ApiResponse.<Instructor>builder()
                    .message("Instructor ID "+ instructorId+" not found.")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        ApiResponse<Instructor> response = ApiResponse.<Instructor>builder()
                .message("Instructor has been successfully updated.")
                .payload(instructor)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Instructor>> addInstructor(@RequestBody InstructorRequest instructorRequest){
        Instructor instructor = instructorService.addInstructor(instructorRequest);
        ApiResponse<Instructor> response = ApiResponse.<Instructor>builder()
                .message("Instructor has been successfully added.")
                .payload(instructor)
                .status(HttpStatus.CREATED)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{instructor-id}")
    public ResponseEntity<ApiResponse<Instructor>> deleteInstructorById(@PathVariable("instructor-id") Integer instructorId){
        Instructor instructor = instructorService.deleteInstructorById(instructorId);
        if(instructor == null){
            ApiResponse<Instructor> response = ApiResponse.<Instructor>builder()
                    .message("Instructor ID "+ instructorId+" not found.")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        ApiResponse<Instructor> response = ApiResponse.<Instructor>builder()
                .message("Instructor has been successfully removed.")
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
