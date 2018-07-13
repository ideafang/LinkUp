package tools;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Linkup.Block;

public class Algorithm {
	private static List<Point> list = new ArrayList<Point>();
	
	/* 连通总方法判断
	 * 找到返回list（连通点的集合）
	 * 失败返回null */
	public static List<Point> checkLinked(Block[][] arr, Point a, Point b){
		/* 判断图片是否相同 */
		if (arr[a.x][a.y].getStatus() != arr[b.x][b.y].getStatus()) {
			return null;
		}
		
		list.clear();
		/* 判断能否0拐点连通 */
		if (noCorner(arr, a, b) != null) {
			return list;
		}
		/* 判断能否1拐点连通 */
		if (oneCorner(arr, a, b) != null) {
			return list;
		}
		/* 判断能否2拐点连通 */
		if (twoCorner(arr, a, b) != null) {
			return list;
		}
		return null;
	}
	
	/* 判断两点能否直线连通的算法实现 
	 * 若能连通返回true
	 * 若不能连通返回false*/
	public static boolean line(Block[][] arr, Point a, Point b) {
		/* 判断横向 */
		if (a.x == b.x) {
			for (int i = Math.min(a.y, b.y) + 1; i < Math.max(a.y, b.y); i++) {
				if (arr[a.x][i].getStatus() != 0) {
					return false;
				}
			}
			return true;
		}
		
		/* 判断纵向 */
		if (a.y == b.y) {
			for (int i = Math.min(a.x, b.x) + 1; i < Math.max(a.x, b.x); i++) {
				if (arr[i][a.y].getStatus() != 0) {
					return false;
				}
			}
			return true;
		}
		
		return false;
	}
	
	/* 0拐点连通判断算法实现 */
	public static List<Point> noCorner(Block[][] arr, Point a, Point b){
		if (line(arr, a, b)) {
			list.add(a);
			list.add(b);
			return list;
		}
		return null;
	}
	
	/* 1拐点连通判断算法实现 */
	public static List<Point> oneCorner(Block[][] arr, Point a, Point b){
		/* 获取两个可能的拐点 */
		Point c = new Point(a.x, b.y); 
		Point d = new Point(b.x, a.y); 
		
		/* 判断拐点c能否连通 */
		/* 判断拐点c是否已经消除且和a点b点能否连通 */
		if (arr[c.x][c.y].getStatus() == 0 
				&& line(arr, a, c)
				&& line(arr, b, c)) { 
			list.add(a);
			list.add(b);
			list.add(c);
			return list;
		}
		
		/* 判断拐点d能否连通 */
		/* 判断拐点d是否已经消除且和a点b点能否连通 */
		if (arr[d.x][d.y].getStatus() == 0 
				&& line(arr, a, d) 
				&& line(arr, b, d)) {
			list.add(a);
			list.add(b);
			list.add(d);
			return list;
		}
		return null;
	}
	
	/* 2拐点连通判断算法实现 */
	public static List<Point> twoCorner(Block[][] arr, Point a, Point b){
		Point c;
		for (int i = 0; i < arr[0].length; i++) {
			c = new Point(a.x, i);
			
			if (arr[c.x][c.y].getStatus() == 0 
					&& line(arr, a, c) 
					&& oneCorner(arr, b, c) != null) {
				list.add(0, a);
				return list;
			}
		}
		
		for (int i = 0; i < arr.length; i++) {
			c = new Point(i, a.y);
			
			if (arr[c.x][c.y].getStatus() == 0 
					&& line(arr, a, c) 
					&& oneCorner(arr, b, c) != null) {
				list.add(0, a);
				return list;
			}
		}
		
		return null;
	}
	
	/* 提示算法实现
	 * 遍历棋盘寻找两个可以连通的图标 */
	public static List<Point> promptArr(Block[][] arr){
		for (int i = 1; i < arr.length-1; i++) {
			for (int j = 1; j < arr[0].length-1; j++) {
				if (arr[i][j].getStatus() == 0) {
					continue;
				}
				
				for (int x = 1; x < arr.length; x++) {
					for (int y = 1; y < arr[0].length; y++) {
						if (arr[x][y].getStatus() == 0) {
							continue;
						}
						
						if (i == x && j == y) {
							continue;
						}
						
						Point a = new Point(i, j);
						Point b = new Point(x, y);
						if (checkLinked(arr, a, b) != null) {
							List<Point> list = new ArrayList<Point>();
							list.add(a);
							list.add(b);
							return list;
						}
					}
				}
			}
		}
		return null;
	}

	public static void refreshArr(Block[][] arr) {
		// TODO Auto-generated method stub
		List<Block> list = new ArrayList<Block>();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j].getStatus() != 0) {
					list.add(arr[i][j]);
				}
			}
		}
		
		Random random = new Random();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j].getStatus() != 0) {
					int index = random.nextInt(list.size());
					arr[i][j] = list.get(index);
					list.remove(index);
				}
			}
		}
	}
	
}
