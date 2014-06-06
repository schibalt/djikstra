
import java.util.ArrayList;

/**
 *
 * @author Andrew Tilisky
 */
public class Vertex
{
    private String label;
    private ArrayList<Edge> edgeList;
    
    private Vertex previousVertex;
    
    public String getLabel()
    {
        return label;
    }
    
    public ArrayList<Edge> getNeighbors(){
        return edgeList;
    }

    public Vertex(String vertexLabel)
    {
        this.label = vertexLabel;
        this.edgeList = new ArrayList<Edge>();
        
        previousVertex = null;
        
    }

    public boolean connect(Vertex endVertex, int weight)
    {
        boolean result = false;

        if (!label.equals(endVertex.getLabel()))
        {
            boolean duplicateEdge = false;

            for (int i = 0; i < edgeList.size(); i++)
            {
                if (edgeList.get(i).getEndVertex().getLabel().equals(endVertex.getLabel()))
                {
                    duplicateEdge = true;
                }
            }
            if (!duplicateEdge)
            {
                edgeList.add(new Edge(endVertex, weight));
                result = true;
            }
        }
        return result;
    }
}
