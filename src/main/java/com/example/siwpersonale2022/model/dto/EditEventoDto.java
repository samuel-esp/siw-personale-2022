package com.example.siwpersonale2022.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class EditEventoDto {

    @NotNull
    private Long id;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data;

    @NotNull
    @Positive
    private Integer prezzoInt;



}
