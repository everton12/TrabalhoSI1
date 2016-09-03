import java.util.ArrayList;
import java.util.List;

public class Control {

	private List<Integer> matriz;
	
	public Control(){
		matriz = getTabuleiroRandom();
		algoritmo();
		printGame();
	}
	
	
	public void algoritmo(){
		//escrever o algoritmo aqui
		
		
		
		
	}
	
	
	public ArrayList<Integer> getTabuleiroRandom(){
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
		
		
		
		return matrizList;
	}
	
	
	public void printGame(){
		printMatriz(matriz);
	}
	
	public void printMatriz(List<Integer> matriz){
		for(int row = 0; row < 3; row++){
			String rowString = "";
			for(int column = 0; column < 3; column++){
				Integer listValue = matriz.get((row*3 + column));
				rowString+= (listValue > 0 ? listValue : " ") + "|";
			}
			System.out.println(rowString);
		}
	}
	
}
