/*
var nombre = "Jesus Garay"
var altura = 185;

var concat = nombre + " " + altura

var datos = document.getElementById("datos");
datos.innerHTML = `
    <h1> Soy la caja de datos </h1>
    <h2> Mi nombre es: ${nombre}</h2>
    <h3> Mido: ${altura} cm</h3>
`;

if(altura >= 180){
    datos.innerHTML +='<h1>Eres una persona alta</h1>';
}else{
    datos.innerHTML += '<h1>Eres una persona baja</h1>';
}

for(var i = 0; i<= 2020; i++){
    datos.innerHTML += "<h2>Estamos en el año: " + i>
}
*/
function MuestraMiNombre(nombre, altura){
    var misDatos = `
        <h1> Soy la caja de datos </h1>
        <h2> Mi nombre es: ${nombre}</h2>
        <h3> Mido: ${altura} cm</h3>
    `;
    return misDatos;
}
function imprimir(){
    var datos = document.getElementById("datos");
    datos.innerHTML = MuestraMiNombre("Jesús Garay", 187);
}
imprimir();

var nombres = ['Victor', 'Antonio', 'Joaquin'];

document.write('<h1>Listado de nombres</h1>');

/*
for(i = 0; i < nombres.length; i++){
    document.write(nombres[i] + '<br/>');
 */
nombres.forEach((nombre) =>{
    document.write(nombre + '<br/>')
});   
