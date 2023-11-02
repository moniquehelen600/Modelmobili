package com.senai.mobili.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDto(
@NotBlank String nome,
@NotBlank @Email(message = "o email deve estar no formato valido") String email,
@NotBlank String senha,
@NotBlank String telefone
){

}


