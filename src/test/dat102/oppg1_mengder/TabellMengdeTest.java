package test.dat102.oppg1_mengder;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import dat102.uke89.oppg1_mengder.MengdeADT;
import dat102.uke89.oppg1_mengder.TabellMengde;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class TabellMengdeTest {
    // deklarere referansevariabeler, det er referansepeekere og ikk tabbeler derfor må vi gjøre det om til tabell via tilTabel()- metoden
    MengdeADT<Integer> tomTabell;
    MengdeADT<Integer> tabell;
    MengdeADT<Integer> annenTabell;
    MengdeADT<Integer> delmengde;
    MengdeADT<Integer> likMengde;
    MengdeADT<Integer> disjunkt;

    @BeforeEach
    void nullstill() {

        tomTabell = new TabellMengde<>();

        tabell = new TabellMengde<>();
        tabell.leggTil(1);
        tabell.leggTil(2);
        tabell.leggTil(3);
        tabell.leggTil(4);
        tabell.leggTil(4);


        delmengde = new TabellMengde<>();
        delmengde.leggTil(2);
        delmengde.leggTil(4);

        disjunkt = new TabellMengde<>();

        disjunkt.leggTil(5);
        disjunkt.leggTil(6);
        disjunkt.leggTil(7);



       MengdeADT<Integer> likMengde = new TabellMengde<>();

        //likMengde.leggTilAlleFra(tabell);


    }
    @Test
    void testErTom() {

        if (tomTabell == null) {
            System.out.println("hei");
        }
        assertTrue(tomTabell.erTom());
        assertFalse(tabell.erTom());

    }
    @Test
    void testInnholder() {
        assertTrue(tabell.inneholder(1));
        assertTrue(tabell.inneholder(2));
        assertTrue(tabell.inneholder(3));
        assertTrue(tabell.inneholder(4));

        assertFalse(tabell.inneholder(5));
        assertFalse(tabell.inneholder(6));
        assertFalse(tabell.inneholder(7));
        assertFalse(tomTabell.inneholder(1));
    }

    @Test
    void testerDelmengdeAv() {

        assertTrue(delmengde.erDelmengdeAv(tabell));
        assertTrue(tabell.erDelmengdeAv(tabell));
        assertTrue(tomTabell.erDelmengdeAv(tomTabell));

        assertFalse(tabell.erDelmengdeAv(tomTabell));
        assertFalse(tomTabell.erDelmengdeAv(tabell));

    }

    @Test
    void testerLik() {

        assertTrue(tabell.erLik(likMengde));
        assertFalse(tabell.erLik(tomTabell));
        assertFalse(likMengde.erLik(tomTabell));

    }

    @Test
    void testerDisjunkt() {

        assertTrue(tabell.erDisjunkt(disjunkt));
        assertTrue(tabell.erDisjunkt(tomTabell));
        assertFalse(tabell.erDisjunkt(likMengde));

    }

    @Test
    void testSnit() {
        assertEquals(delmengde, tabell.snitt(delmengde));

    }

    @Test
    void testUnion() {
        MengdeADT<Integer> union = new TabellMengde<>();
        union.leggTil(1);
        union.leggTil(2);
        union.leggTil(3);
        union.leggTil(4);
        union.leggTil(5);
        union.leggTil(6);
        union.leggTil(7);



        assertEquals(union, tabell.union(disjunkt));
        // må bruke assertArrayEquals for å sammenligne elementenee i tabellen.
        // Ellers vil den bare sammenligne referansen til tabellen (assertEquals)

    }

    @Test
    void testMinus() {
        Integer [] forventet = {1, 2, 3};
        tabell.fjern(4);

        assertArrayEquals(tabell.tilTabell(), forventet);

    }

    @Test
    void testLeggTil() {
        Integer[] lagtTil = {1, 2, 3, 4, 5};

        tabell.leggTil(5);
        assertArrayEquals(tabell.tilTabell(), lagtTil);


    }

    @Test
    void testLeggTilAlleFra() {
        annenTabell.leggTil(5);
        annenTabell.leggTil(6);

        Integer[] forventet = {1, 2, 3, 4, 5, 6};

        tabell.leggTilAlleFra(annenTabell);

        assertArrayEquals(forventet, tabell.tilTabell());

    }

    @Test
    void testFjern() {
        tabell.fjern(3);
        assertEquals(3, tabell.antallElementer());
        assertFalse(tabell.inneholder(3));
    }

    @Test
    void testTilTabell() {
        Integer[] forventet = {1, 2, 3, 4};
        assertArrayEquals(tabell.tilTabell(), forventet);

    }

    @Test
    void testAntallElementer() {
        assertEquals(0, tomTabell.antallElementer());
        assertEquals(4, tabell.antallElementer());

    }

}

