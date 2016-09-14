
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
	
	public static MoveEnum get(int code) {
		switch (code) {
		case 0:
			return UP;
		case 1:
			return DOWN;
		case 2:
			return LEFT;
		case 3:
			return RIGHT;
		default:
			return null;
		}
	}
	
	
}
