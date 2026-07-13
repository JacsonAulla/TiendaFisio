import { Component, Input, Output, EventEmitter, inject, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SalesService } from '../../../../services/sales.service';
import { VentaDetalleDTO } from '../../../../models/sales.model';


@Component({
  selector: 'app-invoice-modal',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './invoice-modal.component.html',
  styleUrls: ['./invoice-modal.component.scss']
})
export class InvoiceModalComponent implements OnInit {
  @Input() ventaId!: number;
  @Output() close = new EventEmitter<void>();

  private salesService = inject(SalesService);
  private cdr = inject(ChangeDetectorRef);

  detalle: VentaDetalleDTO | null = null;
  loading = true;
  error = '';

  ngOnInit() {
    this.loadDetalle();
  }

  loadDetalle() {
    this.loading = true;
    this.salesService.getDetalle(this.ventaId).subscribe({
      next: (res) => {
        this.detalle = res.data;
        this.loading = false;
        this.cdr.detectChanges();
      },
      error: (err) => {
        this.error = 'No se pudo cargar el detalle de la factura';
        this.loading = false;
        this.cdr.detectChanges();
      }
    });
  }

  printReceipt() {
    window.print();
  }
}
