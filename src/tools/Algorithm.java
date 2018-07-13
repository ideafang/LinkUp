package tools;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Linkup.Block;

public class Algorithm {
	private static List<Point> list = new ArrayList<Point>();
	
	/* ��ͨ�ܷ����ж�
	 * �ҵ�����list����ͨ��ļ��ϣ�
	 * ʧ�ܷ���null */
	public static List<Point> checkLinked(Block[][] arr, Point a, Point b){
		/* �ж�ͼƬ�Ƿ���ͬ */
		if (arr[a.x][a.y].getStatus() != arr[b.x][b.y].getStatus()) {
			return null;
		}
		
		list.clear();
		/* �ж��ܷ�0�յ���ͨ */
		if (noCorner(arr, a, b) != null) {
			return list;
		}
		/* �ж��ܷ�1�յ���ͨ */
		if (oneCorner(arr, a, b) != null) {
			return list;
		}
		/* �ж��ܷ�2�յ���ͨ */
		if (twoCorner(arr, a, b) != null) {
			return list;
		}
		return null;
	}
	
	/* �ж������ܷ�ֱ����ͨ���㷨ʵ�� 
	 * ������ͨ����true
	 * ��������ͨ����false*/
	public static boolean line(Block[][] arr, Point a, Point b) {
		/* �жϺ��� */
		if (a.x == b.x) {
			for (int i = Math.min(a.y, b.y) + 1; i < Math.max(a.y, b.y); i++) {
				if (arr[a.x][i].getStatus() != 0) {
					return false;
				}
			}
			return true;
		}
		
		/* �ж����� */
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
	
	/* 0�յ���ͨ�ж��㷨ʵ�� */
	public static List<Point> noCorner(Block[][] arr, Point a, Point b){
		if (line(arr, a, b)) {
			list.add(a);
			list.add(b);
			return list;
		}
		return null;
	}
	
	/* 1�յ���ͨ�ж��㷨ʵ�� */
	public static List<Point> oneCorner(Block[][] arr, Point a, Point b){
		/* ��ȡ�������ܵĹյ� */
		Point c = new Point(a.x, b.y); 
		Point d = new Point(b.x, a.y); 
		
		/* �жϹյ�c�ܷ���ͨ */
		/* �жϹյ�c�Ƿ��Ѿ������Һ�a��b���ܷ���ͨ */
		if (arr[c.x][c.y].getStatus() == 0 
				&& line(arr, a, c)
				&& line(arr, b, c)) { 
			list.add(a);
			list.add(b);
			list.add(c);
			return list;
		}
		
		/* �жϹյ�d�ܷ���ͨ */
		/* �жϹյ�d�Ƿ��Ѿ������Һ�a��b���ܷ���ͨ */
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
	
	/* 2�յ���ͨ�ж��㷨ʵ�� */
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
	
	/* ��ʾ�㷨ʵ��
	 * ��������Ѱ������������ͨ��ͼ�� */
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
