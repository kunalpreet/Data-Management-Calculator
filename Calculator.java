/**********************************************************
// Author: Kunalpreet Matharu 613430
//
// Assignment Summative - Data Management
//
// Description: User enters data points, and using that the program calculates linear regression
//              and coeffecicent correlation. Also, the line of best fit is displaced on the graph.
//
// Date Start:
// Date Completed:
//
// Completion time: 
// Honor Code: I pledge that this program represents my own
// program code. I received help from no one
*//////
import java.io.*;
import java.util.*;
import java.text.*;

public class Calculator
{
   public static void main(String [] args)
   {
      Scanner input = new Scanner(System.in);
      DecimalFormat df = new DecimalFormat("#.######");
   
      System.out.print("please enter the number of data points: ");
      int n = input.nextInt();
   
      double[] x = new double[n];
      double[] y = new double [n];
      System.out.print("please enter " + n + " points as the x value data points: ");
   
      for(int i = 0;i<n;i++)
      {
         x[i]= input.nextDouble();
      }
      System.out.println(Arrays.toString(x));
      System.out.print("please enter " + n + " points as the y value data points: ");
   
      for(int i = 0;i<n;i++){
         y[i]= input.nextDouble();
      }
   
      String [][] dataPoints = new String[n][2];
      
      for(int r = 0; r < dataPoints.length; r++)
      {
         for(int c = 0; c < dataPoints[r].length; c++)
         {
            if(c == 0)
            {
               dataPoints[r][c] =  Double.toString(x[r]);
            }
            else
            {
               dataPoints[r][c] =  Double.toString(y[r]);
            }
         }
      }   
      
   
   // formula for the slope m  
      double m = ((n*sum(x,y))-(sum(x)*sum(y)))/((n*sumPow2(x))-Math.pow(sum(x),2)); 
      
   //formula for the y intercept b
      double b = ((sum(y)*sumPow2(x))-(sum(x)*sum(x,y)))/((n*sumPow2(x))-Math.pow(sum(x),2)); 
   
      if(b > 0)
      {
         System.out.println( "y ≈ " + df.format(m) + "x" + " + " + df.format(b));  
      }
      else if(b < 0)
      {
         System.out.println( "y ≈ " + df.format(m) + "x " + df.format(b));
      }
      else
      {
         System.out.println( "y ≈ " + df.format(m) + "x");
      }
      
      double correlationCoefficient = (n*sum(x,y) - sum(x)*sum(y))/(Math.sqrt((n*sumPow2(x) - Math.pow(sum(x),2))*(n*sumPow2(y) - Math.pow(sum(y),2))));
      
      System.out.println("The Correlation Coefficient is: " + df.format(correlationCoefficient));
      System.out.println("The the R-Squared value: " + df.format(Math.pow(correlationCoefficient,2)));
      printCorrelation(correlationCoefficient);
   }
      
   
   //calculates sums of X and Y (sigma notation)
   public static double sum(double[] a){  
      double sum = 0.0;
      for(int i = 0; i <a.length;i++){
         sum = sum + a[i];
      }
      return sum;
   }    
   // calculates sum of correlation*y (sigma of correlation x*y)
   public static double sum(double[] a,double[] b){  
      double sum = 0.0;
   
      for(int i = 0;i<a.length;i++){
         sum += a[i]*b[i];
      }
      return sum;
   }   
// calculates sum of correlation^2
   public static double sumPow2(double[] a){   
   
      double sum = 0.0;
      for(int i = 0;i < a.length; i++){
         sum += Math.pow(a[i],2);
      }
      return sum;
   }

   public static void printCorrelation(double x)
   {
      if(x > 0 && x <= (1/3))
      {
         System.out.println("This is a weak positive correlation");
      }
      
      else if(x > (1/3) && x <= (2/3))
      {
         System.out.print("This is a moderate positive correlation");
      }
      
      else if(x > (2/3) && x <=1 )
      {
         System.out.print("This is a strong postive correlation");
      }
      
      else if(x >= (-1/3) && x < 0)
      {
         System.out.print("This is a weak negative correlation");
      }
      else if(x >= (-2/3) && x < (-1/3))
      {
         System.out.print("This is a moderate negative correlation");
      }    
      else if(x >= -1 && x < (-2/3))
      {
         System.out.print("This is a strong negative correlation");
      }
   }
}