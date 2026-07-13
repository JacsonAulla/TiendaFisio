import { Component, Input, OnInit, OnChanges, SimpleChanges, inject, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InvoiceModalComponent } from '../invoice-modal/invoice-modal.component';
import { SalesService } from '../../../../services/sales.service';
import { VentaResumenDTO } from '../../../../models/sales.model';

@Component({
  selector: 'app-history-view',
  standalone: true,
  imports: [CommonModule, InvoiceModalComponent],
  templateUrl: './history-view.component.html',
  styleUrls: ['./history-view.component.scss']
})
export class HistoryViewComponent implements OnInit, OnChanges {
  @Input() searchTerm = '';

  private salesService = inject(SalesService);
  private cdr = inject(ChangeDetectorRef);

  ventas: VentaResumenDTO[] = [];
  filteredVentas: VentaResumenDTO[] = [];

  loading = true;
  totalRevenue = 0;
  completedSales = 0;

  selectedVentaId: number | null = null;

  private sub: any;

  ngOnInit() {
    this.loadHistorial();
    this.sub = this.salesService.saleCompleted$.subscribe(() => {
      this.loadHistorial();
    });
  }

  ngOnDestroy() {
    if (this.sub) this.sub.unsubscribe();
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['searchTerm']) {
      this.filterVentas();
    }
  }

  loadHistorial() {
    this.loading = true;
    this.salesService.getHistorial().subscribe({
      next: (res) => {
        this.ventas = res.data || [];
        this.filterVentas();
        this.calculateStats();
        this.loading = false;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error("Backend error details:", err.error);
        this.loading = false;
        this.cdr.detectChanges();
      }
    });
  }

  filterVentas() {
    if (!this.searchTerm) {
      this.filteredVentas = [...this.ventas];
    } else {
      const term = this.searchTerm.toLowerCase();
      this.filteredVentas = this.ventas.filter(v =>
        v.numeroBoleta.toLowerCase().includes(term) ||
        (v.paciente && v.paciente.toLowerCase().includes(term))
      );
    }
  }

  calculateStats() {
    this.totalRevenue = this.ventas
      .filter(v => v.estado === 'COMPLETADA' || v.estado === 'COMPLETADO' || v.estado === 'COMPLETADA ') // account for spacing issues if any
      .reduce((sum, v) => sum + v.total, 0);

    this.completedSales = this.ventas
      .filter(v => v.estado === 'COMPLETADA' || v.estado === 'COMPLETADO').length;
  }

  openInvoice(id: number) {
    this.selectedVentaId = id;
  }

  closeInvoice() {
    this.selectedVentaId = null;
  }
}
