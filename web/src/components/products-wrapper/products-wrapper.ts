import {Component, inject, OnInit, signal} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Products} from '../products/products';
import {FormsModule} from '@angular/forms';

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
  templateUrl: './products-wrapper.html'
})
export class ProductsWrapper implements OnInit {
  private http = inject(HttpClient);

  products = signal<IProduct[]>([]);
  nameFragment = '';

  dateFrom = '';
  dateTo = '';

  ngOnInit(): void {
    this.loadProducts();
  }

  // TODO: dodać debounce
  loadProducts(): void {
    let params = new HttpParams()
      .set('nameFragment', this.nameFragment)
      .set('sortBy', 'name')
      .set('sortDirection', 'asc');

    if (this.dateFrom) {
      params = params.set('dateFrom', this.dateFrom);
    }

    if (this.dateTo) {
      params = params.set('dateTo', this.dateTo);
    }

    this.http.get<IProduct[]>('http://localhost:8080/controller', {params})
      .subscribe({
        next: (data) => {
          this.products.set(data);
        },
        error: (err) => {
          console.error('Błąd pobierania produktów', err);
        },
      });
  }
}
