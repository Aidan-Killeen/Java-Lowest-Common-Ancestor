import static org.junit.Assert.*;

import org.junit.Test;

public class DAGTest {

	@Test
	public void testBuild() 
	{
		Character[] input = {'a','b','c','d','e'};
		DAG<Character> testDAG = new DAG(input);
		String list = testDAG.listAll();//change this to use.toString from nodes
		String result = "a[][],b[][],c[][],d[][],e[][]";
		assertEquals(list, result);
		
		testDAG.linkValue('a', 'b');
		testDAG.linkValue('a', 'c');
		testDAG.linkValue('a', 'e');
		testDAG.linkValue('b', 'd');
		testDAG.linkValue('c', 'd');
		testDAG.linkValue('c', 'e');
		testDAG.linkValue('d', 'e');
		
		list = testDAG.listAll();
		result = "a[][b,c,e],b[a][d],c[a][d,e],d[a,b,c][e],e[a,c,d][]";
		//fail("Not yet implemented");
	}

}
