package com.zgdev.usuario.business.converter;

import com.zgdev.usuario.Infrastructure.entity.Endereco;
import com.zgdev.usuario.Infrastructure.entity.Telefone;
import com.zgdev.usuario.Infrastructure.entity.Usuario;
import com.zgdev.usuario.business.Dto.EnderecoDTO;
import com.zgdev.usuario.business.Dto.TelefoneDTO;
import com.zgdev.usuario.business.Dto.UsuarioDTO;
import com.zgdev.usuario.business.UsuarioService;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Component
public class UsuarioConverter {

    // Convertendo UsuarioDTO para Usuario
    public Usuario paraUsuario(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEndereco(usuarioDTO.getEnderecos()))
                .telefones(paraListaTelefone(usuarioDTO.getTelefones()))
                .build();
    }

    public List<Endereco> paraListaEndereco(List<EnderecoDTO> enderecoDTOs) {

        if (enderecoDTOs == null) {
            return List.of();
        }

        return enderecoDTOs.stream()
                .map(this::paraEndereco)
                .toList();
    }

    public Endereco paraEndereco(EnderecoDTO enderecoDTO) {
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .cidade(enderecoDTO.getCidade())
                .complemento(enderecoDTO.getComplemento())
                .cep(enderecoDTO.getCep())
                .estado(enderecoDTO.getEstado())
                .build();
    }

    public List<Telefone> paraListaTelefone(List<TelefoneDTO> telefoneDTOs) {

        if (telefoneDTOs == null) {
            return List.of();
        }

        return telefoneDTOs.stream()
                .map(this::paraTelefone)
                .toList();
    }

    public Telefone paraTelefone(TelefoneDTO telefoneDTO) {
        return Telefone.builder()
                .ddd(telefoneDTO.getDdd())
                .numero(telefoneDTO.getNumero())
                .build();
    }

    // Convertendo Usuario para UsuarioDTO
    public UsuarioDTO paraUsuarioDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .enderecos(paraListaEnderecoDTO(usuario.getEnderecos()))
                .telefones(paraListaTelefonesDTO(usuario.getTelefones()))
                .build();
    }

    public List<EnderecoDTO> paraListaEnderecoDTO(List<Endereco> enderecos) {

        if (enderecos == null) {
            return List.of();
        }

        return enderecos.stream()
                .map(this::paraEnderecoDTO)
                .toList();
    }

    public EnderecoDTO paraEnderecoDTO(Endereco endereco) {
        return EnderecoDTO.builder()
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .cidade(endereco.getCidade())
                .complemento(endereco.getComplemento())
                .cep(endereco.getCep())
                .estado(endereco.getEstado())
                .build();
    }

    public List<TelefoneDTO> paraListaTelefonesDTO(List<Telefone> telefones) {

        if (telefones == null) {
            return List.of();
        }

        return telefones.stream()
                .map(this::paraTelefoneDTO)
                .toList();
    }

    public TelefoneDTO paraTelefoneDTO(Telefone telefone) {
        return TelefoneDTO.builder()
                .ddd(telefone.getDdd())
                .numero(telefone.getNumero())
                .build();
    }

    public Usuario updateUsuario(UsuarioDTO usuarioDTO, Usuario usuarioEntity){
        return Usuario.builder()
                .nome(usuarioDTO.getNome() != null ? usuarioDTO.getNome() : usuarioEntity.getNome())
                .id(usuarioEntity.getId())
                .senha(usuarioDTO.getSenha() != null ? usuarioDTO.getSenha() : usuarioEntity.getSenha())
                .email(usuarioDTO.getEmail() != null ? usuarioDTO.getEmail() : usuarioEntity.getEmail())
                .enderecos(usuarioEntity.getEnderecos())
                .telefones(usuarioEntity.getTelefones())
                .build();
    }
}