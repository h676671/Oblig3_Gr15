package test.dat102.oppg1_mengder;

import dat102.uke89.oppg1_mengder.LenketMengde;
import dat102.uke89.oppg1_mengder.MengdeADT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LenketMengdeTest {
    @BeforeEach
    void init() {

    }

    @Test
    void erTom() {
        MengdeADT<String> mengde = new LenketMengde();
        assertTrue(mengde.erTom());
        //legger til også fjerner bort, og sjekker om mengden er tom
        mengde.leggTil("hei");
        mengde.fjern("hei");
        assertTrue(mengde.erTom());
    }

    @Test
    void inneholder() {
        MengdeADT<String> mengde = new LenketMengde();
        mengde.leggTil("hei");
        assertTrue(mengde.inneholder("hei"));
        mengde.fjern("hei");
    }

    @Test
    void erDelmengdeAv() {
        MengdeADT<Integer> mengde = new LenketMengde();
        mengde.leggTil(1);
        mengde.leggTil(2);
        MengdeADT<Integer> annenMengde = new LenketMengde();
        annenMengde.leggTil(1);
    }

    @Test
    void erLik() {

        MengdeADT<String> mengde = new LenketMengde();
        mengde.leggTil("En");
        mengde.leggTil("To");
        MengdeADT<String> annenMengde = new LenketMengde();
        annenMengde.leggTil("En");
        annenMengde.leggTil("To");

        assertTrue(annenMengde.erLik(mengde));

        MengdeADT<String> mengde2 = new LenketMengde();
        mengde2.leggTil("En");
        mengde2.leggTil("To");
        MengdeADT<String> annenMengde2 = new LenketMengde();
        annenMengde2.leggTil("Tre");

        assertFalse(annenMengde2.erLik(mengde2));
    }


    @Test
    void erDisjunkt() {
    }

    @Test
    void snitt() {
    }

    @Test
    void union() {
    }

    @Test
    void minus() {
    }

    @Test
    void leggTil() {
    }

    @Test
    void leggTilAlleFra() {
    }

    @Test
    void fjern() {
    }

    @Test
    void tilTabell() {
    }

    @Test
    void antallElementer() {
    }


}