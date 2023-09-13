package com.miniProject.miniProjectTask.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoryDto {
    private Integer id;
    private Date borrowedDate;
    private Date dueDate;
}
