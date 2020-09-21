import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';


// URL for api
const ORDER_API_URL = environment.orderApiUrl;

@Injectable({
  providedIn: 'root'
})
export class OrderApiService {

  constructor(private http: HttpClient) { }

  // TO DO :
  // implémenter en-dessous les méthodes dont tu as besoin (celles qui vont attaquer les services exposés par Order-manager,
  // puis par le gateway/customer quand on aura fini l'intégration)
}
