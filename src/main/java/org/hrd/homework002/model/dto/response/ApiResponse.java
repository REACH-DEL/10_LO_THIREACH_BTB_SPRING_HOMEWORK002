package org.hrd.homework002.model.dto.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiResponse <T>{
    private String message;
    private T payload;
    private HttpStatus status;
    private LocalDateTime times = LocalDateTime.now();
}
