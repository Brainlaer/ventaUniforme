package com.christian.ventauniforme.controller;

import com.christian.ventauniforme.dto.ReservaDto;
import com.christian.ventauniforme.dto.ReservaDtoPreview;
import com.christian.ventauniforme.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/reserva")
@CrossOrigin("*")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping("/insert")
    public ResponseEntity<String> insert(@RequestBody ReservaDto reservaDto){
        return reservaService.insert(reservaDto);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ReservaDtoPreview>> findAll(){
        return reservaService.findAll();
    }

}
