export const environment = {
  production: true,

  // url vers l'api gateway de aws
  // dans un premier temps, url vers le manager en local pour tester le front
  customerApiUrl: 'http://localhost:8280/manager/customer',
  
  orderApiUrl: 'http://localhost:8380/manager/order',

  productApiUrl: 'http://localhost:8180/manager/product',

  stockApiUrl: 'http://localhost:5000/api/stock'
};
