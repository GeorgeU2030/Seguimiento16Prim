package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Graph<T> {
	 
	        public int[][]matrixFloyd;
	        public int[][]costMatrix;
	        public HashMap<T, ArrayList<T>> adjVertices;
	        public ArrayList<T>costos=new ArrayList<>();
	        
	        public Graph(int nv) {
	        	this.costMatrix=new int[nv][nv];
	        	matrixFloyd = new int[nv][nv];
	        	costMatrixweight();
	        	this.adjVertices=new HashMap<>();
	        }
	        
	        public void costMatrixweight() {
	        	for(int i=0;i<costMatrix.length;i++) {
	        		for(int j=0;j<costMatrix[0].length;j++) {
	        			if(i==j) {
	        				costMatrix[i][j]=0;
	        			}else {
	        			costMatrix[i][j]=Integer.MAX_VALUE;
	        			}
	        		}
	        	}
	        }
	       public void addVertex(T data) {
	            adjVertices.put(data, new ArrayList<>());
	            costos.add(data);
	        }

	       public void removeVertex(T data) {
	            Vertex v = new Vertex(data);
	            //adjVertices.values().stream().forEach(e -> e.remove(v));
	            adjVertices.remove(new Vertex(data));
	        }
	       public void addEdge(T vertex1, T vertex2) {
	    	    Vertex v1 = new Vertex(vertex1);
	    	    Vertex v2 = new Vertex(vertex2);
	    	    adjVertices.get(vertex1).add(vertex2);
	    	   // adjVertices.get(vertex2).add(vertex1);
	    	}
	     public void searchadj(T data) {
	    	 System.out.println(adjVertices.get(data));
	     }
	     
	     public Set<T> bfs(Graph graph, T root) {
	    	    Set<T> visited = new LinkedHashSet<T>();
	    	    Queue<T> queue = new LinkedList<T>();
	    	    queue.add(root);
	    	    visited.add(root);
	    	    while (!queue.isEmpty()) {
	    	        T vertex = queue.poll();
	    	        for (T v : adjVertices.get(vertex)) {
	    	            if (!visited.contains(v)) {
	    	                visited.add((T) v);
	    	                queue.add((T) v);
	    	            }
	    	        }
	    	    }
	    	    return visited;
	    	}
	    public Set<T> dfs(Graph graph, T root) {
	    	    Set<T> visited = new LinkedHashSet<T>();
	    	    Stack<T> stack = new Stack<T>();
	    	    stack.push(root);
	    	    while (!stack.isEmpty()) {
	    	        T vertex = stack.pop();
	    	        if (!visited.contains(vertex)) {
	    	            visited.add(vertex);
	    	            for (T v : adjVertices.get(vertex)) {              
	    	                stack.push(v);
	    	            }
	    	        }
	    	    }
	    	    return visited;
	    	}
	    public boolean conexo(Graph graph) {
	    	
	    	for(T v:adjVertices.keySet()) {
	    		Set<T> size = new LinkedHashSet<T>();
	    		size = bfs(graph,v);
	    		
	    		if(size.size()!=graph.adjVertices.keySet().size()) {
	    			return false;
	    		}
	    	}
	    	return true;
	    }
	    
	    public void addEdge(T v1,T v2,int cost) {
	    	int i=0;
         for(T v:costos) {
        	 if(v.equals(v1)) {
        		 break;
        	 }
        	 i++;
         }
         int j=0;
         for(T v:costos) {
        	 if(v.equals(v2)) {
        		break; 
        	 }
        	 j++;
         }
         costMatrix[i][j]=cost;
         costMatrix[j][i]=cost;
	    }
	    public void printCost() {
	    	for (int x=0; x < costMatrix.length; x++) {
	    		  System.out.print("|");
	    		  for (int y=0; y < costMatrix[x].length; y++) {
	    		    System.out.print (costMatrix[x][y]);
	    		    if (y!=costMatrix[x].length-1) System.out.print("\t");
	    		  }
	    		  System.out.println("|");
	    		}
	    }
	    
	   public void dijkstra(int graph[][], int src,int V)
	    {
	        int dist[] = new int[V]; 
	        Boolean sptSet[] = new Boolean[V];
	 
	        for (int i = 0; i < V; i++) {
	            dist[i] = Integer.MAX_VALUE;
	            sptSet[i] = false;
	        }
	 
	        dist[src] = 0;
	 
	        
	        for (int count = 0; count < V - 1; count++) {
	            
	            int u = minDistance(dist, sptSet);
	 
	            
	            sptSet[u] = true;
	 
	           
	            for (int v = 0; v < V; v++)
	 
	               
	                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v])
	                    dist[v] = dist[u] + graph[u][v];
	        }
	 
	       
	        printSolution(dist);
	    }
	    public int minDistance(int dist[], Boolean sptSet[])
	    {
	        
	        int min = Integer.MAX_VALUE, min_index = -1;
	 
	        for (int v = 0; v < dist.length; v++)
	            if (sptSet[v] == false && dist[v] <= min) {
	                min = dist[v];
	                min_index = v;
	            }
	 
	        return min_index;
	    }
	    
	   public void printSolution(int dist[])
	    {
	        System.out.println("Vertex \t\t Distance from Source");
	        for (int i = 0; i < dist.length; i++)
	            System.out.println(costos.get(i) + " \t\t " + dist[i]);
	    }
	    
	    public void floydWarshall(int[][]graph) {
	    	int nV=graph.length;
	   
	    	    int i=0;
	    	    int j=0;
	    	    int k=0;

	    	   for( i=0;i<matrixFloyd.length;i++) {
	    		   for(j=0;j<matrixFloyd[0].length;j++) {
	    			   matrixFloyd[i][j]=graph[i][j];
	    			   
	    		   }
	    	   }
	    	   
	    	   for (k = 0; k < nV; k++) {
	    	        for (i = 0; i < nV; i++) {
	    	            for (j = 0; j < nV; j++) {
	    	                
	    	                if (matrixFloyd[i][j] > (matrixFloyd[i][k] + matrixFloyd[k][j])
	    	                    && (matrixFloyd[k][j] != Integer.MAX_VALUE
	    	                        && matrixFloyd[i][k] != Integer.MAX_VALUE))
	    	                    matrixFloyd[i][j] = matrixFloyd[i][k] + matrixFloyd[k][j];
	    	            }
	    	        }
	    	    }
	    }
	    public void printshortroads() {
	    	for (int x=0; x < matrixFloyd.length; x++) {
	    		  System.out.print("|");
	    		  for (int y=0; y < matrixFloyd[x].length; y++) {
	    		    System.out.print (matrixFloyd[x][y]);
	    		    if (y!=matrixFloyd[x].length-1) System.out.print("\t");
	    		  }
	    		  System.out.println("|");
	    		}
	    	
	    }
	    public void prim(int costMatrix[][]) {
	    	
	        int nV = costMatrix.length;
	        int parent[] = new int[nV];
	        int key[] = new int[nV];
	 
	        
	        Boolean mstSet[] = new Boolean[nV];
	 
	        // Initialize all keys a INFINITE
	        for (int i = 0; i < nV; i++) {
	            key[i] = Integer.MAX_VALUE;
	            mstSet[i] = false;
	        }
	 
	        
	        key[0] = 0;
	       
	        parent[0] = -1; 
	 
	        
	        for (int count = 0; count < nV - 1; count++) {
	            
	            int u = minKey(key, mstSet,nV);
	 
	            
	            mstSet[u] = true;
	 
	           
	            for (int v = 0; v < nV; v++)
	 
	               
	                if (costMatrix[u][v] != 0 && mstSet[v] == false && costMatrix[u][v] < key[v]) {
	                    parent[v] = u;
	                    key[v] = costMatrix[u][v];
	                }
	        }
	 
	        // print the constructed MST
	        printMST(parent, costMatrix);
	    }
	    public int minKey(int key[], Boolean mstSet[],int nV)
	    {
	        // Initialize min value
	        int min = Integer.MAX_VALUE, min_index = -1;
	 
	        for (int v = 0; v < nV; v++)
	            if (mstSet[v] == false && key[v] < min) {
	                min = key[v];
	                min_index = v;
	            }
	 
	        return min_index;
	    }
	    public void printMST(int parent[], int costMatrix[][])
	    {
	    	int nV = costMatrix.length;
	        System.out.println("Edge \tWeight");
	        for (int i = 1; i < nV; i++)
	            System.out.println(parent[i] + " - " + i + "\t" + costMatrix[i][parent[i]]);
	    }
}
	    

