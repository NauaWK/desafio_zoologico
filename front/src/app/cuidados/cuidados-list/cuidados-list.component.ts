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
  searchId: number | null = null;

  constructor(private cuidadosService: CuidadosService){}

  ngOnInit() {
    this.cuidadosService.getCuidados().subscribe({
      next: (dados) => this.cuidados = dados,
      error: (erro) => this.mensagemErro = erro.error.message
    });
  }

  buscarPorId() {
  if (this.searchId) {
    this.cuidadosService.getCuidadoById(this.searchId).subscribe({
      next: (cuidado) => {
        this.cuidados = [cuidado];
        this.mensagemSucesso = 'Cuidado encontrado!';
        this.mensagemErro = null;
      },
      error: (erro) => {
        this.mensagemErro = erro.error.error || 'Cuidado não encontrado';
        this.cuidados = [];
      }
    });
    }
    else{
      this.cuidadosService.getCuidados().subscribe({
      next: (dados) => this.cuidados = dados,
      error: (erro) => this.mensagemErro = erro.error.message
     });
    }
  }

  deleteCuidado(id: number){
  this.cuidadosService.deleteCuidado(id).subscribe({
    next: () => {
      this.mensagemSucesso = 'Cuidado removido com sucesso!';
      //remove da lista local sem precisar recarregar
      this.cuidados = this.cuidados.filter(c => c.id !== id);
    },
    error: (erro) => {
      this.mensagemErro = erro.error.error;
    }
    });
  }

}
