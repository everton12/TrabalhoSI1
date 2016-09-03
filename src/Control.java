import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Control {
	
	protected Node atualNode;
	protected LinkedHashMap<String, Node> checkedNodes;
	protected LinkedHashMap<String, Node> finalNodes;

	public Control(){
		initializeControls();
	}
	
	
	public void initializeControls(){
		checkedNodes = new LinkedHashMap<String, Node>();
		finalNodes = new LinkedHashMap<String, Node>();
		atualNode = getRandomNode();
		
		checkedNodes.put(atualNode.getHash(), atualNode);
		
		while(verifyFinalNode(atualNode) || verifyCheckedNode(atualNode)){
			useHeuristicToMove();
		}
	}
	
	private boolean verifyCheckedNode(Node atualNode2) {
		return checkedNodes.containsKey(atualNode2.getHash());
	}


	private boolean verifyFinalNode(Node atualNode2) {
		List<String> auxList = new ArrayList<String>(atualNode2.getMatriz());
		auxList.remove(auxList.indexOf("0"));
		for(String nodePosition : auxList){
			if(!nodePosition.equals(((auxList.indexOf(nodePosition + "")+1)))){
				break;
			}
		}
		return false;
	}


	public Node getRandomNode(){
		String [][] matrizAux = { {"1","2","3"}, {"4","5","6"}, {"7","8","0"}};
		ArrayList<String> matrizList = new ArrayList<String>();
		
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
	
	public Node moveLeftPosition(List<String> matriz){
		System.out.println("left");
		int availablePosition = matriz.indexOf("0");
		//0|1|2
		int col = availablePosition%3;
		if(col == 0 || col == 1){
			matriz.add(availablePosition, matriz.get(availablePosition+1));
			matriz.add(availablePosition+1, "0");
		}
		
		Node node = new Node(matriz);
		printMatriz(node);
		return node;
	}
	
	public Node moveRightPosition(List<String> matriz){
		System.out.println("right");
		int availablePosition = matriz.indexOf("0");
		//0|1|2
		int col = availablePosition%3;
		if(col == 1 || col == 2){
			matriz.add(availablePosition, matriz.get(availablePosition-1));
			matriz.add(availablePosition-1, "0");
		}
		
		Node node = new Node(matriz);
		printMatriz(node);
		return node;
	}
	
	public Node moveUpPosition(List<String> matriz){
		System.out.println("up");
		int availablePosition = matriz.indexOf("0");
		
		int row = availablePosition/3; //0 a 2
		int column = availablePosition%3; //0 a 2
		if(row == 1 || row == 2){
			int movedIndex = ((row-1)*3)+column;
			int newIndex = ((row*3)+column);
			
			String movedPosition = matriz.get(movedIndex);
			matriz.add(newIndex, movedPosition);
			matriz.remove("0");
			matriz.add(movedIndex, "0");
			matriz.remove(movedIndex+1);
		}
		
		Node node = new Node(matriz);
		printMatriz(node);
		return node;
	}
	
	public Node moveDownPosition(List<String> matriz){
		System.out.println("down");
		int availablePosition = matriz.indexOf("0");
		
		int row = availablePosition/3; //0 a 2
		int column = availablePosition%3; //0 a 2
		if(row == 0 || row == 1){
			int movedIndex = ((row-1)*3)+column;
			int newIndex = ((row*3)+column-1);
			
			String movedPosition = matriz.get(movedIndex);
			matriz.remove("0");
			matriz.remove(movedIndex);
			matriz.add(newIndex, movedPosition);
			matriz.add(movedIndex, "0");
		}
		
		Node node = new Node(matriz);
		printMatriz(node);
		return node;
	}
	
	public void useHeuristicToMove() {
		int emptyPosition = atualNode.getMatriz().indexOf("0");
		int columnEmptyPosition = emptyPosition%3;
		int rowEmptyPosition = emptyPosition/3;
		
		List<Integer> possibleMoves = getPossibleMoves(rowEmptyPosition, columnEmptyPosition);
	}

	private List<Integer> getPossibleMoves(int rowEmptyPosition, int columnEmptyPosition) {
		List<MoveEnum> possibleMoves = new ArrayList<MoveEnum>();
		
		if(rowEmptyPosition > 0){
			possibleMoves.add(MoveEnum.UP);
		}
		
//		if(rowEmptyPosition < 2){
//			possibleMoves.add(MoveEnum.DOWN);
//		}
//		
//		if(columnEmptyPosition > 0){
//			possibleMoves.add(MoveEnum.LEFT);
//		}
//		
//		if(columnEmptyPosition < 2){
//			possibleMoves.add(MoveEnum.RIGHT);
//		}
		
//		List<Integer> movePositions = new ArrayList<Integer>();
//		
//		for(Integer position : movePositions){
//			int column = position%3;
//			int row = position/3;
//		}
		
		if(possibleMoves.size() > 0){
			MoveEnum move = possibleMoves.get(0);
			
			if(move == MoveEnum.UP){
				atualNode = moveUpPosition(atualNode.getMatriz());
			} else if(move == MoveEnum.DOWN){
				atualNode = moveDownPosition(atualNode.getMatriz());
			} else if(move == MoveEnum.RIGHT){
				atualNode = moveRightPosition(atualNode.getMatriz());
			} else if(move == MoveEnum.LEFT){
				atualNode = moveLeftPosition(atualNode.getMatriz());
			}
		}
		
		return new ArrayList<Integer>();
	}
	
	public void printMatriz(Node node){
		for(int row = 0; row < 3; row++){
			String rowString = "";
			for(int column = 0; column < 3; column++){
				String listValue = node.getMatriz().get((row*3 + column));
				rowString+= (!listValue.equals("0") ? listValue : " ") + "|";
			}
			System.out.println(rowString);
		}
		
		System.out.println("\n\n");
	}
	
}
