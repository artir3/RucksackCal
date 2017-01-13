package source;

import org.junit.Test;

import static org.junit.Assert.*;


public class ElementTest {
    @Test
    public void totalWeight() throws Exception {
    Element element = new Element.Builder("test").amount(1).weight(10).build();
    assert(10== element.getTotalWeight());
    }

    @Test
    public void getName() throws Exception {
        Element element = new Element.Builder("test").amount(1).weight(10).build();
        assertEquals("Object with name: test, amount: 1, weight: 10, so must be 10",10, element.getTotalWeight());

    }

    @Test
    public void getWeight() throws Exception {

    }

    @Test
    public void setWeight() throws Exception {

    }

    @Test
    public void getAmount() throws Exception {

    }

    @Test
    public void setAmount() throws Exception {

    }

}