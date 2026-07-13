import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { ApiResponse } from '../models/ApiResponse.model';
import { VentaRequestDTO, VentaResumenDTO, VentaDetalleDTO } from '../models/sales.model';

@Injectable({
  providedIn: 'root'
})
export class SalesService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = 'http://localhost:8080/api/ventas';

  public saleCompleted$ = new Subject<void>();

  procesarVenta(request: VentaRequestDTO): Observable<ApiResponse<any>> {
    return this.http.post<ApiResponse<any>>(this.apiUrl, request);
  }

  getHistorial(): Observable<ApiResponse<VentaResumenDTO[]>> {
    return this.http.get<ApiResponse<VentaResumenDTO[]>>(`${this.apiUrl}/mis-compras`);
  }

  getDetalle(id: number): Observable<ApiResponse<VentaDetalleDTO>> {
    return this.http.get<ApiResponse<VentaDetalleDTO>>(`${this.apiUrl}/${id}`);
  }
}
