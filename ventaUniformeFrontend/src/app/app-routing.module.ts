import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistroPersonaComponent } from './pages/registro-persona/registro-persona.component';
import { InicioSessionComponent } from './pages/inicio-session/inicio-session.component';
import { InicioComponent } from './pages/inicio/inicio.component';
import { ResultadoBusquedaComponent } from './pages/resultado-busqueda/resultado-busqueda.component';
import { SobreNosotrosComponent } from './pages/sobre-nosotros/sobre-nosotros.component';
import { VistaVestimentaComponent } from './pages/vista-vestimenta/vista-vestimenta.component';
import { PerfilComponent } from './pages/perfil/perfil.component';
import { ReservasComponent } from './pages/reservas/reservas.component';

const routes: Routes = [
  {
    path:"inicio",
    component:InicioComponent
  },{
    path:"registro",
    component:RegistroPersonaComponent
  },{
    path:"inicio-session",
    component:InicioSessionComponent
  },{
    path:"resultado-busqueda",
    component:ResultadoBusquedaComponent
  },{
    path:"sobre-nosotros",
    component:SobreNosotrosComponent
  },{
    path:"vista-vestimenta",
    component:VistaVestimentaComponent
  },{
    path:"perfil",
    component:PerfilComponent
  },{
    path:"reservas",
    component:ReservasComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
