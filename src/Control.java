import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

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
		nodosEscolhidos = new LinkedHashMap<String, Node>();
		
		///vamos utilizar um embaralhamento inicial default somente enquanto desenvolvemos
		///quando for pra fazer os testes finais, nós perdemos tempo com a criação do random e tal
		ArrayList<String> initialNode = new ArrayList<String>(Arrays.asList("2", "3", "6", "1", "8", "0", "4", "5", "7"));
		atualNode = new Node(initialNode);
		
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


	///tem erros no conceito desse algoritmo, aquilo que comentei contigo pelo whats
//	public Node getRandomNode(){
//		String [][] matrizAux = { {"1","2","3"}, {"4","5","6"}, {"7","8","0"}};
//		ArrayList<String> matrizList = new ArrayList<String>();
//		
//		for (int i = 0; i < matrizAux.length; i++) {
//			for (int j = 0; j < matrizAux[i].length; j++) {
//				while(matrizList.size() < 9){
//					int x = getRandomNumber(3);
//					int y = getRandomNumber(3);
//					
//					if(!matrizList.contains(matrizAux[x][y])){
//						matrizList.add(matrizAux[x][y]);
//					}
//				}
//			}
//		}
//		
//		Node node = new Node(matrizList);
//		printMatriz(node);
//		return node;
//	}
	
	
	///ok move o espaço vazio para a esquerda
	public Node moveLeftPosition(List<String> matriz){
		System.out.println("left");
		int availablePosition = matriz.indexOf("0");
		
		//0|1|2
		int col = availablePosition%3;
		if( col > 0 ){
			int idx_to_move = availablePosition-1;
			matriz = move(matriz, availablePosition, idx_to_move);
		}
		
		Node node = new Node(matriz);
		printMatriz(node);
		return node;
	}
	
	
	//ok ///move o espaço vazio para a direita
	public Node moveRightPosition(List<String> matriz){
		System.out.println("right");
		int availablePosition = matriz.indexOf("0");
		
		//0|1|2
		int col = availablePosition%3;
		if( col < (SIZE_OF_TABULEIRO-1) ){
			int idx_to_move = availablePosition+1; 
			matriz = move(matriz, availablePosition, idx_to_move);
		}
		
		Node node = new Node(matriz);
		printMatriz(node);
		return node;
	}
	
	
	///OK
	public Node moveUpPosition(List<String> matriz){
		System.out.println("up");
		int availablePosition = matriz.indexOf("0");
		
		int row = availablePosition/3; //1 a 2
		if(row > 0){
			int idx_to_move = availablePosition-SIZE_OF_TABULEIRO; 
			matriz = move(matriz, availablePosition, idx_to_move);
		}
		
		Node node = new Node(matriz);
		printMatriz(node);
		return node;
	}
	
	
	///OK
	public Node moveDownPosition(List<String> matriz){
		System.out.println("down");
		int availablePosition = matriz.indexOf("0");
		
		int row = availablePosition/3; //0 a 2
		if(row < (SIZE_OF_TABULEIRO-1)){
			int idx_to_move = availablePosition+SIZE_OF_TABULEIRO;
			matriz = move(matriz, availablePosition, idx_to_move);
		}
		
		Node node = new Node(matriz);
		printMatriz(node);
		return node;
	}
	
	//ok ///Criado pq a forma de trocas os elementos do array é feito da mesma maneira pra todas as movimentações
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
		System.out.println("  >>>  " + emptyPosition +" "+ columnEmptyPosition + " " + rowEmptyPosition);
		
		List<MoveEnum> possibleMoves = getPossibleMoves(rowEmptyPosition, columnEmptyPosition);
	}
	
	//ok, esta pegando as possiblesMoves, só não tá retornando nada 
	private List<MoveEnum> getPossibleMoves(int rowEmptyPosition, int columnEmptyPosition) {
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
		
		for(MoveEnum position : possibleMoves){
			System.out.println(position.getType());
		}
		
//		List<Integer> movePositions = new ArrayList<Integer>();
		
//		for(Integer position : movePositions){
//			int column = position%3;
//			int row = position/3;
//		}
		
		if(possibleMoves.size() > 0){
			MoveEnum move = possibleMoves.get(0);
			
			if( possibleMoves.contains(MoveEnum.LEFT) ){
				atualNode = moveLeftPosition(atualNode.getMatriz());
			}else{
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
			
		}
		
//		return new ArrayList<Integer>();
		return possibleMoves;
	}
	
	//TODO
	public int getHeuristicPunctuation(Node node_to_get){
		
		
		return 0;
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
