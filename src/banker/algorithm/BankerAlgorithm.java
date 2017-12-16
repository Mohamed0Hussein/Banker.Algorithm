package banker.algorithm;
import java.util.*;
public class BankerAlgorithm {
    
    private int available[];
    private int old_available[];
    private int max[][];
    private int allocation[][];
    private int old_allocation[][];
    private int need[][];
    private int old_need[][];
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
        old_available = new int[typesNum];
        max = new int[processesNum][typesNum];
        allocation = new int[processesNum][typesNum];
        old_allocation = new int[processesNum][typesNum];
        need = new int[processesNum][typesNum];
        old_need = new int[processesNum][typesNum];
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
        for(int i = 0; i < typesNum; i++) {
            old_available[i] = available[i];
        }
        
        sc.nextLine();
        
        for(int i = 0; i < processesNum; i++) {
            System.out.print("Please enter the allocated resources for ");
            System.out.println("p" + i);
            
            for(int j = 0; j < typesNum; j++) {
                allocation[i][j] = sc.nextInt();
                old_allocation[i][j]=allocation[i][j];
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
                    boolean can = true;
                    for(int j=0;j<typesNum;j++)
                        if(req[j]>need[target][j])
                            can = false;
                        else
                            if(req[j]> available[j])
                                can = false;
                    if(can)
                    {
                        for(int j = 0;j<typesNum;j++)
                        {
                            available[j] = available[j] - req[j];
                            allocation[target][j] = allocation[target][j] + req[j];
                            need[target][j] = need[target][j] - req[j];
                        }
                    }
                    else
                        return "Request Can't Be Granted";
                
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
        returnstate();
    }
    private void calculateNeed() {
        for(int i = 0; i < processesNum; i++) {
            for(int j = 0; j < typesNum; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
                old_need[i][j] = max[i][j] - allocation[i][j];
            }
        }
    }

    private void returnstate() {
        for(int i=0;i<typesNum;i++){
            available[i]=old_available[i];
        }
        for(int i=0;i<processesNum;i++){
            for(int j=0;j<typesNum;j++){
                allocation[i][j]=old_allocation[i][j];
                need[i][j]=old_need[i][j];
            }
        }
        for(int i=0;i<processesNum;i++){
            finish[i]=false;
        }
    }
}