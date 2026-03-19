import {Component, input, OnInit} from '@angular/core';
import {IProduct} from "../products-wrapper/products-wrapper";
import {Product} from "../product/product";

@Component({
  selector: 'app-products',
  imports: [Product],
  standalone: true,//todo: cot to?
  templateUrl: './products.html',
  styleUrl: './products.css',
})
export class Products {
  products = input.required<IProduct[]>();



}
