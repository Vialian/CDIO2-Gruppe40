package Entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class DieTest {
    private int faceValue;
    @Test
    public void roll() {
        faceValue = (int)(Math.random() * 6.0 + 1.0);

    }

    @Test
    public void getFaceValue() {
        assertEquals(1,faceValue,6);
    }
}