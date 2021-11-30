package com.interview.pwc.countryrouter.routing.graph.model;

/**
 * Interface that models the edge of {@link IGraph}.
 * Edge structure is necessary because it is required to model undirected graphs.
 * @author Armin Tiri�
 *
 * @param <Data> Data of the vertex it connects NOT this edge.
 */
public interface IEdge<Data> {

	/**
	 * Getter for weight.
	 * @return weight of edge.
	 */
	Integer getWeight();
	/**
	 * Setter for weight.
	 * @param weight New weight.
	 */
	void setWeight(Integer weight);
	/**
	 * Getter for adjected graph which is the one that is different of the one given and edge that is tested must have given graph
	 * otherwise throws {@link IllegalArgumentException}.
	 * @param graph Vertex that is on one part of the edge.
	 * @return Adjeceted Vertex.
	 */
	IGraph<Data> getAdjected(IGraph<Data> graph);
	/**
	 * Setter for adjeceted vertex.
	 * @param vertex Adjected vertex.
	 */
	void setAdjected(IGraph<Data> vertex);
	/**
	 * Setter for direction of the edge. The first set vertex of this edge
	 * will be the one on the left side directing towards right side of the edge.
	 * @param direction Integer that if set to nuber greater than 0 it means its left to right direction,
	 *  	if set to number less than 0 than its right to left direction, otherwise the edge has no direction meaning the edge is undirected. 
	 */
	void setDir(int direction);
	
}
