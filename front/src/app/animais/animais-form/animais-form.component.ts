import { Component, OnInit } from '@angular/core';
import { AnimaisService } from '../animais.service';
import { Animal } from '../animal';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-animais-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './animais-form.component.html',
  styleUrl: './animais-form.component.css'
})
export class AnimaisFormComponent implements OnInit {
  animal: Animal = {
    nome: '',
    descricao: '',
    dataNascimento: '',
    especie: '',
    habitat: '',
    paisOrigem: ''
  };

  mensagemSucesso: string | null = null;
  mensagemErro: string | null = null;

  editMode = false;

  constructor(
    private animaisService: AnimaisService,
    private route: ActivatedRoute
  ) {}

  //detecta se existe um ID na rota, indicando se é uma edição ou cadastro
  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.editMode = true;
      this.animaisService.getAnimalById(+id).subscribe({
        next: (dados) => this.animal = dados
      });
    }
  }

  //ao enviar o form, faz a verificação se é uma edição ou cadastro, mudando o método chamado no service
  onSubmit() {
    if (this.editMode) {
      this.animaisService.updateAnimal(this.animal.id!, this.animal).subscribe({
        next: () => alert('Animal atualizado com sucesso!')
      });
    } else {
      this.animaisService.createAnimal(this.animal).subscribe({
        next: () => alert('Animal cadastrado com sucesso!')
      });
    }
  }
}
