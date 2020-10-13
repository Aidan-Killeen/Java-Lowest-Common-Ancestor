import static org.junit.Assert.*;

import org.junit.Test;

public class LCATest 
{

	@Test
	public void test() 
	{
	    LCA test = new LCA();
        test.put(7, 7);   //        _7_
        test.put(8, 8);   //      /     \
        test.put(3, 3);   //    _3_      8
        test.put(1, 1);   //  /     \
        test.put(2, 2);   // 1       6
        test.put(6, 6);   //  \     /
        test.put(4, 4);   //   2   4
        test.put(5, 5);   //        \
                         //         5
        //System.out.println("LCA(4, 5): " + test.findLCA(4,5));
        //System.out.println("LCA(4, 8): " + test.findLCA(4,8));
        //System.out.println("LCA(1, 6): " + test.findLCA(1,6));
        assertEquals("LCA(4, 5): ", 4, test.findLCA(4,5));
        assertEquals("LCA(4, 8): ", 7, test.findLCA(4,8));
        assertEquals("LCA(1, 6): ", 3, test.findLCA(1,6));

	}

}
