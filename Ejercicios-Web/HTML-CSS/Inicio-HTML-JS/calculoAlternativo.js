const alto = parseFloat(prompt("Introduce el alto:"));
const ancho = parseFloat(prompt("Introduce el ancho:"));
const fondo = parseFloat(prompt("Introduce el fondo:"));

// Create a container for the output
const outputContainer = document.createElement('div');
document.body.appendChild(outputContainer);

// Create and append elements for the output
const entradaHeader = document.createElement('h3');
entradaHeader.textContent = "==== ENTRADA ====";
outputContainer.appendChild(entradaHeader);

const altoParaDisplay = document.createElement('p');
altoParaDisplay.textContent = `Alto = ${alto}`;
outputContainer.appendChild(altoParaDisplay);

const anchoParaDisplay = document.createElement('p');
anchoParaDisplay.textContent = `Ancho = ${ancho}`;
outputContainer.appendChild(anchoParaDisplay);

const fondoParaDisplay = document.createElement('p');
fondoParaDisplay.textContent = `Fondo = ${fondo}`;
outputContainer.appendChild(fondoParaDisplay);

const superficie = 2 * (ancho * alto + alto * fondo + ancho * fondo);
const volumen = ancho * alto * fondo;

const superficiePies = superficie * 3.28 * 3.28;
const volumenPies = volumen * 3.28 * 3.28 * 3.28;

const salidaHeader = document.createElement('h3');
salidaHeader.textContent = "==== SALIDA ====";
outputContainer.appendChild(salidaHeader);

const superficieParaDisplay = document.createElement('p');
superficieParaDisplay.textContent = `Superficie = ${superficie.toFixed(2)} (${superficiePies.toFixed(2)} pies)`;
outputContainer.appendChild(superficieParaDisplay);

const volumenParaDisplay = document.createElement('p');
volumenParaDisplay.textContent = `Volumen = ${volumen.toFixed(2)} (${volumenPies.toFixed(2)} pies)`;
outputContainer.appendChild(volumenParaDisplay);
