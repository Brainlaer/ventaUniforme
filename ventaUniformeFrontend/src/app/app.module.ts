import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegistroPersonaComponent } from './pages/registro-persona/registro-persona.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { InicioSessionComponent } from './pages/inicio-session/inicio-session.component';
import { InicioComponent } from './pages/inicio/inicio.component';
import { ResultadoBusquedaComponent } from './pages/resultado-busqueda/resultado-busqueda.component';
import { NavbarComponent } from './pages/navbar/navbar.component';
import { SobreNosotrosComponent } from './pages/sobre-nosotros/sobre-nosotros.component';
import { FooterComponent } from './pages/footer/footer.component';
import { CarritoComponent } from './pages/carrito/carrito.component';
import { VistaVestimentaComponent } from './pages/vista-vestimenta/vista-vestimenta.component';
import { PersonaPerfilComponent } from './pages/persona-perfil/persona-perfil.component';
import { ToastComponent } from './components/toast/toast.component';
import { FormsModule } from '@angular/forms';
import { PerfilComponent } from './pages/perfil/perfil.component';
import { ReservasComponent } from './pages/reservas/reservas.component';

@NgModule({
  declarations: [
    AppComponent,
    RegistroPersonaComponent,
    InicioSessionComponent,
    InicioComponent,
    ResultadoBusquedaComponent,
    NavbarComponent,
    SobreNosotrosComponent,
    FooterComponent,
    CarritoComponent,
    VistaVestimentaComponent,
    PersonaPerfilComponent,
    ToastComponent,
    PerfilComponent,
    ReservasComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
