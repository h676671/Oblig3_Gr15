package dat102.uke89.oppg1_mengder;

import java.util.Arrays;

public class LenketMengde<T> implements MengdeADT<T>{

    private class Node{

        private T data;
        private Node neste;

        private Node(T data) {
            this.data = data;
            this.neste = null;
        }
    }

    /*******************************************/

    private Node forste;
    private int antall;

    public LenketMengde(){
        this.forste = null;
        this.antall = 0;
    }

    /*******************************************/
    @Override
    public boolean erTom() {
        return antall == 0;
    }

    @Override
    //SEX.inneholder("hei");
    public boolean inneholder(T element) {
        Node temp = forste;
        while (forste != null) {
            if (element.equals(temp.data)) {
                return true;
            }
            temp = temp.neste;
        }
        return false;
    }

    @Override
    public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
        Node temp = forste;
        while (forste != null) {
            if (!annenMengde.inneholder(temp.data)) {
                return false;
            }
            temp = temp.neste;
        }
        return true;
    }

    @Override
    public boolean erLik(MengdeADT<T> annenMengde) {
        Node temp = forste;
        while(forste != null) {
            if (temp.data.equals(annenMengde)) {
                return true;
            }
            temp = temp.neste;
        }
        return false;
    }

    @Override
    public boolean erDisjunkt(MengdeADT<T> annenMengde) {
        Node temp = forste;
        while(forste != null) {
            if (temp.data.equals(annenMengde)) {
                return false;
            }
            temp = temp.neste;
        }

        return true;
    }

    @Override
    public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
        MengdeADT<T> snittMengde = new LenketMengde<>();
        Node temp = forste;

        while (temp != null) {
            if (annenMengde.inneholder(temp.data)) {
                snittMengde.leggTil(temp.data);
            }
            temp = temp.neste;
        }
        return snittMengde;
    }

    @Override
    public MengdeADT<T> union(MengdeADT<T> annenMengde) {

        MengdeADT<T> unionMengde = new LenketMengde<>();
        Node temp = forste;

        unionMengde.leggTilAlleFra(annenMengde);
        while(temp != null) {
            if(!unionMengde.inneholder(temp.data)) {
                unionMengde.leggTil(temp.data);

            }
            temp = temp.neste;
        }
        return unionMengde;
    }

    @Override
    public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
        MengdeADT<T> minusMengde = new LenketMengde<>();
        Node temp = forste;

        // Skal ta mengde minus annenMengde
        while(temp != null) {
            if (!annenMengde.inneholder(temp.data)) {
                minusMengde.leggTil(temp.data);
            }
            temp = temp.neste;
        }
        return minusMengde;
    }

    @Override
    public void leggTil(T element) {
        /* vi må lage en ny node som representerer elementes data ellers
         * vil det bryte strukturen til en lenke da forste er ment for å
         * peke på den forste noden og ikke selve elementet
         */

        Node nyNode = new Node((T) element);

        //sjekke om den tabellen er tom, hvis den er kan vi putte element til forste
        Node temp = forste;
        if (temp == null) {
            temp = nyNode;
        } else {
            //sjekker at neste temp ikke er null, da betyr det at du ikke er i slutten av lenken.
            while (temp.neste != null) {
                temp = temp.neste;
            }
            //hvis temp.neste == null, da er vi i slutten av lenken og setter temp.neste = nyNode
            temp.neste = nyNode;
        }
        antall++;
    }

    @Override
    public void leggTilAlleFra(MengdeADT<T> annenMengde) {
        LenketMengde<T> tempMengde = new LenketMengde<>();
        Node temp = forste;

        //kopier elementene fra den nåværende liste til en midlertidig lenke
        while (temp != null) {
            tempMengde.leggTil(temp.data);
            temp = temp.neste;
        }

        //legg til alle elementen fra den midlertidig lenke til annenMengde
        temp = tempMengde.forste;
        while (temp != null) {
            annenMengde.leggTil(temp.data);
            temp = temp.neste;
        }
    }

    @Override
    public boolean fjern(T element) {

        //1) Finn referanse til den som skal fjernes, null hvis ikke funnet.
        //   Nesten som da vi søkte etter "Anne". Kanskje vi burde laget en
        //   private hjelpemetode for denne?

        //2) Hvis ikke funnet, så return false.

        //3) Fjerning kan gjøres ved å overskrive data med data fra første node,
        //	 og deretter fjerne første node.

        if (erTom()) {
            return null;
        }
        // Finne (indeksen til) den som skal slettes og lagre den.
        Node forrige = null;
        Node temp = forste;

        while(temp != null) {
            if (temp.data.equals(element)) {
                if(forrige == null) {
                    forste = temp.neste;
                }

            }
        }
        return (T) temp;
    }

    @Override
    public T[] tilTabell() {
        // Starter med å lage tabellen elementene skal settes inn i.
        // Hva hvis tom? Lage en tabell med plass til 0 elementer?
        @SuppressWarnings("unchecked")
        T[] resultat = (T[]) new Object[antall];

        Arrays.copyOf(resultat, antall);

        return resultat;
    }

    @Override
    public int antallElementer() {
        return antall;
    }
}
