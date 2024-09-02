import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { VestimentaModel } from 'src/app/models/vestimenta';
import { IVestimentaResultado } from 'src/app/models/vestimenta-preview';
import { CarritoService } from 'src/app/services/carrito/carrito.service';
import { VestimentaService } from 'src/app/services/vestimenta/vestimenta.service';

@Component({
  selector: 'app-vista-vestimenta',
  templateUrl: './vista-vestimenta.component.html',
  styleUrls: ['./vista-vestimenta.component.css']
})
export class VistaVestimentaComponent implements OnInit{
  alertMensaje!:string;
  id:string='';
  vestimenta:any;
  talla:any='Tallas';
  

  constructor(
    private activatedRoute:ActivatedRoute,
    private vestimentaService:VestimentaService,
    private carritoService:CarritoService
  ){}

  ngOnInit(): void {
    this.getId();
  }

  getId(){
    this.activatedRoute.queryParams.subscribe(
      params=>{
        this.id=params['id'];
        this.findById();
      }
    )
  }
  addVestimenta(vestimenta:any){
    this.carritoService.addVestimenta(vestimenta);
  }

  findById(){
    this.vestimentaService.findById(this.id).subscribe(
      (vestimentaData:any)=>{
        this.vestimenta=vestimentaData;
      },(error)=>{
        console.log(error);
      }
    )
  }
  
  addToCarrito(vestimenta:any){
    if(sessionStorage.getItem('isLoggin')){
      let tallaFound=vestimenta.tallaList.find((talla:any)=>talla.talla==this.talla);
      vestimenta.talla=tallaFound;
      console.log(tallaFound);
      this.addVestimenta(vestimenta);
    }else{
      this.alertMensaje='Por favor, primero inicia sessión'
    }
  }

}
