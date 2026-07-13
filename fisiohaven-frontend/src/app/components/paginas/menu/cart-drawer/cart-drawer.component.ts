import { Component, Output, EventEmitter, inject, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CartService } from '../../../../services/cart.service';
import { SalesService } from '../../../../services/sales.service';
import { DetalleVentaRequestDTO, VentaRequestDTO } from '../../../../models/sales.model';


@Component({
  selector: 'app-cart-drawer',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './cart-drawer.component.html',
  styleUrls: ['./cart-drawer.component.scss']
})
export class CartDrawerComponent {
  @Output() close = new EventEmitter<void>();

  cartService = inject(CartService);
  private salesService = inject(SalesService);
  private cdr = inject(ChangeDetectorRef);

  isProcessing = false;
  successMessage = '';
  errorMessage = '';

  increaseQuantity(productoId: number) {
    this.cartService.updateQuantity(productoId, 1);
  }

  decreaseQuantity(productoId: number) {
    this.cartService.updateQuantity(productoId, -1);
  }

  removeItem(productoId: number) {
    this.cartService.removeFromCart(productoId);
  }

  processSale() {
    if (this.cartService.itemCount() === 0) return;

    this.isProcessing = true;
    this.errorMessage = '';
    this.successMessage = '';

    const items: DetalleVentaRequestDTO[] = this.cartService.items().map(i => ({
      idProducto: i.producto.id,
      cantidad: i.cantidad
    }));

    const request: VentaRequestDTO = { items };

    this.salesService.procesarVenta(request).subscribe({
      next: (res) => {
        this.successMessage = res.message || 'Venta procesada con éxito';
        this.cartService.clearCart();
        this.salesService.saleCompleted$.next();
        this.isProcessing = false;
        this.cdr.detectChanges();
        setTimeout(() => this.close.emit(), 2000);
      },
      error: (err) => {
        this.errorMessage = err.error?.message || 'Error al procesar la venta';
        this.isProcessing = false;
        this.cdr.detectChanges();
      }
    });
  }
}
