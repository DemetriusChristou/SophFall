package cs228hw4.tests;


import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Iterator;

import org.junit.Test;

import cs228hw4.graph.CS228Dijkstra;
import cs228hw4.graph.MyGraph;




public class GraphTests {
	
	@Test
	public void gTest() {
		MyGraph<Object> g = new MyGraph<>();
		
		g.addVector("A");
		g.addVector("B");
		g.addVector("C");
		g.addVector("D");
		g.addVector("E");
		g.addVector("F");
		
		g.addEdge("A", "B", 1);
		g.addEdge("A", "D", 3);
		g.addEdge("B", "D", 5);
		g.addEdge("B", "C", 2);
		g.addEdge("D", "C", 4);
		g.addEdge("C", "E", 6);
		g.addEdge("E", "D", 7);
		g.addEdge("E", "F", 1);
		
		System.out.println(g.numVertices());
		
		Iterator<Object> it = g.iterator();
		Object A =  it.next();
		Object B = it.next();
		Object C = it.next();
		Object D = it.next();
		Object E = it.next();
		Object F = it.next();
		System.out.println(g.getEdgeCost(A, B));
		HashSet<String> e = (HashSet<String>) g.getNeighbors(A);
		System.out.println();
		Iterator<String> f = e.iterator();
		
		
		
		CS228Dijkstra<Object> dijk = new CS228Dijkstra<>(g);
		dijk.run(A);
		
		
		System.out.println(dijk.getShortestDistance(A));
		System.out.println(dijk.getShortestDistance(B));
		System.out.println(dijk.getShortestDistance(C));
		System.out.println(dijk.getShortestDistance(D));
		System.out.println(dijk.getShortestDistance(E));
		System.out.println(dijk.getShortestDistance(F));
		
		System.out.println(dijk.getShortestPath(A));
		System.out.println(dijk.getShortestPath(B));
		System.out.println(dijk.getShortestPath(C));
		System.out.println(dijk.getShortestPath(D));
		System.out.println(dijk.getShortestPath(E));
		System.out.println(dijk.getShortestPath(F));
		
		System.out.println();
		dijk.run(B);
		
		
		System.out.println(dijk.getShortestDistance(A));
		System.out.println(dijk.getShortestDistance(B));
		System.out.println(dijk.getShortestDistance(C));
		System.out.println(dijk.getShortestDistance(D));
		System.out.println(dijk.getShortestDistance(E));
		System.out.println(dijk.getShortestDistance(F));
		
		System.out.println(dijk.getShortestPath(A));
		System.out.println(dijk.getShortestPath(B));
		System.out.println(dijk.getShortestPath(C));
		System.out.println(dijk.getShortestPath(D));
		System.out.println(dijk.getShortestPath(E));
		System.out.println(dijk.getShortestPath(F));
		
	}
	
	@Test
	public void gTest2() {
		MyGraph<Object> g = new MyGraph<>();
		
		g.addVector("S");
		g.addVector("V1");
		g.addVector("V2");
		g.addVector("V3");
		g.addVector("V4");
		
		
		g.addEdge("S", "V1", 2);
		g.addEdge("S", "V3", 5);
		g.addEdge("V1", "V2", 6);
		g.addEdge("V1", "V3", 4);
		g.addEdge("V3", "V2", 2);
		g.addEdge("V3", "V4", 5);
		g.addEdge("V2", "V4", 1);
		
		
		System.out.println(g.numVertices());
		
		Iterator<Object> it = g.iterator();
		Object S =  it.next();
		Object V1 = it.next();
		Object V2 = it.next();
		Object V3 = it.next();
		Object V4 = it.next();
		
		System.out.println(g.getEdgeCost(S, V1));
		HashSet<String> e = (HashSet<String>) g.getNeighbors(S);
		System.out.println();
		Iterator<String> f = e.iterator();
		
		
		CS228Dijkstra<Object> dijk = new CS228Dijkstra<>(g);
		dijk.run(S);
		
		
		System.out.println(dijk.getShortestDistance(S));
		System.out.println(dijk.getShortestDistance(V1));
		System.out.println(dijk.getShortestDistance(V2));
		System.out.println(dijk.getShortestDistance(V3));
		System.out.println(dijk.getShortestDistance(V4));
		
		System.out.println(dijk.getShortestPath(S));
		System.out.println(dijk.getShortestPath(V1));
		System.out.println(dijk.getShortestPath(V2));
		System.out.println(dijk.getShortestPath(V3));
		System.out.println(dijk.getShortestPath(V4));
		
		System.out.println();
		
	}
	
	@Test
	public void gTest3() {
		MyGraph<Object> g = new MyGraph<>();
		
		g.addVector(1);
		g.addVector(2);
		g.addVector(3);
		g.addVector(4);
		g.addVector(5);
		
		
		g.addEdge(1, 2, 1);
		g.addEdge(1, 5, 2);
		g.addEdge(1, 3, 3);
		g.addEdge(2, 3, 1);
		g.addEdge(3, 4, 1);
		g.addEdge(3, 5, 3);
		g.addEdge(4, 5, 5);
		
		
		System.out.println(g.numVertices());
		
		Iterator<Object> it = g.iterator();
		Object A =  it.next();
		Object B = it.next();
		Object C = it.next();
		Object D = it.next();
		Object E = it.next();
		
		assertEquals(1, g.getEdgeCost(A, B));
		assertEquals(2, g.getEdgeCost(A, E));
		
		
		CS228Dijkstra<Object> dijk = new CS228Dijkstra<>(g);
		dijk.run(A);
		
		assertEquals(1, dijk.getShortestDistance(B));
		assertEquals(0, dijk.getShortestDistance(A));
		assertEquals(2, dijk.getShortestDistance(C));
		assertEquals(3, dijk.getShortestDistance(D));
		assertEquals(2, dijk.getShortestDistance(E));
		
		
		System.out.println(dijk.getShortestPath(A));
		System.out.println(dijk.getShortestPath(B));
		System.out.println(dijk.getShortestPath(C));
		System.out.println(dijk.getShortestPath(D));
		System.out.println(dijk.getShortestPath(E));
		
		System.out.println();
		
	}
}
