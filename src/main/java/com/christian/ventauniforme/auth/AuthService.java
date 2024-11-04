package com.christian.ventauniforme.auth;

import com.christian.ventauniforme.jwt.JwtService;
import com.christian.ventauniforme.model.Persona;
import com.christian.ventauniforme.model.Rol;
import com.christian.ventauniforme.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponse login(LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getPassword()));
        UserDetails user=personaRepository.findByCorreo(request.getCorreo());
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request){
        Persona persona = new Persona();
        Rol rol=new Rol();
        rol.setId("CUSTOMER");
        persona.setCorreo(request.getCorreo());
        persona.setId(request.getId());
        persona.setNombre(request.getNombre());
        persona.setApellido(request.getApellido());
        persona.setCelular(request.getCelular());
        persona.setPassword(passwordEncoder.encode(request.getPassword()));
        persona.setRol(rol);
        personaRepository.save(persona);
        return AuthResponse.builder()
                .token(jwtService.getToken(persona))
                .build();
    }
}
