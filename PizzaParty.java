/***************************************************************  
*  file: PizzaParty.java  
*  author: W. Dam  
*  class: CS 1400 – Intro Programming Prob Solving  
*  
*  assignment: program 2  
*  date last modified: 2/22/2022  
*  
*  purpose: The purpose of this program is to calculate the cost of
*  hosting three pizza parties on Friday, Saturday, and Sunday from
*  input of the user and outputs the total for each day as well as 
*  the total for all three days. 
*  
****************************************************************/
//Initial commit
import java.util.Scanner;
public class PizzaParty{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int numPeople, numPizza;
        double avgSlice, costperPizza;
        double total;
        double weekendTotal = 0;
        double tax, deliveryFee;
        final double SALES_TAX = 0.07;
        final double DELIVERY_RATE= 0.2;
        for(int i = 0; i < 3; i++){
            numPeople = scan.nextInt();
            avgSlice = scan.nextDouble();
            costperPizza = scan.nextDouble();
            numPizza = (int) Math.ceil((numPeople * avgSlice) / 8);
            total = numPizza * costperPizza;
            tax = SALES_TAX * total;
            deliveryFee = DELIVERY_RATE * (total + tax);
            if(i==0){
                System.out.println("Friday Night Party");
                System.out.printf("%d Pizzas: $%.2f\n", numPizza, total);
                System.out.printf("Tax: $%.2f\n", tax);
                total+=tax;
                System.out.printf("Delivery: $%.2f\n", deliveryFee);
                total+=deliveryFee;
                System.out.printf("Total: $%.2f\n", total);
                weekendTotal+=total;
                System.out.println();
            }
            else if(i==1){
                System.out.println("Saturday Night Party");
                System.out.printf("%d Pizzas: $%.2f\n", numPizza, total);
                System.out.printf("Tax: $%.2f\n", tax);
                total+=tax;
                System.out.printf("Delivery: $%.2f\n", deliveryFee);
                total+=deliveryFee;
                System.out.printf("Total: $%.2f\n", total);
                weekendTotal+=total;
                System.out.println();
            }
            else if(i==2){
                System.out.println("Sunday Night Party");
                System.out.printf("%d Pizzas: $%.2f\n", numPizza, total);
                System.out.printf("Tax: $%.2f\n", tax);
                total+=tax;
                System.out.printf("Delivery: $%.2f\n", deliveryFee);
                total+=deliveryFee;
                System.out.printf("Total: $%.2f\n", total);
                weekendTotal+=total;
                System.out.println();
            }
        }
        System.out.printf("Weekend Total: $%.2f", weekendTotal);
    }
}