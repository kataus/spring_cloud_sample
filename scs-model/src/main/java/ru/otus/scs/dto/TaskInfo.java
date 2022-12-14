package ru.otus.scs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskInfo {
    private String taskId;
    private String title;
    private User responsible;
}
