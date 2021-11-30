import java.util.ArrayList;
import java.util.List;

import com.interview.pwc.countryrouter.input.Country;
import com.interview.pwc.countryrouter.routing.graph.model.IEdge;
import com.interview.pwc.countryrouter.routing.graph.model.IGraph;

public class Graph implements IGraph<Country> {

	List<IEdge<Country>> edges;
	Country data;
	IGraph<Country> predecessor;
	
	public Graph(Country country) {
		this.data = country;
		edges = new ArrayList<IEdge<Country>>();
	}
	@Override
	public List<IEdge<Country>> getEdges() {
		return edges;
	}

	@Override
	public Country getData() {
		return data;
	}

	@Override
	public void addEdge(IEdge<Country> e) {
		edges.add(e);
	}

	@Override
	public void deleteEdge(int i) {
		edges.remove(i);
		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Graph other = (Graph) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}
	@Override
	public IGraph<Country> getPredecessor() {
	
		return predecessor;
	}
	@Override
	public void setPredecessor(IGraph<Country> predecessor) {
		this.predecessor = predecessor;
	}
	
	@Override
	public String toString() {
		return (String) this.data.getName().get("common");
	}

}
