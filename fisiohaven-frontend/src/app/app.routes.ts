import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: 'login',
        loadComponent: () => import('./components/auth/login/login.component').then(m => m.LoginComponent)
    },

    {
        path: 'registro',
        loadComponent: () => import('./components/auth/registro/registro.component').then(m => m.RegistroComponent)
    },

    {
        path: 'menu',
        loadComponent: () => import('./components/paginas/menu/menu.component').then(m => m.MenuComponent)
    },

    {
        path: '',
        redirectTo: 'login',
        pathMatch: 'full'
    },

    {
        path: '**',
        redirectTo: 'login'
    }
];