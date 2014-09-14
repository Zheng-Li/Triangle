package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Triangle {
	private ArrayList<String> raw_data;
	private int[][] matrix;
	private int max_sum[];
	
	public Triangle() {
		//Matrix stores the trangle data
		raw_data = new ArrayList<String>();
		matrix = new int[100][];
		
		//This array stores the maximum total for each number in row 100
		max_sum = new int[100];
		
		readTriangle();
		parse();
		
		//There's 100 row, starts with 0
		calculation(99);
		
		//Find the maximum end point in the bottom 100 numbers
		row_max(max_sum);
		
		//Display the result of calculation
		for(int i=0; i<max_sum.length; i++) {
			System.out.print(max_sum[i] + ", ");
		}
	}
	
	//Read triangle data from txt file
	public void readTriangle() {
		FileReader fr;
		BufferedReader br;	
	
		try {
			fr = new FileReader("triangle.txt");
			br = new BufferedReader(fr);
			String tmp = null;
			while((tmp = br.readLine()) != null) {
				raw_data.add(tmp);
			}
			br.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//Parse triangle data from String to int
	public void parse() {
		//String to Triangle array
		String[][] data = new String[100][];
		for(int i=0; i<100; i++) {
			data[i] = raw_data.get(i).split(" ");
		}
		
		//Triangle to int
		int num;
		for(int x=0; x<100; x++) {
			num = data[x].length;
			matrix[x] = new int[num];
			for(int y=0; y<num; y++) {
				matrix[x][y] = Integer.parseInt(data[x][y]);
			}
		}
	}
	
	//Recursively calculate the maximum total from top to down
	//Note that the tip point is actually row 0
	public int[] calculation(int k) {
		if(k == 0) {
			max_sum[0] = matrix[0][0];
			return max_sum;
		} else {
			int[] o_max = Arrays.copyOf(calculation(k-1), 100);
			max_sum[0] = o_max[0] + matrix[k][0];
			max_sum[k] = o_max[k-1] + matrix[k][k];
			for(int i=1; i<k; i++) {
				max_sum[i] = Math.max(o_max[i-1], o_max[i]) + matrix[k][i];
			}
			return max_sum;
		}
	}

	//Find out the maximum number in the row
	public void row_max(int[] m) {
		int max = 0;
		int index = 0;
		for(int i=0; i<m.length; i++) {
			if(m[i] > max) {
				max = m[i];
				index = i;
			}
		}
		System.out.println("The max number is at " + index +", " + max);
	}
	
	public static void main(String[] args) {
		new Triangle();
	}

}
