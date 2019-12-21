package cs228hw4.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

public class CS228Dijkstra<V> implements Dijkstra<V> {

	private Hashtable<V, Integer> dist;

	private Hashtable<V, ArrayList<V>> paths;
	
	private HashMap<V, Integer> hm = new HashMap<>();
	

	private DiGraph<V> givenGraph;

	public CS228Dijkstra(DiGraph<V> g) {
		givenGraph = g;
		dist = new Hashtable<>();
		paths = new Hashtable<>();
		

	}

	/**
	 * runs Dijkstra's algorith and places all of the paths and distances in the
	 * dist and paths Hashtable
	 */
	public void run(V start) {
		// holds the Vertices that have not been visited
		ArrayList<V> open = new ArrayList<>();
		open.add(start);
		// holds the Vertices have been visited
		ArrayList<V> closed = new ArrayList<>();

		Iterator<V> it = givenGraph.iterator();

		// sets the default values for both the distances and paths
		while (it.hasNext()) {
			V cur = it.next();
			dist.put(cur, Integer.MAX_VALUE);
			paths.put(cur, new ArrayList<V>());
		}
		// distance from start to start is 0
		dist.replace(start, 0);

		ArrayList<V> beginPath = new ArrayList<V>();
		beginPath.add(start);
		paths.replace(start, beginPath);

		while (!open.isEmpty()) {
			V cur = open.get(0);
			open.remove(0);

			closed.add(cur);

			HashSet<V> neighbors = (HashSet<V>) givenGraph.getNeighbors(cur);

			for (V n : neighbors) {

				int alt = dist.get(cur) + givenGraph.getEdgeCost(cur, n);
				if (alt < dist.get(n)) {
					dist.replace(n, alt);
					// update path by replacing arrayList
					ArrayList<V> temp = (ArrayList<V>) paths.get(cur).clone();
					temp.add(n);
					paths.replace(n, temp);
					//
					open.add(n);
				}

			}

		}

	}

	/**
	 * returns a list of the shortest path to take from the starting vertex to the
	 * given vertex
	 * 
	 * returns an empty list if there is no path
	 */
	public List<V> getShortestPath(V vertex) {

		return paths.get(vertex);
	}

	/**
	 * returns the value of the path to get from the starting vertex to the given
	 * vertex
	 * 
	 * returns Integer.MaxValue if there is no path
	 */
	public int getShortestDistance(V vertex) {

		return dist.get(vertex);
	}

}
