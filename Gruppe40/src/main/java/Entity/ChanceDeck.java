package Entity;

public class ChanceDeck {
    int index;
    int[] deck;
    public ChanceDeck() {
        deck = new int[20];
        mixDeck();
    }

    private void mixDeck() {
        int[] initDeck = new int[20];
        for (int i = 0; i < 20; i++)
            initDeck[i] = 1;
        
        int mixCount = 0;
        while (mixCount < 20) {
            int i;
            do {
                i = (int)(Math.random() * 20);
            } while (initDeck[i] == 0);
            deck[mixCount++] = i;
            initDeck[i] = 0;
        }
        index = 0;
    }

    public void drawCard() {
        performAction(index++);
        if (index == 20)
            index = 0;
    }
    
    public void performAction(int index) {
        switch(index) {
            case 0:
                //Giv kort til bilen
                //Tage et chancekort mere
                //Til BIL:
                //På næste tur skal du flytte til ledigt felt og køb det
                //HVIS Ingen ledige: Køb af anden spiller
            case 1:
                //Ryk frem til start
                //Modtag 2M
            case 2:
                //Ryk op til 5 felter frem
            case 3:
                //Ryk frem til et orange felt
                //HVIS ledigt: Modtag felt gratis
                //ELLERS betal leje til ejeren
            case 4:
                //ENTEN ryk 1 felt frem
                //ELLER tag et chancekort mere
            case 5:
                //Giv dette kort til SKIBET
                //Tag et chancekort mere
                //Til SKIB:
                //Næste tur, ryk til hvilket som helst ledigt felt og køb
                //HVIS Ingen ledige: Køb af anden spiller
            case 6:
                //Du har spist for meget slik
                //Betal 2M til banken
            case 7:
                //GRATIS FELT
                //Ryk frem til Orange eller Grønt felt
                //HVIS ledigt: Modtag felt
                //ELLERS betal leje
            case 8:
                // --- || ----
                // (men for lyseblåt felt)
            case 9:
                // Modtag kort
                // Bruges til løsladelse fra fængsel
            case 10:
                // Ryk frem til Strandpromenaden
            case 11:
                // Som 5, men til KAT
            case 12:
                // Som 5 men til HUND
            case 13:
                // Fødselsdag: Modtag 1 M
            case 14:
                // som 7, men for pink eller Lyseblåt
            case 15:
                // Lektier: Modtag 2M
            case 16:
                // som 8, men for Rødt
            case 17:
                //Skaterparken: Ryk til Sk.
                // HVIS ledigt: Modtag gratis
                // ELLERS betal leje til ejer
            case 18:
                // Som 8, men for Lyseblåt eller Rødt
            case 19:
                // Som 8, men for Brunt eller Gult
        }
    }
}

