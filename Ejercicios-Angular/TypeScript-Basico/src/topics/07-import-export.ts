import type { Product } from './06-functions-destructuring';
import { taxCalculation } from './06-functions-destructuring';
const shoppingCart: Product[] = [
    { description: 'Nokia A1', price: 100 },
    { description: 'iPad Air', price: 150 },
];
const [total, tax] = taxCalculation({ products: shoppingCart, tax: 0.15 });
console.log('Total:', total);
console.log('Tax:', tax);