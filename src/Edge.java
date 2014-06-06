

    public class Edge
    {
        private Vertex vertex;
        private int weight;

        protected Edge(Vertex endVertex, int edgeWeight)
        {
            vertex = endVertex;
            weight = edgeWeight;
        }

        protected Vertex getEndVertex()
        {
            return vertex;
        }

        protected int getWeight()
        {
            return weight;
        }
    }