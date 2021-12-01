package com.interview.pwc.countryrouter.routing.graph;

import com.interview.pwc.countryrouter.input.Country;
import com.interview.pwc.countryrouter.routing.graph.model.IEdge;
import com.interview.pwc.countryrouter.routing.graph.model.IGraph;

public class Edge implements IEdge<Country> {

	IGraph<Country> lAdjected;
	IGraph<Country> rAdjected;
	Integer weight;
	int dir;
	
	/**
	 * Create directional or undirectional edge between two verixes.
	 * @param weight any number that models trail
	 * @param startVertex start vertex of the graph, depending on given direction
	 * @param direction Integer if greater than or equal of 0 defines left to right orientation of edge, if number is 0 the edge is undirectional otherwise edge defines directional path.
	 */
	public Edge(Integer weight, Graph startVertex, int direction) {
		this.weight = weight;
		if( direction >= 0){
			this.lAdjected = startVertex;
		} else {
			this.rAdjected = startVertex;
		}
		this.dir = direction;
	}

	
	@Override
	public Integer getWeight() {
		return weight;
	}


	@Override
	public void setWeight(Integer weight) {
		this.weight = weight;
		
	}
	

	@Override
	public IGraph<Country> getAdjected(IGraph<Country> graph) {
		if( dir == 0 && ( graph.equals(lAdjected) || graph.equals(rAdjected) )){
			return graph.equals(lAdjected)?rAdjected:lAdjected;
		}
		if( graph.equals(lAdjected) && dir > 0 ){
			return rAdjected;
		} else if ( graph.equals(rAdjected) && dir < 0){
			return lAdjected;
		} else {
			return null;
		}
		
	}


	@Override
	public void setAdjected(IGraph<Country> vertex) {
		if( dir >= 0 ){
			rAdjected = vertex;
		} else {
			lAdjected = vertex;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		return result;
	}



	@Override
	public void setDir(int direction) {
		dir = direction;
		
	}
	
	@Override
	public String toString() {
		if( dir > 0 ){
			return lAdjected + " -> " + rAdjected + "[label=\"" + weight + "\"];";
		} else if ( dir < 0){
			return rAdjected + " -> " + lAdjected + "[label=\"" + weight + "\"];";
		} else {
			return lAdjected + " -- " + rAdjected + "[label=\"" + weight + "\"];";
		}
	}

	
	
}
