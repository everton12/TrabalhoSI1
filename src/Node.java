import java.util.ArrayList;
import java.util.List;

public class Node {
	
	protected List<String> matriz;

	public Node(List<String> matriz) {
		this.matriz = matriz;
	}
	
	public List<String> getMatriz() {
		return matriz;
	}

	public String getHash() {
		String hash = "";
		for(String String : matriz){
			hash+= String + ",";
		}
		return hash;
	}
}
