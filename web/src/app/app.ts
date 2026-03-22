import {Component} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {ProductsWrapper} from '../components/products-wrapper/products-wrapper';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ProductsWrapper],
  templateUrl: './app.html'
})
export class App {
}
