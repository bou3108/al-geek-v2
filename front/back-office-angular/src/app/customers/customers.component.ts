import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {

  _listFilter : string;

  get listFilter() : string {
    return this._listFilter;
  }

  set listFilter(value : string) {
    this._listFilter = value;
    this.filteredCustomers = this._listFilter 
    ? this.performFilter(this.listFilter) : this.customers;
  }

  filteredCustomers : ICustomers[];
  
  performFilter();

  constructor() { }

  ngOnInit(): void {
  }

 
}
