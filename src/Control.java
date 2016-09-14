import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

public class Control {

	public static int SIZE_OF_TABULEIRO = 3;
	public static Node OBJECTIVE_NODE = new Node(new ArrayList<String>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "0")));
	
	protected Node atualNode;
	protected LinkedHashMap<String, Node> checkedNodes;
	protected LinkedHashMap<String, Node> nodosEscolhidos;

	public Control(){
		initializeControls();
	}
	
	
	public void initializeControls(){
		checkedNodes = new LinkedHashMap<String, Node>();
		atualNode = getRandomNode();
		
		while(!verifyFinalNode(atualNode)){
			useHeuristicToMove();
		}
	}
	
	private boolean verifyCheckedNode(Node atualNode2) {
		return checkedNodes.containsKey(atualNode2.getHash());
	}


	private boolean verifyFinalNode(Node atualNode2) {
		boolean isFinalNode = true;
		List<String> auxList = new ArrayList<String>(atualNode2.getMatriz());
		auxList.remove(auxList.indexOf("0"));
		for(String nodePosition : auxList){
			if(!Integer.valueOf(nodePosition).equals(((auxList.indexOf(nodePosition)+1)))){
				isFinalNode = false;
				break;
			}
		}
		return isFinalNode;
	}


	public Node getRandomNode(){
		ArrayList<String> matrizList = new ArrayList<String>(Arrays.asList("1","2","3","4","5","6","7","8","0"));
		Node randomNode = new Node(matrizList);
		
		Random gerador = new Random();
		 
		for(int index = 0; index < 500; index++){
			Integer enumNodeCode = new Integer(gerador.nextInt(3));
			
			List<Integer> possibles = getPossibleMoves(randomNode.getMatriz().indexOf("0")/3, randomNode.getMatriz().indexOf("0")%3);
			
			if(possibles.contains(enumNodeCode)){
				randomNode = getMoveByEnum(MoveEnum.get(enumNodeCode), randomNode);
			}
		}
		
		printMatriz(randomNode);
		
		return randomNode;
	}
	
	
	///ok move o espa�o vazio para a esquerda
	public Node moveLeftPosition(List<String> matriz){
		int availablePosition = matriz.indexOf("0");
		
		//0|1|2
		int col = availablePosition%3;
		if( col > 0 ){
			int idx_to_move = availablePosition-1;
			matriz = move(matriz, availablePosition, idx_to_move);
		}
		
		Node node = new Node(matriz);
//		printMatriz(node);
		return node;
	}
	
	
	//ok ///move o espa�o vazio para a direita
	public Node moveRightPosition(List<String> matriz){
		int availablePosition = matriz.indexOf("0");
		
		//0|1|2
		int col = availablePosition%3;
		if( col < (SIZE_OF_TABULEIRO-1) ){
			int idx_to_move = availablePosition+1; 
			matriz = move(matriz, availablePosition, idx_to_move);
		}
		
		Node node = new Node(matriz);
//		printMatriz(node);
		return node;
	}
	
	
	///OK
	public Node moveUpPosition(List<String> matriz){
		int availablePosition = matriz.indexOf("0");
		
		int row = availablePosition/3; //1 a 2
		if(row > 0){
			int idx_to_move = availablePosition-SIZE_OF_TABULEIRO; 
			matriz = move(matriz, availablePosition, idx_to_move);
		}
		
		Node node = new Node(matriz);
//		printMatriz(node);
		return node;
	}
	
	
	///OK
	public Node moveDownPosition(List<String> matriz){
		int availablePosition = matriz.indexOf("0");
		
		int row = availablePosition/3; //0 a 2
		if(row < (SIZE_OF_TABULEIRO-1)){
			int idx_to_move = availablePosition+SIZE_OF_TABULEIRO;
			matriz = move(matriz, availablePosition, idx_to_move);
		}
		
		Node node = new Node(matriz);
//		printMatriz(node);
		return node;
	}
	
	//ok ///Criado pq a forma de trocas os elementos do array � feito da mesma maneira pra todas as movimenta��es
	public List<String> move( List<String> matriz, int availablePosition, int idx_to_move ){
		String valid_item_to_move = matriz.get(idx_to_move);
		
		matriz.remove("0");
		matriz.add(availablePosition, "temp");
		
		matriz.add(idx_to_move, "0");
		matriz.remove(valid_item_to_move);
		matriz.remove("temp");
		
		matriz.add(availablePosition, valid_item_to_move);
		
		return matriz;
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
			nodeReturn = moveDownPosition(node.getMatriz());
		} else if(move == MoveEnum.LEFT){
			nodeReturn = moveLeftPosition(node.getMatriz());
		} else if(move == MoveEnum.RIGHT){
			nodeReturn = moveRightPosition(node.getMatriz());
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
		
		ArrayList<Integer> moves = new ArrayList<Integer>();
		for (int i = 0; i < possibleMoves.size(); i++) {
			moves.add(possibleMoves.get(i).getType());
		}
		
		return moves;
	}
	
	public boolean isObjectiveNode(Node node){
		
		if(node.equals(OBJECTIVE_NODE))
			return true;
		else return false;
	}
	
	//ok
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
	
	public int getRandomNumber(int max){
		return (int )(Math.random() * max);
	}
}
