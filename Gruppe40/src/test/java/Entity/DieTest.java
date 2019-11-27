package Entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class DieTest {
    private int faceValue;
    private int prob = (1/6);
    int[] amountCount = new int[7];


    @Test
    public void roll() {
        int amountTest =  1001;
        for (int i = 1; i < amountTest; i++) {
            faceValue = (int)(Math.random() * 6.0 + 1.0);
            assertEquals(3.5, faceValue,2.5);
            amountCount[faceValue] += 1;
        }

        for (int i = 1; i < 6  ; i++) {
            assertEquals(prob, (amountCount[i]/amountTest), 0.01);
        }


    }

    @Test
    public void getFaceValue() {


        for(int i = 1; i < 7; i++) {
            while (faceValue != i)
                faceValue = (int)(Math.random() * 6.0 + 1.0);
            assertEquals(i, faceValue);
        }
    }
}