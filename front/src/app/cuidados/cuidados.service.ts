import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cuidado } from './cuidado';

@Injectable({
  providedIn: 'root'
})
export class CuidadosService {
  private apiUrl = 'http://localhost:8080/api/cuidados';

  constructor(private http: HttpClient) { }

  getCuidados(){
    return this.http.get<Cuidado[]>(this.apiUrl);
  }
  
  getCuidadoById(id: number){
    return this.http.get<Cuidado>(`${this.apiUrl}/${id}`);
  }

  createCuidado(cuidado: Cuidado){
    return this.http.post(this.apiUrl, cuidado);
  }

  updateCuidado(id: number, cuidado: Cuidado){
    return this.http.put(`${this.apiUrl}/${id}`, cuidado);
  }

  deleteCuidado(id: number){
    return this.http.delete(`${this.apiUrl}/${id}`);
  }

}
