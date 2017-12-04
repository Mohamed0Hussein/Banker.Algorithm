package banker.algorithm;

import java.util.*;

public class process {
    String name;
    boolean isSafe;
    public int noOfResources;
    public ArrayList allocated,have,needs;
    public process(String name,int n){
        this.name = name;
        this.noOfResources = n;
        this.allocated = new ArrayList(n);
        this.have = new ArrayList(n);
        this.needs = new ArrayList(n);
        this.isSafe = false;
    }
    public void setAllo(int a)
    {
        allocated.add(a);
    }
    public void setHave(int a)
    {
        allocated.add(a);
    }
    public void setNeed()
    {
        for(int i=0;i<noOfResources;i++)
            needs.add((int)have.get(i)-(int)allocated.get(i));
    }
}
