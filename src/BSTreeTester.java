import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class BSTreeTester {

    BSTree <Integer> test;
    LinkedList<Integer> test_lk;
    Stack<Integer> stack;
    BSTree <String> test_1;
    BSTree <Integer> test_2;
    @Before
    public void setUp(){
        test = new BSTree<Integer>();
        test_2 = new BSTree<Integer>();
        test_1 = new BSTree<String>();
        test_lk = new LinkedList<Integer>();
        test_lk.add(10);
        test_lk.add(20);
        stack = new Stack<Integer>();
        stack.push(30);
        stack.push(20);
    }

    @Test
    public void testGetRoot_1(){
        assertNull(test.getRoot());
    }

    @Test
    public void testGetRoot_2(){
        assertTrue(test.insert(17));
        assertEquals(Integer.valueOf(17),test.getRoot().getKey());
        assertNull(test.getRoot().getLeft());
        assertNull(test.getRoot().getRight());
    }

    @Test
    public void testGetRoot_3(){
        test.insert(100);
        assertEquals(Integer.valueOf(100),test.getRoot().getKey());
        assertNull(test.getRoot().getLeft());
        assertNull(test.getRoot().getRight());
    }

    @Test
    public void testGetSize_1(){
        test.insert(10);
        test.insert(20);
        test.insert(5);
        assertEquals(3,test.getSize());
    }

    @Test
    public void testGetSize_2(){
        assertEquals(0,test.getSize());
    }

    @Test
    public void testGetSize_3(){
        test.insert(10);
        assertEquals(1,test.getSize());
    }

    @Test
    public void test_insert_1(){
        test.insert(20);
        test.insert(15);
        test.insert(25);
        assertEquals(Integer.valueOf(20),test.getRoot().getKey());
        assertEquals(Integer.valueOf(15),test.getRoot().getLeft().getKey());
        assertEquals(Integer.valueOf(25),test.getRoot().getRight().getKey());
    }

    @Test
    public void test_insert_2(){
        test.insert(20);
        test.insert(15);
        test.insert(12);
        assertEquals(Integer.valueOf(20),test.getRoot().getKey());
        assertNull(test.getRoot().getRight());
        assertEquals(Integer.valueOf(15),test.getRoot().getLeft().getKey());
        assertEquals(Integer.valueOf(12),test.getRoot().getLeft().getLeft().getKey());
    }

    @Test
    public void test_insert_3(){
        test.insert(30);
        test.insert(35);
        test.insert(40);
        test.insert(20);
        assertEquals(Integer.valueOf(30),test.getRoot().getKey());
        assertEquals(Integer.valueOf(20),test.getRoot().getLeft().getKey());
        assertEquals(Integer.valueOf(35),test.getRoot().getRight().getKey());
        assertEquals(Integer.valueOf(40),test.getRoot().getRight().getRight().getKey());

    }

    @Test
    public void test_findKey_1(){
        test.insert(30);
        test.insert(35);
        test.insert(40);
        test.insert(20);
        assertTrue(test.findKey(20));
    }

    @Test
    public void test_findKey_2(){
        test.insert(30);
        test.insert(35);
        test.insert(40);
        test.insert(20);
        assertTrue(test.findKey(40));
    }

    @Test
    public void test_findKey_3(){
        test.insert(30);
        test.insert(35);
        test.insert(40);
        test.insert(20);
        assertFalse(test.findKey(50));
    }

    @Test
    public void test_insertData_1(){

        test.insert(30);
        test.insert(35);
        test.insert(40);
        test.insert(20);
        test.insertData(30,10);
        test.insertData(30,20);
        assertEquals(test_lk,test.getRoot().getDataList());
    }

    @Test
    public void test_insertData_2(){

        test.insert(30);
        test.insert(35);
        test.insert(40);
        test.insert(20);
        test.insertData(35,10);
        test.insertData(35,20);
        assertEquals(test_lk,test.getRoot().getRight().getDataList());
    }

    @Test
    public void test_insertData_3(){
        test.insert(30);
        test.insert(35);
        test.insert(40);
        test.insert(20);
        test.insertData(20,10);
        test.insertData(20,20);
        assertEquals(test_lk,test.getRoot().getLeft().getDataList());
    }

    @Test
    public void test_findDataList_1(){
        test.insert(30);
        test.insert(35);
        test.insert(40);
        test.insert(20);
        test.insertData(20,10);
        test.insertData(20,20);
        assertEquals(test_lk,test.findDataList(20));
    }

    @Test
    public void test_findDataList_2(){
        test.insert(30);
        test.insert(35);
        test.insert(40);
        test.insert(20);
        test.insertData(30,10);
        test.insertData(30,20);
        assertEquals(test_lk,test.findDataList(30));
    }

    @Test
    public void test_findDataList_3(){
        test.insert(30);
        test.insert(35);
        test.insert(40);
        test.insert(20);
        test.insertData(35,10);
        test.insertData(35,20);
        assertEquals(test_lk,test.findDataList(35));
    }

    @Test
    public void test_findHeight_1(){
        test.insert(30);
        test.insert(35);
        test.insert(40);
        test.insert(20);
        assertEquals(2,test.findHeight());
    }

    @Test
    public void test_findHeight_2(){
        assertEquals(-1,test.findHeight());
    }

    @Test
    public void test_findHeight_3(){
        test.insert(30);
        assertEquals(0,test.findHeight());
    }
    @Test
    public void test_findHeight_4(){
        test.insert(30);
        test.insert(35);
        test.insert(40);
        test.insert(20);
        test.insert(50);
        test.insert(60);
        assertEquals(4,test.findHeight());
    }
    @Test
    public void test_hasNext_1(){
        test.insert(30);
        test.insert(35);
        test.insert(40);
        test.insert(20);
        test.insert(50);
        test.insert(60);
        assertTrue(test.iterator().hasNext());
        assertEquals(Integer.valueOf(20),test.iterator().next());
    }
    @Test
    public void test_hasNext_2(){
        test.insert(30);
        test.insert(35);
        test.insert(40);
        test.insert(20);
        test.insert(50);
        test.insert(60);
        Iterator<Integer> iter = test.iterator();
        assertTrue(iter.hasNext());

    }
    @Test
    public void test_hasNext_3(){
        test.insert(30);
        test.insert(35);
        test.insert(40);
        test.insert(20);
        test.insert(50);
        test.insert(60);
        Iterator<Integer> iter = test.iterator();
        assertEquals(Integer.valueOf(20),iter.next());
        assertEquals(Integer.valueOf(30),iter.next());
        assertTrue(test.iterator().hasNext());
    }
    @Test
    public void test_Next_1(){
        test.insert(8);
        test.insert(3);
        test.insert(1);
        test.insert(6);
        test.insert(4);
        test.insert(7);
        test.insert(10);
        test.insert(14);
        test.insert(13);
        Iterator<Integer> iter = test.iterator();
        assertEquals(Integer.valueOf(1),iter.next());
        assertEquals(Integer.valueOf(3),iter.next());
        assertEquals(Integer.valueOf(4),iter.next());
        assertEquals(Integer.valueOf(6),iter.next());
        assertEquals(Integer.valueOf(7),iter.next());
        assertEquals(Integer.valueOf(8),iter.next());
        assertEquals(Integer.valueOf(10),iter.next());
        assertEquals(Integer.valueOf(13),iter.next());
        assertEquals(Integer.valueOf(14),iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void test_Next_2(){
        test.insert(30);
        test.insert(20);
        test.insert(35);
        test.insert(40);
        test.insert(50);
        test.insert(60);
        Iterator<Integer> iter = test.iterator();
        assertEquals(Integer.valueOf(20),iter.next());
        assertEquals(Integer.valueOf(30),iter.next());
        assertEquals(Integer.valueOf(35),iter.next());
        assertEquals(Integer.valueOf(40),iter.next());
        assertEquals(Integer.valueOf(50),iter.next());
        assertEquals(Integer.valueOf(60),iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void test_Next_3(){
        test_1.insert("B");
        test_1.insert("A");
        test_1.insert("D");
        test_1.insert("E");
        Iterator<String> iter = test_1.iterator();
        assertEquals("A",iter.next());
        assertEquals("B",iter.next());
        assertEquals("D",iter.next());
        assertEquals("E",iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void test_intersection_1(){
        test.insert(30);
        test.insert(35);
        test.insert(40);
        test.insert(20);
        test.insert(50);
        test.insert(60);
        Iterator<Integer> iter_1 = test.iterator();
        test_2.insert(20);
        test_2.insert(35);
        test_2.insert(90);
        test_2.insert(10);
        test_2.insert(40);
        test_2.insert(60);
        Iterator<Integer> iter_2 = test_2.iterator();
        ArrayList<Integer> check = new ArrayList<>();
        check.add(20);
        check.add(35);
        check.add(40);
        check.add(60);
        assertEquals(check, test.intersection(iter_1,iter_2));

    }
    @Test
    public void test_intersection_2(){
        test.insert(50);
        test.insert(60);
        Iterator<Integer> iter_1 = test.iterator();
        test_2.insert(40);
        test_2.insert(60);
        Iterator<Integer> iter_2 = test_2.iterator();
        ArrayList<Integer> check = new ArrayList<>();
        check.add(60);
        assertEquals(check, test.intersection(iter_1,iter_2));

    }
    @Test
    public void test_intersection_3(){
        test.insert(50);
        test.insert(90);
        Iterator<Integer> iter_1 = test.iterator();
        test_2.insert(40);
        test_2.insert(60);
        Iterator<Integer> iter_2 = test_2.iterator();
        ArrayList<Integer> check = new ArrayList<>();
        assertEquals(check, test.intersection(iter_1,iter_2));

    }

    @Test(expected = NullPointerException.class)
    public void test_exception_1(){
        test.insert(null);
    }

    @Test(expected = NullPointerException.class)
    public void test_exception_2() {
        test.insert(50);
        test.insert(60);
        test.findKey(null);
    }

    @Test(expected = NullPointerException.class)
    public void test_exception_3() {
        test.insert(50);
        test.insert(60);
        test.findKey(null);
    }

    @Test(expected = NullPointerException.class)
    public void test_exception_4(){
        test.insert(50);
        test.insert(60);
        test.insertData(null, 12);
    }

    @Test(expected = NullPointerException.class)
    public void test_exception_5(){
        test.insert(50);
        test.insert(60);
        test.insertData(50, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_exception_6(){
        test.insert(50);
        test.insert(60);
        test.insertData(80, 12);
    }

    @Test(expected = NullPointerException.class)
    public void test_exception_7(){
        test.insert(50);
        test.insert(60);
        test.findDataList(null);
    }

    @Test(expected = NullPointerException.class)
    public void test_exception_8(){
        test.insert(50);
        test.insert(60);
        test.insertData(50, null);
    }

    @Test(expected = NullPointerException.class)
    public void test_exception_9(){
        test.insert(50);
        test.insert(60);
        test.findDataList(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_exception_10(){
        test.insert(50);
        test.insert(60);
        test.findDataList(80);
    }

    @Test(expected = NoSuchElementException.class)
    public void test_exception_11(){
        test_1.insert("B");
        test_1.insert("A");
        test_1.insert("D");
        test_1.insert("E");
        Iterator<String> iter = test_1.iterator();
        assertEquals("A",iter.next());
        assertEquals("B",iter.next());
        assertEquals("D",iter.next());
        assertEquals("E",iter.next());
        iter.next();
    }
}
