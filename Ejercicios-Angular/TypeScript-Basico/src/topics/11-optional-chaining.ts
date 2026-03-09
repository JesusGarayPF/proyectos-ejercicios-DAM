export interface Passenger{
    name:string;
    children?:string[];
}

const passenger1:Passenger = {
    name:'Jesus'
}
const passenger2:Passenger = {
    name:'Paco',
    children:['Sara','Laura']
}
const passenger3:Passenger = {
    name:'Sanz',
    children:['Juan']
}

const printchildren = (passenger:Passenger): void => {
    const howManyChildren = passenger.children?.length || 0;
    console.log(passenger.name, howManyChildren);
}
printchildren(passenger1);   
printchildren(passenger2);