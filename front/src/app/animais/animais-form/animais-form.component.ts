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
    data_nascimento: '',
    especie: '',
    habitat: '',
    pais_origem: ''
  };

  //objeto que guarda todos os campos com erro do JSON que vem do backend (múltiplos campos)
  mensagensErro: { [campo: string]: string } = {};

  //mensagem geral de erro
  mensagemErro: string | null = null;

  mensagemSucesso: string | null = null;
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
      this.animaisService.getAnimalById(+id).subscribe(dados => this.animal = dados);
    }
  }

  //ao enviar o form, faz a verificação se é uma edição ou cadastro, mudando o método chamado no service
  onSubmit() {
    if (this.editMode) {
      this.animaisService.updateAnimal(this.animal.id!, this.animal).subscribe({
        next: () => {
          this.mensagemSucesso = 'Animal atualizado com sucesso!';
          this.mensagensErro = {}; //limpa os erros
        },
        error: (erro) => {
          this.mensagemErro = erro.error.error; // mensagem geral
          this.mensagensErro = erro.error;      // guarda todos os campos do form que contém erro
        }
      });
    } else {
      this.animaisService.createAnimal(this.animal).subscribe({
        next: () => {
          this.mensagemSucesso = 'Animal cadastrado com sucesso!';
          this.mensagensErro = {}; 
        },
        error: (erro) => {
          this.mensagemErro = erro.error.error; 
          this.mensagensErro = erro.error;      
        }
      });
    }
  }

}
