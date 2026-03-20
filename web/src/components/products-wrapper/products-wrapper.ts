import {Component, inject, OnInit, signal} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Products} from '../products/products';
import {FormsModule} from '@angular/forms';

export interface IProductsPage {
  products: IProduct[],
  totalElements: number,
  hasNext: boolean
  hasPrevious: boolean
  number: number
}

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
  sortBy = 'bookingDate';
  sortDirection = 'desc';
  dateFrom = '';
  dateTo = '';
  totalElements = 0;
  pageNumber = 0;
  hasNext = false;
  hasPrevious = false;

  ngOnInit(): void {
    this.loadProducts(this.pageNumber);
  }

  handlePrevious(): void {
    this.loadProducts(this.pageNumber - 1)
  }

  handleNext(): void {
    this.loadProducts(this.pageNumber + 1)
  }

  // TODO: dodać debounce
  loadProducts(newPageNumber: number = 0): void {
    let params = new HttpParams()
      .set('page', newPageNumber)
      .set('nameFragment', this.nameFragment)
      .set('sortBy', this.sortBy)
      .set('sortDirection', this.sortDirection);

    if (this.dateFrom) {
      params = params.set('dateFrom', this.dateFrom);
    }

    if (this.dateTo) {
      params = params.set('dateTo', this.dateTo);
    }

    this.http.get<IProductsPage>('http://localhost:8080/controller', {params})
      .subscribe({
        next: (data) => {
          this.products.set(data.products);
          this.totalElements = data.totalElements;
          this.pageNumber = data.number;
          this.hasNext = data.hasNext;
          this.hasPrevious = data.hasPrevious;
        },
        error: (err) => {
          console.error('Błąd pobierania produktów', err);
        },
      });
  }
}
