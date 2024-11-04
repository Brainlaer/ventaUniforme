package com.christian.ventauniforme.controller;

import com.christian.ventauniforme.dto.PersonaDto;
import com.christian.ventauniforme.model.Persona;
import com.christian.ventauniforme.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/persona")
@CrossOrigin("*")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/findByCorreo")
    public ResponseEntity<Persona> findByCorreo(@RequestParam("correo") String correo){
        return personaService.findByCorreo(correo);
    }
    @PostMapping("/noAuth/crear")
    public String crear(@RequestBody Persona persona){
        return personaService.crear(persona);
    }
    @DeleteMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id){
        return  personaService.deleteById(id);
    }
    @PostMapping("/noAuth/welcome")
    public String hola(){
        return "hola";
    }
}
