package ai;

public class IterativeDeepeningSearch extends AbstractSearch {

	public IterativeDeepeningSearch(Pair startNode) {
		this.startNode = startNode;
		createRooms();
	}
	
  public Room dls(Room r, int limit){
		
	    visitedList.add(r);
		if (r.goal) {
			return r;
		}else if (limit == 0) {
			return null;
		}else {
			Room result = null;
			if (r.e && ((r.parent != null )? (r.current.y+1)!=r.parent.y:true)) {
				rooms[r.current.x][r.current.y+1].parent = r.current;
				result = dls(rooms[r.current.x][r.current.y+1], limit-1);
				if (result != null) {
					return result;
				}
			}
			if (r.s && ((r.parent != null )? (r.current.x+1)!=r.parent.x:true)) {
				rooms[r.current.x+1][r.current.y].parent = r.current;
				result = dls(rooms[r.current.x+1][r.current.y], limit-1);
				if (result != null) {
					return result;
				}
			}
			if (r.w && ((r.parent != null )? (r.current.y-1)!=r.parent.y:true)) {
				rooms[r.current.x][r.current.y-1].parent = r.current;
				result = dls(rooms[r.current.x][r.current.y-1], limit-1);
				if (result != null) {
					return result;
				}
			}
			if (r.n && ((r.parent != null )? (r.current.x-1)!=r.parent.x:true)) {
				rooms[r.current.x-1][r.current.y].parent = r.current;
				result = dls(rooms[r.current.x-1][r.current.y], limit-1);
				if (result != null) {
					return result;
				}
			}
		}
		return null;
	}
	
	public void ids(){
		Room r = null;
		int limit = 0;
		while ((r = dls(rooms[startNode.x][startNode.y], limit)) == null){
			System.out.println("not found in limit:"+limit);
			limit++;
		}
		rooms[startNode.x][startNode.y].parent = null;
		printSolution("ids", r);
	}
	
}
