Proyecto 1
Diseno de Algoritmos CI-5651
Autores:
Alejandro Garbi, 08-10398
David Lilue, 

1) Grafo de Internet:
La idea para solucionar de forma optima el problema consiste en conseguir el Arbol Minimo Cobertor para el grafo. Se inicia con un grafo con N nodos y N componentes conexas, sin arcos. Posteriormente, se crean y almacenan (en una cola de prioridades) todos los posibles arcos con sus respectivos costos-distancias. Finalmente, se itera agregando el arco de menor costo, evitando ciclos y manteniendo el numero de componentes. El algoritmo finaliza cuando |#Componentes Conexas| <= M.

Implementacion y Tiempos:
Algoritmo Prim o Kruskal -> (m log n).
Orden del algoritmo: O(m log n).

3) Scheduling:
La regla voraz (greedy) que conduce a la solucion optima se basa en la siguiente idea:
En primer lugar, se ordenan las actividades de acuerdo a sus tiempos de finalizacion. Se debe aceptar la actividad que termina primero (f(i) minimo). De esta forma, se maximiza el tiempo que resta para satisfacer otros intervalos. Luego se itera aceptando aquellas que cumplan s[i] >= f[m], donde m es el ultimo valor aceptado.

Implementacion y Tiempos:
Ordenamiento de n actividades con MergeSort -> O(n log n) 
Iteracion sobre el arreglo S[1..n] -> O(n)
Orden del algoritmo: O(n).
