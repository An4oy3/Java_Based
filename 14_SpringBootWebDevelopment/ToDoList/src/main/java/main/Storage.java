package main;

import main.model.Casse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Storage {
    private volatile static int currentId = 1;
    private volatile static HashMap<Integer, Casse> caseList = new HashMap<>();

    public static synchronized List<Casse> getAllCases(){
        ArrayList<Casse> listCasse = new ArrayList<>();
        listCasse.addAll(caseList.values());
        return listCasse;
    }

    public static synchronized int add(Casse c){
        int id = currentId++;
        c.setId(id);
        caseList.put(id, c);
        return currentId;
    }
    public static synchronized Casse getCase(int id){
        if(caseList.containsKey(id)){
            return caseList.get(id);
        } else {
            return null;
        }
    }

    public static synchronized void deleteAllCases(){
        caseList.clear();
    }

    public static synchronized boolean deleteCurrentCase(int id){
        if(!caseList.containsKey(id)){
            return false;
        } else {
            caseList.remove(id);
            return true;
        }
    }

    public static synchronized void putAllCases(){
        caseList.forEach((integer, aCasse) -> aCasse.setName("BUY MILK!"));
    }

    public static synchronized void putCurrentCase(int i){
        caseList.get(i).setName("marj");
    }
}
