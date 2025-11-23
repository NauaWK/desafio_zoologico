import { Component, OnInit } from '@angular/core';
import { AnimaisService } from '../animais.service';
import { Animal } from '../animal';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-animais-list',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './animais-list.component.html',
  styleUrl: './animais-list.component.css'
})
export class AnimaisListComponent implements OnInit{

  animais: Animal[] = [];
  mensagemSucesso: string | null = null;
  mensagemErro: string | null = null;

  //variáveis que guardam dados das rotas GET extras
  searchId: number | null = null;
  searchHabitat: string = '';
  searchEspecie: string = '';

  constructor(private animaisService: AnimaisService) {}

  ngOnInit() {
    this.animaisService.getAnimais().subscribe({
      next: (dados) => this.animais = dados,
      error: (erro) => this.mensagemErro = erro.error.error
    });
  }

  buscarPorId() {
  if (this.searchId) {
    this.animaisService.getAnimalById(this.searchId).subscribe({
      next: (animal) => {
        this.animais = [animal]; //mostra só o encontrado
        this.mensagemSucesso = 'Animal encontrado!';
        this.mensagemErro = null;
      },
      error: (erro) => {
        this.mensagemErro = erro.error.error;
        this.animais = [];
      }
      });
    }
  else{
    this.animaisService.getAnimais().subscribe({
      next: (dados) => this.animais = dados,
      error: (erro) => this.mensagemErro = erro.error.error
      });
    }
  }

  buscarPorFiltro() {
    this.animaisService.getAnimaisByFiltro(this.searchHabitat, this.searchEspecie).subscribe({
      next: (dados) => {
        this.animais = dados;
        this.mensagemSucesso = 'Busca concluída!';
        this.mensagemErro = null;
      },
      error: (erro) => {
        this.mensagemErro = erro.error.error || 'Animal não encontrado!'
        this.animais = [];
      }
    });
  }

  deleteAnimal(id: number){
  this.animaisService.deleteAnimal(id).subscribe({
    next: () => {
      this.mensagemSucesso = 'Animal removido com sucesso!';
      //remove da lista local sem precisar recarregar
      this.animais = this.animais.filter(c => c.id !== id);
    },
    error: (erro) => {
      this.mensagemErro = erro.error.error;
    }
    });
  }

}
