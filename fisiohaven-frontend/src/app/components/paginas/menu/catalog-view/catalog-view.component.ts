import { Component, Input, inject, OnInit, OnChanges, SimpleChanges, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CatalogService } from '../../../../services/catalog.service';
import { CartService } from '../../../../services/cart.service';
import { SalesService } from '../../../../services/sales.service';
import { CategoriaResponseDTO, MarcaResponseDTO, ProductoResponseDTO } from '../../../../models/catalog.model';

@Component({
  selector: 'app-catalog-view',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './catalog-view.component.html',
  styleUrls: ['./catalog-view.component.scss']
})
export class CatalogViewComponent implements OnInit, OnChanges {
  @Input() searchTerm = '';

  private catalogService = inject(CatalogService);
  private cartService = inject(CartService);
  private salesService = inject(SalesService);
  private cdr = inject(ChangeDetectorRef);

  productos: ProductoResponseDTO[] = [];
  categorias: CategoriaResponseDTO[] = [];
  marcas: MarcaResponseDTO[] = [];

  selectedCategorias = new Set<number>();
  selectedMarcas = new Set<number>();

  totalProductos = 0;
  private sub: any;

  ngOnInit() {
    this.loadFiltros();
    this.loadProductos();
    this.sub = this.salesService.saleCompleted$.subscribe(() => {
      this.loadProductos();
    });
  }

  ngOnDestroy() {
    if (this.sub) this.sub.unsubscribe();
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['searchTerm']) {
      this.loadProductos();
    }
  }

  loadFiltros() {
    this.catalogService.getCategorias().subscribe({
      next: (res) => {
        this.categorias = res.data;
        this.cdr.detectChanges();
      },
      error: () => console.warn('No se pudieron cargar categorias')
    });
    this.catalogService.getMarcas().subscribe({
      next: (res) => {
        this.marcas = res.data;
        this.cdr.detectChanges();
      },
      error: () => console.warn('No se pudieron cargar marcas')
    });
  }

  loadProductos() {
    const cats = Array.from(this.selectedCategorias);
    const marks = Array.from(this.selectedMarcas);
    this.catalogService.getProductos(this.searchTerm, cats, marks, 0, 100).subscribe({
      next: (res) => {
        this.productos = res.data.content;
        this.totalProductos = res.data.totalElements;
        this.cdr.detectChanges();
      },
      error: () => console.warn('Error cargando productos')
    });
  }

  toggleCategoria(id: number) {
    if (this.selectedCategorias.has(id)) {
      this.selectedCategorias.delete(id);
    } else {
      this.selectedCategorias.add(id);
    }
    this.loadProductos();
  }

  toggleMarca(id: number) {
    if (this.selectedMarcas.has(id)) {
      this.selectedMarcas.delete(id);
    } else {
      this.selectedMarcas.add(id);
    }
    this.loadProductos();
  }

  addToCart(producto: ProductoResponseDTO) {
    if (producto.stock > 0) {
      this.cartService.addToCart(producto);
    }
  }

  getBgColor(producto: ProductoResponseDTO): string {
    const colors = ['bg-blue-100', 'bg-emerald-100', 'bg-red-100', 'bg-purple-100', 'bg-amber-100', 'bg-pink-100', 'bg-sky-100', 'bg-slate-100'];
    return colors[producto.id % colors.length];
  }

  getIconColor(producto: ProductoResponseDTO): string {
    const colors = ['text-blue-500', 'text-emerald-500', 'text-red-500', 'text-purple-500', 'text-amber-500', 'text-pink-500', 'text-sky-500', 'text-slate-500'];
    return colors[producto.id % colors.length];
  }
}
