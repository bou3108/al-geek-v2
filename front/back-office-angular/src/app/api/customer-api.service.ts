import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';


// URL for api
const CUSTOMER_API_URL = environment.customerApiUrl;

@Injectable({
  providedIn: 'root'
})
export class CustomerApiService {

  constructor(private http: HttpClient) { }

  // TO DO :
  // implémenter en-dessous les méthodes dont tu as besoin (celles qui vont attaquer les services exposés par Customer-manager,
  // puis par le gateway/customer quand on aura fini l'intégration)
}
