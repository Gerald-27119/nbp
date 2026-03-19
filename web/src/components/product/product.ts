import {Component, input} from '@angular/core';
import {IProduct} from '../products-wrapper/products-wrapper';

@Component({
  selector: 'app-product',
  imports: [],
  standalone: true,
  templateUrl: './product.html',
  styleUrl: './product.css',
})
export class Product {
  product = input.required<IProduct>();
}
