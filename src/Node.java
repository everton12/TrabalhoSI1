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

	public int getPunctuation() {
		int score = 0;
		
		for(String value : matriz){
			Integer positionOfObjectiveNode = Integer.valueOf(value)-1;
			Integer positionAtual = matriz.indexOf(value);
			
			int objectiveCol = positionOfObjectiveNode%3;
			int objectiveRow = positionOfObjectiveNode/3;
			
			int atualCol = positionAtual%3;
			int atualRow = positionAtual/3;
			
			score+= (objectiveCol - atualCol)*(objectiveCol - atualCol);
			score+= (objectiveRow - atualRow)*(objectiveRow - atualRow);
		}
		return score;
	}
}
