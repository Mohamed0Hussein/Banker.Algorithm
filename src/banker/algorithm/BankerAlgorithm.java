package banker.algorithm;
import java.util.*;
public class BankerAlgorithm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int noOfProcesses,noOfResources;
        ArrayList seq = new ArrayList();
        System.out.println("Enter how many processes in the system-:");
        noOfProcesses = sc.nextInt();
        ArrayList Processes = new ArrayList(noOfProcesses);
        System.out.println("Enter how many resources in the system-:");
        noOfResources = sc.nextInt();
        ArrayList Resources = new ArrayList(noOfResources);
        for(int i=0;i<noOfResources;i++)
        {
            System.out.println("enter Resource name and no.of instances respectively");
            Resources.add(new resource(sc.nextLine(),sc.nextInt()));
        }
        for(int i=0;i<noOfProcesses;i++)
        {
            Processes.add(new process("P"+i,noOfResources));
            for(int j=0;j<noOfResources;j++)
            {
            System.out.println("enter the no.of instances allocated and have respectively for resource "+((resource)(Resources.get(j))).Name +" -:");
            process x = (process)Processes.get(i);
            x.setAllo(sc.nextInt());
            x.setHave(sc.nextInt());
            x.setNeed();
            }
        }
        while(true)
        {
            int x = 0;
            for(int i=0;i<noOfProcesses;i++)
                if(((process)Processes.get(i)).isSafe)
                    x++;
            if(x==noOfProcesses)
                break;
            for(int i=0;i<noOfProcesses;i++)
                if(((process)(Processes.get(i))).needs.get(i))
        }
    }
    
}
