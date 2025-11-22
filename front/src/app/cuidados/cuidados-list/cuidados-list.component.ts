import { Component, OnInit } from '@angular/core';
import { Cuidado } from '../cuidado';
import { CuidadosService } from '../cuidados.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-cuidados-list',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './cuidados-list.component.html',
  styleUrl: './cuidados-list.component.css'
})
export class CuidadosListComponent implements OnInit {

  cuidados: Cuidado[] = [];
  mensagemSucesso: string | null = null;
  mensagemErro: string | null = null;

  constructor(private cuidadosService: CuidadosService){}

  ngOnInit() {
    this.cuidadosService.getCuidados().subscribe({
      next: (dados) => this.cuidados = dados,
      error: (err) => this.mensagemErro = err.error.message
    });
  }

  deleteCuidado(id: number){
    this.cuidadosService.deleteCuidado(id);
  }

}
