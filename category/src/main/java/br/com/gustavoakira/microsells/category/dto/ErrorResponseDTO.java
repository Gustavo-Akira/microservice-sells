package br.com.gustavoakira.microsells.category.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponseDTO (
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    LocalDateTime timestamp,

    Integer code,

    String status,

    List<String> error
){}
