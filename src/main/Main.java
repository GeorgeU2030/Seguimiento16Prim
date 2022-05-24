package main;

import java.util.Scanner;

import model.Graph;

public class Main {

	
	private Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		Main obj=new Main();
		obj.init();

	}

	public void init() {
		System.out.println("Enter the numbers of V");
		int v = sc.nextInt();
		Graph<Integer> graph=new Graph<>(v);
		for(int i=0;i<v;i++) {
			int vertex = sc.nextInt();
			graph.addVertex(vertex);
		}
		int option=1;
		while(option==1) {
		System.out.println("Enter the vertex 1");
		int v1=sc.nextInt();
		System.out.println("Enter the vertex 2");
		int v2=sc.nextInt();
		System.out.println("Enter the weight");
		int w=sc.nextInt();
		System.out.println("you connect "+v1 +" with "+v2+" with weight " + w+"\n");
		graph.addEdge(v1, v2,w);
		System.out.println("\ndo you want to enter a edge?");
		System.out.println("1.Yes");
		System.out.println("2.No");
		option = sc.nextInt();
		}
		graph.prim(graph.costMatrix);
	}
}
