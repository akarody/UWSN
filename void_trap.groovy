/////////////////////////////////////////////////////////////////////////////////////////////////
/// IMPORTING LIBRARIES


import java.util.Scanner;
import java.lang.Math;
import java.util.Random;

/////////////////////////////////////////////////////////////////////////////////////////////////
/// CREATION OF NODES


class Node {

    int x;
    int y;
    int z;
    double dist_from_sink;
    int next_node_index;

    double euclidianDistance(int a2, int b2, int c2) {
        return (Math.sqrt(Math.pow(this.x-a2,2)+Math.pow(this.y-b2,2)+Math.pow(this.z-c2,2)));
    }

}

/////////////////////////////////////////////////////////////////////////////////////////////////
/// RUNNING SIMULATION


        int i,j,nodes,range,size;
        Node []node = new Node[50];
        Node sink = new Node();
        Random random = new Random();
        int []void_node = new int[50];
        int []trap_node = new int[50];
        int voids = 0;
        int traps = 0;
        int minimum_neighbour;
        double dist_from_neighbour;

        //initializing variables
        for(i=0;i<50;i++){
            void_node[i] = 1;
            trap_node[i] = 0;
        }

        //inputting values
        System.out.print("Enter number of nodes : ");
        Scanner scan = new Scanner(System.in);
        nodes = scan.nextInt();
        voids = nodes;
        System.out.print("Enter range of transmission of each node : ");
        range = scan.nextInt();
        System.out.print("Enter dimension of graph : ");
        size = scan.nextInt();
        System.out.print("Enter location of sink node (x,y,z) : ");
        sink.x = scan.nextInt();
        sink.y = scan.nextInt();
        sink.z = scan.nextInt();

        //creating nodes in graph
        for(i=0;i<nodes;i++){
            node[i] = new Node();
            node[i].x = Math.abs(random.nextInt()%size)+1;
            node[i].y = Math.abs(random.nextInt()%size)+1;
            node[i].z = Math.abs(random.nextInt()%size)+1;
        }

        //calculating euclidian distance from sink node
        for(i=0;i<nodes;i++)
            node[i].dist_from_sink = node[i].euclidianDistance(sink.x, sink.y, sink.z);

        //finding void nodes
        for(j=0;j<nodes;j++){
            minimum_neighbour = 0;
            for(i=0;i<nodes;i++){
                dist_from_neighbour = node[i].euclidianDistance(node[j].x, node[j].y, node[j].z);
                if(i!=j){
                    if( (dist_from_neighbour<=range) && ((node[i].dist_from_sink<node[j].dist_from_sink) || (node[j].dist_from_sink<=range))) {
                        void_node[j] = 0;
                        if(node[i].dist_from_sink<node[minimum_neighbour].dist_from_sink){
                            minimum_neighbour = i;
                        }
                    }
                }
                else continue;
            }
            if(void_node[j] == 0) {
                voids--;
                node[j].next_node_index = minimum_neighbour;
            }
            else
                node[j].next_node_index = 999;
        }

        //finding trap nodes
        for(i=0;i<nodes;i++){
            if(node[i].next_node_index != 999) {
                if (void_node[node[i].next_node_index] == 1) {
                    trap_node[i] = 1;
                    traps++;
                }
            }
        }

/////////////////////////////////////////////////////////////////////////////////////////////////
/// PRINTING VALUES

        
        System.out.println("\n-----------------NODE POSTITIONS------------------\n");
        System.out.println("Sink node : ("+sink.x+", "+sink.y+", "+sink.z+")\n");
        int k;
        for(k=0;k<nodes;k++){
            System.out.println("Node "+(k+1)+" : ("+node[k].x+", "+node[k].y+", "+node[k].z+")");
        }

        System.out.println("\n---------------VOID NODE POSTITIONS---------------\n");
        System.out.println("Number of void nodes : "+voids+"\n");
        for(k=0;k<nodes;k++){
            if(void_node[k] == 1){
                System.out.println("Node "+(k+1)+" : ("+node[k].x+", "+node[k].y+", "+node[k].z+")");
            }
        }

        System.out.println("\n---------------TRAP NODE POSTITIONS---------------\n");
        System.out.println("Number of trap nodes : "+traps+"\n");
        for(k=0;k<nodes;k++){
            if(trap_node[k] == 1){
                System.out.println("Node "+(k+1)+" : ("+node[k].x+", "+node[k].y+", "+node[k].z+")");
            }
        }
    
/////////////////////////////////////////////////////////////////////////////////////////////////
/// END OF FILE