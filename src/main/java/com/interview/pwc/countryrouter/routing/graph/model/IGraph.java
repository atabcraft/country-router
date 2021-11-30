package com.interview.pwc.countryrouter.routing.graph.model;

import java.util.List;
/**
 * Interface that model the graph which is parameterized by data. Graph is a structure that contains number of vertexes and edges.
 * This interface models vertex with its set of edges so that makes it a graph.
 * @author Armin Tiri�
 *
 * @param <Data> Data that is contained in vertex.
 */
public interface IGraph<Data> {

	/**
	 * Getter for all edges of one vertex.
	 * @return all edges of one vertex.
	 */
	List<IEdge<Data>> getEdges();
	/**
	 * Getter for data.
	 * @return Data object.
	 */
	Data getData();
	/**
	 * Adds the edge to this vertex of this graph.
	 * This method also sets the edge to this vertex as one of adjeceted vertexes.
	 * @param e Edge to be added.
	 */
	void addEdge(IEdge<Data> e);
	/**
	 * Deletes the edge on given index.
	 * @param i Index of edge.
	 */
	void deleteEdge(int i);
	/**
	 * Getter for predecessor, used to create a walk between vertexes.
	 * @return Predecessor vertex.
	 */
	IGraph<Data> getPredecessor();
	/**
	 * Setter for predecessor, used to create a walk between vertexes.
	 * @param predecessor last predecessor of vertex.
	 */
	void setPredecessor(IGraph<Data> predecessor);
	
}
