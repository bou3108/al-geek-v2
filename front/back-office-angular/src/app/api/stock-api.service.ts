import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Stock } from '../models/stock.model';


// URL for API
const STOCK_API_URL = environment.stockApiUrl;

@Injectable({
  providedIn: 'root'
})
export class StockApiService {

  constructor(private http: HttpClient) { }


  getStockById(productId: number): Observable<Stock>{
    const headers = new HttpHeaders().set("Content-Type", "application/json");
    return this.http.get<Stock>(STOCK_API_URL + "/" + productId, {headers});
  }

  getStocks() : Observable<Stock[]>{
    const headers = new HttpHeaders().set("Content-Type", "application/json");
    return this.http.get<Stock[]>(STOCK_API_URL, {headers}).pipe(catchError(this.handleError));
  }




  private handleError(error: Response | any) {
    return Observable.throw(error);
  }
}
