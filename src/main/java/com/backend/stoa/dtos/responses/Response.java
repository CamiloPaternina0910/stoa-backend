package com.backend.stoa.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private LocalDate timestamp;

    private boolean error;

    private Object data;

    private int status;

}
