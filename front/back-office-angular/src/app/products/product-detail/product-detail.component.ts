import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductApiService } from 'src/app/api/product-api.service';
import { StockApiService } from 'src/app/api/stock-api.service';
import { Product } from 'src/app/models/product.model';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  product : Product;
  errorMessage : string;

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
  }

  onBack() : void {
    this.router.navigate(['/product-list']);
  }

}
