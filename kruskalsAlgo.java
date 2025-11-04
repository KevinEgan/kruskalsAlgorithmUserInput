import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class GfG {
    public static int kruskalsMST(int V, int[][] edges) {
        
        // Sort all edges based on weight
        Arrays.sort(edges, Comparator.comparingInt(e -> e[2]));
        
        System.out.println("$$$$$$ Kruskal's Algorithm Execution");
        System.out.println("Edges sorted by weight: \n");
        for (int[] edge : edges) {
            System.out.println("Edge: " + edge[0] + " -> " + edge[1] + ". Weight: " + edge[2]);
        }
        System.out.println();
        
        // Traverse edges in sorted order
        DSU dsu = new DSU(V);
        int cost = 0, count = 0;
        
        System.out.println("Building MST:");
        for (int[] e : edges) {
            int x = e[0], y = e[1], w = e[2];
            
            // Make sure that there is no cycle
            if (dsu.find(x) != dsu.find(y)) {
                dsu.union(x, y);
                cost += w;
                count++;
                System.out.println("Added edge: " + x + " -> " + y + ". Weight: " + w + ". Total cost: " + cost);
                if (count == V - 1) {
                    break;
                }
            }
        }
        System.out.println();
        return cost;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("$$$$$ Kruskal's Algorithm CLI");
    
        System.out.print("Enter number of vertices: ");
        int vertices = scanner.nextInt();
        System.out.print("Enter number of edges: ");
        int numberOfEdges = scanner.nextInt();
        
        //user input to create edges
        int[][] edges = new int[numberOfEdges][3];
        System.out.println("\nEnter edges in the following format: Source Destination Weight");
        for (int i = 0; i < numberOfEdges; i++) {
            System.out.print("Edge " + (i + 1) + ": ");
            edges[i][0] = scanner.nextInt(); 
            edges[i][1] = scanner.nextInt();
            edges[i][2] = scanner.nextInt();
        }
        
        System.out.println("FINAL RESULT: Minimum Spanning Tree cost = " + kruskalsMST(vertices, edges));

    }
}

// Disjoint set data structure
class DSU {
    private int[] parent, rank;

    public DSU(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int i) {
        if (parent[i] != i) {
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }

    public void union(int x, int y) {
        int s1 = find(x);
        int s2 = find(y);
        if (s1 != s2) {
            if (rank[s1] < rank[s2]) {
                parent[s1] = s2;
            } else if (rank[s1] > rank[s2]) {
                parent[s2] = s1;
            } else {
                parent[s2] = s1;
                rank[s1]++;
            }
        }
    }
}