package ai;

public class Test {

	public static void main(String[] args) {
		Home home = new Home();
		Room r = home.dfs(2, 1);
		home.createRooms();
		r = home.bfs(2, 1);
		home.createRooms();
		r = home.ids(2, 1);
		home.createRooms();
		r = home.uniform(2, 1);
		home.createRooms();
		r = home.greedy(2, 1);
		home.createRooms();
		r = home.astar(2, 1);
		
	}
	
}
