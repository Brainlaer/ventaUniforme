package com.christian.ventauniforme.controller;

import com.christian.ventauniforme.dto.TallaDto;
import com.christian.ventauniforme.dto.VestimentaDto;
import com.christian.ventauniforme.dto.VestimentaDtoGet;
import com.christian.ventauniforme.dto.VestimentaDtoInsert;
import com.christian.ventauniforme.model.Vestimenta;
import com.christian.ventauniforme.service.VestimentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/api/v1/vestimenta")
@CrossOrigin("*")
public class VestimentaController {

    @Autowired
    private VestimentaService vestimentaService;

    @PostMapping("/insertar")
    public ResponseEntity<String> insert(@RequestBody VestimentaDtoInsert vestimentaDto){
        return vestimentaService.insert(vestimentaDto.getVestimentaDto(),vestimentaDto.getTallaDtoList());
    }
    @GetMapping("/findById")
    public ResponseEntity<VestimentaDtoGet> findById(@RequestParam("id") String id){
        return vestimentaService.findById(id);
    }
    @GetMapping("/findByCategoria")
    public ResponseEntity<List<VestimentaDtoGet>> findByCategoria(@RequestParam String categoria){
        return vestimentaService.findByCategoria(categoria);
    }
    @GetMapping("/findBySearch")
    public ResponseEntity<List<VestimentaDtoGet>> findBySearch(@RequestParam String search){
        return vestimentaService.findBySearch(search);
    }
    @GetMapping("/findTheMostRecents")
    public ResponseEntity<List<VestimentaDtoGet>> findTheMostRecents(){
        return vestimentaService.findTheMostRecents();
    }
    @PatchMapping("/update")
    public ResponseEntity<String> update(@RequestParam("id") String id, @RequestBody VestimentaDtoInsert vestimentaDtoInsert){
        return vestimentaService.update(vestimentaDtoInsert, id);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam String id){
        return vestimentaService.delete(id);
    }
}
