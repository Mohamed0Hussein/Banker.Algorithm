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
        System.out.println("If you want to use the default value of processes and resources press 1."
                + "And to customize input press 2");
        int x = sc.nextInt();
        BankerAlgorithm bankerAlgorithm = new BankerAlgorithm();
        if(x == 1){
        bankerAlgorithm.defaultInput();
        }
        else if(x == 2)
        {
          bankerAlgorithm.customInput();  
        }
        else{
            System.out.println("Not Availble Option");
        }
    }
   
}
