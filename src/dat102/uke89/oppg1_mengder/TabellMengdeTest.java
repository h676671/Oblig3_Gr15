package dat102.uke89.oppg1_mengder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.event.MenuDragMouseEvent;

import static org.junit.jupiter.api.Assertions.*;

class TabellMengdeTest{
     MengdeADT<Integer> tabell;
     MengdeADT<Integer> tomTabell;

    @BeforeEach
    void init() {
         tabell = new TabellMengde<>();

         tabell.leggTil(1);
         tabell.leggTil(2);9
         tabell.leggTil(3);
         tabell.leggTil(4);
         tabell.leggTil(4);
         tabell.leggTil(5);

    }
    @Test
    void erTom() {
        assertTrue(tomTabell.erTom());
        assertFalse(tabell.erTom());
    }

    @Test
    void inneholder() {
        assertTrue(tabell.inneholder(1));
        assertTrue(tabell.inneholder(2));
        assertTrue(tabell.inneholder(3));
        assertFalse(tabell.inneholder(0));
        assertFalse(tabell.inneholder(-1));
        assertFalse(tabell.inneholder(-2));
        assertFalse(tabell.inneholder(-3));
    }

    @Test
    void erDelmengdeAv() {
        MengdeADT<Integer> sjekkDelmengde = new TabellMengde<>();

        sjekkDelmengde.leggTil(2);
        sjekkDelmengde.leggTil(4);

        assertTrue(
                tabell.erDelmengdeAv(tabell)
        );
        assertTrue(
                sjekkDelmengde.erDelmengdeAv(tabell)
        );
        assertFalse(
                tabell.erDelmengdeAv(sjekkDelmengde)
        );
    }

    @Test
    void erLik() {
        MengdeADT<Integer> likMengde = new TabellMengde<>();

        likMengde.leggTilAlleFra(tabell);
        assertTrue(tabell.erLik(likMengde));
        assertFalse(tabell.erLik(tomTabell));
        assertFalse(likMengde.erLik(tomTabell));
    }

    @Test
    void erDisjunkt() {
        assertTrue(tabell.erDisjunkt(tomTabell));

    }

    @Test
    void snitt() {
        MengdeADT<Integer> nyTabell = new TabellMengde<>();
        nyTabell.leggTil(1);
        nyTabell.leggTil(9);
        assertEquals(1, tabell.snitt(nyTabell));
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
        tabell.fjern(3);
        assertFalse(tabell.inneholder(3));

    }

    @Test
    void tilTabell() {
    }

    @Test
    void antallElementer() {
        assertEquals(0, tomTabell.antallElementer());

    }
}