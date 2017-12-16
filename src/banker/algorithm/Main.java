/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banker.algorithm;

import java.util.Scanner;

/**
 *
 * @author yehia
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Please enter the number of processes "
                + " and the number of resources types");
        
        int numProcesses = sc.nextInt();
        int numResourcesTypes = sc.nextInt();
        
        BankerAlgorithm ba = new BankerAlgorithm(numProcesses, 
                numResourcesTypes);
        String result = ba.execute();
        System.out.println();
        System.out.println("if you want to request, enter 1, else if you want the seq, enter 2 :");
        if(sc.nextInt()==1)
        {
            System.out.println("which process requests ?? (enter only the number of the process!!!)");
            String result2 = ba.Request(sc.nextInt());
            System.out.println();
            System.out.println(result2);   
        }
        else
            System.out.println("the State of the seq was \n" +result);
    }
   
}
