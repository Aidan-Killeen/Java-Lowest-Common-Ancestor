import static org.junit.Assert.*;

import org.junit.Test;

public class DAGTest {

	@Test
	public void testBuild() 
	{
		Character[] input = {'a','b','c','d','e'};
		DAG<Character> testDAG = new DAG(input);
		String list = testDAG.toString();//change this to use.toString from nodes
		String result = "a[][],b[][],c[][],d[][],e[][]";
		assertEquals(list, result);
		
		testDAG.linkValue('a', 'b');
		testDAG.linkValue('a', 'c');
		testDAG.linkValue('a', 'd');
		testDAG.linkValue('a', 'e');
		testDAG.linkValue('b', 'd');
		testDAG.linkValue('c', 'd');
		testDAG.linkValue('c', 'e');
		testDAG.linkValue('d', 'e');
		
		list = testDAG.toString();
		result = "a[][b,c,d,e],b[a][d],c[a][d,e],d[a,b,c][e],e[a,c,d][]";
		assertEquals(list, result);
		//fail("Not yet implemented");
	}
	
	@Test
	public void testLCA() 
	{
		Character[] input = {'a','b','c','d','e'};
		DAG<Character> testDAG = new DAG(input);
		
		testDAG.linkValue('a', 'b');
		testDAG.linkValue('a', 'c');
		testDAG.linkValue('a', 'd');
		testDAG.linkValue('a', 'e');
		testDAG.linkValue('b', 'd');
		testDAG.linkValue('c', 'd');
		testDAG.linkValue('c', 'e');
		testDAG.linkValue('d', 'e');
		
		String list = testDAG.toString(testDAG.listAllPossAnc('e'));
		String result = "e[a,c,d][],a[][b,c,d,e],c[a][d,e],d[a,b,c][e],b[a][d]";
		assertEquals(list, result);
		
		list = testDAG.toString(testDAG.findLCA('e', 'd'));
		result = "d[a,b,c][e]";
		assertEquals(list, result);
		
		//multiple LCA's
		Character[] input2 = {'a','b','c','d','e','f','g'};
		DAG<Character> testDAG2 = new DAG(input2);
		
		testDAG2.linkValue('a', 'd');
		testDAG2.linkValue('b', 'd');
		testDAG2.linkValue('b', 'e');
		testDAG2.linkValue('c', 'f');
		testDAG2.linkValue('c', 'g');
		testDAG2.linkValue('d', 'f');
		testDAG2.linkValue('d', 'g');
		testDAG2.linkValue('e', 'g');
		

		list = testDAG2.toString(testDAG2.findLCA('f', 'g'));
		result = "c[][f,g],d[a,b][f,g]";
		assertEquals(list, result);
		
		
		//fail("Not yet implemented");
	}
}
