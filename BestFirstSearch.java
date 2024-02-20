import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

public class BestFirstSearch {
    static ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
    static String[] cityNames;

    // Function for adding the edges to a graph
    static class Edge implements Comparable<Edge> {
        int v, cost;

        Edge(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            if (o.cost < cost) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    public BestFirstSearch(int v, String[] names) {
        cityNames = names;
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
    }

    // Function to apply Best-First Search
    static void best_first_search(int source, int target, int v) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[v];
        visited[source] = true;
        pq.add(new Edge(source, -1));

        int totalCost = 0;

        while (!pq.isEmpty()) {
            Edge currentEdge = pq.poll();
            int currentVertex = currentEdge.v;

            // Displaying the path having the lowest cost.
            System.out.print(cityNames[currentVertex] + " ");

            if (currentVertex == target) {
                break;
            }

            totalCost += currentEdge.cost;

            for (Edge adjacentNodeEdge : adj.get(currentVertex)) {
                if (!visited[adjacentNodeEdge.v]) {
                    visited[adjacentNodeEdge.v] = true;
                    pq.add(adjacentNodeEdge);
                }
            }
        }
        System.out.println("\nDestination city reached!");
        System.out.println("Total Cost of the Path: " + totalCost);
    }

    void addEdge(int u, int v, int cost) {
        adj.get(u).add(new Edge(v, cost));
        adj.get(v).add(new Edge(u, cost));
    }

    
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        int v = 7;
        String[] cityNames = {"Vijayawada", "Hyderabad", "Pune", "Chennai", "Bangalore", "Tirupati", "Mysore"};

        BestFirstSearch graph = new BestFirstSearch(v, cityNames);
        graph.addEdge(0, 1, 274);
        graph.addEdge(0, 3, 627);
        graph.addEdge(1, 2, 561);
        graph.addEdge(1, 3, 627);
        graph.addEdge(1, 4, 515);
        graph.addEdge(2, 4, 884);
        graph.addEdge(3, 5, 133);
        graph.addEdge(4, 5, 247);
        graph.addEdge(5, 6, 389); 

        System.out.print("Enter the source city: ");
        String sourceCity = scanner.nextLine();

        System.out.print("Enter the destination city: ");
        String destinationCity = scanner.nextLine();

        int source = getIndex(cityNames, sourceCity);
        int target = getIndex(cityNames, destinationCity);

        System.out.println("Path:");
        best_first_search(source, target, v);

        scanner.close();
    }

    // Helper function to get the index of a city in the cityNames array
    static int getIndex(String[] array, String city) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equalsIgnoreCase(city)) {
                return i;
            }
        }
        return -1; 
    }
}
