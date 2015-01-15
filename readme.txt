Proyecto 1
Diseno de Algoritmos CI-5651
Autores:
Alejandro Garbi, 08-10398
David Lilue, 

1) Grafo de Internet:
La idea para solucionar de forma optima el problema consiste en conseguir el Arbol Minimo Cobertor para el grafo. Se inicia con un grafo con N nodos y N componentes conexas, sin arcos. Posteriormente, se crean y almacenan (en una cola de prioridades) todos los posibles arcos con sus respectivos costos-distancias. Finalmente, se itera agregando el arco de menor costo, evitando ciclos y verificando el numero de componentes conexas. El algoritmo finaliza cuando |#Componentes Conexas| <= M o cuando no hay mas arcos que agregar.
Se utiliza una estructura de arreglos basada en punteros para mantener los conjuntos disjuntos. Operaciones:
- Find -> O(log n).
- Join/Union -> O(1).
- makeSet -> O(1).

Pseudocodigo:
  Utilizar una cola de prioridades Q para mantener las aristas ordenadas por costo.
  Para cada vertice en G, crear una componente conexa (makeSet).
  Sea T un nuevo arbol sin arcos, con los mismos nodos de G.
  Mientras Q no este vacia o |CC| <= M:
    Elegir un arco i ∈ Q con menor costo.
    Si C(i.src) ≠ C(i.dst):
      Agregar el arco a T (ya que no forma un ciclo).
      Hacer merge de los conjuntos de ambos nodos.
  FinMientras.
  Se calculan los costos para cada arco en T, segun la distancia del arco.
  El resultado del algoritmo corresponde a los costos total_ucost y total_vcost.

Implementacion y Tiempos:
Adicion y ordenamiento de k arcos en la cola de prioridades -> O(k log n).
makeSet para cada nodo -> O(n).
Algoritmo de Kruskal (Join-Find) -> O(k log n).
Calculo de costos -> O(k).
Orden del algoritmo: O(k log n).

3) Scheduling:
La regla voraz (greedy) que conduce a la solucion optima se basa en la siguiente idea:
En primer lugar, se ordenan las actividades de acuerdo a sus tiempos de finalizacion. Se debe aceptar la actividad que termina primero (f(i) minimo). De esta forma, se maximiza el tiempo que resta para satisfacer otros intervalos. Luego se itera aceptando aquellas que cumplan s[i] >= f[x], donde x es el ultimo valor aceptado.

Pseudocodigo:
  Sea R el conjunto de todas las actividades. 
  R debe ordenarse de forma ascendente segun f[i] (se usa MergeSort).
  Seleccionar para el conjunto A al primer elemento de R.
  Sea x el valor f[i] del elemento seleccionado.
  Mientras R no este vacio:
    Elegir una actividad i ∈ R con menor f[i].
    Si s[i] >= x:
      Agregar i al conjunto A
      x <- f[i]
  FinMientras.
  El resultado del algoritmo es |A|.

Implementacion y Tiempos:
Ordenamiento de n actividades con MergeSort -> O(n log n) 
Iteracion sobre el arreglo S[1..n] -> O(n)
Orden del algoritmo: O(n log n).
