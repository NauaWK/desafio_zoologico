import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AnimaisListComponent } from './animais/animais-list/animais-list.component';
import { AnimaisFormComponent } from './animais/animais-form/animais-form.component';
import { CuidadosListComponent } from './cuidados/cuidados-list/cuidados-list.component';
import { CuidadosFormComponent } from './cuidados/cuidados-form/cuidados-form.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'animais', component: AnimaisListComponent },
  { path: 'animais/novo', component: AnimaisFormComponent },
  { path: 'animais/editar/:id', component: AnimaisFormComponent },
  { path: 'cuidados', component: CuidadosListComponent },
  { path: 'cuidados/novo', component: CuidadosFormComponent },
  { path: 'cuidados/editar/:id', component: CuidadosFormComponent }
];

