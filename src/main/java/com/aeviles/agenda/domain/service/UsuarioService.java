package com.aeviles.agenda.domain.service;

import com.aeviles.agenda.domain.entity.Usuario;
import com.aeviles.agenda.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioService implements UserDetailsService {//foi implementado devido o security no método configue o UserDetailsService

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    //vamos dar uma Load para User name, ou seja vamos na base buscar o usuario
    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByUsuario(usuario);

        if(optionalUsuario.isEmpty()){
            throw new UsernameNotFoundException("Usuario não encontrado");
        }

        Usuario user = optionalUsuario.get();
        return new User(user.getUsuario(), user.getSenha(),new ArrayList<>()) ;
    }

    public List<Usuario> getAll(){
        return usuarioRepository.findAll();
    }


    public Usuario save(Usuario usuario){
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);

    }



}
