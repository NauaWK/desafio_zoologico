import { Component, OnInit } from '@angular/core';
import { Cuidado } from '../cuidado';
import { Animal } from '../../animais/animal';
import { CuidadosService } from '../cuidados.service';
import { AnimaisService } from '../../animais/animais.service';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-cuidados-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './cuidados-form.component.html',
  styleUrl: './cuidados-form.component.css'
})
export class CuidadosFormComponent implements OnInit {
  cuidado: Cuidado = {
    nomeCuidado: '',
    descricao: '',
    frequencia: '',
    animal_id: undefined
  };

  mensagemSucesso: string | null = null;
  mensagemErro: string | null = null;
  animais: Animal[] = [];
  editMode = false;

  constructor(
    private cuidadosService: CuidadosService,
    private animaisService: AnimaisService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    //carrega lista de animais para o select
    this.animaisService.getAnimais().subscribe(dados => this.animais = dados);

    //verificar se é edição (igual no AnimaisForm)
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.editMode = true;
      this.cuidadosService.getCuidadoById(+id).subscribe(dados => this.cuidado = dados);
    }
  }

  onSubmit() {
    if (this.editMode) {
      this.cuidadosService.updateCuidado(this.cuidado.id!, this.cuidado).subscribe({
        next: () => alert('Cuidado atualizado com sucesso!')
      });
    } else {
      this.cuidadosService.createCuidado(this.cuidado).subscribe({
        next: () => alert('Cuidado cadastrado com sucesso!')
      });
    }
  }
}
