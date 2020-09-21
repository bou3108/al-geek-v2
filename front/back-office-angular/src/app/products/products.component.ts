import { Component, OnInit } from '@angular/core';
import { ProductApiService } from '../api/product-api.service';
import { StockApiService } from '../api/stock-api.service';
import { Product } from './../models/product.model';
import { Stock } from './../models/stock.model';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  constructor(private productApi: ProductApiService,
              private stockApi: StockApiService) { }

  ngOnInit(): void {
    this.getProducts();
  }

  _products: Product[] = [];

  getProducts() {
    this.productApi.getProducts().subscribe(
      (data) => {this._products = data}
    );
  }

}
