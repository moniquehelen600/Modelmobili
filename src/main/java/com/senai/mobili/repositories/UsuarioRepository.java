package com.senai.mobili.repositories;

import com.senai.mobili.dtos.UsuarioDto;
import com.senai.mobili.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, UUID> {
    static Object save(UsuarioDto usuario) {
    }

    UsuarioModel findByEmail(String email);
}