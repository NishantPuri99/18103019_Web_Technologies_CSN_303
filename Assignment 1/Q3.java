//When working with Shortest Path Problems, negative cycles play a very critical role. WAP using
//Java to print the shortest path between the two nodes if it exists otherwise print 
//"Negative Cycles Exist". Can the program be extended to include all possible paths between 
//the two nodes?
import java.util.*;
public class Q3 {
    public static class Edge
    {
        int src, dest, weight;
    }

    public static class Graph
    {
        int V, E;
        Edge[] edge;
    }

    //Creating a graph
    private static Graph createGraph(int V, int E)
    {
        Graph graph = new Graph();
        graph.V = V;
        graph.E = E;
        graph.edge = new Edge[graph.E];

        for (int i = 0; i < graph.E; i++)
        {
            graph.edge[i] = new Edge();
        }
        return graph;
    }

    //Checking if a negative cycle exists
    private static boolean isNegativeCycle(Graph graph, int src, int v1, int v2)
    {
        int V = graph.V;
        int E = graph.E;
        int[] dist = new int[V];

        for (int i = 0; i < V; i++)
            dist[i] = Integer.MAX_VALUE; //Initialize distances from src to all other vertices as INFINTE
        dist[src] = 0;

        for (int i = 1; i <= V - 1; i++)
        {
            for (int j = 0; j < E; j++)
            {
                int u = graph.edge[j].src;
                int v = graph.edge[j].dest;
                int weight = graph.edge[j].weight;
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v])
                    dist[v] = dist[u] + weight;
            }
        }

        for (int i = 0; i < E; i++)
        {
            if(v1 == graph.edge[i].src && v2 == graph.edge[i].dest)
            {
                int weight = graph.edge[i].weight;
                if (dist[v1] != Integer.MAX_VALUE && dist[v1] + weight < dist[v2])
                    return true;
            }
        }
        return false;
    }

    //Finding all paths (shortest path included in that)
    private static void allPaths(int V, int v1, int v2, boolean[][] g, ArrayList<Integer> v, int distance, boolean[] vis)
    {
        v.add(v1);
        vis[v1] = true;
        if (v1 == v2)
        {
            System.out.println("Path: ");
            for (Integer integer : v)
            {
                System.out.print(integer + 1 + " ");
            }
            System.out.println("Distance From Source: " + distance);
            vis[v1] = false;
            v.remove(v.size() - 1);
            return;
        }

        for (int i = 0; i < V; i++)
        {
            if (vis[i] == false && g[v1][i])
            {
                allPaths(V, i, v2, g, v, distance + 1, vis);
            }
        }
        vis[v1] = false;
        v.remove(v.size() - 1);
    }

    public static void main(String []args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number of vertices: ");
        int V = sc.nextInt();
        System.out.print("Enter a number of edges: ");
        int E = sc.nextInt();
        Graph graph = createGraph(V, E);

        //Taking input for the values of Graph
        for(int i=0; i<E; i++)
        {
            System.out.print("Enter the first vertice: ");
            graph.edge[i].src = sc.nextInt();

            System.out.print("Enter the second vertice: ");
            graph.edge[i].dest = sc.nextInt();

            System.out.print("Enter the weight of the edge: ");
            graph.edge[i].weight = sc.nextInt();
        }

        System.out.print("Enter the first node: ");
        int v1 = sc.nextInt(); //source node
        System.out.print("Enter the second node: ");
        int v2 = sc.nextInt(); //destination node

        if(isNegativeCycle(graph, 0, v1, v2))
            System.out.println("Negative Cycle Exists");
        else //if negative cycle does not exist
        {
            boolean[] vis = new boolean[V];
            ArrayList<Integer> pathList = new ArrayList<>();
            boolean[][] g = new boolean[V][V];
            for (Edge e : graph.edge)
                g[e.src-1][e.dest-1] = true; //creating a adjanceny matrix
            System.out.println("All paths from source to destination: ");
            allPaths(V, v1-1, v2-1, g, pathList, 0, vis);
        }
    }
}