package java.util;


import org.junit.Test;

public class ArrayListTest {

    @Test
    public void foreach() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(1);
        list.add(4);
        list.add(6);

        list.forEach(System.out::print);
    }
}
