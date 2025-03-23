package org.hrd.homework002.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Instructor {
    private Integer instructorId;
    private String instructorName;
    private String email;
}