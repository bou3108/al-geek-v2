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

  // PROPRIETES :
  filteredProducts : Product[];
  products : Product[];
  stocks : Stock[];
  errorMessage : string;

  _listFilterByName : string;
  _listFilterByCategory : string;
  _listFilterByBrand : string;

  pageTitle : string = 'Product List';
  imageWidth: number = 50;
  imageMargin: number = 50;

  // CONSTRUCTEUR :
  constructor(private productApi: ProductApiService,
    private stockApi: StockApiService,
    private router : Router) { }

  // GESTION DES FILTRES :
  get listFilterByName() : string {
        return this._listFilterByName;
  }
  set listFilterByName(value : string) {
        this._listFilterByName = value;
        this.filteredProducts = this._listFilterByName ? this.performFilter(this.listFilterByName) : this.products;
  }
  get listFilterByCategory() : string {
    return this._listFilterByCategory;
  }
  set listFilterByCategory(value : string) {
    this._listFilterByCategory = value;
    this.filteredProducts = this._listFilterByCategory ? this.performFilter(this.listFilterByCategory) : this.products;
  }
  get listFilterByBrand() : string {
    return this._listFilterByBrand;
  }
  set listFilterByBrand(value : string) {
    this._listFilterByBrand = value;
    this.filteredProducts = this._listFilterByBrand ? this.performFilter(this.listFilterByBrand) : this.products;
  }

  performFilter(filter : string) : any[] {
    return this.products.filter( (product : Product ) =>
    product.nom.toLocaleLowerCase().lastIndexOf(filter) !== -1 );
  }

  

  // INITIALISATION DU COMPONENT :
  ngOnInit(): void {
    this.productApi.getProducts().subscribe({
      next:products => {
        this.products = products;
        
        this.filteredProducts = this.products;
      },
      error: err => this.errorMessage = err
    }); 
  }

  // REDIRECTION VERS PRODUCT-DETAIL
  goToDetails(productId: number) {
    this.router.navigateByUrl('/product-detail/' + productId);
  }

  onEUR() : void {

  }

  onUSD() : void {

  }

  onGBP() : void {

  }

  onJPY() : void {
    
  }
}
