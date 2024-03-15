package dat102.uke89.oppg1_mengder;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class JavaSetToMengde<T> implements MengdeADT<T> {

    private Set<T> set;

    public JavaSetToMengde() {
        set = new HashSet<>();
    }

    @Override
    public boolean erTom() {
        return set.isEmpty();
    }

    @Override
    public boolean inneholder(T element) {
        return set.contains(element);
    }

    @Override
    public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
        Iterator<T> setIterator = set.iterator();
        T element = setIterator.next();
        while (setIterator.hasNext()) {
            if (!annenMengde.inneholder(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean erLik(MengdeADT<T> annenMengde) {
        if (set.size() != annenMengde.antallElementer()) {
            return false;
        }
        return this.erDelmengdeAv(annenMengde);
    }

    @Override
    public boolean erDisjunkt(MengdeADT<T> annenMengde) {

        Iterator disIterator = set.iterator();
        while (disIterator.hasNext()) {
            if (this.inneholder((T) (annenMengde))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
        //Lager en ny mengde som jeg kan lagre snittet i
        MengdeADT<T> snitt = new JavaSetToMengde<>();

        //Ittererer gjennom settet og sjekker om annenmengde inneholder neste element i iterasjonen
        //Hvis den inneholder så legger vi den til i snitt mengde og returnerer snittet når vi er ferdig
        Iterator<T> iterator = set.iterator();
        while (iterator.hasNext()) {
            if (annenMengde.inneholder(iterator.next())) {
                snitt.leggTil(iterator.next());
            }
        }


        return snitt;
    }

    @Override
    public MengdeADT<T> union(MengdeADT<T> annenMengde) {

        //Vi kan kombinere to mengder og så legge de til i et sett, da vil vi fjerne "duplicates" automatisk.
        MengdeADT<T> snittMengde = new JavaSetToMengde<>();

        //Kan bruke enhanced for lække siden vi er sikker på at settet er fullt i denne metoden.
        // (Vanligvis lurt med while(iterator.hasNext())
        for (T e : set) {
            annenMengde.leggTil(e);
        }
        //Her trenger vi ikke sjekke for om elementet allerede er lagt til siden i et sett har vi ikke duplikater
        snittMengde.leggTilAlleFra(annenMengde);

        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public MengdeADT<T> minus(MengdeADT<T> annenMengde) {

        MengdeADT<T> minus = new JavaSetToMengde<>();
        Iterator iterator = set.iterator();
        T element = (T) iterator.next();

        //Bruker while, vi vet ikke om tabellen er full så kan ikke bruke "for-løkken"
        while (iterator.hasNext()) {
            minus.leggTil(element);

            if (annenMengde.inneholder(element)) {
                minus.fjern(element);
            }
        }
        return minus;
    }

    @Override
    public void leggTil(T element) {
        //Trenger ikke logikk for duplikat siden vi jobber med sets, vi får aldri to av samme alement i et sett
        set.add(element);
    }
    @Override
    @SuppressWarnings("unchecked")
    public void leggTilAlleFra(MengdeADT<T> annenMengde) {

        //Her jobber vi med sets så vi trenger ikke kode får å sjekke om element allerede finnes
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            set.add((T) iterator.next());
        }
    }

    @Override
    public T fjern(T element) {
        set.remove(element);
        return element;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] tilTabell() {
        return (T[])set.toArray();
    }

    @Override
    public int antallElementer() {
        return set.size();
    }
}



