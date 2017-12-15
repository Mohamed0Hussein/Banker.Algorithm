package banker.algorithm;
import java.util.*;
public class BankerAlgorithm {
    
    private int available[];
    private int max[][];
    private int allocation[][];
    private int need[][];
    private boolean finish[];
    private int processesNum;
    private int typesNum;
    private int req[];
    Scanner sc = new Scanner(System.in);
    public BankerAlgorithm(int numProcesses, int numTypes) {
        processesNum = numProcesses;
        typesNum = numTypes;
        finish = new boolean[processesNum];
        available = new int[typesNum];
        max = new int[processesNum][typesNum];
        allocation = new int[processesNum][typesNum];
        need = new int[processesNum][typesNum];
        req = new int[typesNum];
        getInput();
        
        calculateNeed();
        
        for(int i = 0; i < processesNum; i++) {
            finish[i] = false;
        }
    }
    
    public String execute() {
        String result = "Sequence of the safe state is  :-\n";
        
        int finished = 0;
        while(true) {
                int tmp = finished;
                boolean can;
                for(int i = 0; i < processesNum; i++) {
                    can = true;
                    
                    if(finish[i]) {
                        continue;
                    }
                    
                    for(int j = 0; j < typesNum; j++) {
                        /*if((int)AvailableRes.get(j)<Processes.get(i).getNeed(j)) {
                            can = false;
                        }*/
                        if(need[i][j] > available[j]) {
                            can = false;
                            break;
                        }
                    }
                    
                    if(can) {
                        for(int j = 0; j < typesNum; j++) {
                            /*Processes.get(i).isFree = true;
                           AvailableRes.set(j,(int)AvailableRes.get(j)+Processes.get(i).getAllo(j));*/
                            available[j] += allocation[i][j];
                        }
                        
                        finish[i] = true;
                        finished++;
                        result += "p" + i + " ";
                        
                        /*if(!Processes.get(i).isAdded) {
                            seq.add(Processes.get(i));
                            Processes.get(i).isAdded = true;
                        }*/
                    }
                }
                
                if(finished == processesNum)
                    break;
                else if(tmp == finished)
                {
                    String deadlockProcesses = "";
                    for(int k = 0; k < processesNum; k++) {
                        if(!finish[k]) {
                            deadlockProcesses += "P" + k + " ";
                        }
                    }
                    result = "The System is Unsafe\n" 
                            + "The processes causing the deadlock are :-\n"
                            + deadlockProcesses;
                    break;
                            
                }
            }
        
        return result;
    }
    
    private void getInput() {
        System.out.println("enter the Available resources' instances");
        
        for(int i = 0; i < typesNum; i++) {
            available[i] = sc.nextInt();
        }
        
        sc.nextLine();
        
        for(int i = 0; i < processesNum; i++) {
            System.out.print("Please enter the allocated resources for ");
            System.out.println("p" + i);
            
            for(int j = 0; j < typesNum; j++) {
                allocation[i][j] = sc.nextInt();
            }
            
            sc.nextLine();
            
            System.out.println("Please enter the max resources for p" + i);
            
            for(int j = 0; j < typesNum; j++) {
                max[i][j] = sc.nextInt();
            }
            
            sc.nextLine();
        }
    }
    public String Request(int target){
            setReq();
            for(int i=0;i<typesNum;i++)
                if(target == i)
                {
                    boolean can = true;
                    for(int j=0;j<typesNum;j++)
                        if(req[j]>need[i][j])
                            if(req[j]> available[j])
                                can = false;
                    if(can)
                    {
                        for(int j = 0;j<typesNum;j++)
                        {
                            available[j] = available[j] - req[j];
                            allocation[i][j] = allocation[i][j] + req[j];
                            need[i][j] = need[i][j] - req[j];
                        }
                    }
                    else
                        return "Request Can't Be Granted";
                }
        String res = execute();
        if(res.charAt(0) == 'S')
            return "Request CAN be granted";
        else
            return "Request Can't Be Granted 11111";
    }
    private void setReq(){
        System.out.println("Please enter the requested instances ");
        for(int i=0;i<typesNum;i++)
            req[i] = sc.nextInt();
    }
    private void calculateNeed() {
        for(int i = 0; i < processesNum; i++) {
            for(int j = 0; j < typesNum; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
    }
}