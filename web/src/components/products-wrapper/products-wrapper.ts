import { Component, inject, OnInit, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Products } from '../products/products';
import { FormsModule } from '@angular/forms';

export interface IProduct {
  id: number;
  name: string;
  bookingDate: string;
  priceUSD: number;
  pricePLN: number;
}

@Component({
  selector: 'app-products-wrapper',
  standalone: true,
  imports: [Products, FormsModule],
  templateUrl: './products-wrapper.html',
  styleUrl: './products-wrapper.css',
})
export class ProductsWrapper implements OnInit {
  private http = inject(HttpClient);

  products = signal<IProduct[]>([]);
  nameFragment = '';

  ngOnInit(): void {
    this.loadProducts();
  }

  // TODO: dodac debounce
  loadProducts(): void {
    this.http.get<IProduct[]>('http://localhost:8080/controller', {
      params: {
        nameFragment: this.nameFragment,
        sortBy: 'name',
        sortDirection: 'asc',
      },
    }).subscribe({
      next: (data) => {
        this.products.set(data);
      },
      error: (err) => {
        console.error('Błąd pobierania produktów', err);
      },
    });
  }
}
