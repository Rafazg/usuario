package com.zgdev.usuario.business;

import com.zgdev.usuario.Infrastructure.entity.Usuario;
import com.zgdev.usuario.Infrastructure.repository.UsuarioRepository;
import com.zgdev.usuario.business.Dto.UsuarioDTO;
import com.zgdev.usuario.business.converter.UsuarioConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return usuarioConverter.paraUsuarioDTO(usuario);
    }
}
