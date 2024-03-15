package dat102.uke89.oppg1_mengder;

import java.util.Arrays;

public class TabellMengde<T> implements MengdeADT<T> {

    private T[] tab;
    private int antall;
    private static final int DEFAULT_SIZE = 10;

    public TabellMengde() {
        this(DEFAULT_SIZE);
    }

    @SuppressWarnings("unchecked")
    public TabellMengde(int size) {
        tab = (T[]) new Object[size];
        antall = 0;
    }

    @Override
    public boolean erTom() {
        return antall == 0;
    }

    //blabla.inneholder("e")
    @Override
    public boolean inneholder(T element) {
        for (T tabElement : tab) {
            if (tabElement.equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
        for (int i = 0; i < antall; i++) {
            if (!annenMengde.inneholder(tab[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean erLik(MengdeADT<T> annenMengde) {

        int i = 0;
        int j = 0;

        while (i < antall && j < annenMengde.antallElementer()) {
            if (inneholder(annenMengde.tilTabell()[j])) {
                i++;
            }
            j++;
        }

        return i == antall && j == annenMengde.antallElementer();
    }

    @Override
    public boolean erDisjunkt(MengdeADT<T> annenMengde) {

        boolean dis = true;
        //For at mengdene skal være disjunkte, kan de ikke ha noen elementer til felles.
        for (int i = 0; i < antall; i++) {
            if (annenMengde.inneholder(tab[i])) {
                dis = false;
                break;
            }
        }
        return dis;
    }

    @Override
    public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {

        MengdeADT<T> snittMengde = new TabellMengde<>();

        for (T element : tab) {
            if (annenMengde.inneholder(element)) {
                snittMengde.leggTil(element);
            }
        }

        return snittMengde;
    }

    @Override
    public MengdeADT<T> union(MengdeADT<T> annenMengde) {

        MengdeADT<T> unionMengde = new TabellMengde<>();
        unionMengde.leggTilAlleFra(annenMengde);

        for (T element : tab) {
            if (!unionMengde.inneholder(element)) {
                unionMengde.leggTil(element);
            }
        }

        return unionMengde;
    }

    @Override
    public MengdeADT<T> minus(MengdeADT<T> annenMengde) {

        MengdeADT<T> minusMengde = new TabellMengde<>();

        //Skal ta mengde minus annenMengde
        for (T element : tab) {
            minusMengde.leggTil(element);

            if (annenMengde.inneholder(element)) {
                minusMengde.fjern(element);
            }
        }
        return null;
    }

    @Override
    public void leggTil(T element) {
        if (this.inneholder(element)) {
            return;
        }
        if (antall == tab.length) {
            tab = Arrays.copyOf(tab, tab.length * 2);
        }
        tab[antall] = element;
        antall++;
    }


    @Override
    public void leggTilAlleFra(MengdeADT<T> annenMengde) {

        for (T element : tab) {
            if (!annenMengde.inneholder(element)) {
                annenMengde.leggTil(element);
            }
        }

    }

    @Override
    public T fjern(T element) {

        // Finne (indeksen til) den som skal slettes og lagre den.
        int index = -1;
        for (int i = 0; i < antall; i++) {
            if (element.equals(tab[i])) {
                index = i; //lagrer indekspossisjonen
            }
        }
        //elementet finnes ikke i tabellen-
        if (index == -1) {
            return null;
        }
        //flytter det bakerste elementet til elementes possisjon. Sletter bakerst element.
        tab[index] = tab[antall - 1];
        antall--;
        return element;
    }

    @Override
    public T[] tilTabell() {
        return Arrays.copyOf(tab, antall);
    }

    @Override
    public int antallElementer() {
        return antall;
    }
}
