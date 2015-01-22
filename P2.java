/**
 * Red de Oficinas
 * @author Alejandro Garbi, 08-10398
 * @author David Lilue, 09-10444
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.LinkedList;

public class P2 {

    public static class Node {

        private int id;
        private LinkedList<Node> edges;

        /**
         * Creates a new node with id i.
         * @param i Identifier of the node.
         */
        public Node (int i) {
            this.id = i;
            this.edges = new LinkedList<Node>();
        }

        public boolean addEdge(Node n) {
            if (!this.edges.contains(n)) {
                this.edges.add(n);
                return true;
            }
            return false;
        }

        public boolean removeEdge(Node n) {
            return this.edges.remove((Object) n);
        }

        /**
         * Compare nodes.
         * @param obj Object to compare.
         * @return boolean.
         */
        @Override
        public boolean equals(Object obj) {
            Node node;

            if (obj == null)
                return false;

            if (!(obj instanceof Node))
                return false;

            node = (Node) obj;

            return this.id == node.id;
        }

        /**
         * Prints the node.
         */
        public void printNode() {
            System.out.println("Id: " + this.id);
        }
    }

    public static class Pair {
        
        public int a;
        public int b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static class Graph {

        public int node_size, edge_size, node_count;
        public Pair[] edges;
        public Node[] nodes;
   
        /**
         * Creates a new graph.
         * @param size Number of nodes.
         */
        public Graph(int size) {
            this.node_size = size; 
            this.edge_size = size - 1;
            this.node_count = 0;
            this.nodes = new Node[size];
            this.edges = new Pair[size - 1];
        }
        
        /**
         * Adds a new node to the graph.
         * @param node Node to be added.
         */
        public void add(Node node) {
            if (this.nodes[node.id - 1] == null) {
                this.nodes[node.id - 1] = node;
                this.node_count++;
            }
        }
        
        /**
         * Adds a new edge to the graph.
         * @param edge Edge to be added.
         */
        public void add(int node, Node edge) {
            this.nodes[node - 1].addEdge(edge);
        }

        public void add(int index, int a, int b) {
            this.edges[index - 1] = new Pair(a, b);
        }

        public void remove(int index) {
            this.nodes[this.edges[index].a - 1].removeEdge(new Node(this.edges[index].b));
            this.nodes[this.edges[index].b - 1].removeEdge(new Node(this.edges[index].a));
        }

        public boolean isolateNode() {
            int i = 0;
            while (i < node_count) {
                if (this.nodes[i].edges.size() == 0) {
                    return true;
                }
                i++;
            }
            return false;
        }

        public int disconnectOf() {
            int count = 0, res = 0, rept = 0, trs = 0;

            boolean[] marked = new boolean[node_count];
            Node aux;
            LinkedList<Node> queue = new LinkedList<Node>();
            Object[] edgesQ;

            if (this.isolateNode()) {
                while (count < this.node_count) {
                    if (this.nodes[count].edges.size() == 0) {
                        res = res + (this.node_size - rept);
                        rept++;
                    }
                    count++;
                }
            }
            res = res - rept;

            count = 0;
            //BFS
            while (count < this.node_count) {
                if (this.nodes[count].edges.size() != 0) { break; }
                count++;
            }

            if (count == this.node_count) { return res; }

            queue.add(this.nodes[count]);

            while (!queue.isEmpty()) {
                aux = queue.poll();
                if (marked[aux.id - 1] != true) { 
                    marked[aux.id - 1] = true;
                    count = 0;
                    edgesQ = aux.edges.toArray();
                    while (count < edgesQ.length) {
                        queue.add((Node) edgesQ[count]);
                        count++;
                    }
                }
            }

            count = 0;
            while (count < marked.length) {
                if (marked[count] == true) { trs++; }
                count++;
            }
            return res + (marked.length - rept - trs) * trs;
        }
    }

    public static void main(String[] args)
    {    
        try
            {
                BufferedReader br = new BufferedReader(new FileReader(args[0]));
                String line;
                int size;
                int count = 0;
                int conectionN;
                int n;
                String[] pairs;

                Graph grafo;
                Node nodo1, nodo2;

                int queryN;

                line = br.readLine();
                size = Integer.parseInt(line);

                if (size < 0)
                    {
                        System.out.println("Error: El numero de casos de prueba debe ser positivo.");
                        System.exit(1);
                    }

                while (count < size) {
                    line = br.readLine();
                    line = br.readLine();
                    conectionN = Integer.parseInt(line);
                    n = 1;

                    grafo = new Graph(conectionN);
                    while (n < conectionN)
                        {
                            line = br.readLine();
                            pairs = line.split(" ");
                            nodo1 = new Node(Integer.parseInt(pairs[0]));
                            grafo.add(nodo1);
                            nodo2 = new Node(Integer.parseInt(pairs[1]));
                            grafo.add(nodo2);
                            grafo.add(Integer.parseInt(pairs[0]), nodo2);
                            grafo.add(Integer.parseInt(pairs[1]), nodo1);
                            grafo.add(n, Integer.parseInt(pairs[0]), Integer.parseInt(pairs[1]));
                            n++;
                        }
                    line = br.readLine();

                    n = 0;
                    queryN = Integer.parseInt(line);

                    while (n < queryN)
                        {
                            line = br.readLine();
                            pairs = line.split(" ");
                            if (pairs[0].equals("Q")) {
                                System.out.println(grafo.disconnectOf());
                            } else {
                                grafo.remove(Integer.parseInt(pairs[1]) - 1);
                            }
                            n++;
                        }
                    count++;
                    System.out.println("");
                }
            }
        catch (IOException | NumberFormatException e)
            {
                System.out.println("Error: No se ha podido procesar el archivo.\n");
            }
    }
}
