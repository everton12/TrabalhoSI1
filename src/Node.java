import java.util.ArrayList;

public class Node {
	
	protected ArrayList<Integer> matriz;

	public Node(ArrayList<Integer> matriz) {
		this.matriz = matriz;
	}
	
	public Node moveLeftPosition(){
		int availablePosition = matriz.indexOf(0);
		
		if(availablePosition%3 == 0 || availablePosition%3 == 1){
			matriz.add(availablePosition, matriz.get(availablePosition+1));
			matriz.add(availablePosition+1, 0);
		}
		return new Node(matriz);
	}
	
	public Node moveRightPosition(){
		int availablePosition = matriz.indexOf(0);
		
		if(availablePosition%3 == 1 || availablePosition%3 == 2){
			matriz.add(availablePosition, matriz.get(availablePosition-1));
			matriz.add(availablePosition-1, 0);
		}
		return new Node(matriz);
	}
	
	public Node moveUpPosition(){
		int availablePosition = matriz.indexOf(0);
		
		if(availablePosition%3 == 1 || availablePosition%3 == 2){
			matriz.add(availablePosition, matriz.get(availablePosition-1));
			matriz.add(availablePosition-1, 0);
		}
		return new Node(matriz);
	}
	
	public Node moveDownPosition(){
		Node node = new Node(matriz);
		return node;
	}
}
