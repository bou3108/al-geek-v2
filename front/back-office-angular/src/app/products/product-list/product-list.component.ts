import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductApiService } from '../../api/product-api.service';
import { StockApiService } from '../../api/stock-api.service';
import { Product } from './../../models/product.model';
import { Stock } from './../../models/stock.model';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  pageTitle : string = 'Product List';
  imageWidth: number = 50;
  imageMargin: number = 50;
  _listFilter : string;

  get listFilter() : string {
        return this._listFilter;
  }

  set listFilter(value : string) {
        this._listFilter = value;
        this.filteredProducts = this._listFilter ? this.performFilter(this.listFilter) : this.products;
  }

  filteredProducts : Product[];
  products : Product[];
  errorMessage : string;

  performFilter(filter : string) : any[] {
    // En argument je récupère le filtre inséré dans l'input text
    // je vais filtrer mon tableau products
    // et je vais garder de products que les produits dont le nom en minuscule
    // contient le caractère de mon filtre
    // return this.products.filter( (product : any ) =>
    // (<string>product.productName).toLocaleLowerCase().lastIndexOf(filter) !== -1 );
    return this.products.filter( (product : Product ) =>
    product.nom.toLocaleLowerCase().lastIndexOf(filter) !== -1 );
  }

  constructor(private productApi: ProductApiService,
              private stockApi: StockApiService,
              private router : Router) { }

  ngOnInit(): void {
    this.productApi.getProducts().subscribe({
      next:products => {
        this.products = products;
        this.filteredProducts = this.products;
      },
      error: err => this.errorMessage = err
    }); 
  }


  getProducts() {
    this.productApi.getProducts().subscribe(
      (data) => {this.products = data}
    );
  }

  getImageUrl(product: Product): string {
    console.log("src/assets/" + product.photo);
    return "src/assets/" + product.photo;
}

  goToDetails(productId: number) {
    this.router.navigateByUrl('/product-detail/' + productId);
  }
}
