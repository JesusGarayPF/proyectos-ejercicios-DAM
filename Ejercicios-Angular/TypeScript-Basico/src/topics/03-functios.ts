function addNumbers(a:number, b:number):number {
   return a + b;
}
addNumbers(1,2);
const addNumbersArrow = (a:number, b:number):string => {
    return `${a + b}`;
}

function multiply(firstNumber:number, secondNumber?:number, base:number = 2):number {
    return firstNumber * base;
}

/*const result:number = addNumbers(1,2);
const result2:string = addNumbersArrow(1,2);
const multiplyResult:number = multiply(5);
console.log(result, result2, multiplyResult);*/

interface Character {
    name: string;
    hp: number;
    showHp: () => void;
}

const strider: Character = {
    name: 'Strider',
    hp: 50,
    showHp() {
        console.log(`Puntos de vida ${this.hp}`);
    }
};

const healCharacter = (character: Character, amount:number):void => {
    character.hp += amount;
}
healCharacter(strider, 10);
strider.showHp();
healCharacter(strider, 20);
strider.showHp();

export {}