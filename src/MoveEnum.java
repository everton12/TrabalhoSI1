
public enum MoveEnum {

	UP(0),
	DOWN(1),
	LEFT(2),
	RIGHT(3);
	
	int type;
	
	MoveEnum(int type){
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
}
