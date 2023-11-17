package com.senai.mobili.controllers;

import com.senai.mobili.dtos.UsuarioDto;
import com.senai.mobili.models.UsuarioModel;
import com.senai.mobili.repositories.UsuarioRepository;
import jakarta.validation.Valid;
//import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value= "/usuario",produces = {"application/json"})
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;


    @GetMapping
    public ResponseEntity<List<UsuarioModel>> listarUsuarios() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findAll());
    }

    @GetMapping("/{idusuario}")
    public ResponseEntity<Object> buscarUsuario(@PathVariable(value = "idUsuario") UUID id) {
        Optional<UsuarioModel> usuarioBuscado = usuarioRepository.findById(id);

        if (usuarioBuscado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioBuscado.get());
    }

    @PostMapping
    public ResponseEntity<Object> criarUsuario(@RequestBody @Valid UsuarioDto usuario) {
        if (usuarioRepository.findByEmail(usuario.email()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }

        UsuarioModel novoUsuario = new UsuarioModel();
        BeanUtils.copyProperties(usuario, novoUsuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioRepository.save(usuario));
    }
}

