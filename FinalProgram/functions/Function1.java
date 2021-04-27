package functions;

import ui.ProgressBar;
import utility.BusStopEdge;
import utility.Graph;

import java.io.IOException;
import java.util.*;


class Node implements Comparable<Node>
{
    double dist;
    int id;

    public Node(int id, double dist)
    {
        this.id = id;
        this.dist = dist;
    }

    @Override
    public int compareTo(Node o) {
        return Double.valueOf(this.dist).compareTo(o.dist);
    }
}

/*
author: Pavel Petrukhin

Class which implements function 1, shortest path between two stops
 */
public class Function1
{
    Graph graph;
    int V;

    public Function1()
    {
        try {
            graph = new Graph();
            this.V = graph.V;
        }
        catch (IOException e)
        {
            System.err.println(e);
            System.err.println("ERROR");
        }

    }


/* it needs to compute the associative costs here  return two things Pair<String,Double> 
Since in requirement it says that "Finding shortest paths between 2 bus stops (as input by the user), returning the list of stops en route as well as the associated “cost”."
:) 

*/
    public String findShortestPathById(int from_id, int to_id)
    {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Set<Integer> finished = new HashSet<>();

        HashMap<Integer,Double> dst = new HashMap<>();
        HashMap<Integer,Integer> edgeTo = new HashMap<>();

        for(Integer id : graph.vertices)
        {
            edgeTo.put(id,-1);
        }

        edgeTo.put(from_id,from_id);

        for(Integer id : graph.vertices)
        {
            dst.put(id,Double.MAX_VALUE);
        }

        dst.put(from_id, (double) 0);

        pq.add(new Node(from_id,0));

        ProgressBar computingBar = new ProgressBar(V, "Computing");
        int count = 0;

        while(finished.size() < V)
        {
            if(finished.size()%(V/100) == 0 || finished.size() == V)
                computingBar.printBar(finished.size());

            if(!pq.isEmpty()) {
                Node current = pq.remove();

                finished.add(current.id);

                if(graph.adjacencyList.containsKey(current.id)) {
                    for (BusStopEdge b : graph.adjacencyList.get(current.id)) {
                        if (!finished.contains(b.to_id)) {
                            double w = b.w;

                            double currentDst = current.dist;

                            if (currentDst + w < dst.get(b.to_id)) {
                                dst.put(b.to_id, currentDst + w);
                                edgeTo.put(b.to_id, current.id);
                            }

                            pq.add(new Node(b.to_id, dst.get(b.to_id)));
                        }
                    }
                }
            }
            else
            {
                computingBar.printBar(V);
                break;
            }
        }
        String path = "";
        int end = to_id;

        if(!edgeTo.containsKey(end))
            return "No path";
        else if(from_id == to_id)
            return Integer.toString(from_id);

        while(edgeTo.get(end)!=end)
        {
            if(edgeTo.get(end) == null)
            {
                return "No path";
            }

            path = end + ((path.isEmpty())?"":"->" + path);
            end = edgeTo.get(end);

            if(!edgeTo.containsKey(end))
                return "No path";
        }

        path = "PATH:\n" + end + "->" + path;
        return path;
    }
}
