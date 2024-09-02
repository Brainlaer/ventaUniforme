import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { IVestimentaResultado } from 'src/app/models/vestimenta-preview';
import { VestimentaService } from 'src/app/services/vestimenta/vestimenta.service';

@Component({
  selector: 'app-resultado-busqueda',
  templateUrl: './resultado-busqueda.component.html',
  styleUrls: ['./resultado-busqueda.component.css']
})
export class ResultadoBusquedaComponent implements OnInit{

  busqueda:string='';
  vestimentaList:IVestimentaResultado[]=[];
  filtro:string='';

  constructor(
    private activatedRoute:ActivatedRoute,
    private vestimentaService:VestimentaService,
    private router:Router
  ){}

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(
      params=>{
        this.busqueda=params['busqueda'];
        this.filtro=params['vestimenta']
        this.buscar();

      }
      
    );
  }

  buscar(){
    this.vestimentaService.findBySearch(this.busqueda).subscribe(
      (vestimentas:any)=>{
        if(!this.filtro){
          this.vestimentaList=vestimentas;
        }else if(this.filtro){
          this.buscarByCategoria();
        }
        console.log(vestimentas);
      },(error)=>{
        console.log(error);
      }
    )
  }
  buscarByCategoria(){
    this.vestimentaService.findByCategoria(this.filtro).subscribe(
      (vestimentas:any)=>{
        if(!this.busqueda){
          this.vestimentaList=vestimentas;
        }
        console.log(vestimentas);
      },(error)=>{
        console.log(error);
      }
    )
  }

  irVistaVestimenta(id:string){
    this.router.navigate(['/vista-vestimenta'],{queryParams:{id:id}})
  }
  

  searchByCamisa(){
    this.router.navigate(['/resultado-busqueda'],{queryParams:{busqueda:this.busqueda,vestimenta:'camisas'}});
    if(this.busqueda&&this.filtro){
      this.vestimentaService.findBySearch(this.busqueda).subscribe(
        (vestimentas: any) => {
            this.vestimentaList = vestimentas.filter((vestimenta: any) => {
                return vestimenta?.categoria === 'camisas'; 
            });
        },
        (error) => {
            console.log(error);
        }
    );
    }else{
      this.buscarByCategoria();
    }

  }
  searchByPantaloneta(){
    this.router.navigate(['/resultado-busqueda'],{queryParams:{busqueda:this.busqueda,vestimenta:'pantalonetas'}})
    if(this.busqueda&&this.filtro){
      this.vestimentaService.findBySearch(this.busqueda).subscribe(
        (vestimentas: any) => {
            this.vestimentaList = vestimentas.filter((vestimenta: any) => {
                return vestimenta?.categoria === 'pantalonetas';
            });
        },
        (error) => {
            console.log(error);
        }
    );
    }else{
      this.buscarByCategoria();
    }
  }
  searchByzapatilla(){
    this.router.navigate(['/resultado-busqueda'],{queryParams:{busqueda:this.busqueda,vestimenta:'zapatillas'}});
    if(this.filtro&&this.busqueda){
      this.vestimentaService.findBySearch(this.busqueda).subscribe(
        (vestimentas: any) => {
            this.vestimentaList = vestimentas.filter((vestimenta: any) => {
                return vestimenta?.categoria === 'zapatillas';
            });
        },
        (error) => {
            console.log(error);
        }
    );
    }else{
      this.buscarByCategoria();
    }

  }  searchTodo(){
    this.router.navigate(['/resultado-busqueda'],{queryParams:{busqueda:this.busqueda}})
  }

}
