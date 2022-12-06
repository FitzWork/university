package Aufgabenblatt_10;

import java.util.Collections;
import java.util.Formatter;
import java.util.HashMap;
import java.util.HashSet;

public class Relation<T> {
    private HashSet<T> hashSet;
    private HashMap<T, HashSet<T>> hashMap;
    public static String matrix = "%-4s";

    public Relation() {
        this.hashSet = new HashSet<>(); this.hashMap = new HashMap<>();
    }

    public Relation(HashSet<T> hs, HashMap<T, HashSet<T>> hm) {
        this.hashSet = hs; this.hashMap = hm;
    }

    @SafeVarargs
    public final void addElements(T... e) {
        Collections.addAll(hashSet, e);
    }

    public Boolean addRelation(T first, T sek) {
        if (hashSet.contains(first) && hashSet.contains(sek)) {
            if (hashMap.containsKey(first)) {
                if (hashMap.get(first).contains(sek))
                    return false;
                else {
                    hashMap.get(first).add(sek);
                    return true;
                }
            } else
                hashMap.put(first, new HashSet<>() {{ add(sek); }} );
            return true;
        }
        return false;
    }


    public Boolean PairInRel(T first, T sek) {
        if (hashMap.get(first) != null)
            return hashMap.get(first).contains(sek);
        return false;
    }

    public Boolean isRef() {
        for (T i : hashSet)
            if (!PairInRel(i, i))
                return false;
        return true;
    }

    public Boolean isTra() {
        for (T i : hashSet) {
            if (!hashMap.containsKey(i))
                continue;
            for (T j : hashMap.get(i)) {
                if (!hashMap.containsKey(j))
                    continue;
                for (T t : hashMap.get(j))
                    if (!PairInRel(i, t))
                        return false;
            }

        }
        return true;
    }

    public Boolean isSym() {
        for (T i : hashSet) {
            if (!hashMap.containsKey(i))
                continue;
            for (T j: hashMap.get(i))
                if (!PairInRel(j, i))
                    return false;
        }
        return true;
    }

    public Boolean isEquals() {
        return isSym() && isTra() && isRef();
    }

    public Relation<T> refHuelle() {
        if (isRef())
            return this;
        Relation<T> relation = new Relation<>(hashSet, hashMap);

        for (T i : hashSet)
            if (!PairInRel(i, i))
                relation.addRelation(i, i);
        return relation;
    }
    public Relation<T> traHuelle() {
        if (isTra())
            return this;
        Relation<T> relation = new Relation<T>(hashSet, hashMap);

        for (T i : hashSet) {
            if (!hashMap.containsKey(i))
                continue;
            for (T j : hashMap.get(i)) {
                if (!hashMap.containsKey(j))
                    continue;
                for (T t : hashMap.get(j))
                    if (!PairInRel(i, t))
                        relation.addRelation(i, t);
            }
        }
        return relation;
    }
    public Relation<T> symHuelle() {
        if (isSym())
            return this;
        Relation<T> relation = new Relation<>(hashSet, hashMap);

        for (T i : hashSet) {
            if (!hashMap.containsKey(i))
                continue;
            for (T j: hashMap.get(i))
                if (!PairInRel(j, i))
                    relation.addRelation(j, i);
        }
        return relation;
    }

    public String print() {
        StringBuilder s = new StringBuilder();
        Formatter f = new Formatter(s);

        f.format(matrix, "");
        for (T i : hashSet)
            f.format(matrix, i.toString());
        f.format("%n");
        for (T i : hashSet) {
            f.format(matrix, i.toString());
            for (T j : hashSet)
                f.format(matrix, PairInRel(i, j) ? "1" : "O");
            f.format("%n");
        }
        return s.toString();
    }
}
