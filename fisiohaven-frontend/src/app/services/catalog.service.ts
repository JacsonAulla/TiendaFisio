import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiResponse } from '../models/ApiResponse.model';
import { ProductoResponseDTO, CategoriaResponseDTO, MarcaResponseDTO, Page } from '../models/catalog.model';

@Injectable({
  providedIn: 'root'
})
export class CatalogService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = 'http://localhost:8080/api';

  getProductos(nombre?: string, categoriasId?: number[], marcasId?: number[], page: number = 0, size: number = 10): Observable<ApiResponse<Page<ProductoResponseDTO>>> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    if (nombre) {
      params = params.set('nombre', nombre);
    }
    
    if (categoriasId && categoriasId.length > 0) {
      params = params.set('categoriasId', categoriasId.join(','));
    }

    if (marcasId && marcasId.length > 0) {
      params = params.set('marcasId', marcasId.join(','));
    }

    return this.http.get<ApiResponse<Page<ProductoResponseDTO>>>(`${this.apiUrl}/productos`, { params });
  }

  getCategorias(): Observable<ApiResponse<CategoriaResponseDTO[]>> {
    return this.http.get<ApiResponse<CategoriaResponseDTO[]>>(`${this.apiUrl}/categorias`);
  }

  getMarcas(): Observable<ApiResponse<MarcaResponseDTO[]>> {
    return this.http.get<ApiResponse<MarcaResponseDTO[]>>(`${this.apiUrl}/marcas`);
  }
}
