import java.util.ArrayList;
import java.util.Arrays;
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
		
		while(verifyFinalNode(atualNode)){
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
		ArrayList<String> matrizList = new ArrayList<String>(Arrays.asList("1","2","3","4","5","6","7","8","0"));
		Node randomNode = new Node(matrizList);
		
		for(int index = 0; index < 50; index++){
			Integer enumNodeCode = (int )(Math.random() * 4);
			
			List<Integer> possibles = getPossibleMoves(randomNode.getMatriz().indexOf("0")/3, randomNode.getMatriz().indexOf("0")%3);
			
			if(possibles.contains(enumNodeCode)){
				randomNode = getMoveByEnum(MoveEnum.get(enumNodeCode), randomNode);
			}
		}
		
		printMatriz(randomNode);
		
		return randomNode;
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
		
		int rowAvailable = availablePosition/3; //0 a 2
		if(rowAvailable == 1 || rowAvailable == 2){
			String[][] matrizArray = new String[3][3];
			for(String position : matriz){
				int rowAdd = availablePosition/3; //0 a 2
				int columnAdd = availablePosition%3; //0 a 2
				
				matrizArray[rowAdd][columnAdd] = position;
			}
			
			int indexValue = rowAvailable - 1;
			
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
		
		Node nodeToMove = null;
		
		for(Integer index : possibleMoves){
			Node node = getMoveByEnum(MoveEnum.get(index), atualNode);
			if(!verifyCheckedNode(node)){
				if(nodeToMove == null || nodeToMove.getPunctuation() < node.getPunctuation()){
					nodeToMove = node;
				}
			}
		}
		
		if(nodeToMove != null){
			atualNode = nodeToMove;
			checkedNodes.put(atualNode.getHash(), atualNode);
			printMatriz(atualNode);
		}
	}
	
	private Node getMoveByEnum(MoveEnum move, Node node){
		Node nodeReturn = null;
		if(move == MoveEnum.UP){
			nodeReturn = moveUpPosition(node.getMatriz());
		} else if(move == MoveEnum.DOWN){
			nodeReturn = moveUpPosition(node.getMatriz());
		} else if(move == MoveEnum.LEFT){
			nodeReturn = moveUpPosition(node.getMatriz());
		} else if(move == MoveEnum.RIGHT){
			nodeReturn = moveUpPosition(node.getMatriz());
		}
		
		return nodeReturn;
	}

	private List<Integer> getPossibleMoves(int rowEmptyPosition, int columnEmptyPosition) {
		List<MoveEnum> possibleMoves = new ArrayList<MoveEnum>();
		
		if(rowEmptyPosition > 0){
			possibleMoves.add(MoveEnum.UP);
		}
		
		if(rowEmptyPosition < 2){
			possibleMoves.add(MoveEnum.DOWN);
		}
		
		if(columnEmptyPosition > 0){
			possibleMoves.add(MoveEnum.LEFT);
		}
		
		if(columnEmptyPosition < 2){
			possibleMoves.add(MoveEnum.RIGHT);
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
