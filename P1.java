/**
 * Grafo de Internet
 * @author Alejandro Garbi, 08-10398
 * @author David Lilue,
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class P1 {
    
    public static class Node {

	public int id = -1;
        public int x = -1;
        public int y = -1;

	/**
	 * Creates a new node with id i.
         * @param i Identifier of the node.
         * @param x Coordinate of the x-axe.
         * @param y Coordinate of the y-axe.
	 */
	public Node (int i, int x, int y) {
            this.id = i;
            this.x = x;
            this.y = y;
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
         * String representation of the node.
         * @return String.
         */
	@Override
	public String toString() {
            return Integer.toString(this.id);
	}

	/**
	 * Prints the node.
	 */
	public void printNode() {
            System.out.println("Id: "+this.id+" ("+this.x+", "+this.y+")");
	}
    }
    
    public static class Edge {
  
        public final int src;
        public final int dst;
        public final double cost;
        
        public Edge(int src, int dst, double cost) {
            this.src = src;
            this.dst = dst;
            this.cost = cost;
        }
        
        /**
         * Compare edges.
         * @param obj Object to compare.
         * @return boolean.
         */
        @Override
        public boolean equals(Object obj) {
            Edge edge;
       
            if (obj == null) {
                return false;
            }
        
            if (!(obj instanceof Edge)) {
                return false;
            }

            edge = (Edge) obj;

            return (this.dst == edge.dst && this.src == edge.src) ||
                    (this.dst == edge.src && this.src == edge.dst);
        }
    
        /**
         * String representation of the edge.
         * @return String.
         */
        @Override
        public String toString() {
            return "(" + Integer.toString(src) + ", " + Integer.toString(dst) +")";
        }
        
        /**
         * Prints the edge.
         */
        public void printEdge() {
            System.out.println("Origen: "+this.src+" Destino: "+this.dst+" Costo: "+this.cost);
        }
    }
    
    public static class EdgeComparator implements Comparator<Edge>
    {

        /**
         * Compare two edges.
         * @param x Edge to compare.
         * @param y Edge to compare.
         * @return integer.
         */
        @Override
        public int compare(Edge x, Edge y)
        {
            if (x.cost == y.cost)
                return 0;
            else if (x.cost > y.cost)        
                return 1;
            else
                return -1;
        }
    }
    
    public static class Graph {

        private int node_size, edge_size; 
        private Node nodes[];
        private PriorityQueue<Edge> edges;
   
        /**
         * Creates a new graph.
         * @param size Number of nodes.
         */
        public Graph(int size) {
            this.edge_size = 0;
            this.node_size = 0; 
            this.nodes = new Node[size];
            Comparator<Edge> comparator = new EdgeComparator();
            this.edges = new PriorityQueue<Edge>(10,comparator);
            
        }
        
        /**
         * Adds a new node to the graph.
         * @param node Node to be added.
         */
        public void add(Node node) {
            this.nodes[node.id] = node;
        }
        
        /**
         * Adds a new edge to the graph.
         * @param edge Edge to be added.
         */
        public void add(Edge edge) {
            if (!this.edges.contains(edge)) {
                this.edges.add(edge);
            }    
        }
        
        /**
         * Calculates the distance between two points.
         * @param n1 Point 1.
         * @param n2 Point 2.
         * @return double.
         */
        public double distance(Node n1, Node n2) {
            double x = Math.abs(n1.x - n2.x);
            double y = Math.abs(n1.y - n2.y);
            double dist = Math.sqrt(x*x + y*y);
            return dist;
        }
        
        /**
         * Calculates all posible edges.
         */
        public void createEdges() {
            for (Node element : this.nodes) {
                for (Node other : this.nodes) {
                    if (!(element.equals(other))) {
                        double dist = distance(element, other);
                        Edge edge = new Edge(element.id,other.id,dist);
                        this.add(edge);
                    }
                }
            }
        }
        
        /**
         * Prints the graph.
         */
        public void printGraph() {
            for (Node element : this.nodes) {
                element.printNode();
            }
         
            for (Edge aux : this.edges) {
                aux.printEdge();
            }
        }
    }

    /**
     * Main method.
     * @param args the command line arguments.
     */
    public static void main(String[] args) 
    {

        // Check arguments
        if (args.length < 1)
        {
            System.out.println("Error: Debe indicar el nombre del archivo.");
            System.exit(1);
        }
        
        try 
        {
            // Parse instance
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            String line;
            int size;
            int count = 0;
            
            line = br.readLine();
            size = Integer.parseInt(line);
            
            if (size < 0)
            {
                System.out.println("Error: El numero de casos de prueba debe ser positivo.");
                System.exit(1);
            }
            
            int[] result = new int[size];
            
            // Solve every instance
            while (count < size)
            {
                line = br.readLine();
                String[] sf = line.split(" ");
                int n = Integer.parseInt(sf[0]);
                int r = Integer.parseInt(sf[1]);
                int m = Integer.parseInt(sf[2]);
                int u = Integer.parseInt(sf[3]);
                int v = Integer.parseInt(sf[4]);
                int index = 0;
                Graph g = new Graph(n);
                
                if ((n < 0) || (r < 0) || (m < 0) || (u < 0) || (v < 0))
                {
                    System.out.println("Error: Los enteros N,R,M,U y V deben ser positivos.");
                    System.exit(1);
                }
                
                if (n < m) 
                {
                    System.out.println("Error: El numero de oficinas (N) debe ser mayor que el de modem (M).");
                    System.exit(1);
                }
                
                while (index < n)
                {
                    line = br.readLine();
                    String[] coord = line.split(" "); 
                    Node node = new Node(index, Integer.parseInt(coord[0]), Integer.parseInt(coord[1]));
                    g.add(node);
                    index++;
                }
                
                g.createEdges();
                g.printGraph();
                
                
                
                // Min_Tree(G, V);
                
                count++;
            }
        } 
        catch (IOException | NumberFormatException e) 
        {
            System.out.println("Error: No ha podido procesarse el archivo.");
        }
    }
    }