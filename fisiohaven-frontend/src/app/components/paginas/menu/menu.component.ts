import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

import { CatalogViewComponent } from './catalog-view/catalog-view.component';
import { HistoryViewComponent } from './history-view/history-view.component';
import { CartDrawerComponent } from './cart-drawer/cart-drawer.component';
import { FormsModule } from '@angular/forms';
import { TokenStorageService } from '../../../services/token.service';
import { CartService } from '../../../services/cart.service';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [CommonModule, CatalogViewComponent, HistoryViewComponent, CartDrawerComponent, FormsModule],
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent {
  readonly tokenService = inject(TokenStorageService);
  readonly cartService = inject(CartService);
  readonly router = inject(Router);

  currentView = signal<'catalog' | 'history'>('catalog');
  cartOpen = signal(false);
  searchTerm = signal('');

  logout() {
    this.tokenService.signOut();
    this.router.navigate(['/login']);
  }
}
