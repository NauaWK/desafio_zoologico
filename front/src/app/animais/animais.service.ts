import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Animal } from './animal';

@Injectable({
  providedIn: 'root'
})
export class AnimaisService {
  private apiUrl = 'http://localhost:8080/api/animais';

  constructor(private http: HttpClient) { }

  getAnimais(){
    return this.http.get<Animal[]>(this.apiUrl);
  }

  getAnimalById(id: number){
    return this.http.get<Animal>(`${this.apiUrl}/${id}`)
  }

  createAnimal(animal: Animal){
    return this.http.post(this.apiUrl, animal);
  }

  updateAnimal(id: number, animal: Animal){
    return this.http.put(`${this.apiUrl}/${id}`, animal);
  }

  deleteAnimal(id: number){
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
  
}
