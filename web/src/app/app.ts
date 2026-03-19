import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {Products} from '../components/products/products';
import {ProductsWrapper} from '../components/products-wrapper/products-wrapper';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ProductsWrapper],
  templateUrl: './app.html'
})
export class App {
}
