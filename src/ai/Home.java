package ai;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Home {

	Room[][] rooms = new Room[8][8];
	
	
	public Home() {
		createRooms();
	}

	
	public void createRooms(){
		String fname = "maze.txt";
		File file = new File(fname);
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = "";
			int row = 0;
			while ((line = br.readLine()) != null) {
				String[] columns = line.split("-");
				
				for (int i=0; i<columns.length; i++) {
					rooms[row][i] = new Room(columns[i], row, i);
				}
				row++;
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		rooms[7][7].goal = true;
	}
	
	public Room bfs(int row, int column){
		List<Room> visitedList = new ArrayList<Room>();
		Queue<Room> queue = new LinkedList<Room>();
		queue.add(rooms[row][column]);
		
		while (!queue.isEmpty()) {
			Room r = queue.remove();
			visitedList.add(r);
			System.out.println("Room x:"+(r.current.x+1)+"  y:"+(r.current.y+1));
			if (r.goal) {
				printSolution("bfs", r, visitedList);
				return r;
			}
			if (r.e && !visitedList.contains(rooms[r.current.x][r.current.y+1]) && !queue.contains(rooms[r.current.x][r.current.y+1])) {
				rooms[r.current.x][r.current.y+1].parent = new Pair(r.current.x, r.current.y);
				if (rooms[r.current.x][r.current.y+1].goal) {
					printSolution("bfs", rooms[r.current.x][r.current.y+1], visitedList);
					return rooms[r.current.x][r.current.y+1];
				}
				queue.add(rooms[r.current.x][r.current.y+1]);
				}
			if (r.s && !visitedList.contains(rooms[r.current.x+1][r.current.y]) && !queue.contains(rooms[r.current.x+1][r.current.y])) {
				rooms[r.current.x+1][r.current.y].parent = new Pair(r.current.x, r.current.y);
				if (rooms[r.current.x+1][r.current.y].goal) {
					printSolution("bfs",rooms[r.current.x+1][r.current.y],visitedList);
					return rooms[r.current.x+1][r.current.y];
				}
				queue.add(rooms[r.current.x+1][r.current.y]);
				}
			if (r.w && !visitedList.contains(rooms[r.current.x][r.current.y-1]) && !queue.contains(rooms[r.current.x][r.current.y-1])) {
				rooms[r.current.x][r.current.y-1].parent = new Pair(r.current.x, r.current.y);
				if (rooms[r.current.x][r.current.y-1].goal) {
					printSolution("bfs",rooms[r.current.x][r.current.y-1],visitedList);
					return rooms[r.current.x][r.current.y-1];
				}
				queue.add(rooms[r.current.x][r.current.y-1]);
				}
			if (r.n && !visitedList.contains(rooms[r.current.x-1][r.current.y]) && !queue.contains(rooms[r.current.x-1][r.current.y])) {
				rooms[r.current.x-1][r.current.y].parent = new Pair(r.current.x, r.current.y);
				if (rooms[r.current.x-1][r.current.y].goal) {
					printSolution("bfs",rooms[r.current.x-1][r.current.y],visitedList);
					return rooms[r.current.x-1][r.current.y];
				}
				queue.add(rooms[r.current.x-1][r.current.y]);
				}
		}
		return null;
	}
	
	public Room dfs(int row, int column){
		List<Room> visitedList = new ArrayList<Room>();
		Stack<Room> stack = new Stack<Room>();
		stack.push(rooms[row][column]);
		
		while(!stack.isEmpty()){
			Room r = stack.pop();
			visitedList.add(r);
			System.out.println("Room x:"+(r.current.x+1)+"  y:"+(r.current.y+1));
			if (r.goal) {
				printSolution("dfs",r,visitedList);
				return r;
			}
			if (r.e && !visitedList.contains(rooms[r.current.x][r.current.y+1]) && !stack.contains(rooms[r.current.x][r.current.y+1])) {
				rooms[r.current.x][r.current.y+1].parent = new Pair(r.current.x, r.current.y);
				if (rooms[r.current.x][r.current.y+1].goal) {
					printSolution("dfs", rooms[r.current.x][r.current.y+1], visitedList);
					return rooms[r.current.x][r.current.y+1];
				}
				stack.push(rooms[r.current.x][r.current.y+1]);
				}
			if (r.s && !visitedList.contains(rooms[r.current.x+1][r.current.y]) && !stack.contains(rooms[r.current.x+1][r.current.y])) {
				rooms[r.current.x+1][r.current.y].parent = new Pair(r.current.x, r.current.y);
				if (rooms[r.current.x+1][r.current.y].goal) {
					printSolution("dfs",rooms[r.current.x+1][r.current.y],visitedList);
					return rooms[r.current.x+1][r.current.y];
				}
				stack.push(rooms[r.current.x+1][r.current.y]);
				}
			if (r.w && !visitedList.contains(rooms[r.current.x][r.current.y-1]) && !stack.contains(rooms[r.current.x][r.current.y-1])) {
				rooms[r.current.x][r.current.y-1].parent = new Pair(r.current.x, r.current.y);
				if (rooms[r.current.x][r.current.y-1].goal) {
					printSolution("dfs",rooms[r.current.x][r.current.y-1],visitedList);
					return rooms[r.current.x][r.current.y-1];
				}
				stack.push(rooms[r.current.x][r.current.y-1]);
				}
			if (r.n && !visitedList.contains(rooms[r.current.x-1][r.current.y]) && !stack.contains(rooms[r.current.x-1][r.current.y])) {
				rooms[r.current.x-1][r.current.y].parent = new Pair(r.current.x, r.current.y);
				if (rooms[r.current.x-1][r.current.y].goal) {
					printSolution("bfs",rooms[r.current.x-1][r.current.y],visitedList);
					return rooms[r.current.x-1][r.current.y];
				}
				stack.push(rooms[r.current.x-1][r.current.y]);
				}
		}
		
		return null;
	}
	
	public void printSolution(String alg, Room r, List<Room> visited){
		File file = new File(""+alg+".txt");
		PrintWriter pw;
		try {
			pw = new PrintWriter(file);
			pw.append("Solution Path:\n" );
			int totalCost = 1;
			totalCost = printSolutionPath(r, pw, totalCost);
			pw.append("Solution Cost: "+totalCost+"\n");
			
			if (!"ids".equals(alg)) { // printed on console but small problem on writing file
				pw.append("Expanded Path\n");
				printExpanded(pw, visited);
			}
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void printExpanded(PrintWriter pw, List<Room> visited) {
		for (Room room : visited) {
			pw.append("x: "+(room.current.x+1)+"\ty: "+(room.current.y+1)+"\n");
		}
	}


	public int printSolutionPath(Room r, PrintWriter pw, Integer totalCost){
		pw.append("x: "+(r.current.x+1)+"\ty: "+(r.current.y+1)+"\n");
		if(r.parent != null)
			totalCost = printSolutionPath(rooms[r.parent.x][r.parent.y], pw, ++totalCost);
		return totalCost;
	}
	
	public Room dls(Room r, int limit){
		
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
	
	public Room ids(int row, int column){
		Room r = null;
		int limit = 0;
		while ((r = dls(rooms[row][column], limit)) == null){
			System.out.println("not found in limit:"+limit);
			limit++;
		}
		rooms[row][column].parent = null;
		printSolution("ids", r, null);
		return r;
	}
	
	
	public Room uniform(int row, int column){
		List<Room> visitedList = new ArrayList<Room>();
		List<Room> queue = new ArrayList<Room>();
		queue.add(rooms[row][column]);
		
		
		while(!queue.isEmpty()){
			Collections.sort(queue, new Comparator<Room>() {

				@Override
				public int compare(Room o1, Room o2) {
					// TODO Auto-generated method stub
					return o1.cost>o2.cost?1:-1;
				}
			});
			Room r = queue.remove(0);
			if (r.goal) {
				printSolution("uniform", r, visitedList);
				return r;
			}
			double cost = 0;
			if(r.parent != null){
				cost = rooms[r.parent.x][r.parent.y].cost+1;
			}
			visitedList.add(r);
			if (r.e && !visitedList.contains(rooms[r.current.x][r.current.y+1]) && !queue.contains(rooms[r.current.x][r.current.y+1])) {
				rooms[r.current.x][r.current.y+1].parent = new Pair(r.current.x, r.current.y);
				
				queue.add(rooms[r.current.x][r.current.y+1]);
			}else if(r.e && queue.contains(rooms[r.current.x][r.current.y+1]) && rooms[r.current.x][r.current.y+1].cost>cost){
				rooms[r.current.x][r.current.y+1].cost = cost;
				rooms[r.current.x][r.current.y+1].parent = new Pair(r.current.x, r.current.y);
			}
			if (r.s && !visitedList.contains(rooms[r.current.x+1][r.current.y]) && !queue.contains(rooms[r.current.x+1][r.current.y])) {
				rooms[r.current.x+1][r.current.y].parent = new Pair(r.current.x, r.current.y);
				
				queue.add(rooms[r.current.x+1][r.current.y]);
			}else if(r.s && queue.contains(rooms[r.current.x+1][r.current.y]) && rooms[r.current.x+1][r.current.y].cost>cost){
				rooms[r.current.x+1][r.current.y].cost = cost;
				rooms[r.current.x+1][r.current.y].parent = new Pair(r.current.x, r.current.y);
			}
			if (r.w && !visitedList.contains(rooms[r.current.x][r.current.y-1]) && !queue.contains(rooms[r.current.x][r.current.y-1])) {
				rooms[r.current.x][r.current.y-1].parent = new Pair(r.current.x, r.current.y);
				
				queue.add(rooms[r.current.x][r.current.y-1]);
			}else if(r.w && queue.contains(rooms[r.current.x][r.current.y-1]) && rooms[r.current.x][r.current.y-1].cost>cost){
				rooms[r.current.x][r.current.y-1].cost = cost;
				rooms[r.current.x][r.current.y-1].parent = new Pair(r.current.x, r.current.y);
			}
			if (r.n && !visitedList.contains(rooms[r.current.x-1][r.current.y]) && !queue.contains(rooms[r.current.x-1][r.current.y])) {
				rooms[r.current.x-1][r.current.y].parent = new Pair(r.current.x, r.current.y);
				
				queue.add(rooms[r.current.x-1][r.current.y]);
			}else if(r.n && queue.contains(rooms[r.current.x-1][r.current.y]) && rooms[r.current.x-1][r.current.y].cost>cost){
				rooms[r.current.x-1][r.current.y].cost = cost;
				rooms[r.current.x-1][r.current.y].parent = new Pair(r.current.x, r.current.y);
			}
			
		}
		
		return null;
		
	}
	
	public Room greedy(int row, int column){
		List<Room> visitedList = new ArrayList<Room>();
		List<Room> queue = new ArrayList<Room>();
		queue.add(rooms[row][column]);
		
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
				printSolution("greedy", r, visitedList);
				return r;
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
		return null;
	}
	public Room astar(int row, int column){
		List<Room> visitedList = new ArrayList<Room>();
		List<Room> queue = new ArrayList<Room>();
		queue.add(rooms[row][column]);
		
		while(!queue.isEmpty()){
			Collections.sort(queue, new Comparator<Room>() {
				
				@Override
				public int compare(Room o1, Room o2) {
					return o1.cost>o2.cost?1:-1;
				}
			});
			Room r = queue.remove(0);
			double cost = 0;
			if(r.parent != null){
				cost = rooms[r.parent.x][r.parent.y].cost+1+r.manhattonCost;
			}
			System.out.println("Room x-y:"+r.current.x+"-"+r.current.y);
			visitedList.add(r);
			if (r.goal) {
				printSolution("astar",r,visitedList);
				return r;
			}
			
			if (r.e && !visitedList.contains(rooms[r.current.x][r.current.y+1]) && !queue.contains(rooms[r.current.x][r.current.y+1])) {
				rooms[r.current.x][r.current.y+1].parent = new Pair(r.current.x, r.current.y);
				rooms[r.current.x][r.current.y+1].cost = cost;
				queue.add(rooms[r.current.x][r.current.y+1]);
			}
			if (r.s && !visitedList.contains(rooms[r.current.x+1][r.current.y]) && !queue.contains(rooms[r.current.x+1][r.current.y])) {
				rooms[r.current.x+1][r.current.y].parent = new Pair(r.current.x, r.current.y);
				rooms[r.current.x+1][r.current.y].cost = cost;
				queue.add(rooms[r.current.x+1][r.current.y]);
			}
			if (r.w && !visitedList.contains(rooms[r.current.x][r.current.y-1]) && !queue.contains(rooms[r.current.x][r.current.y-1])) {
				rooms[r.current.x][r.current.y-1].parent = new Pair(r.current.x, r.current.y);
				rooms[r.current.x][r.current.y-1].cost = cost;
				
				queue.add(rooms[r.current.x][r.current.y-1]);
			}
			if (r.n && !visitedList.contains(rooms[r.current.x-1][r.current.y]) && !queue.contains(rooms[r.current.x-1][r.current.y])) {
				rooms[r.current.x-1][r.current.y].parent = new Pair(r.current.x, r.current.y);
				rooms[r.current.x-1][r.current.y].cost = cost;
				
				queue.add(rooms[r.current.x-1][r.current.y]);
			}
		}
		return null;
	}
	
	
}





