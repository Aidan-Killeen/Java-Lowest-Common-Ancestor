//BST.java from previous assignment



public class BST<Key extends Comparable<Key>, Value> 
{
    protected Node root;             // root of BST

    /**
     * Private node class.
     */
    class Node {
        Key key;           // sorted by key
        private Value val;         // associated data
        Node left, right;  // left and right subtrees
		//private Node right;
        private int N;             // number of nodes in subtree

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    // is the symbol table empty?
    public boolean isEmpty() { return size() == 0; }

    // return number of key-value pairs in BST
    public int size() { return size(root); }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    /**
     *  Search BST for given key.
     *  Does there exist a key-value pair with given key?
     *
     *  @param key the search key
     *  @return true if key is found and false otherwise
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /**
     *  Search BST for given key.
     *  What is the value associated with given key?
     *
     *  @param key the search key
     *  @return value associated with the given key if found, or null if no such key exists.
     */
    public Value get(Key key) { return get(root, key); }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
    }

    /**
     *  Insert key-value pair into BST.
     *  If key already exists, update with new value.
     *
     *  @param key the key to insert
     *  @param val the value associated with key
     */
    public void put(Key key, Value val) {
        if (val == null) 
        { 
        	delete(key); return; 
        }
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) 
        	return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0)
        	x.left  = put(x.left,  key, val);
        else if (cmp > 0) 
        	x.right = put(x.right, key, val);
        else             
        	x.val   = val;
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    /**
     * Tree height.
     *
     * Asymptotic worst-case running time using Theta notation: TODO
     *
     * @return the number of links from the root to the deepest leaf.
     *
     * Example 1: for an empty tree this should return -1.
     * Example 2: for a tree with only one node it should return 0.
     * Example 3: for the following tree it should return 2.
     *   B
     *  / \
     * A   C
     *      \
     *       D
     */
    public int height() 
    {
    	if(!isEmpty())
    	{
    		return getHeight(root);
    	}
    	else
    		return -1;
    }
    
    private int getHeight(Node input)
    {
		int result;
		Node left = input.left;
		Node right = input.right;
		
		if(left == null && right == null)
			result = 0;
		
		else if(left == null)
			result = getHeight(right) + 1;
		else if(right == null)
			result = getHeight(left) + 1;
		
		else if(getHeight(left) > getHeight(right))
		{
			result = getHeight(left) + 1;
		}
		else
			result = getHeight(right) + 1;
		
		return result;
    }

    /**
     * Median key.
     * If the tree has N keys k1 < k2 < k3 < ... < kN, then their median key 
     * is the element at position (N+1)/2 (where "/" here is integer division)
     *
     * @return the median key, or null if the tree is empty.
     */
    public Key median() {
      if (isEmpty()) return null;
      else
      {
    	  Key result = null;
    	  int noOfElements = root.N;
    	  int elementPosition = (noOfElements + 1)/2;
    	  boolean found = false;
    	  Node current = root;
    	  while(!found)
    	  {
    		  int currentIndex;
    		  if(current.left != null)
    		  {
    			  currentIndex = current.left.N + 1; 
    		  }
    		  else
    			  currentIndex = 1;
    		  if(currentIndex == elementPosition)
    		  {
    			  found = true;
    			  result = current.key;
    		  }
    		  else if(currentIndex > elementPosition)
    		  {
    			  current = current.left;
    		  }
    		  else if(currentIndex < elementPosition)
    		  {
    			  elementPosition = elementPosition - currentIndex;
    			  current = current.right;
    		  }
    	  }
    	  return result;
      }
      
    }


    /**
     * Print all keys of the tree in a sequence, in-order.
     * That is, for each node, the keys in the left subtree should appear before the key in the node.
     * Also, for each node, the keys in the right subtree should appear before the key in the node.
     * For each subtree, its keys should appear within a parenthesis.
     *
     * Example 1: Empty tree -- output: "()"
     * Example 2: Tree containing only "A" -- output: "(()A())"
     * Example 3: Tree:
     *   B
     *  / \
     * A   C
     *      \
     *       D
     *
     * output: "((()A())B(()C(()D())))"
     *
     * output of example in the assignment: (((()A(()C()))E((()H(()M()))R()))S(()X()))
     *
     * @return a String with all keys in the tree, in order, parenthesized.
     */
    public String printKeysInOrder() {
      if (isEmpty()) return "()";
      
      else return "(" + printNode(root) + ")";
    }
    
    private String printNode(Node input)
    {
    	String output;
    	if(input == null)
    	{
    		output = "";
    	}
    	
    	else
    		output = "(" + printNode(input.left) + ")" + input.key + "(" + printNode(input.right) + ")";
    	
    	return output;
    	
    	
    }
    
    /**
     * Pretty Printing the tree. Each node is on one line -- see assignment for details.
     *
     * @return a multi-line string with the pretty ascii picture of the tree.
     */
    public String prettyPrintKeys()
    {
      return prettyPrint(root, "");
    }
    
    private String prettyPrint(Node node, String prefix)
    {
    	String output = "";
    	String newPrefix1 = prefix + " |";
    	String newPrefix2 = prefix + "  ";
    	
    	if(node != null)
    	{
    		output =  prefix + "-" + node.key + "\n";
    		output += prettyPrint(node.left, newPrefix1);
    		output += prettyPrint(node.right, newPrefix2);
    	}
    	
    	else
    		output = prefix + "-null" + "\n";
    	
    	return output;
    	
    }

    /**
     * Deteles a key from a tree (if the key is in the tree).
     * Note that this method works symmetrically from the Hibbard deletion:
     * If the node to be deleted has two child nodes, then it needs to be
     * replaced with its predecessor (not its successor) node.
     *
     * @param key the key to delete
     */
    public void delete(Key key)
    { 
    	root = delete(root, key); 
    }
    
    private Node delete(Node x, Key key) 
    {
    	if (x == null) 
    		return null;
    	int cmp = key.compareTo(x.key);
    	
    	if (cmp < 0)
    		x.left = delete(x.left, key);
    	
    	else if (cmp > 0)
    		x.right = delete(x.right, key);
    	
    	else 
    	{
    		if (x.right == null) 
    			return x.left;
    		if (x.left == null) 
    			return x.right;
    		
    		Node t = x;
    		//use max?
    		x = max(t.left);
    		x.left = deleteMax(t.left);
    		x.right = t.right;
    	}
    	x.N = size(x.left) + size(x.right) + 1;
    	return x;
    } 
    
    private Node deleteMax(Node x)
    {
    	if (x.right == null) 
    		return x.left;
    	x.right = deleteMax(x.right);
    	x.N = 1 + size(x.left) + size(x.right);
    	return x;
    }
    private Node max(Node x)
    {
    	
    	if(x.right != null) 
    	{
    		return max(x.right);
    	}
    	else 
    	{
    		return x; 
    	}
    	
    }


}