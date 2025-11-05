package com.zgdev.usuario.business.converter;

import com.zgdev.usuario.business.dto.EnderecoDTO;
import com.zgdev.usuario.business.dto.TelefoneDTO;
import com.zgdev.usuario.business.dto.UsuarioDTO;
import com.zgdev.usuario.infrastructure.entity.Endereco;
import com.zgdev.usuario.infrastructure.entity.Telefone;
import com.zgdev.usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioConverter {

    // Converte um DTO em uma entidade
    public Usuario paraUsuario (UsuarioDTO usuarioDTO){
        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEndereco(usuarioDTO.getEnderecos()))
                .telefones(paraListaTelefone(usuarioDTO.getTelefones()))
                .build();
    }

    // Converte uma lista de EnderecoDTO em uma lista de entidades Endereco.
    // Utiliza stream para mapear cada DTO usando o método paraEndereco.
    public List<Endereco> paraListaEndereco(List<EnderecoDTO> enderecoDTOS) {
        return enderecoDTOS.stream()
                .map(this::paraEndereco) // chama o método abaixo para cada item
                .toList(); // retorna como lista
    }


    // Converte um único EnderecoDTO em uma entidade Endereco.
    // Usado dentro do método paraListaEndereco.
    public Endereco paraEndereco(EnderecoDTO enderecoDTO){
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .cidade(enderecoDTO.getCidade())
                .complemento(enderecoDTO.getComplemento())
                .cep(enderecoDTO.getCep())
                .estado(enderecoDTO.getEstado())
                .build();
    }

    public List<Telefone> paraListaTelefone(List<TelefoneDTO> telefoneDTOS){
        return telefoneDTOS.stream().map(this::paraTelefone).toList();
    }

    public Telefone paraTelefone(TelefoneDTO telefoneDTO){
        return Telefone.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .build();
    }







    // Converte uma entidade em DTO
    public UsuarioDTO paraUsuarioDTO (Usuario usuario){
        return UsuarioDTO.builder()
                .email(usuario.getEmail())
                .build();
    }
}
