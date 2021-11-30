import com.interview.pwc.countryrouter.input.Country;
import com.interview.pwc.countryrouter.routing.graph.model.IEdge;
import com.interview.pwc.countryrouter.routing.graph.model.IGraph;

public class Edge implements IEdge<Country> {

	IGraph<Country> lAdjected;
	IGraph<Country> rAdjected;
	Integer weight;
	int dir;
	
	public Edge(Integer weight) {
		this.weight = weight;
		dir = 0;
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
		if( lAdjected == null && rAdjected == null ){
			lAdjected = vertex;
		} else if( lAdjected != null && rAdjected == null ){
			rAdjected = vertex;
		} else if( lAdjected == null && rAdjected != null){
			lAdjected = vertex;
		} else {
			throw new IllegalArgumentException("Can't set vertex, two adjected vertexes already exist");
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
