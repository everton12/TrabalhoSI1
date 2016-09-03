
public class Control {

	private Integer [][] matriz;
	
	public Control(){
		matriz = getTabuleiroRamdom();
		algoritmo();
		printGame();
	}
	
	
	public void algoritmo(){
		//escrever o algoritmo aqui
		
		
		
		
	}
	
	
	public Integer [][] getTabuleiroRamdom(){
		Integer [][] matrizAux = { {1,2,3}, {4,5,6}, {7,8, null}};
		Integer [][] matriz = new Integer[3][3];
		
		boolean isNull = true;
		for (int i = 0; i < matrizAux.length; i++) {
			for (int j = 0; j < matrizAux[i].length; j++) {
				
				while( isNull ){
					int x = (int )(Math.random() * 3);
					int y = (int )(Math.random() * 3);
					
					if(matriz[x][y]==null){
						matriz[x][y] = matrizAux[i][j];
						isNull = false;
					}
				}
				isNull = true;
			}
		}
		
		return matriz;
	}
	
	
	public void printGame(){
		printMatriz(matriz);
	}
	
	public void printMatriz(Integer [][] matriz){
		System.out.println( (matriz[0][0]==null?" ":matriz[0][0]) + " | " + (matriz[0][1]==null?" ":matriz[0][1]) + " | " + (matriz[0][2]==null?" ":matriz[0][2]));
		System.out.println( (matriz[1][0]==null?" ":matriz[1][0]) + " | " + (matriz[1][1]==null?" ":matriz[1][1]) + " | " + (matriz[1][2]==null?" ":matriz[1][2]));
		System.out.println( (matriz[2][0]==null?" ":matriz[2][0]) + " | " + (matriz[2][1]==null?" ":matriz[2][1]) + " | " + (matriz[2][2]==null?" ":matriz[2][2]));
	}
	
}
