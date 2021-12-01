package com.interview.pwc.countryrouter.routing.graph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.interview.pwc.countryrouter.input.Country;
import com.interview.pwc.countryrouter.routing.graph.model.IEdge;
import com.interview.pwc.countryrouter.routing.graph.model.IGraph;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BellmanFord {

	private Logger log = LoggerFactory.getLogger(BellmanFord.class);
	HashMap<IGraph<Country>, Integer> distances;
	List<IGraph<Country>> toBeCh;
	
	IGraph<Country> source;
	IGraph<Country> end;
	
	public BellmanFord(IGraph<Country> source, IGraph<Country> end) {
		this.source = source;
		this.end = end;
	}
	
	public List<IGraph<Country>> startSearch(Map<String, IGraph<Country>> allVertexes){
		
		distances = new HashMap<IGraph<Country>, Integer>();
		intializeToMax(distances, allVertexes);
		distances.put(source, 0);
		toBeCh = new ArrayList<IGraph<Country>>();
		toBeCh.add(allVertexes.get(source.getData().getCca3()));
		
		while ( !toBeCh.isEmpty() ) {
			IGraph<Country> curentVertex = toBeCh.remove(0);
			
			List<IEdge<Country>> currentEdges = curentVertex.getEdges();
			for(IEdge<Country> e : currentEdges){
				IGraph<Country> adjected = e.getAdjected(curentVertex);
				if( adjected == null ) continue;
				int currentDist = distances.get(curentVertex) + e.getWeight(); 
				if ( currentDist < distances.get(adjected) ){
					distances.put(adjected, currentDist);
					adjected.setPredecessor(curentVertex);
					if( !toBeCh.contains(adjected) ){
						toBeCh.add(adjected);
					}
				}
			}
		}

		end = allVertexes.get(end.getData().getCca3());
		log.info("Shortest path is " + distances.get(end) + " units!");
		if( distances.get(end) == Integer.MAX_VALUE/2 ) throw new IllegalArgumentException("Destination is unreachable!");
		List<IGraph<Country>> shortest = new ArrayList<IGraph<Country>>();
		while( end.getPredecessor() != null ){
			shortest.add(0,end);
			end = end.getPredecessor();
		}
		shortest.add(0,end);
		return shortest;
	}



	private void intializeToMax(Map<IGraph<Country>, Integer> d,
			Map<String, IGraph<Country>> allVertexes) {
		for(IGraph<Country> v: allVertexes.values()){
			d.put(v, Integer.MAX_VALUE/2);
		}
	}



}
