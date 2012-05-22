package ai;

public class Room {

	Pair parent;
	final Pair current;
	boolean goal,e,w,s,n;
	
	double cost, manhattonCost; //for uniform cost
	
	public Room(String alias, int x, int y){
		//ESWN
		this.n = (alias.charAt(0)=='0')?true:false;
		this.e = (alias.charAt(1)=='0')?true:false;
		this.s = (alias.charAt(2)=='0')?true:false;
		this.w = (alias.charAt(3)=='0')?true:false;
		this.current = new Pair(x, y);
		cost = 0;
		manhattonCost = Math.sqrt((Math.pow((7-x), 2)+Math.pow((7-y), 2)));
	}
}
