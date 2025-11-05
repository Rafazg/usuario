package com.zgdev.usuario.infrastructure.repository;


import com.zgdev.usuario.infrastructure.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    boolean existsByEmail(String email);

    // "Optional evita o retorno de informações nulas"
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findById(Long id);

    @Transactional
    void deleteByEmail(String email);
}
