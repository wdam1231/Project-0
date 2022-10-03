import static org.junit.Assert.*;

import org.junit.Test;

public class ResizeableArrayBagTest
{
    @Test
    public void testUnion(){
        ResizeableArrayBag<Integer> bag1 = new ResizeableArrayBag<>(2);
        bag1.add(5);
        bag1.add(6);
        ResizeableArrayBag<Integer> bag2 = new ResizeableArrayBag<>(2);
        bag2.add(5);
        bag2.add(4);

        BagInterface<Integer> result = bag1.union(bag2);

        assertTrue(result.contains(5));
        assertTrue(result.contains(6));
        assertTrue(result.contains(5));
        assertTrue(result.contains(5));
    }

    @Test
    public void testIntersection(){
        ResizeableArrayBag<Object> bag1 = new ResizeableArrayBag<>(2);
        bag1.add(1);
        bag1.add(5);
        bag1.add(5);
        bag1.add(3);
        bag1.add(5);
        ResizeableArrayBag<Object> bag2 = new ResizeableArrayBag<>(2);
        bag2.add(2);
        bag2.add(1);
        bag2.add(1);
        bag2.add(5);
        bag2.add(4);

        BagInterface<Object> intersection = bag1.intersection(bag2);

        assertTrue(intersection.contains(1));
        assertTrue(intersection.contains(5));
    }

    @Test
    public void testDifference(){
        ResizeableArrayBag<Integer> bag1 = new ResizeableArrayBag<>(2);
        bag1.add(4);
        bag1.add(3);
        bag1.add(5);
        ResizeableArrayBag<Integer> bag2 = new ResizeableArrayBag<>(2);
        bag2.add(5);
        bag2.add(3);

        BagInterface<Integer> result = bag1.difference(bag2);

        assertTrue(result.contains(4));
        assertFalse(result.contains(3));
        assertFalse(result.contains(5));
    }
}