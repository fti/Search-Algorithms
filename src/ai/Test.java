package ai;

public class Test {

	public static void main(String[] args) {
		
		Pair startNode = new Pair(2, 1);
		
		DepthFirstSearch dfs = new DepthFirstSearch(startNode);
		dfs.dfs();
		
		BreadthFirstSearch bfs = new BreadthFirstSearch(startNode);
		bfs.bfs();
		
		IterativeDeepeningSearch ids = new IterativeDeepeningSearch(startNode);
		ids.ids();
		
		UniformCostSearch uniform = new UniformCostSearch(startNode);
		uniform.uniform();
		
		GreedyBestFirstSearch greedy = new GreedyBestFirstSearch(startNode);
		greedy.greedy();
		
		AStarSearch aStar = new AStarSearch(startNode);
		aStar.astar();
		
	}
	
}
