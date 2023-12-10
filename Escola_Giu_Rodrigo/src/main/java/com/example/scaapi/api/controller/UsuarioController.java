package com.example.scaapi.api.controller;

import com.example.scaapi.api.dto.CredenciaisDTO;
import com.example.scaapi.api.dto.TokenDTO;
import com.example.scaapi.exception.SenhaInvalidaException;
import com.example.scaapi.model.entity.Usuario;
import com.example.scaapi.security.JwtService;
import com.example.scaapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody CredenciaisDTO credenciais){
        String senhaCriptografada = passwordEncoder.encode(credenciais.getSenha());
        credenciais.setSenha(senhaCriptografada);
        return usuarioService.salvar(converter(credenciais));
    }

    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais){
        try{
            Usuario usuario = Usuario.builder()
                    .login(credenciais.getLogin())
                    .senha(credenciais.getSenha()).build();
            UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getLogin(), token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e ){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    public Usuario converter(CredenciaisDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Usuario user = new Usuario();
        user = modelMapper.map(dto, Usuario.class);
        return user;
    }
}
