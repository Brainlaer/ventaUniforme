import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-toast',
  templateUrl: './toast.component.html',
  styleUrls: ['./toast.component.css']
})
export class ToastComponent {

  @Input() mensaje='';
  @Input() visibilidad='';

  cerrar(){
    this.visibilidad='';
  }

}