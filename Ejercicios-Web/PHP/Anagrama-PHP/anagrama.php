<?php 
    $palabra1 = strtolower($_GET['palabra1']);
    $palabra2 = strtolower($_GET['palabra2']);
    echo "Palabra 1: $palabra1<br>";
    echo "Palabra 2: $palabra2<br>";
    $trim1 = str_replace(' ', '', $palabra1);
    $trim2 = str_replace(' ', '', $palabra2);
    $letrasDe1 = str_split($trim1);
    $letrasDe2 = str_split($trim2);
    sort($trim1);
    sort($trim2);
    if ($trim1 == $trim2){
        echo "Son anagramas";
    }else{
        echo "No son anagramas";
    }
?>