import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DAG <Value>//<Key extends Comparable<Key>, Value> 
{
	//protected Node allNodes[];
	protected ArrayList<Node> allNodes;
	class Node 
	{
        //Key key;           		// sorted by key
        private Value val;         // associated data
		
        ArrayList<Node> ancestors;
        ArrayList<Node> decendents;
        /*
        Node [] ancestors = null;  // left and right subtrees
        Node [] decendents = null;
        */
        private int depth;
        private boolean lowest = true;
		//private Node right;
        //private int N;             // number of nodes in subtree

        /*
        public Node(Key key, Value val, int N) 
        {
            this.key = key;
            this.val = val;
            this.N = N;
        }
        */
        public Node(Value val)
        {
        	this.val = val;
        	//ancestors = null;
        	//decendents = null;
        	depth = -1;
        }
        public void setDepth(int depth)
        {
        	this.depth = depth;
        }
        
        public void addAnc(Node a)
        {
        	ancestors.add(a);
        }
        public void addDec(Node a)
        {
        	decendents.add(a);
        }
        
        public ArrayList<Node> listAnc()
        {
        	return ancestors;
        }
        public ArrayList<Node> listAllAnc()
        {
        	ArrayList<Node> output = null;
        	for(int i = 0; i < ancestors.size(); i++)
        	{
        		Node temp = (DAG<Value>.Node) this.ancestors.get(i);
        		List<Node> listTwoCopy = new ArrayList<>(temp.listAllAnc());
                listTwoCopy.removeAll(output);
                output.addAll(listTwoCopy);
        	}
        	return output;
        }
        
        public ArrayList<Node> listDec()
        {
        	return decendents ;
        }
        
	}
	public DAG(Value[] input)
	{
		for(int i = 0; i < input.length; i++)
		{
			Node temp = new Node(input[i]);
			allNodes.add(temp);
			
		}
	}
	public void addNode()
	{
		//set value in constructor
	}
	
	public void linkNode(Node parent, Node child)
	{
		parent.addDec(child);
		child.addAnc(parent);
	}
}
