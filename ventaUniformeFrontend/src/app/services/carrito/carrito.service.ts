import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { IVestimentaResultado } from 'src/app/models/vestimenta-preview';

@Injectable({
  providedIn: 'root'
})
export class CarritoService {

  constructor() { }

  private vestimentas: any[] = [];
  private costo: BehaviorSubject<number> = new BehaviorSubject<number>(0);

  getVestimentas() {
    return this.vestimentas;

  }
  getTotal() {
    return this.costo.asObservable();
  }

  addVestimenta(vestimenta: any) {
    // Busca una vestimenta existente que coincida con el ID y la talla
    let vestimentaExistente = this.vestimentas.find(item => 
      item.vestimenta.id === vestimenta.id && 
      item.vestimenta.talla === vestimenta.talla
    );

    if (vestimentaExistente) {
      // Si ya existe una vestimenta con la misma ID y talla, solo incrementa las unidades
      vestimentaExistente.vestimenta.unidadesVestimenta += 1;
    } else {
      // Si no existe, agrega una nueva entrada al carrito
      let vestimentaItem = {
        id: this.vestimentas.length + 1,
        vestimenta: { ...vestimenta, unidadesVestimenta: 1 }
      };
      this.vestimentas.push(vestimentaItem);
    }
    this.costo.next(this.costo.value + Number(vestimenta.costo));

  }
  resetCarrito(){
    this.vestimentas=[];
    this.costo.next(0);
  }
  removeVestimenta(vestimentaItem: any) {
    if (vestimentaItem.vestimenta.unidadesVestimenta > 1) {
      vestimentaItem.vestimenta.unidadesVestimenta--;
    } else {
      const index = this.vestimentas.indexOf(vestimentaItem);
      if (index > -1) {
        this.vestimentas.splice(index, 1);
      }

    }
    this.costo.next(this.costo.value - Number(vestimentaItem.vestimenta.costo));

  }
  countVestimentas(vestimentaList: any): boolean {
    if (vestimentaList.length > 0) {
      return true;
    } else {
      return false;
    }
  }
}
