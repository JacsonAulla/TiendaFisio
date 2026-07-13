export interface LoginRequest {
    username: string;
    password: string;
}

export interface RegistroRequest {
    username: string;
    password: string;
}

export interface JwtResponse {
    token: string;
    username: string;
    rol: string;
}