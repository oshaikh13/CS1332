import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Your implementation of various different graph algorithms.
 *
 * @author Omar Shaikh
 * @userid oshaikh3
 * @GTID 903403821
 * @version 1.0
 */
public class GraphAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * {@code start} which represents the starting vertex.
     *
     * When exploring a vertex, make sure to explore in the order that the
     * adjacency list returns the neighbors to you. Failure to do so may cause
     * you to lose points.
     *
     * You may import/use {@code java.util.Set}, {@code java.util.List},
     * {@code java.util.Queue}, and any classes that implement the
     * aforementioned interfaces, as long as it is efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for BFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the bfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     */
    public static <T> List<Vertex<T>> breadthFirstSearch(Vertex<T> start,
                                                         Graph<T> graph) {

        if (graph == null || start == null) {
            throw new 
                IllegalArgumentException("Graph or Start cannot be null!");
        }

        Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();

        if (!adjList.containsKey(start)) {
            throw new 
                IllegalArgumentException("Graph does not contain start vertex");
        }
                                                    
        Queue<Vertex<T>> q = new LinkedList<Vertex<T>>();
        List<Vertex<T>> results = new LinkedList<>();                   
        Set<Vertex<T>> visited = new HashSet<>();

        q.offer(start);
        visited.add(start);
        results.add(start);

        while (!q.isEmpty()) {
            Vertex<T> current = q.poll();
            for (VertexDistance<T> next : adjList.get(current)) {
                if (!visited.contains(next.getVertex())) {
                    visited.add(next.getVertex());
                    q.offer(next.getVertex());
                    results.add(next.getVertex());
                }
            }
        }
        return results;
    }

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * {@code start} which represents the starting vertex.
     *
     * When deciding which neighbors to visit next from a vertex, visit the
     * vertices in the order presented in that entry of the adjacency list.
     *
     * *NOTE* You MUST implement this method recursively, or else you will lose
     * most if not all points for this method.
     *
     * You may import/use {@code java.util.Set}, {@code java.util.List}, and
     * any classes that implement the aforementioned interfaces, as long as it
     * is efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     */
    public static <T> List<Vertex<T>> depthFirstSearch(Vertex<T> start,
                                                       Graph<T> graph) {
        if (graph == null || start == null) {
            throw new 
                IllegalArgumentException("Graph/start vertex cannot be null");
        }

        if (!graph.getAdjList().containsKey(start)) {
            throw new 
                IllegalArgumentException("Graph does not contain start vertex");
        }

        List<Vertex<T>> results = new LinkedList<>();                    
        dfsHelper(start, new HashSet<>(), graph.getAdjList(), results);
        return results;
    }

    /**
    * Recursive helper method for depthFirstSearch 
    *
    * @param <T> the generic typing of the data
    * @param current the vertex to begin the dfs on
    * @param visited the set of visited vertex
    * @param adjList the adjacency list of the graph
    * @param results the list of nodes to add to
    */
    public static <T> void dfsHelper(Vertex<T> current, Set<Vertex<T>> visited, 
                                     Map<Vertex<T>, 
                                     List<VertexDistance<T>>> adjList, 
                                     List<Vertex<T>> results) {
        visited.add(current);
        results.add(current);
        for (VertexDistance<T> next : adjList.get(current)) {
            if (!visited.contains(next.getVertex())) {
                dfsHelper(next.getVertex(), visited, adjList, results);
            }
        }
    }


    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     *
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from {@code start}, or Integer.MAX_VALUE (representing
     * infinity) if no path exists.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Map}, and {@code java.util.Set} and any class that
     * implements the aforementioned interfaces, as long as your use of it
     * is efficient as possible.
     *
     * You should implement the version of Dijkstra's where you use two
     * termination conditions in conjunction.
     *
     * 1) Check that not all vertices have been visited.
     * 2) Check that the PQ is not empty yet.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input is null, or if start
     *  doesn't exist in the graph.
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the Dijkstra's on (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from {@code start} to every
     *          other node in the graph
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                        Graph<T> graph) {

        if (graph == null || start == null) {
            throw new IllegalArgumentException("Graph/Start cannot be null!");
        }

        Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();

        if (!adjList.containsKey(start)) {
            throw new 
                IllegalArgumentException("Graph does not contain start vertex");
        }

        Map<Vertex<T>, Integer> dists = new HashMap<>();
        PriorityQueue<VertexDistance<T>> pq = new PriorityQueue<>();
        HashSet<Vertex<T>> visitedSet = new HashSet<Vertex<T>>();

        for (Vertex<T> vertex: graph.getVertices()) {
            dists.put(vertex, vertex.equals(start) ? 0 : Integer.MAX_VALUE); 
        }

        pq.add(new VertexDistance<T>(start, 0));

        while (!pq.isEmpty() 
            && visitedSet.size() < graph.getVertices().size()) {
            VertexDistance<T> smallest = pq.poll();
            visitedSet.add(smallest.getVertex());
            for (VertexDistance<T> neighboor
                :adjList.get(smallest.getVertex())) {

                int potentiallySmallerDist = 
                    neighboor.getDistance() + smallest.getDistance();

                if (dists.get(neighboor.getVertex()) > potentiallySmallerDist) {
                    dists.put(neighboor.getVertex(), potentiallySmallerDist);
                    pq.offer(new VertexDistance<T>(neighboor.getVertex(), 
                        potentiallySmallerDist));
                } 
            }
        }

        return dists;
    }


    /**
     * Runs Kruskal's algorithm on the given graph and returns the Minimal
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * reverse edge to the set as well. This is for testing purposes. This
     * reverse edge does not need to be the one from the graph itself; you can
     * just make a new edge object representing the reverse edge.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * Kruskal's will also require you to use a Disjoint Set which has been
     * provided for you. A Disjoint Set will keep track of which vertices are
     * connected given the edges in your current MST, allowing you to easily
     * figure out whether adding an edge will create a cycle. Refer
     * to the {@code DisjointSet} and {@code DisjointSetNode} classes that
     * have been provided to you for more information.
     *
     * You should NOT allow self-loops or parallel edges into the MST.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Set}, and any class that implements the aforementioned
     * interface.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input is null
     * @param <T> the generic typing of the data
     * @param graph the graph we are applying Kruskals to
     * @return the MST of the graph or null if there is no valid MST
     */
    public static <T> Set<Edge<T>> kruskals(Graph<T> graph) {

        if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null");
        }

        if (graph.getVertices().size() - 1 > graph.getEdges().size()) {
            return null;
        }

        PriorityQueue<Edge<T>> pq = 
            new PriorityQueue<Edge<T>>(graph.getEdges());
    
        DisjointSet<Vertex<T>> ds = 
            new DisjointSet<Vertex<T>>(graph.getVertices());

        Set<Edge<T>> mst = new HashSet<Edge<T>>();
        int validMSTSize = 2 * (graph.getVertices().size() - 1);

        while (mst.size() < validMSTSize && !pq.isEmpty()) {
            Edge<T> e = pq.poll();
            if (!ds.find(e.getU()).equals(ds.find(e.getV()))) {
                ds.union(e.getU(), e.getV());
                mst.add(e);
                mst.add(new Edge<T>(e.getV(), e.getU(), e.getWeight()));
            }
        }

        return mst.size() == validMSTSize ? mst : null;

    }
}
