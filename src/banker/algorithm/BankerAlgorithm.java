package banker.algorithm;
import java.util.*;
public class BankerAlgorithm {
    public static Scanner sc = new Scanner(System.in);
    public static boolean isSafe = true;
    public static int nProcesses,nResources;
    public static ArrayList<process> Processes;
    public static ArrayList AvailableRes;
    public static ArrayList<process> seq;
    public static process r;
    public void customInput(){
        System.out.println("enter the no.of processes in the system:");
        nProcesses = sc.nextInt();
        System.out.println("enter the no.of Resources in the system:");
        nResources = sc.nextInt();
        Processes = new ArrayList(nProcesses);
        for(int i=0;i<nProcesses;i++)
            Processes.add(new process("P"+i,nResources));
        System.out.println("enter the Available resources' instances");
        AvailableRes = new ArrayList(nResources);
        for(int i=0;i<nResources;i++)
            AvailableRes.add(sc.nextInt());
        System.out.println("enter the allocation matrix : ");
        for(int i=0;i<nProcesses;i++)
            for(int j =0;j<nResources;j++)
                (Processes.get(i)).addAllo(sc.nextInt());
        System.out.println("enter the max matrix : ");
        for(int i=0;i<nProcesses;i++)
            for(int j =0;j<nResources;j++)
                    Processes.get(i).addMax(sc.nextInt());
        for(int i=0;i<nProcesses;i++)
            Processes.get(i).setNeed();
        seq = new ArrayList(nProcesses);
        while(true)
        {
            int Rem1 = seq.size();
            if(seq.size()==nProcesses)
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
                        }
                        if(!Processes.get(i).isAdded)
                        {
                            seq.add(Processes.get(i));
                            Processes.get(i).isAdded = true;
                        }
                    }
                }
            }
            int Rem2 = seq.size();
            if(Rem1 == Rem2)
            {
                isSafe = false;
                break;
            }
        }
        System.out.println("\n\n\n\n");
        if(!isSafe)
        {
            System.out.println("The System is Unsafe !!!");
            System.out.println("The Processes causing DeadLock are : ");
            for(int i=0;i<nProcesses;i++)
            {
                boolean isLocked = false;
                for(int j=0;j<seq.size();j++)
                    if(Processes.get(i).name.equals(seq.get(j).name))
                        isLocked = true;
                if(!isLocked)
                    System.out.println(Processes.get(i).name);
            }
        }
        else
        {
        for(int i=0;i<seq.size();i++)
            System.out.print(seq.get(i).name +" --> ");
        }
        String response;
        System.out.println("Do You Want To Request ?? Y = yes, N = no");
        response  = sc.nextLine();
        while("Y".equals(response))
        {
            ArrayList req = new ArrayList(nResources);
            System.out.println("which process requests ?? ");
            r = new process(sc.nextLine(),nResources);
            System.out.println("enter the instances requested : ");
            for(int i=0;i<nResources;i++)
                req.add(sc.nextInt());
            request(req);
            System.out.println("Do You Want To Request ?? Y = yes, N = no");
            response  = sc.nextLine();
        }
        System.out.println("End");
    }
    public void request(ArrayList req)
    {
        
     boolean canReq =true;
        for(int i=0;i<nProcesses;i++)
            if(Processes.get(i).getName()==r.getName())
            {
                canReq = true;
                for(int j=0;j<nResources;j++)
                    if((int)req.get(j) > Processes.get(i).getNeed(j))
                        canReq = false;     
            }//check : if request <= need .. canReq should be true 
        for(int k=0;k<nResources;k++)
            if((int)req.get(k) > (int)AvailableRes.get(k))
                canReq = false;//check if request <= available .. canReq should be true     
        if(canReq)
        {
        
            while(true)
                { 
                    int Rem1 = seq.size();
                    if(seq.size()==nProcesses)
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
                               }
                              if(!Processes.get(i).isAdded)
                                {
                                    seq.add(Processes.get(i));
                                 Processes.get(i).isAdded = true;
                              }
                          }
                        }
                    }
                   int Rem2 = seq.size();
                   if(Rem1 == Rem2)
                   {
                      isSafe = false;
                      break;
                   }
             }
    }
}
}
/*
enter the no.of processes in the system:
5
enter the no.of Resources in the system:
3
enter the Available resources' instances
3 3 2
enter the allocation matrix : 
0 1 0
2 0 0
3 0 2
2 1 1
0 0 2
enter the max matrix : 
7 5 3
3 2 2
9 0 2
2 2 2
4 3 3





P1 --> P3 --> P4 --> P0 --> P2 --> End
------------------------------------------------------
enter the no.of processes in the system:
5
enter the no.of Resources in the system:
3
enter the no.of Available instance of every resource respectively in the system:
3 3 2
enter the allocated instance for the process P0 of the resource R0 :
0
enter the allocated instance for the process P0 of the resource R1 :
1
enter the allocated instance for the process P0 of the resource R2 :
0
enter the max instance for the process P0 of the resource R0 :
7
enter the max instance for the process P0 of the resource R1 :
5
enter the max instance for the process P0 of the resource R2 :
3
enter the allocated instance for the process P1 of the resource R0 :
2
enter the allocated instance for the process P1 of the resource R1 :
0
enter the allocated instance for the process P1 of the resource R2 :
0
enter the max instance for the process P1 of the resource R0 :
333
enter the max instance for the process P1 of the resource R1 :
2
enter the max instance for the process P1 of the resource R2 :
2
enter the allocated instance for the process P2 of the resource R0 :
3
enter the allocated instance for the process P2 of the resource R1 :
0
enter the allocated instance for the process P2 of the resource R2 :
2
enter the max instance for the process P2 of the resource R0 :
9
enter the max instance for the process P2 of the resource R1 :
0
enter the max instance for the process P2 of the resource R2 :
2
enter the allocated instance for the process P3 of the resource R0 :
2
enter the allocated instance for the process P3 of the resource R1 :
1
enter the allocated instance for the process P3 of the resource R2 :
1
enter the max instance for the process P3 of the resource R0 :
2
enter the max instance for the process P3 of the resource R1 :
2
enter the max instance for the process P3 of the resource R2 :
2
enter the allocated instance for the process P4 of the resource R0 :
0
enter the allocated instance for the process P4 of the resource R1 :
0
enter the allocated instance for the process P4 of the resource R2 :
2
enter the max instance for the process P4 of the resource R0 :
4
enter the max instance for the process P4 of the resource R1 :
3
enter the max instance for the process P4 of the resource R2 :
3





The System is Unsafe !!!
The Processes causing DeadLock are : 
P0
P1
P2
End
*/