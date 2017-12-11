package banker.algorithm;
import java.util.*;
public class BankerAlgorithm {
    public void CustomInput(){
        Scanner sc = new Scanner(System.in);
        boolean isSafe = true;
        System.out.println("enter the no.of processes in the system:");
        int nProcesses = sc.nextInt();
        System.out.println("enter the no.of Resources in the system:");
        int nResources = sc.nextInt();
        ArrayList<process> Processes = new ArrayList(nProcesses);
        for(int i=0;i<nProcesses;i++)
            Processes.add(new process("P"+i,nResources));
        System.out.println("enter the no.of Available instance of every resource respectively in the system:");
        ArrayList AvailableRes = new ArrayList(nResources);
        for(int i=0;i<nResources;i++)
            AvailableRes.add(sc.nextInt());
        for(int i=0;i<nProcesses;i++)
            for(int j =0;j<nResources;j++)
            {
                System.out.println("enter the allocated instance for the process "+Processes.get(i).name+" of the resource R"+j+ " :");
                (Processes.get(i)).addAllo(sc.nextInt());
                System.out.println("enter the max instance for the process "+Processes.get(i).name+" of the resource R"+j + " :");
                Processes.get(i).addMax(sc.nextInt());
            }
        for(int i=0;i<nProcesses;i++)
            Processes.get(i).setNeed();
            ArrayList<process> seq = new ArrayList(nProcesses);
        while(true)
        {
            int Rem1 = nProcesses - seq.size();
            int completed = 0;
            for(int i =0;i<nProcesses;i++)
                if(Processes.get(i).isFree)
                    completed++;
            if(completed == nProcesses)
                break;
            else
            {
                boolean can;
                for(int i=0;i<nProcesses;i++)
                {
                    can = true;
                    for(int j=0;j<nResources;j++)
                    {
                        if((int)AvailableRes.get(j)<Processes.get(i).getNeed(j))
                            can = false;
                    }
                    if(can)
                    {
                        for(int j=0;j<nResources;j++)
                        {
                            Processes.get(i).isFree = true;
                           AvailableRes.set(j,(int)AvailableRes.get(j)+Processes.get(i).getAllo(j));
                            Processes.get(i).changeAllo(j, 0);
                            Processes.get(i).changeNeed(j, 0);
                        }
                        if(!Processes.get(i).isAdded)
                        {
                            seq.add(Processes.get(i));
                            Processes.get(i).isAdded = true;
                        }
                    }
                }
            }
            int Rem2 = nProcesses - seq.size();
            if(Rem1 == Rem2)
            {
                isSafe = false;
                break;
            }
        }
        System.out.println("\n\n\n\n");
        if(!isSafe)
            System.out.println("The System is Unsafe !!!");
        else
        {
        for(int i=0;i<seq.size();i++)
            System.out.print(seq.get(i).name +" --> ");
        }
        System.out.println("End");
    }
    
}
