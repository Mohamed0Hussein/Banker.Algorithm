package banker.algorithm;

import java.util.*;
public class process {
    public static ArrayList allo,max,need;
    public static String name;
    public static boolean isFree,isAdded;
    public process(String name,int n){
        this.name = name;
        allo = new ArrayList(n);
        max = new ArrayList(n);
        need = new ArrayList(n);
        isFree = false;
        isAdded = false;
    }
    public void addAllo(int a){allo.add(a);}
    public void changeAllo(int index,int value){allo.set(index, value);}
    public int getAllo(int i){return (int)allo.get(i);}
    public void addMax(int a){max.add(a);}
    public int getMax(int i){return (int)max.get(i);}
    public void setNeed(){
    for(int i=0;i<allo.size();i++)
        need.add(this.getMax(i)-this.getAllo(i));
    }
    public int getNeed(int i){return (int)need.get(i);}
    public void changeNeed(int index,int value){need.set(index, value);}
    public String getName(){return this.name;}
    

}
