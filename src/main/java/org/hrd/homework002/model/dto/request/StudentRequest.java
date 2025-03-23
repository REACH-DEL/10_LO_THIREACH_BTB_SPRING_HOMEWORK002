package org.hrd.homework002.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class StudentRequest {
    private String studentName;
    @Schema(defaultValue = "example@example.com")
    private String email;
    private String phoneNumber;
    private List<Integer> courseIds;
}
