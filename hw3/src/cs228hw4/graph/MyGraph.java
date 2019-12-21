package cs228hw4.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class MyGraph<V> implements DiGraph<V> {

	private class Vertex {
		private Object id;

		public Vertex(Object id) {
			this.id = id;

		}

		@Override
		public boolean equals(Object o) {
			Vertex other = (Vertex) o;

			return id.equals(other.id);
		}
		
		@Override
		public String toString() {
			return id.toString();
		}
	}

	private class Edge {

		private Object id1;
		private Object id2;
		private int value;

		/**
		 * 
		 * @param id1   starting edge
		 * @param id2   ending edge
		 * @param value cost to move along edge
		 */
		private Edge(Object id1, Object id2, int value) {
			this.id1 = id1;
			this.id2 = id2;
			this.value = value;
		}

		@Override
		public boolean equals(Object o) {
			Edge other = (Edge) o;

			return id1.equals(other.id1) && id2.equals(other.id2);
		}
	}

	private ArrayList<Vertex> vertices;
	private ArrayList<Edge> edges;
	private Hashtable<Object, Vertex> idToVert;

	public MyGraph() {
		vertices = new ArrayList<>();
		edges = new ArrayList<>();
		idToVert = new Hashtable<>();
	}

	/**
	 * returns a set of strings that stand for the Vector id of the starting vectors
	 * neighbors.
	 */
	public Set<? extends V> getNeighbors(V vertex) {
		HashSet<Object> ret = new HashSet<>();
		
		if (vertex == null) {
			throw new IllegalArgumentException("vertex is null");
		}

		Vertex start = (Vertex) vertex;
		if (!vertices.contains(start)) {
			throw new IllegalArgumentException("vertex doesn't exist in this graph");
		}

		for (Edge e : edges) {
			if (e.id1.equals(start.id)) {
				ret.add(idToVert.get(e.id2));
			}
		}

		return (Set<? extends V>) ret;
	}

	@Override
	public int getEdgeCost(V start, V end) {
		Vertex st = (Vertex) start;
		Vertex en = (Vertex) end;

		if (!vertices.contains(st) || !vertices.contains(en)) {
			throw new IllegalArgumentException("Start or end doesn't exist.");
		}

		for (Edge e : edges) {
			if (e.id1.equals(st.id)) {
				if (e.id2.equals(en.id)) {
					return e.value;
				}
			}
		}

		throw new IllegalArgumentException("There is no path between these vertices");
	}

	/**
	 * returns number of vertices
	 */
	public int numVertices() {

		return vertices.size();
	}

	public Iterator<V> iterator() {

		return (Iterator<V>) vertices.iterator();
	}

	public boolean addVector(Object o ) {
		Vertex toAdd = new Vertex(o);

		if (vertices.contains(toAdd)) {
			return false;
		} else
			idToVert.put(o, toAdd);
			return vertices.add(toAdd);
	}

	public boolean addEdge(Object Vid1, Object Vid2, int value) {
		Edge toAdd = new Edge(Vid1, Vid2, value);

		if (edges.contains(toAdd)) {
			return false;
		} else
			return edges.add(toAdd);
	}
	
	public Vertex getVertex(String id) {
		return idToVert.get(id);
	}

}
