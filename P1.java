/**
 * Grafo de Internet
 * @author Alejandro Garbi, 08-10398
 * @author David Lilue,
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class P1 {
    
    public class Node {

	// Node identifier.
	public int id = -1;
        // Coordinates
        public int x = -1;
        public int y = -1;

	/**
	 * Create a new node with id i.
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
            System.out.println("Id: "+this.id);
	}

	/**
	 * Hash-code for the nodes.
         * @return integer.
	 */
	@Override
	public int hashCode() {
            return this.id;
	}
    }
    
    public class Arc {
  
        public int src;
        public int dst;
        public int cost;
        
        public Arc(int src, int dst, int cost) {
            this.src = src;
            this.dst = dst;
            this.cost = cost;
        }
        
        /**
         * Compare arcs.
         * @param obj Object to compare.
         * @return boolean.
         */
        @Override
        public boolean equals(Object obj) {
            Arc arc;
       
            if (obj == null) {
                return false;
            }
        
            if (!(obj instanceof Arc)) {
                return false;
            }

            arc = (Arc) obj;

            return this.dst == arc.dst && this.src == arc.src;
        }
    
        /**
         * String representation of the arc.
         * @return String.
         */
        @Override
        public String toString() {
            return "(" + Integer.toString(src) + ", " + Integer.toString(dst) +")";
        }
        
        /**
         * Prints the arc.
         */
        public void printArc() {
            System.out.println("Origen: "+this.src+" Destino: "+this.dst+" Costo: "+this.cost);
        }

        /**
         * Hash-code for the arcs.
         * @return integer.
         */
        @Override
        public int hashCode() {
            return this.src;
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
                    
                    System.out.println("Index: " + index);
                    System.out.println("X: " + coord[0]);
                    System.out.println("Y: " + coord[1]);
                    //Nodo(index, x, y);
                    
                    index++;
                }
                
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