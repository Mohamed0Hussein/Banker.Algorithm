package banker.algorithm;
import java.util.*;
public class BankerAlgorithm {
   
    
    public void customInput(){
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
 public  void defaultInput(){
       int noOfProcess = 5;
       int noOfResources = 3;
       int[][] allocation = new int [noOfProcess][noOfResources];
       int[][] Max = new int [noOfProcess][noOfResources];
       initAllocation(allocation);
       initMax(Max);
       int[] ResourceInstance = {10,5,7};
       if(!checkResources(ResourceInstance, allocation, Max)){
           System.err.println("Allocation or Max values are incorrect !!");       
       }  
       int[][] Need = new int [noOfProcess][noOfResources];
       Need = CalculateNeed(allocation, Max);
       if(Need == null)
       {
           System.err.println("Allocation or Max values are incorrect !!");
       }
       int[][] Available = new int [1][noOfResources];
       initAvailable(Available);
       System.out.println(Issafe(Max, Need, allocation, Available));
       
       
   }
    private void initAllocation(int allocation[][])
   {
       //p0
       allocation[0][0] = 0;
       allocation[0][1] = 1;
       allocation[0][2] = 0;
       //p1
       allocation[1][0] = 2;
       allocation[1][1] = 0;
       allocation[1][2] = 0;
       //p2
       allocation[2][0] = 3;
       allocation[2][1] = 0;
       allocation[2][2] = 2;
       //p3
       allocation[3][0] = 2;
       allocation[3][1] = 1;
       allocation[3][2] = 1;
       //p4
       allocation[4][0] = 0;
       allocation[4][1] = 0;
       allocation[4][2] = 2;
       
   }
   private void initMax(int Max[][])
   {
       //p0
        Max[0][0] = 7;
        Max[0][1] = 5;
        Max[0][2] = 3;
       //p1
        Max[1][0] = 3;
        Max[1][1] = 2;
        Max[1][2] = 2;
       //p2
        Max[2][0] = 9;
        Max[2][1] = 0;
        Max[2][2] = 2;
       //p3
        Max[3][0] = 2;
        Max[3][1] = 2;
        Max[3][2] = 2;
       //p4
        Max[4][0] = 4;
        Max[4][1] = 3;
        Max[4][2] = 3;
       
   }
   private boolean checkResources(int[] ResourceInstamce,int[][] alloacation,int[][] max){
       for(int i = 0; i < 5;i++)
       {
           for(int j = 0; j < 3;j++)
           {
               if(ResourceInstamce[j] < alloacation[i][j] || ResourceInstamce[j] < max[i][j] )
               {
                   return false;
               }
           }
       }
       return true;
   }
   private void initAvailable(int[][] Availble)
   {
      Availble[0][0] = 3;
      Availble[0][1] = 3;
      Availble[0][2] = 2;
   }
  
    private int [][] CalculateNeed(int allocation[][],int max[][]){
        int Need[][] = new int [5][3];
        for(int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 3 ;j++) {
                if(max[i][j] < allocation[i][j])
                {
                    return null;
                }
                Need[i][j] = max[i][j]-allocation[i][j];  
            }
        }
        return Need;
        
    }
    private String Issafe(int max[][],int[][] need, int[][] allocation,int[][] available){
        int[] holdProcess,processSequence;
        holdProcess = new int[5];
        processSequence = new int[5] ;
       boolean done = true;
        int j = 0,c1 = 0;
           for(int i = 0; i< 5; i++)
           {
               
                   if(available[0][0] > need[i][0])
                   {
                       
                       available[0][0] += allocation[i][0];
                       available[0][1] += allocation[i][1];
                       available[0][2] += allocation[i][2];
                       processSequence[c1] = i;
                       c1++;
                   }
                   else if(available[0][0] == need[i][0])
                   {
                       if(available[0][1] > need[i][1]){
                       available[0][0] += allocation[i][0];
                       available[0][1] += allocation[i][1];
                       available[0][2] += allocation[i][2];
                       processSequence[c1] = i;
                       c1++;
                       }
                       else if(available[0][2] > need[i][2]){
                       available[0][0] += allocation[i][0];
                       available[0][1] += allocation[i][1];
                       available[0][2] += allocation[i][2];
                       processSequence[c1] = i;
                       c1++;
                       }
                       else{
                       holdProcess[j] = i;
                       j++;
                   }
                   }
                   
                   else{
                       holdProcess[j] = i;
                       j++;
                   }
           }
           for(int c = 0; c < j; c++)
           {
               if(need[holdProcess[c]][0] < available[0][0] )
               {
                      available[0][0] += allocation[holdProcess[c]][0];
                      available[0][1] += allocation[holdProcess[c]][1];
                      available[0][2] += allocation[holdProcess[c]][2];
                      processSequence[c1] = holdProcess[c];
                      c1++;
               } else if(need[holdProcess[j]][0] == available[0][0] )
               {      if(need[holdProcess[j]][1] < available[0][1] )
               {
                      available[0][0] += allocation[holdProcess[c]][0];
                      available[0][1] += allocation[holdProcess[c]][1];
                      available[0][2] += allocation[holdProcess[c]][2];
                      processSequence[c1] = holdProcess[c];
                      c1++;
               }
               else if(need[holdProcess[c]][2] < available[0][2] )
               {
                   available[0][0] += allocation[holdProcess[c]][0];
                      available[0][1] += allocation[holdProcess[c]][1];
                      available[0][2] += allocation[holdProcess[c]][2];
                      processSequence[c1] = holdProcess[c];
                      c1++;
               }
               else{
                   done = false;
               }   
               }
           }
           
           if(done)
           {
               String s = "All process Allocated in safe state"
                       + " Process sequence is ";
              for(int i = 0; i < 5 ; i++)
              {
                  s += "P"+processSequence[i]+" ";
              }
              return s;
           }
           
          return "Not All the procees can be allocated safely";
           
    }
    
    
}
