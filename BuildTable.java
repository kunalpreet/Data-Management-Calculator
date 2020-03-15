/**********************************************************
// Author: Kunalpreet Matharu 613430
//
// Assignment Summative - Data Management & Computer Science
//
// Description: User enters data points, and using that the program calculates linear regression
//              and coeffecicent correlation. Also, the line of best fit is displaced on the graph.
/
// Honor Code: I pledge that this program represents my own
// program code. I received help from no one
***********************************************/
import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;   
import javax.swing.event.*;
import java.util.*;
import java.text.*;
import java.io.*;
import javax.swing.JFrame;  

public class BuildTable extends JFrame implements ActionListener
{

   public int n;
   public String x;
   public String y;
   public double[] YdataPoints;
   public double[] XdataPoints;
   public JTable table; 
   public DecimalFormat df = new DecimalFormat("#.######");
   
   JButton ShowTable = new JButton("Show Table");
   JButton Calculate = new JButton("Calculate");
   JButton ref = new JButton("Example");

   JLabel inLabel = new JLabel( "Enter Number of Data Points  " ) ;
   TextField inText = new TextField( 20 );

   
   JLabel rSqrd = new JLabel( "R-Squared Value:                    " ) ;
   TextField rsqrd = new TextField(20);
   
   JLabel LOBF = new JLabel( "Line of Best Fit:         " ) ;
   TextField line = new TextField(27);
   
   JLabel CorrelationC = new JLabel( "Correlation Coefficient:          " ) ;
   TextField CorrelationCf = new TextField(20);
   
   JLabel xData = new JLabel( "Enter X Data Points                 " ) ;
   TextField inText1 = new TextField( 20);
   JLabel yData = new JLabel( "Enter Y Data Points:                " ) ;
   TextField inText2 = new TextField( 20 );
   JButton Create = new JButton("Create Table");
   
     
   public BuildTable(String title)  
   {  
      super(title);
      setLayout(new FlowLayout()); 
      add( inLabel ) ;
      add( inText  ) ;
      
      add( xData ) ;
      add( inText1  ) ;
      
      add( yData ) ;
      add( inText2  ) ;
      
      add( CorrelationC ) ;
      add( CorrelationCf );
      
      add( rSqrd ) ;
      add( rsqrd );
      
      add( LOBF );
      add( line );
      
      Create.setActionCommand( "Create Table" );
      add( Create );  
      Calculate.setActionCommand( "Calculate" );
      add( Calculate );  
      ref.setActionCommand("Example");
      add( ref );   
   
      setBounds(500, 200, 400, 300);
     
      inText.addActionListener( this );
      Create.addActionListener( this );
      Calculate.addActionListener ( this );
      ref.addActionListener( this );
      setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );   
   }
   
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

   public void actionPerformed( ActionEvent evt)  
   {
   
      if( evt.getActionCommand().equals("Calculate") )
      {
         n = Integer.parseInt(inText.getText());
         // m = slope
         double m = ((n*sum(XdataPoints,YdataPoints))-(sum(XdataPoints)*sum(YdataPoints)))/((n*sumPow2(XdataPoints))-Math.pow(sum(XdataPoints),2)); 
         // b = y-intercept
         double b = ((sum(YdataPoints)*sumPow2(XdataPoints))-(sum(XdataPoints)*sum(XdataPoints,YdataPoints)))/((n*sumPow2(XdataPoints))-Math.pow(sum(XdataPoints),2)); 
         double correlationCoefficient = (n*sum(XdataPoints,YdataPoints) - sum(XdataPoints)*sum(YdataPoints))/(Math.sqrt((n*sumPow2(XdataPoints) - Math.pow(sum(XdataPoints),2))*(n*sumPow2(YdataPoints) - Math.pow(sum(YdataPoints),2))));
         CorrelationCf.setText((df.format(correlationCoefficient)));      
         rsqrd.setText((df.format(Math.pow(correlationCoefficient,2))));
         line.setText("y ≈ " + df.format(m) + "x" + " + " + df.format(b));
      }
      
      else if( evt.getActionCommand().equals("Create Table") )
      {
         n = Integer.parseInt(inText.getText());
         n = Integer.parseInt(inText.getText());
         x = inText1.getText();
         x = x.replaceAll(" ", "");
         y = inText2.getText();
         y = y.replaceAll(" ", "");
         XdataPoints = new double[n];
         YdataPoints = new double[n];
         int commax;
         int commay;
      
         for(int i = 0; i < XdataPoints.length; i++)
         {
            commax = x.indexOf(',');
            commay = y.indexOf(',');
            if(commax >= 0)
            {
               XdataPoints[i] = Integer.parseInt(x.substring(0,commax));
               x = x.substring(commax+1);
            }
            if(commay >= 0)
            {
               YdataPoints[i] = Integer.parseInt(y.substring(0,commay));
               y = y.substring(commay+1);
            }
         }
      
         String [][] dataPoints = new String[n][2];
      
         for(int r = 0; r < dataPoints.length; r++)
         {
            for(int c = 0; c < dataPoints[r].length; c++)
            {
               if(c == 0)
               {
                  dataPoints[r][c] = Double.toString(XdataPoints[r]);
               }
               else
               {
                  dataPoints[r][c] =  Double.toString(YdataPoints[r]);
               }
            }
         }  
      
         JFrame f = new JFrame("Table"); 
         f.setBounds(200, 200, 300, 200);
                        
         String column[]={"X","Y"};         
         table = new JTable(dataPoints,column);    
         table.setCellSelectionEnabled(true);
          
         JScrollPane scroll = new JScrollPane(table);    
         f.add(scroll);
         f.setVisible(true);      
      }
         
      if( evt.getActionCommand().equals("Example") )
      {
               
         JFrame frame = new JFrame("How to Enter");
      
         JPanel panel = (JPanel)frame.getContentPane();
      
         JLabel label = new JLabel();
         label.setIcon(new ImageIcon("Help.png"));// your image here
         panel.add(label);
      
         frame.setLocationRelativeTo(null);
         
         frame.pack();  
         frame.setBounds(500,500,500,200);
         frame.setVisible(true);
      }
   }
   
   public static void main(String[] a) 
   {  
      BuildTable x = new BuildTable("Calculator");  
      x.setVisible(true);      
   } 

}