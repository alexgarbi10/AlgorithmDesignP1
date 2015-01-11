Proyecto 1
Diseno de Algoritmos CI-5651
Autores:
Alejandro Garbi, 08-10398
David Lillue, 

3) Scheduling:
La regla voraz (greedy) que conduce a la solucion optima se basa en la siguiente idea:
Se debe aceptar primero la actividad que termina primero (f(i) minimo). De esta manera
se maximiza el tiempo que resta para satisfacer otros intervalos. Posteriormente, se 
itera aceptando aquellas que cumplan: s[i] >= f[m], donde m es el ultimo valor aceptado.

Implementacion y Tiempos:
Ordenamiento de n actividades con MergeSort -> O(n log n) 
Iteracion sobre el arreglo S[1..n] -> O(n)
Orden del algoritmo: O(n log n)