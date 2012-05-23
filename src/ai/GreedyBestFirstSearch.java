package ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GreedyBestFirstSearch extends AbstractSearch {

	public GreedyBestFirstSearch(Pair startNode) {
		this.startNode = startNode;
		createRooms();
	}
	
	public void greedy(){
		List<Room> queue = new ArrayList<Room>();
		queue.add(rooms[startNode.x][startNode.y]);
		
		while(!queue.isEmpty()){
			Collections.sort(queue, new Comparator<Room>() {

				@Override
				public int compare(Room o1, Room o2) {
					return o1.manhattonCost>o2.manhattonCost?1:-1;
				}
			});
			Room r = queue.remove(0);
			System.out.println("Room x-y:"+r.current.x+"-"+r.current.y);
			if (r.goal) {
				printSolution("greedy", r);
				return;
			}
			
			visitedList.add(r);
			if (r.e && !visitedList.contains(rooms[r.current.x][r.current.y+1]) && !queue.contains(rooms[r.current.x][r.current.y+1])) {
				rooms[r.current.x][r.current.y+1].parent = new Pair(r.current.x, r.current.y);
				queue.add(rooms[r.current.x][r.current.y+1]);
			}
			if (r.s && !visitedList.contains(rooms[r.current.x+1][r.current.y]) && !queue.contains(rooms[r.current.x+1][r.current.y])) {
				rooms[r.current.x+1][r.current.y].parent = new Pair(r.current.x, r.current.y);
				queue.add(rooms[r.current.x+1][r.current.y]);
			}
			if (r.w && !visitedList.contains(rooms[r.current.x][r.current.y-1]) && !queue.contains(rooms[r.current.x][r.current.y-1])) {
				rooms[r.current.x][r.current.y-1].parent = new Pair(r.current.x, r.current.y);
				queue.add(rooms[r.current.x][r.current.y-1]);
			}
			if (r.n && !visitedList.contains(rooms[r.current.x-1][r.current.y]) && !queue.contains(rooms[r.current.x-1][r.current.y])) {
				rooms[r.current.x-1][r.current.y].parent = new Pair(r.current.x, r.current.y);
				queue.add(rooms[r.current.x-1][r.current.y]);
			}
		}
	}
	
}
