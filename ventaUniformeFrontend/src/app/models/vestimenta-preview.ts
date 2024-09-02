import { ITalla } from "./talla";

export interface IVestimentaResultado{
    id:string
    imagen:string;
    titulo:string;
    descripcion:string;
    costo:number
    unidades:number
    categoria:string;
    tallaList:ITalla[];
}