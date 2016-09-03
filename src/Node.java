import java.util.List;

public class Node {
	
	protected List<Integer> matriz;

	public Node(List<Integer> matriz) {
		this.matriz = matriz;
	}
	
	public List<Integer> getMatriz() {
		return matriz;
	}

	public String getHash() {
		String hash = "";
		for(Integer integer : matriz){
			hash+= integer + ",";
		}
		return hash;
	}
}
