import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginRequest, RegistroRequest, JwtResponse } from '../models/auth.model';
import { ApiResponse } from '../models/ApiResponse.model';

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private readonly http = inject(HttpClient);
    private readonly apiUrl = 'http://localhost:8080/api/auth';

    registro(registroData: RegistroRequest): Observable<ApiResponse<void>> {
        return this.http.post<ApiResponse<void>>(`${this.apiUrl}/registro`, registroData);
    }

    login(loginData: LoginRequest): Observable<ApiResponse<JwtResponse>> {
        return this.http.post<ApiResponse<JwtResponse>>(`${this.apiUrl}/login`, loginData);
    }
}