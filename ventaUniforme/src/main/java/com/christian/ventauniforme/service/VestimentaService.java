package com.christian.ventauniforme.service;

import com.christian.ventauniforme.dto.TallaDto;
import com.christian.ventauniforme.dto.VestimentaDto;
import com.christian.ventauniforme.dto.VestimentaDtoGet;
import com.christian.ventauniforme.dto.VestimentaDtoInsert;
import com.christian.ventauniforme.model.Talla;
import com.christian.ventauniforme.model.Vestimenta;
import com.christian.ventauniforme.repository.TallaRepository;
import com.christian.ventauniforme.repository.VestimentaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class VestimentaService{

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private VestimentaRepository vestimentaRepository;

    @Autowired
    private TallaRepository tallaRepository;

    public ResponseEntity<String> insert(VestimentaDto vestimentaDto, List<TallaDto> tallaDtoList){
        if(vestimentaDto!=null){
            Vestimenta vestimenta = modelMapper.map(vestimentaDto, Vestimenta.class);
            Vestimenta vestimenta1=vestimentaRepository.save(vestimenta);

            System.out.println("vestimentaDto = " + vestimentaDto.toString() + ", tallaDtoList = " + tallaDtoList.toString());

            for(TallaDto tallaDto:tallaDtoList){
                Talla talla=modelMapper.map(tallaDto, Talla.class);
                talla.setVestimentaId(vestimenta1.getId());
                vestimenta1.setFixUnidades(tallaDto.getUnidades());
                tallaRepository.save(talla);
            }
            vestimentaRepository.save(vestimenta1);
            return new ResponseEntity<>("Vestimenta insertada satisfactoriamente", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("BAD_REQUEST" ,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<VestimentaDtoGet>findById(String id){
        Optional<Vestimenta> optionalVestimenta=vestimentaRepository.findById(id);
        if(optionalVestimenta.isPresent()){
            Vestimenta vestimenta = optionalVestimenta.get();
            List<TallaDto> tallaDtoList=new ArrayList<>();
            List<Talla> tallaList = tallaRepository.findByVestimentaId(id);
            for(Talla talla:tallaList){
                TallaDto tallaDto = new TallaDto(talla.getId(),talla.getTalla(),talla.getUnidades());
                tallaDtoList.add(tallaDto);
            }
            VestimentaDtoGet vestimentaDtoGet=new VestimentaDtoGet(vestimenta.getId(),vestimenta.getImagen(), vestimenta.getTitulo(), vestimenta.getDescripcion(), vestimenta.getCosto(), vestimenta.getUnidades(), vestimenta.getCategoria(), tallaDtoList);
            return new ResponseEntity<>(vestimentaDtoGet, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<VestimentaDtoGet>> findByCategoria(String categoria){
        if(categoria!=null){
            List<VestimentaDtoGet> vestimentaDtoGetList = new ArrayList<>();
            List<Vestimenta> vestimentaList=vestimentaRepository.findByCategoria(categoria);

            for (Vestimenta vestimenta:vestimentaList){
                List<TallaDto> tallaDtoList=new ArrayList<>();
                List<Talla> tallaList = tallaRepository.findByVestimentaId(vestimenta.getId());
                for(Talla talla:tallaList){
                    TallaDto tallaDto = new TallaDto(talla.getId(), talla.getTalla(),talla.getUnidades());
                    tallaDtoList.add(tallaDto);
                }
                VestimentaDtoGet vestimentaDtoGet=new VestimentaDtoGet(vestimenta.getId(),vestimenta.getImagen(), vestimenta.getTitulo(), vestimenta.getDescripcion(), vestimenta.getCosto(), vestimenta.getUnidades(), vestimenta.getCategoria(), tallaDtoList);

                vestimentaDtoGetList.add(vestimentaDtoGet);
            }
            if(!vestimentaList.isEmpty()){
                return new ResponseEntity<>(vestimentaDtoGetList, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }else{
            return null;
        }
    }
    public ResponseEntity<List<VestimentaDtoGet>> findBySearch(String search){
        if(search!=null){
            List<VestimentaDtoGet> vestimentaDtoGetList = new ArrayList<>();
            List<Vestimenta> vestimentaList=vestimentaRepository.findByTituloContainingIgnoreCaseOrDescripcionContainingIgnoreCaseOrIdContainingIgnoreCase(search,search,search);

            for (Vestimenta vestimenta:vestimentaList){
                List<TallaDto> tallaDtoList=new ArrayList<>();
                List<Talla> tallaList = tallaRepository.findByVestimentaId(vestimenta.getId());
                for(Talla talla:tallaList){
                    TallaDto tallaDto = new TallaDto(talla.getId(), talla.getTalla(),talla.getUnidades());
                    tallaDtoList.add(tallaDto);
                }
                VestimentaDtoGet vestimentaDtoGet=new VestimentaDtoGet(vestimenta.getId(),vestimenta.getImagen(), vestimenta.getTitulo(), vestimenta.getDescripcion(), vestimenta.getCosto(), vestimenta.getUnidades(), vestimenta.getCategoria(), tallaDtoList);

                vestimentaDtoGetList.add(vestimentaDtoGet);
            }
            if(!vestimentaList.isEmpty()){
                return new ResponseEntity<>(vestimentaDtoGetList, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }else{
            return null;
        }
    }

    public ResponseEntity<List<VestimentaDtoGet>> findTheMostRecents(){

        if(vestimentaRepository.count()>0){
            List<VestimentaDtoGet> vestimentaDtoGetList = new ArrayList<>();
            List<Vestimenta> vestimentaList=vestimentaRepository.findTop10ByOrderByFechaInsertadoDesc();

            for (Vestimenta vestimenta:vestimentaList){
                List<TallaDto> tallaDtoList=new ArrayList<>();
                List<Talla> tallaList = tallaRepository.findByVestimentaId(vestimenta.getId());
                for(Talla talla:tallaList){
                    TallaDto tallaDto = new TallaDto(talla.getId(), talla.getTalla(),talla.getUnidades());
                    tallaDtoList.add(tallaDto);
                }
                VestimentaDtoGet vestimentaDtoGet=new VestimentaDtoGet(vestimenta.getId(),vestimenta.getImagen(), vestimenta.getTitulo(), vestimenta.getDescripcion(), vestimenta.getCosto(), vestimenta.getUnidades(), vestimenta.getCategoria(), tallaDtoList);

                vestimentaDtoGetList.add(vestimentaDtoGet);
            }
            if(!vestimentaList.isEmpty()){
                return new ResponseEntity<>(vestimentaDtoGetList, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }else{
            return null;
        }
    }

    public ResponseEntity<String> update(VestimentaDtoInsert vestimentaDtoInsert, String id){
        Optional<Vestimenta> optionalVestimenta = vestimentaRepository.findById(id);
        List<Talla> tallaList = tallaRepository.findByVestimentaId(id);
        for(Talla talla:tallaList){
            tallaRepository.delete(talla);
        }
        for(TallaDto tallaDto:vestimentaDtoInsert.getTallaDtoList()){
            Talla talla = new Talla();
            talla.setTalla(tallaDto.getTalla());
            talla.setUnidades(tallaDto.getUnidades());
            talla.setVestimentaId(id);
            tallaRepository.save(talla);
        }

        if(optionalVestimenta.isPresent()){
            Vestimenta vestimenta = optionalVestimenta.get();
            int unidadesTalla=0;

            if(vestimenta.getTitulo()!=vestimentaDtoInsert.getVestimentaDto().getTitulo()){
                vestimenta.setTitulo(vestimentaDtoInsert.getVestimentaDto().getTitulo());
            }if(vestimenta.getImagen()!=vestimentaDtoInsert.getVestimentaDto().getImagen()){
                vestimenta.setImagen(vestimentaDtoInsert.getVestimentaDto().getImagen());
            }if(vestimenta.getCosto()!= vestimentaDtoInsert.getVestimentaDto().getCosto()){
                vestimenta.setCosto(vestimentaDtoInsert.getVestimentaDto().getCosto());
            }if(vestimenta.getDescripcion()!= vestimentaDtoInsert.getVestimentaDto().getDescripcion()){
                vestimenta.setDescripcion(vestimentaDtoInsert.getVestimentaDto().getDescripcion());
            }if(vestimenta.getUnidades()!= unidadesTalla){
                vestimenta.setUnidades(unidadesTalla);
            }if(vestimenta.getCategoria()!= vestimentaDtoInsert.getVestimentaDto().getCategoria()){
                vestimenta.setCategoria(vestimentaDtoInsert.getVestimentaDto().getCategoria());
            }


            vestimentaRepository.save(vestimenta);

            return new ResponseEntity<>("Vestimenta actualizada", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("VESTIMENTA_NO_ENCONTRADA", HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<String> delete(String id){
        if(vestimentaRepository.existsById(id)){
            vestimentaRepository.deleteById(id);
            List<Talla> tallaList = tallaRepository.findByVestimentaId(id);
            for(Talla talla:tallaList){
                tallaRepository.delete(talla);
            }
            return new ResponseEntity<>("Vestimenta eliminada", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("VESTIMENTA_NO_ENCONTRADA", HttpStatus.NOT_FOUND);
        }
    }
}
