package com.hugaojanuario.crud.cadastro_usuarios.business;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hugaojanuario.crud.cadastro_usuarios.infrastructure.entitys.Usuario;
import com.hugaojanuario.crud.cadastro_usuarios.infrastructure.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }
    
    public List <Usuario> listarUsuario(){
        return usuarioRepository.findAll();
    }

    public void salvarUsuario(Usuario usuario){
     usuarioRepository.saveAndFlush(usuario);   
    }

    public Usuario buscarPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(
            () -> new RuntimeException("email nao encontrado")
        );
    }

    public void deletarPorEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }

    public void atualizarDadosPorEmail(String email, Usuario usuario){
        Usuario usuarioEntity = buscarPorEmail(email);
        Usuario usuarioAtualizado = Usuario.builder()
        .email(email)
        .nome(usuario.getNome() != null ?
            usuario.getNome() : usuarioEntity.getNome())
        .id(usuarioEntity.getId())    
        .build();

        usuarioRepository.saveAndFlush(usuarioAtualizado);
    }


}
