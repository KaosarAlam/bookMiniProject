package com.miniProject.miniProjectTask.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private Integer id;
    private String review;
    private String ratings;
    private String reviewTime;
}
