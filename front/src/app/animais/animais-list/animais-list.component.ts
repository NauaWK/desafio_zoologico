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

  constructor(private animaisService: AnimaisService) {}

  ngOnInit() {
    this.animaisService.getAnimais().subscribe({
      next: (dados) => this.animais = dados,
      error: (err) => this.mensagemErro = err.error.message
    });
  }

  deleteAnimal(id: number){
    this.animaisService.deleteAnimal(id);
  }

}
