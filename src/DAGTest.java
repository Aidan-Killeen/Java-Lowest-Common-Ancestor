import static org.junit.Assert.*;

import org.junit.Test;

public class DAGTest {

	@Test
	public void testBuild() 
	{
		Character[] input = {'a','b','c','d','e'};
		DAG<Character> testDAG = new DAG(input);
		String list = testDAG.listAll();
		String result = "abcde";
		assertEquals(list, result);
		
		testDAG.linkValue('a', 'b');
		testDAG.linkValue('a', 'c');
		testDAG.linkValue('a', 'e');
		testDAG.linkValue('b', 'd');
		testDAG.linkValue('c', 'd');
		testDAG.linkValue('c', 'e');
		testDAG.linkValue('d', 'e');
		
		String check = testDAG.allNodes.get(0).toString();
		assertEquals(check, "bce");
		//fail("Not yet implemented");
	}

}
