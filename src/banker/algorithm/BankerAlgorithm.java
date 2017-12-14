package banker.algorithm;
import java.util.*;
public class BankerAlgorithm {
//   public  void defaultInput(){
//       int noOfProcess = 5;
//       int noOfResources = 3;
//       int[][] allocation = new int [noOfProcess][noOfResources];
//       int[][] Max = new int [noOfProcess][noOfResources];
//       initAllocation(allocation);
//       initMax(Max);
//       int[] ResourceInstance = {10,5,7};
//       if(!checkResources(ResourceInstance, allocation, Max)){
//           System.err.println("Allocation or Max values are incorrect !!");       
//       }  
//       int[][] Need = new int [noOfProcess][noOfResources];
//       Need = CalculateNeed(allocation, Max);
//       if(Need == null)
//       {
//           System.err.println("Allocation or Max values are incorrect !!");
//       }
//       int[][] Available = new int [1][noOfResources];
//       initAvailable(Available);
//       System.out.println(Issafe(Max, Need, allocation, Available));
//       
//       
//   }
//    private void initAllocation(int allocation[][])
//   {
//       //p0
//       allocation[0][0] = 0;
//       allocation[0][1] = 1;
//       allocation[0][2] = 0;
//       //p1
//       allocation[1][0] = 2;
//       allocation[1][1] = 0;
//       allocation[1][2] = 0;
//       //p2
//       allocation[2][0] = 3;
//       allocation[2][1] = 0;
//       allocation[2][2] = 2;
//       //p3
//       allocation[3][0] = 2;
//       allocation[3][1] = 1;
//       allocation[3][2] = 1;
//       //p4
//       allocation[4][0] = 0;
//       allocation[4][1] = 0;
//       allocation[4][2] = 2;
//       
//   }
//   private void initMax(int Max[][])
//   {
//       //p0
//        Max[0][0] = 7;
//        Max[0][1] = 5;
//        Max[0][2] = 3;
//       //p1
//        Max[1][0] = 3;
//        Max[1][1] = 2;
//        Max[1][2] = 2;
//       //p2
//        Max[2][0] = 9;
//        Max[2][1] = 0;
//        Max[2][2] = 2;
//       //p3
//        Max[3][0] = 2;
//        Max[3][1] = 2;
//        Max[3][2] = 2;
//       //p4
//        Max[4][0] = 4;
//        Max[4][1] = 3;
//        Max[4][2] = 3;
//       
//   }
//   private boolean checkResources(int[] ResourceInstamce,int[][] alloacation,int[][] max){
//       for(int i = 0; i < 5;i++)
//       {
//           for(int j = 0; j < 3;j++)
//           {
//               if(ResourceInstamce[j] < alloacation[i][j] || ResourceInstamce[j] < max[i][j] )
//               {
//                   return false;
//               }
//           }
//       }
//       return true;
//   }
//   private void initAvailable(int[][] Availble)
//   {
//      Availble[0][0] = 3;
//      Availble[0][1] = 3;
//      Availble[0][2] = 2;
//   }
//  
//    private int [][] CalculateNeed(int allocation[][],int max[][]){
//        int Need[][] = new int [5][3];
//        for(int i = 0; i < 5; i++)
//        {
//            for (int j = 0; j < 3 ;j++) {
//                if(max[i][j] < allocation[i][j])
//                {
//                    return null;
//                }
//                Need[i][j] = max[i][j]-allocation[i][j];  
//            }
//        }
//        return Need;
//        
//    }
//    private String Issafe(int max[][],int[][] need, int[][] allocation,int[][] available){
//        boolean check  = true,finish = false;
//       int[][] remaindProcess = new int [1][3];
//        
//           for(int i = 0; i< 5; i++)
//           {
//               
//                   if(available[0][0] > need[i][0])
//                   {
//                       
//                       available[0][0] += allocation[i][0];
//                       available[0][1] += allocation[i][1];
//                       available[0][2] += allocation[i][2];
//                   }
//                   else if(available[0][1] > need[i][1])
//                   {
//                       available[0][0] += allocation[i][0];
//                       available[0][1] += allocation[i][1];
//                       available[0][2] += allocation[i][2];
//                   }
//                   else if(available[0][2] > need[i][2])
//                   {
//                      available[0][0] += allocation[i][0];
//                      available[0][1] += allocation[i][1];
//                      available[0][2] += allocation[i][2];
//                   }
//                   else{
//                       remaindProcess[0][0] +=  need[i][0];
//                       remaindProcess[0][1] +=  need[i][1];
//                       remaindProcess[0][2] +=  need[i][2];           
//                   } 
//           }
//           if(remaindProcess[0][0] < available[0][0])
//           {
//               return "All process Allocated in safe state";
//           }
//           else if(remaindProcess[0][0] == available[0][0])
//           {
//               if(remaindProcess[0][1] < available[0][1])
//               {
//                return "All process Allocated in safe state";   
//               }
//               else if(remaindProcess[0][2] < available[0][2])
//               {
//                   return "All process Allocated in safe statey"; 
//               }
//           }
//          return "Not All the procees can be allocated safely";
//           
//    }
    
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
        System.out.println("enter the Available resources' instances");
        ArrayList AvailableRes = new ArrayList(nResources);
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
        ArrayList<process> seq = new ArrayList(nProcesses);
        while(true)
        {
            int Rem1 = seq.size();
//            int completed = 0;
//            for(int i =0;i<nProcesses;i++)
//                if(Processes.get(i).isFree)
//                    completed++;
//            if(completed == nProcesses)
//                break;
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
//                            Processes.get(i).changeAllo(j, 0);
//                            Processes.get(i).changeNeed(j, 0);
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
        System.out.println("End");
    }
    
}
/*
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
3
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