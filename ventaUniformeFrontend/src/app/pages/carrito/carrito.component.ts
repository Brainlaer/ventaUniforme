import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/services/auth/auth.service';
import { CarritoService } from 'src/app/services/carrito/carrito.service';
import { PersonaService } from 'src/app/services/persona.service';
import { ReservaService } from 'src/app/services/reserva/reserva.service';

@Component({
  selector: 'app-carrito',
  templateUrl: './carrito.component.html',
  styleUrls: ['./carrito.component.css']
})
export class CarritoComponent implements OnInit {

  hasDate:boolean=true;
  hasVestimentas:boolean=true;
  isReservado:boolean=false;

  constructor(
    private carritoService:CarritoService,
    private personaService:PersonaService,
    private reservaService:ReservaService,
    private router:Router,
    private cdRef: ChangeDetectorRef

  ){
  }

  ngOnInit(): void {
    this.costo$ = this.carritoService.getTotal();
  }

  fechaEntrega!:Date;
  vestimentaList:any[]=this.carritoService.getVestimentas();
  costo$:Observable<number>=new Observable();

  removeVestimenta(vestimentaItem:any){
    this.carritoService.removeVestimenta(vestimentaItem);
  }



  async reservar(){
    this.isReservado=false;
    this.hasDate=true;
    this.hasVestimentas=true;
    console.log(sessionStorage.getItem('token'));
    let total:any;
    await this.costo$.subscribe((totalGotten:number)=>(total=totalGotten));
    let persona:string;
    let tallaDtoList:any[]=[];
     await this.personaService.findByCorreo(String(sessionStorage.getItem('correoPersona'))).subscribe(
      (personaGotten:any)=>{
        persona=personaGotten.id;
        console.log(persona);
        if(persona!=null){
          let reserva:any={
            idPersona:persona,
            tallaDtoList:tallaDtoList,
            total:total,
            fechaEntrega:this.fechaEntrega
          }
          
          this.vestimentaList.forEach((item:any)=>{
            item.vestimenta.talla.vestimentaId=item.vestimenta.id;
            item.vestimenta.talla.unidades=item.vestimenta.unidadesVestimenta;
            tallaDtoList.push(item.vestimenta.talla);
          })
          console.log(reserva);
          if(!reserva?.fechaEntrega){
            this.hasDate=false;
          }
          else if(reserva?.tallaDtoList.length==0){
            this.hasVestimentas=false;
          }else{
            this.reservaService.reservar(reserva).subscribe(
               (response:any)=>{
                console.log( this.isReservado);
              },(error)=>{
                if(error.status==403){
                  sessionStorage.removeItem('token');
                  sessionStorage.removeItem('isLoggin');
                  this.router.navigateByUrl('/inicio-session');
                }else if(error.status==200){
                  this.isReservado=true;
                  this.carritoService.resetCarrito();
                  this.vestimentaList=[],
                  this.costo$.subscribe((costo)=>{costo=0});
                  this.fechaEntrega= new Date('');
                }
                console.log(error);
              }
            );
          }
          

        }


      }
    )
    
  }




}
