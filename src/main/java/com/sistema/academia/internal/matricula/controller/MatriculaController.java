package com.sistema.academia.internal.matricula.controller;

import com.sistema.academia.internal.matricula.dto.MatriculaRequestRecord;
import com.sistema.academia.internal.matricula.dto.MatriculaResponseRecord;
import com.sistema.academia.internal.matricula.service.MatriculaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/matriculas")
@RequiredArgsConstructor
public class MatriculaController {

    private final MatriculaService service;

    @PostMapping
    public ResponseEntity<MatriculaResponseRecord>
    salvar(

            @RequestBody
            @Valid
            MatriculaRequestRecord request){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.salvar(request));
    }

    @GetMapping
    public ResponseEntity<List<MatriculaResponseRecord>>
    listar(){

        return ResponseEntity.ok(
                service.listar());
    }

}