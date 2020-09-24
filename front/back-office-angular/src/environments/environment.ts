
export const environment = {
  production: false,

  // url vers l'api gateway de aws
  // dans un premier temps, url vers le manager en local pour tester le front
  customerApiUrl: 'http://localhost:8380/manager/customer',
  
  orderApiUrl: 'http://localhost:8280/manager/order',

  productApiUrl: 'http://localhost:8180/manager/product',

  // stockApiUrl: 'http://15.236.212.75:5000/api/stock'
  stockApiUrl: 'http://127.0.0.1:5000/api/stock'
};

