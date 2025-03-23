package org.hrd.homework002.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hrd.homework002.model.entity.Instructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorRequest {
    private String instructorName;

    @Schema(defaultValue = "example@example.com")
    private String email ;

}
