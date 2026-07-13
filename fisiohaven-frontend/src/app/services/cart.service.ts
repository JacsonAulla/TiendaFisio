import { Injectable, signal, computed } from '@angular/core';
import { ProductoResponseDTO } from '../models/catalog.model';

export interface CartItem {
  producto: ProductoResponseDTO;
  cantidad: number;
}

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private cartItems = signal<CartItem[]>([]);

  // Derived state
  items = this.cartItems.asReadonly();
  
  itemCount = computed(() => {
    return this.cartItems().reduce((total, item) => total + item.cantidad, 0);
  });

  total = computed(() => {
    return this.cartItems().reduce((total, item) => total + (item.producto.precio * item.cantidad), 0);
  });

  addToCart(producto: ProductoResponseDTO) {
    this.cartItems.update(items => {
      const existingItem = items.find(i => i.producto.id === producto.id);
      if (existingItem) {
        return items.map(i => i.producto.id === producto.id 
          ? { ...i, cantidad: i.cantidad + 1 } 
          : i);
      } else {
        return [...items, { producto, cantidad: 1 }];
      }
    });
  }

  removeFromCart(productoId: number) {
    this.cartItems.update(items => items.filter(i => i.producto.id !== productoId));
  }

  updateQuantity(productoId: number, change: number) {
    this.cartItems.update(items => {
      return items.map(i => {
        if (i.producto.id === productoId) {
          const newCantidad = Math.max(1, i.cantidad + change);
          return { ...i, cantidad: newCantidad };
        }
        return i;
      });
    });
  }

  clearCart() {
    this.cartItems.set([]);
  }
}
