package com.christian.ventauniforme.service;

import com.christian.ventauniforme.dto.PersonaDto;
import com.christian.ventauniforme.model.Persona;
import com.christian.ventauniforme.model.Rol;
import com.christian.ventauniforme.repository.PersonaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    ModelMapper modelMapper = new ModelMapper();

    public String crear(Persona persona){
        Optional<Persona> optionalPersona = personaRepository.findById(persona.getId());
        if(optionalPersona.isEmpty()){
            Rol rol = new Rol();
            rol.setId("USER");
            persona.setRol(rol);
            personaRepository.save(persona);
            return "Guardado con exito";
        }else{
            return "Ya existe una persona con el mismo ID";
        }
    }

    public ResponseEntity<Persona> findByCorreo(String correo){
        Persona persona = personaRepository.findByCorreo(correo);
        if(persona!=null){
            return new ResponseEntity<>(persona, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    public Persona update(Persona persona){
        Optional<Persona> optionalPersona = personaRepository.findById(persona.getId());
        if(optionalPersona.isPresent()){
            Persona personaToUpdate = optionalPersona.get();
            if (persona.getId()!=null){
                personaToUpdate.setId(persona.getId());
            }if(persona.getCorreo()!=null){
                personaToUpdate.setCorreo(persona.getCorreo());
            }if (persona.getNombre()!=null){
                personaToUpdate.setNombre(persona.getNombre());
            }if (persona.getCelular()!=null){
                personaToUpdate.setCelular(persona.getCelular());
            }if (persona.getApellido()!=null){
                personaToUpdate.setApellido(persona.getApellido());
            }if (persona.getPassword()!=null){
                personaToUpdate.setPassword(persona.getPassword());
            }
            personaRepository.save(personaToUpdate);
            return personaToUpdate;
        }else {
            return null;
        }
    }
    public String deleteById(Long id){
        Optional<Persona> optionalPersona = personaRepository.findById(id);
        if (optionalPersona.isPresent()){
            personaRepository.deleteById(id);
            return "Persona con identificación"+id+", ha sido eliminado";
        }else {
            return "Persona no encontrada";
        }
    }
}
