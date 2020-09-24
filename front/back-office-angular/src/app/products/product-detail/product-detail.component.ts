import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { stringify } from 'querystring';
import { ProductApiService } from 'src/app/api/product-api.service';
import { StockApiService } from 'src/app/api/stock-api.service';
import { Product } from 'src/app/models/product.model';
import { Stock } from 'src/app/models/stock.model';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  product : Product;
  modifiedProduct : Product;
  stock : Stock;
  errorMessage : string;
  isFormVisible : boolean = false;
  isBtnModifyVisible : boolean = true;
  isDataAvailable:boolean = false;

  constructor(private route : ActivatedRoute,
              private router : Router,
              private productService : ProductApiService,
              private stockService : StockApiService) { }

  ngOnInit(): void {

    let id = +this.route.snapshot.paramMap.get("id");

    this.productService.getProductById(id).subscribe({
      next: product => {
        this.product = product;
      },
      error: err => this.errorMessage = err
    });

    this.stockService.getStockById(id).subscribe({
      next: data => {
        this.stock = data;
        this.isDataAvailable = true;
      },
      error: err => {
        console.log("ERROR IN METHOD");
        console.log(err)
      }
    });
    
  }

  onBack() : void {
    this.router.navigate(['/product-list']);
  }

  onModify() : void {
    this.isFormVisible = true;
    this.isBtnModifyVisible = false;
  }

  onCancelModifications() : void {
    this.modifiedProduct = null;
    this.isFormVisible = false;
  }

  onUpdateProduct() : void {
    // ICI APPEL A SERVICE + UPDATE PRODUCT
    this.modifiedProduct = null;
    this.isFormVisible = false;
  }

}
