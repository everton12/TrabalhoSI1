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

	public void setMatriz(List<String> matriz) {
		this.matriz = matriz;
	}

	
	@Override
	public boolean equals(Object obj) {
		
		List<String> auxList = new ArrayList<String>(((Node)obj).getMatriz());
		
		///(se for remover isto, tem que tratar o 0 no for)
		if ( auxList.indexOf("0") == ((Control.SIZE_OF_TABULEIRO * Control.SIZE_OF_TABULEIRO)-1) )
			auxList.remove(auxList.indexOf("0"));
		else
			///como assumimos só um nodo final podemos fazer essa verificação pra evitar o for 
			return false;
			
		for(String obj_to_check : auxList){
			int nodePosition = auxList.indexOf(obj_to_check);
			
			if( !obj_to_check.equals( (nodePosition+1)+"" ) ){
				return false;
			}
		}
		
		return true;
	}
	
	
	
}
