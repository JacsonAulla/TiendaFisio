import { Injectable } from '@angular/core';

const TOKEN_KEY = 'auth-token';

@Injectable({
    providedIn: 'root'
})
export class TokenStorageService {

    signOut(): void {
        globalThis.localStorage.clear();
    }

    public saveToken(token: string): void {
        globalThis.localStorage.removeItem(TOKEN_KEY);
        globalThis.localStorage.setItem(TOKEN_KEY, token);
    }

    public getToken(): string | null {
        return globalThis.localStorage.getItem(TOKEN_KEY);
    }

    public isLoggedIn(): boolean {
        return !!this.getToken();
    }
}