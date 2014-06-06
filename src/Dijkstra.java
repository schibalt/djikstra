
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dijkstra
{
    private ArrayList<String> cities = new ArrayList<String>(); //store the list of nodes
//    private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    private LinkedList<Vertex> vertices = new LinkedList<Vertex>();
    private int[] distances;

    /**
    read the file that contains the nodes and edges
     * O(lines in the file)
    @param sf : the File
     */
    public void readMap(File sf)
    {
        try
        {
            Scanner sc = new Scanner(sf);
            String re1 = "(\\d+)";	// source
            String re2 = "(\\s+)";	// White Space 1
            String re3 = "(\\d+)";	// destination
            String re4 = "(\\s+)";	// White Space 2
            String re5 = "(\\d+)";	// path weight
            String re6 = "((?:[a-z][a-z]+))";	// city

            Pattern pathWeight = Pattern.compile(re1 + re2 + re3 + re4 + re5, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
            Pattern city = Pattern.compile(re6, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

            while (sc.hasNextLine())
            {
                String mapLine = sc.nextLine();
                Matcher path = pathWeight.matcher(mapLine);
                Matcher cityMatcher = city.matcher(mapLine);

                if (path.find())
                {
                    String int1 = path.group(1);
                    String ws1 = path.group(2);
                    String int2 = path.group(3);
                    String ws2 = path.group(4);
                    String int3 = path.group(5);
                    System.out.print("(" + int1.toString() + ")" + "(" + ws1.toString() + ")" + "(" + int2.toString() + ")"
                            + "(" + ws2.toString() + ")" + "(" + int3.toString() + ")" + "\n");
                    vertices.get(Integer.parseInt(int1)).connect(vertices.get(Integer.parseInt(int2)), Integer.parseInt(int3));
                    vertices.get(Integer.parseInt(int2)).connect(vertices.get(Integer.parseInt(int1)), Integer.parseInt(int3));
                } else
                {
                    if (cityMatcher.find())
                    {
                        String cityName = cityMatcher.group(1);
                        cities.add(cityName);
                        System.out.println(cityName);
                        vertices.add(new Vertex(cityName));
                    }
                }
            }

            System.out.println("");
        } catch (IOException ioE)
        {
            System.err.println("io exception");
        }
        System.out.println("");
    }

    public String[] getCities()
    {
        String[] c = new String[cities.size()];

        for (int i = 0; i < cities.size(); i++)
        {
            c[i] = cities.get(i);
        }
        Arrays.sort(c);

        return c;
    }

    private int smallestDistance()
    {
        int weight = 2147483647;
        int lightestPath = -1;

        for (int i = 0; i < distances.length; i++)
        {
            if (distances[i] < weight)
            {
                weight = distances[i];
                lightestPath = i;
            }
        }
        return lightestPath;
    }

    public String getShortestPath(String start, String end)
    {
        String report = "Path found:\n";

        distances = new int[vertices.size()];

        // O(V) instantiates the distance array
        for (int i = 0; i < distances.length; i++)
        {
            distances[i] = 2147483647;
        }

        Vertex[] previousVertices = new Vertex[vertices.size()];

        distances[cities.indexOf(start)] = 0;
        Queue<Vertex> vertqueue = new LinkedList<Vertex>();

        // O(V)
        for (Vertex vertex : vertices)
        {
            vertqueue.add(vertex);
        }

        // O(V) vertices are only removed from the queue once they're visited so
        // all vertices ahve to be visited
        while (!vertqueue.isEmpty())
        {
            Vertex source = null;
            int weight = 2147483647;

            // O(V) the whole queue has to be iterated the first time
            // this sets the lightest path
            for (Vertex vertex : vertqueue)
            {
                // this iterates through the vertices still in the queue and finds the 
                // lightest path per the array of distances
                if (distances[cities.indexOf(vertex.getLabel())] < weight)
                {
                    weight = distances[cities.indexOf(vertex.getLabel())];
                    source = vertices.get(cities.indexOf(vertex.getLabel()));
                }
            }

            if (distances[cities.indexOf(source.getLabel())] == 2147483647)
            {
                break;
            }

            vertqueue.remove(source);

            // O(E)
            for (Edge edge : source.getNeighbors())
            {
                // if the road doesnt' lead to a city we've already visited
                if (vertqueue.contains(edge.getEndVertex()))
                {
                    // add the distacne from the starting city to the wight of the edge 
                    // we're focused on during this iteration
                    int alt = distances[cities.indexOf(source.getLabel())] + edge.getWeight();
//                    int nextindex = cities.indexOf(edge.getEndVertex().getLabel());

                    // if the weight of the path from the start city to the city to which
                    // the current edge leads is hopefully less than the weight of the 
                    // path we know about right now then update the array of distances
                    // to shown that a shorter path is found
                    if (alt < distances[cities.indexOf(edge.getEndVertex().getLabel())])
                    {
                        distances[cities.indexOf(edge.getEndVertex().getLabel())] = alt;
                        previousVertices[cities.indexOf(edge.getEndVertex().getLabel())] = source;
                    }
                }
            }
        }

        ArrayList<String> hops = new ArrayList<String>();
        String city = end;
        String entry = null;
        int totaldist = 0;

        // O(hops)
        while (!city.equals(start))
        {
            entry = previousVertices[cities.indexOf(city)].getLabel() + " - " + city + ": ";

            for (Edge adjacentCity : previousVertices[cities.indexOf(city)].getNeighbors())
            {
                if (adjacentCity.getEndVertex().getLabel().equals(city))
                {
                    entry += adjacentCity.getWeight() + "\n";
                    hops.add(entry);
//                    System.out.println(entry);
                    totaldist += adjacentCity.getWeight();
                    city = previousVertices[cities.indexOf(city)].getLabel();
                }
            }
        }

        // O(hops)
        for (int i = hops.size(); i > 0; i--)
        {
            report += hops.get(i - 1);
        }

        report += "Total distance: " + totaldist;

        System.out.println(report);
        return report;
    }
}
