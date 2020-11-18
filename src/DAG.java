import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DAG <Value>
{

	protected ArrayList<Node> allNodes;
	class Node 
	{
        private Value val;
		
        ArrayList<Node> ancestors;
        ArrayList<Node> decendents;


        public Node(Value val)
        {
        	this.val = val;
        	ancestors = new ArrayList<Node>(0);
        	decendents = new ArrayList<Node>(0);

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
        	String output = "" + val;
        	String anc = "";
        	String dec = "";
        	for(int i = 0; i < ancestors.size(); i++)
    		{
    			anc += ((i == 0)?(""):(",")) + ancestors.get(i).val;
    		}
        	
    		for(int i = 0; i < decendents.size(); i++)
    		{
    			dec += ((i == 0)?(""):(",")) + decendents.get(i).val;
    		}
    		output += "[" + anc + "]" + "[" + dec + "]";
    		return output;
        }
        public ArrayList<Node> listAllAnc()
        {
        	ArrayList<Node> output = new ArrayList<Node>(0);
        	for(int i = 0; i < ancestors.size(); i++)
        	{
        		Node temp = (DAG<Value>.Node) this.ancestors.get(i);
        		output.add(temp);
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
	
	public void removeNode(Value input)
	{
		Node remove = findNode(input);
		for(int i = 0; i < remove.ancestors.size(); i++)
		{
			remove.ancestors.get(i).decendents.remove(remove);
		}
		for(int i = 0; i < remove.decendents.size(); i++)
		{
			remove.decendents.get(i).ancestors.remove(remove);
		}
		allNodes.remove(remove);
	}
	
	public void linkValue(Value first, Value second)
	{

		Node parent = findNode(first);
		Node child = findNode(second);
		linkNode(parent, child);
	}
	private void linkNode(Node parent, Node child)
	{
		parent.addDec(child);
		child.addAnc(parent);
	}
	
	public ArrayList<Node> listAllPossAnc(Value input)
    {
    	ArrayList<Node> output = new ArrayList<Node>(0);
    	Node valNode = findNode(input);
    	output.add(valNode);
    	output.addAll(valNode.listAllAnc());
    	
    	String check = "";
		for(int i = 0; i < output.size(); i++)
		{
			check += ((i == 0)?(""):(",")) + output.get(i).toString();
		}

    	return output;
    }
    
	
	public Node findNode(Value input)
	{
		Node output = null;
		for(int i = 0; i < allNodes.size() && output == null; i++)
		{
			if(allNodes.get(i).val == input)
				output = allNodes.get(i);
		}
		return output;
	}
	
	//LCA part of DAG
	public ArrayList<Node> findLCA(Value input1, Value input2)
	{
		ArrayList<Node> ancestors1 = listAllPossAnc(input1);
		ArrayList<Node> ancestors2 = listAllPossAnc(input2);
		
		ancestors1.retainAll(ancestors2);
		
		boolean removals = true;
		while(removals)
		{
			removals = false;
			for(int i = 0; i < ancestors1.size() && !removals; i++)
			{
				Node temp = ancestors1.get(i);
				ArrayList<Node> anc = temp.listAllAnc();
				removals = ancestors1.removeAll(anc);
			}
		}
		return ancestors1;
	}
	public String toString()
	{
		String output = "";
		for(int i = 0; i < allNodes.size(); i++)
		{
			output += ((i == 0)?(""):(",")) + allNodes.get(i).toString();
		}

		return output;
	}
	
	public String toString(ArrayList input)
	{
		String output = "";
		for(int i = 0; i < input.size(); i++)
		{
			output += ((i == 0)?(""):(",")) + input.get(i).toString();
		}
		return output;
	}

}
