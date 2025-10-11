package com.zgdev.usuario.business;


import com.zgdev.usuario.business.converter.UsuarioConverter;
import com.zgdev.usuario.business.dto.UsuarioDTO;
import com.zgdev.usuario.infrastructure.entity.Usuario;
import com.zgdev.usuario.infrastructure.exceptions.ConflictExecption;
import com.zgdev.usuario.infrastructure.exceptions.ResourceNotFoundEexception;
import com.zgdev.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(
                usuarioRepository.save(usuario));
    }


    public void emailExiste(String email){
        try {
            boolean existe = verificaEmailExistente(email);
            if (existe){
                throw new ConflictExecption("E-mail Já cadastrado" + email);
            }
        }catch (ConflictExecption e){
            throw new ConflictExecption("Email já cadastrado" + e.getCause());
        }
    }

    public boolean verificaEmailExistente(String email){
        return usuarioRepository.existsByEmail(email);
    }

}
