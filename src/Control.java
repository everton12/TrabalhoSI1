import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Control {
	
	protected Node atualNode;
	protected LinkedHashMap<String, Node> checkedNodes;
	

	public Control(){
		algoritmo();
	}
	
	
	public void algoritmo(){
		checkedNodes = new LinkedHashMap<String, Node>();
		atualNode = getRandomNode();
		
		checkedNodes.put(atualNode.getHash(), atualNode);
		
//		while(verifyFinalNode(atualNode) || verifyCheckedNode(atualNode)){
//			
//		}
	}
	
	
	private boolean verifyCheckedNode(Node atualNode2) {
		return checkedNodes.containsKey(atualNode2.getHash());
	}


	private boolean verifyFinalNode(Node atualNode2) {
		return false;
	}


	public Node getRandomNode(){
		Integer [][] matrizAux = { {1,2,3}, {4,5,6}, {7,8, 0}};
		ArrayList<Integer> matrizList = new ArrayList<Integer>();
		
		for (int i = 0; i < matrizAux.length; i++) {
			for (int j = 0; j < matrizAux[i].length; j++) {
				while(matrizList.size() < 9){
					int x = (int )(Math.random() * 3);
					int y = (int )(Math.random() * 3);
					
					if(!matrizList.contains(matrizAux[x][y])){
						matrizList.add(matrizAux[x][y]);
					}
				}
			}
		}
		
		Node node = new Node(matrizList);
		printMatriz(node);
		return node;
	}
	
	public Node moveLeftPosition(List<Integer> matriz){
		int availablePosition = matriz.indexOf(0);
		//0|1|2
		int col = availablePosition%3;
		if(col == 0 || col == 1){
			matriz.add(availablePosition, matriz.get(availablePosition+1));
			matriz.add(availablePosition+1, 0);
		}
		
		Node node = new Node(matriz);
		printMatriz(node);
		return node;
	}
	
	public Node moveRightPosition(List<Integer> matriz){
		int availablePosition = matriz.indexOf(0);
		//0|1|2
		int col = availablePosition%3;
		if(col == 1 || col == 2){
			matriz.add(availablePosition, matriz.get(availablePosition-1));
			matriz.add(availablePosition-1, 0);
		}
		
		Node node = new Node(matriz);
		printMatriz(node);
		return node;
	}
	
	public Node moveUpPosition(List<Integer> matriz){
		int availablePosition = matriz.indexOf(0);
		
		int row = availablePosition/3;
		if(row == 0 || row == 1){
			matriz.add(availablePosition, matriz.get(availablePosition-1));
			matriz.add(availablePosition-1, 0);
		}
		
		Node node = new Node(matriz);
		printMatriz(node);
		return node;
	}
	
	public Node moveDownPosition(List<Integer> matriz){
	int availablePosition = matriz.indexOf(0);
		
		int row = availablePosition/3;
		if(row == 1 || row == 2){
			matriz.add(availablePosition, matriz.get(availablePosition-1));
			matriz.add(availablePosition-1, 0);
		}
		
		Node node = new Node(matriz);
		printMatriz(node);
		return node;
	}
	
	public void printMatriz(Node node){
		for(int row = 0; row < 3; row++){
			String rowString = "";
			for(int column = 0; column < 3; column++){
				Integer listValue = node.getMatriz().get((row*3 + column));
				rowString+= (listValue > 0 ? listValue : " ") + "|";
			}
			System.out.println(rowString);
		}
	}
	
}
