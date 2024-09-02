package com.christian.ventauniforme.service;

import com.christian.ventauniforme.dto.ReservaDto;
import com.christian.ventauniforme.dto.ReservaDtoPreview;
import com.christian.ventauniforme.dto.ReservaDtoPreviewItem;
import com.christian.ventauniforme.model.*;
import com.christian.ventauniforme.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private TallaRepository tallaRepository;

    @Autowired
    private VestimentaRepository vestimentaRepository;

    @Autowired
    private ReservaItemRepository reservaItemRepository;

    @Autowired
    private PersonaRepository personaRepository;


    ModelMapper modelMapper=new ModelMapper();

    public ResponseEntity<String> insert(ReservaDto reservaDto){
        Reserva reserva =modelMapper.map(reservaDto, Reserva.class);
        Reserva reservaSaved = reservaRepository.save(reserva);

        for(Talla talla : reservaDto.getTallaDtoList()){
            ReservaItem reservaItem=new ReservaItem();
            reservaItem.setIdReserva(reservaSaved);
            Optional<Talla> optionalTalla = tallaRepository.findById(talla.getId());
            Talla tallaGotten=optionalTalla.get();
            if(optionalTalla.isPresent()){
                Optional<Vestimenta> optionalVestimenta=vestimentaRepository.findById(tallaGotten.getVestimentaId());
                if (optionalVestimenta.isPresent()){
                    Vestimenta vestimenta = optionalVestimenta.get();
                    reservaItem.setImagen(vestimenta.getImagen());
                    reservaItem.setTitulo(vestimenta.getTitulo());
                    reservaItem.setTalla(talla.getTalla());
                    reservaItem.setUnidades(talla.getUnidades());
                    reservaItem.setCosto(vestimenta.getCosto());
                    reservaItem.setIdReserva(reservaSaved);
                    reservaItemRepository.save(reservaItem);
                }
                Talla tallaFound=optionalTalla.get();
                tallaFound.setUnidades(tallaFound.getUnidades()-talla.getUnidades());
                tallaRepository.save(tallaFound);

            }
        }

        return new ResponseEntity<>("Reporte creado", HttpStatus.OK);
    }

    public ResponseEntity<List<ReservaDtoPreview>> findAll(){
        List<ReservaDtoPreview> reservaDtoPreviewList=new ArrayList<>();
        List<Reserva> reservas = reservaRepository.findAll();
        for (Reserva reserva:reservas){
            Persona persona=personaRepository.findById(reserva.getIdPersona()).orElse(null);
            List<ReservaDtoPreviewItem> reservaDtoPreviewItems=new ArrayList<>();
            ReservaDtoPreview reservaDtoPreview=modelMapper.map(reserva,ReservaDtoPreview.class);
            reservaDtoPreview.setCorreoPersona(persona.getCorreo());
            List<ReservaItem> reservaItems = reservaItemRepository.findByidReserva(reserva);
            for (ReservaItem reservaItem:reservaItems){
                ReservaDtoPreviewItem reservaDtoPreviewItem=modelMapper.map(reservaItem,ReservaDtoPreviewItem.class);
                reservaDtoPreviewItems.add(reservaDtoPreviewItem);
            }
            reservaDtoPreview.setReservaDtoPreviewItems(reservaDtoPreviewItems);
            reservaDtoPreviewList.add(reservaDtoPreview);
        }
        return new ResponseEntity<>(reservaDtoPreviewList,HttpStatus.OK);
    }
}
