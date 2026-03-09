interface Superhero {
  name: string;
  age: number;
  address: Address;
  showAddress: () => string;
}

interface Address {
    street: string;
    city: string;
    country: string;
}

const superHero: Superhero = {
  name: 'Spiderman',
  age: 30,
  address:{
    street: 'Main St',
    city: 'New York',
    country: 'USA'
  },
  showAddress() {
    return this.name + ', ' + this.address.city + ', ' + this.address.country;
  }
};

const address = superHero.showAddress();
console.log(address);

export {}