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
        	ancestors = new ArrayList<Node>(0);
        	decendents = new ArrayList<Node>(0);
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
        public String toString()
        {
        	String output = "";
    		for(int i = 0; i < decendents.size(); i++)
    		{
    			output += decendents.get(i).val;
    		}
    		return output;
        }
        public ArrayList<Node> listAllAnc()
        {
        	ArrayList<Node> output = null;
        	for(int i = 0; i < ancestors.size(); i++)
        	{
        		Node temp = (DAG<Value>.Node) this.ancestors.get(i);
        		output.add(temp); //is this necessary?
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
		allNodes = new ArrayList<Node>(0);
		for(int i = 0; i < input.length; i++)
		{
			Node temp = new Node(input[i]);
			allNodes.add(temp);
			
		}
	}
	public void addNode(Value input)
	{
		Node temp = new Node(input);
		allNodes.add(temp);
	}
	/*
	public void removeNode(Value input)
	{
		boolean found = false;
		int index = 0;
		for(int i = 0; i < allNodes.size() && !found; i++)
		{
			Node temp = allNodes.get(i);
			if(temp.val == input)
			{
				found = true;
				index = i;
			}
		}
		if(found)
		{
			for(int i = 0; i < temp.ances; i++)
			{
				
			}
			allNodes.remove(index);
		}
	}
	*/
	public void linkValue(Value first, Value second)
	{
		Node parent = new Node(null);
		Node child = new Node(null);
		for(int i = 0; i < allNodes.size(); i++)
		{
			if(allNodes.get(i).val == first)
				parent = allNodes.get(i);
		}
		
		for(int i = 0; i < allNodes.size(); i++)
		{
			if(allNodes.get(i).val == second)
				child = allNodes.get(i);
		}
		linkNode(parent, child);
	}
	public void linkNode(Node parent, Node child)
	{
		parent.addDec(child);
		child.addAnc(parent);
	}
	public String listAll()
	{
		String output = "";
		for(int i = 0; i < allNodes.size(); i++)
		{
			output += allNodes.get(i).val;
		}
		return output;
	}
	//to get LCA, make a funct that gets two array lists of ancestors, then gives intercept. 
	//then run through this list and make all the ancestors of each element no longer applicable(remove from list after each run through
}
