package com.sistema.academia.internal.acesso.controller;

import com.sistema.academia.internal.acesso.dto.AcessoRequestRecord;
import com.sistema.academia.internal.acesso.dto.AcessoResponseRecord;
import com.sistema.academia.internal.acesso.service.AcessoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/acessos")
@RequiredArgsConstructor
public class AcessoController {

    private final AcessoService service;

    @PostMapping
    public ResponseEntity<AcessoResponseRecord>
    salvar(

            @RequestBody
            @Valid
            AcessoRequestRecord request){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.salvar(request));
    }

    @GetMapping
    public ResponseEntity<List<AcessoResponseRecord>>
    listar(){

        return ResponseEntity.ok(
                service.listar());
    }
}
