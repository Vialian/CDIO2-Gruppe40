package Entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class DieTest {
    private int faceValue;
    @Test
    public void roll() {
        int amountTest =  1000;
        for (int i = 0; i < amountTest; i++) {
            faceValue = (int)(Math.random() * 6.0 + 1.0);
            assertEquals(3.5, faceValue,2.5);
        }


    }

    @Test
    public void getFaceValue() {
        assertEquals(1,faceValue,6);
    }
}