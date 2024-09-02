import { Component, OnInit } from '@angular/core';
import { ReservaService } from 'src/app/services/reserva/reserva.service';

@Component({
  selector: 'app-reservas',
  templateUrl: './reservas.component.html',
  styleUrls: ['./reservas.component.css']
})
export class ReservasComponent implements OnInit{
  imagen='https://galgofutbol.com/cdn/shop/files/rojoblanco_fe5173d5-0be8-462f-bbcf-93269ecccad3.png?v=1694317782&width=700';



  // reserva={
  //   id:'',
  //   idPersona:'',
  //   correoPersona:'',
  //   reservaDtoPreviewItems:[],
  //   fechaEntrega:'',
  //   fechaReserva:'',
  //   entregado:false
  // }

  reservas:any[]=[];

  constructor(
    private reservaService:ReservaService
  ){}

  ngOnInit(): void {
    this.findAllReservas();
  }

  findAllReservas(){
    this.reservaService.findAll().subscribe(
      (reservas:any[])=>{
        
        reservas.forEach((reserva:any)=>{
          reserva.fechaEntrega=reserva.fechaEntrega.split('T')[0];
          reserva.fechaReserva=reserva.fechaReserva.split('T')[0];
          this.reservas.push(reserva);
        });
        this.reservas.sort((a, b) => {
          return new Date(b.fechaEntrega).getTime() - new Date(a.fechaEntrega).getTime();
        });
      },(error)=>{
        console.log(error);
      }
    )
  }
}
