/*
Lenguaje: .nodeType, .attributes, .childNodes
*/

let salidaHTML = ''; // Almacena el HTML generado
let nivelIndentacion = 0; // Nivel de indentación
let contadorNodos = 0; // Contador de nodos
let espaciosIndentacion = '. . . . . '; // Espacios para la indentación visual

function iniciar() {
    console.log('Iniciando análisis del DOM');
    const nodoRaiz = document.documentElement; // Nodo raíz
    analizarNodo(nodoRaiz);
    salidaHTML += '<br/>Total de nodos: ' + contadorNodos;
    salidaHTML += '<br/>Nota: La funcion de JS escribe dentro del div con id="d1"';
    document.getElementById('d1').innerHTML = salidaHTML;
}

function analizarNodo(nodo) {
    contadorNodos += 1;
    nivelIndentacion += 1;
    formatearEImprimirNodo(nodo); // Formatea e imprime el nodo actual
    const nodosHijos = nodo.childNodes; // Obtener los nodos hijos
    for (const hijo of nodosHijos) {
        salidaHTML += espaciosIndentacion.repeat(nivelIndentacion); // Crear la indentación visual
        analizarNodo(hijo); // Recursión para cada nodo hijo
    }
    nivelIndentacion -= 1;
}

function formatearEImprimirNodo(nodo) {
    // Tipos de nodos
    const tiposDeNodos = [
        '0', 'Elemento', 'Atributo', 'Texto', 'CDATA', 'Referencia de entidad', 
        'Entidad', 'Instrucción de procesamiento', 'Comentario', 'Documento', 
        'Declaración DTD', 'Fragmento de Documento', 'Nota'
    ];
    
    let contenidoNodo = '';
    
    if (nodo.nodeType === 1) { // Si es un nodo de tipo Elemento
        const atributos = nodo.attributes; // Lista de atributos del nodo
        const cantidadAtributos = atributos.length;
        
        for (let i = cantidadAtributos - 1; i >= 0; i--) {
            const atributo = atributos[i];
            contenidoNodo += crearDiv(atributo.name + "=" + atributo.value + ';', 'atributo'); // Nombre y valor de cada atributo
        }
    }
    
    if (nodo.nodeType === 3) { // Si es un nodo de tipo Texto
        if (nodo.data.trim().length <= 2) { // Presumimos que está vacio si su longitud es <= 2
            contenidoNodo += crearDiv('vacio (longitud = ' + nodo.data.length + ')', 'vacio');
        } else {
            contenidoNodo += crearDiv(nodo.data, 'texto');
        }
    }

    salidaHTML += crearDiv(nodo.nodeName + ' (' + tiposDeNodos[nodo.nodeType] + ')', 'elemento') + contenidoNodo + '<br/>'; // Nombre y tipo de nodo
}

function crearDiv(contenido, clase) {
    return '<div class="' + clase + '">' + contenido + '</div>';
}
