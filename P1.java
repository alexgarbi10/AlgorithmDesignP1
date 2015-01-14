/**
 * Grafo de Internet
 * @author Alejandro Garbi, 08-10398
 * @author David Lilue,
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
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

        public int node_size, edge_size; 
        public Node nodes[];
        public PriorityQueue<Edge> edges;
   
        /**
         * Creates a new graph.
         * @param size Number of nodes.
         */
        public Graph(int size) {
            this.edge_size = 0;
            this.node_size = size; 
            this.nodes = new Node[size];
            Comparator<Edge> comparator = new EdgeComparator();
            this.edges = new PriorityQueue<>(10,comparator);
            
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
    
    public static class Kruskal {
        
        public Graph g;
        public Graph min;
        public int m;
        public double total_ucost;
        public double total_vcost;
        public int ucost;
        public int vcost;
        public int r;
        public int index;
        private int comp_size; // Number of connected components.
        private int compConn[]; // Array of connected components.
        private int rank[]; // Array of rankings for connected components.

        /**
         * Creates a new Kruskal instance for the Graph.
         * @param g Original graph.
         * @param min Minimal cost tree.
         * @param m Number of modems.
         * @param ucost Direct connection.
         * @param vcost Indirect connection.
         * @param r Distance radius for u & v.
         * @param index Instance number.
         */
        public Kruskal(Graph g, Graph min, int m, int ucost, int vcost, int r, int index) {
            this.g = g;
            this.min = min;
            this.m = m;
            this.total_ucost = 0.0;
            this.total_vcost = 0.0;
            this.ucost = ucost;
            this.vcost = vcost;
            this.r = r;
            this.index = index;
            this.comp_size = g.node_size;
            this.compConn = new int[g.node_size];
            this.rank = new int[g.node_size];
        }

        /**
         * Creates new disjoint sets for the node.
         * @param node Node in the set.
         */
        public void makeSet(Node node) {
            this.compConn[node.id] = node.id;
            this.rank[node.id] = 0;
        }

        /**
         * Gets the number of set for the node.
         * @param node Node to search.
         * @return integer.
         */
        public int getComp(int node) {
            if (this.compConn[node] != node) {
		int y = getComp(this.compConn[node]);
		this.compConn[node] = y;
            } 

            return (this.compConn[node]);
        }

        /**
         * Joins two sets into a one connected component.
         * @param src Set to join.
         * @param dst Set to join.
         */
        public void Join(int src, int dst) {
            if (this.rank[src] > this.rank[dst]) {
		this.compConn[dst] = src;
            }
            else { 
		this.compConn[src] = dst;
		
		if (this.rank[src] == this.rank[dst]) {
                    this.rank[src]++;
		}		
            }

            this.comp_size--;		
        }


        /**
         * Run the graph instance to obtain the min-tree.
         */
        public void executeKruskal() {
    
            // Start every set
    	    for (Node element : g.nodes) {
                this.makeSet(element);
            }

            while ((!this.g.edges.isEmpty()) && (this.comp_size > m)) {
        	Edge edge = this.g.edges.poll();
                
		int compx = getComp(edge.src);
		int compy = getComp(edge.dst);
		
                // If compx == compy there is a cycle
		if (compx != compy) {
                    this.min.add(edge);
                    Join(edge.src, edge.dst);
		}
            }
            
            // Calculate costs for every edge in the min-tree
            for (Edge element : min.edges) {
                if (element.cost > this.r) {
                    this.total_vcost = this.total_vcost + (element.cost*this.vcost);
                }
                else {
                    this.total_ucost = this.total_ucost + (element.cost*this.ucost);
                }
            }

            System.out.println("Caso #"+this.index+": "+this.total_ucost+" "+this.total_vcost);
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
                Graph min = new Graph(n);
                
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
                
                // Add every node
                while (index < n)
                {
                    line = br.readLine();
                    String[] coord = line.split(" "); 
                    Node node = new Node(index, Integer.parseInt(coord[0]), Integer.parseInt(coord[1]));
                    g.add(node);
                    min.add(node);
                    index++;
                }
                
                // Add all posible edges with distances
                g.createEdges();
                
                // Get the Minimum Spanning Tree 
                Kruskal kruskal = new Kruskal(g,min,m,u,v,r,count+1);
                kruskal.executeKruskal();
                count++;
            }
        } 
        catch (IOException | NumberFormatException e) 
        {
            System.out.println("Error: No ha podido procesarse el archivo.");
        }
    }
}