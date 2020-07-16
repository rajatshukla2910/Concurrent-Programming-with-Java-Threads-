import java.util.*;
import java.util.concurrent.*; 
import java.io.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import javax.lang.model.util.ElementScanner6;


//*******************************   Shared resources   ***************************************//
class Global  
{ 
    public static AtomicInteger time = new AtomicInteger(0);  
    public static int a =0;
    public static int b =0;
    public static int c =0;
    public static int d =0;
    public static int e =0;
//***************arrays for each booth ********************************/
   public static int arr[][]=new int [100][2];
   public static int brr[][]=new int [100][2];
   public static int crr[][]=new int [100][2];
   public static int drr[][]=new int [100][2];
   public static int err[][]=new int [100][2];
   //******************Sizes of each array  *********************************/
    public static int a_count =0;
    public static int b_count =0;
    public static int c_count =0;
    public static int d_count =0;
    public static int e_count =0;
    //************** vote count of each candidate ******************************************/
    public static int X_count =0;
    public static int Y_count =0;
    public static int Z_count =0;
    public static int global_time =0;

    //************ Lock on vote for each candidate *************************/
    public  static Semaphore x = new Semaphore(1); 
    public static Semaphore y = new Semaphore(1); 
    public  static Semaphore z = new Semaphore(1); 


    //********************Locks to check if all the booth are read at certain time so to increment time */
    public  static Semaphore t1 = new Semaphore(0); 
    public  static Semaphore t2 = new Semaphore(0); 
    public   static Semaphore t3 = new Semaphore(0); 
    public static Semaphore t4 = new Semaphore(0); 
    public static Semaphore t5 = new Semaphore(0); 
   

} 

class tym extends Global implements Runnable
{
     public void run()
        {  try{
           while(time.get()!=global_time-1)            //till maximum time is not reached
            { 

               //********************Locks to check if all the booth are read at certain time then increment time */
               if(a_count>0)  
                t1.acquire();
                
                if(b_count>0)
                t2.acquire();
                
                if(c_count>0)
                t3.acquire();
                
                if(d_count>0)
                t4.acquire();
                
                if(e_count>0)
                t5.acquire();
                



                

                time.set(time.get()+1);
               
          
                   
            }
         }
         catch (Exception ie) {
            ie.printStackTrace();}


        }

    
}



//******************************     Computation at booth A **************************************//

class A extends Global implements Runnable
{      
 int x_local =0;
 int y_local =0;
 int z_local =0;

  public void run()
  {  try{
     
  while (a_count!=-1)
   { //System.out.println(a_count);
       if(arr[a][1]==time.get())
    {         
      if(arr[a][0]==0)
      {     
        x_local++;
       
             x.acquire();                                          // locking on X_count variable
         
             X_count++;
             x.release();                                           // releasing lock on X_count variable
           
     }

     else if(arr[a][0]==1)
     {     
      y_local++;
     
      y.acquire();                                                 // locking on Y_count variable
       Y_count++;
       y.release();                                              // releasing lock on Y_count variable
     
        
     }

     else if(arr[a][0]==2)
     {
      z_local++;
    
      z.acquire();                                                  // locking on Z_count variable
       Z_count++;
       z.release();                                                   // releasing lock on Z_count variable
     }
     else
     {

     }
     a++;
     a_count--;

    }
    
    t1.release();  
  Thread.sleep(1000);

}
  }

catch (Exception ie) {
   ie.printStackTrace();}


  }

}

//******************************     Computation at booth B **************************************//


class B extends Global implements Runnable
{      
 static int x_local =0;
 static int y_local =0;
 static int z_local =0;

  public void run()
  {
try{
  
while(b_count!=-1)
{  
    if(brr[b][1]==time.get())
    {         
      if(brr[b][0]==0)
      {   x_local++;
       
        x.acquire();                                          // locking on X_count variable
    
        X_count++;
        x.release();                                           // releasing lock on X_count variable
      
          

      }
      else if(brr[b][0]==1)
     {     
      y_local++;
     
      y.acquire();                                                 // locking on Y_count variable
       Y_count++;
       y.release();                                              // releasing lock on Y_count variable
     
      
     }
     else if(brr[b][0]==2)
     {
      z_local++;
    
      z.acquire();                                                  // locking on Z_count variable
       Z_count++;
       z.release();                                                   // releasing lock on Z_count variable
      
     }
     else 
     {

     }
     
     b++;
     b_count--;
    }
    
    t2.release();  
    Thread.sleep(1000);
  
}
}
catch (Exception ie) {
   ie.printStackTrace();}

}

}

//******************************     Computation at booth C **************************************//



class C extends Global implements Runnable
{      
 static int x_local =0;
 static int y_local =0;
 static int z_local =0;

  public void run()
  {
try{       
while(c_count!=-1)
{   
    if(crr[c][1]==time.get())
    {         
      if(crr[c][0]==0)
      {     x_local++;
        
             x.acquire();                                          // locking on X_count variable
         
             X_count++;
             x.release();                                           // releasing lock on X_count variable
         
      }
      else if(crr[c][0]==1)
     {     
      y_local++;
     
      y.acquire();                                                 // locking on Y_count variable
       Y_count++;
       y.release();                                              // releasing lock on Y_count variable
     
     }
     else if(crr[c][0]==2)
     {
      z_local++;
    
      z.acquire();                                                  // locking on Z_count variable
       Z_count++;
       z.release();                                                   // releasing lock on Z_count variable
       
     }
     else
     {

     }
     
     c++;
     c_count--;
    }
   
    t3.release();                             // release t3 lock as at time t this booth is read
    Thread.sleep(1000);
  
}
  }
catch (Exception ie) {
   ie.printStackTrace();
}



}

}

//******************************     Computation at booth D **************************************//

class D extends Global implements Runnable
{      
 static int x_local =0;
 static int y_local =0;
 static int z_local =0;

  public void run()
  {
try{
  
while(d_count!=-1)
{
    if(drr[d][1]==time.get())
    {         
      if(drr[d][0]==0)
      {     x_local++;
       
        x.acquire();                                          // locking on X_count variable
    
        X_count++;
        x.release();                                           // releasing lock on X_count variable
      
      }
      else if(drr[d][0]==1)
     {     
      y_local++;
     
      y.acquire();                                                 // locking on Y_count variable
       Y_count++;
       y.release();                                              // releasing lock on Y_count variable
     
     }
     else if(drr[d][0]==2)
     {
      z_local++;
    
      z.acquire();                                                  // locking on Z_count variable
       Z_count++;
       z.release();                                                   // releasing lock on Z_count variable
     }
     else
     {

     }
     
     d++;
     d_count--;
    }
    
    t4.release();  
    Thread.sleep(1000);
}


  }
  catch (Exception ie) {
   ie.printStackTrace();
  }
}
}

//******************************     Computation at booth E **************************************//

class E extends Global implements Runnable 
{
     
 static int x_local =0;
 static int y_local =0;
 static int z_local =0;

  public void run()
  {
   try{
     
   while(e_count!=-1)
{
    if(err[e][1]==time.get())
    {         
      if(err[e][0]==0)
      {    x_local++;
       
        x.acquire();                                          // locking on X_count variable
    
        X_count++;
        x.release();                                           // releasing lock on X_count variable
      
      }
      else if(err[e][0]==1)
     {     
      y_local++;
     
      y.acquire();                                                 // locking on Y_count variable
       Y_count++;
       y.release();                                              // releasing lock on Y_count variable
     
     }
     else if(err[e][0]==2)
     {
      z_local++;
    
      z.acquire();                                                  // locking on Z_count variable
       Z_count++;
       z.release();                                                   // releasing lock on Z_count variable
     }
     else
     {

     }
     e_count--;
     e++;

    }
   
    t5.release();  
    Thread.sleep(1000);
  
}
  }
catch (Exception ie) 
{
   ie.printStackTrace();
}
  }
}






class temp
{

   public static void main(String[] args) throws Exception 
{

  System.out.println("Enter the file name with .txt extension ");


   String file;
   Scanner sc =new Scanner(System.in);

   int array[][]=new int[100][3];  //array to store the file data 0th index =time ,1st index=response,2nd index=booth id
   int max_tym=0;                 //max time in the file
   int i=0;
   file=sc.next();
           BufferedReader br =new BufferedReader(new FileReader(file ));
           String st; 
           while ((st = br.readLine()) != null) 
           {  
             String str[]=st.split(" ");
            
             array[i][0]=Integer.parseInt(str[0]);
             if(str[1].equals("X"))               //mapping candidate X to 0
             array[i][1]=0;
             if(str[1].equals("Y"))               //mapping candidate Y to 1
             array[i][1]=1;
             if(str[1].equals("Z"))               //mapping candidate Z to 2
             array[i][1]=2;
            
             if(str[2].equals("A"))                 //mapping booth A to 0
             array[i][2]=0;              
             if(str[2].equals("B"))                  //mapping booth B to 1
             array[i][2]=1;
             if(str[2].equals("C"))                 //mapping booth C to 2
             array[i][2]=2;

             if(str[2].equals("D"))                 //mapping booth D to 3
             array[i][2]=3;
             if(str[2].equals("E"))                   //mapping booth E to 4
             array[i][2]=4;
             //System.out.println(array[i][1]+" "+array[i][2]);
            if(array[i][0]>max_tym)
            max_tym=array[i][0];                           //calculating maximum time in the file
                   i++;

             }

                // setting sizes of global arrays and time //

           Global.global_time=max_tym+1;
       
    


           Global.a_count=max_tym;    Global.b_count=max_tym ; Global.c_count=max_tym ; Global.d_count=max_tym ;
           Global.e_count=max_tym;
         
           i=0;
          
           int ar=0,b=0,cr=0,dr=0,er=0;

//*********************Seperating data booth wise *********************************************************//

       for(int t=0;t<max_tym+1;++t)
       {   int a_flag=0;
        int b_flag=0;
        int c_flag=0;
        int d_flag=0;
        int e_flag=0;
        int count=0;
          while(array[i][0]==t)
          {  count++;
              if(array[i][2]==0)
              {
                Global.arr[ar][0]=array[i][1];
                Global.arr[ar][1]=t;
                
                a_flag=1;
              }
             if(array[i][2]==1)
              {
                Global.brr[b][0]=array[i][1];
                Global.brr[b][1]=t;
                
                b_flag=1;
              }
              if(array[i][2]==2)
              {
                Global.crr[cr][0]=array[i][1];
                Global.crr[cr][1]=t;
              
                c_flag=1;
                
              }
              if(array[i][2]==3)
              {
                Global.drr[dr][0]=array[i][1];
                Global.drr[dr][1]=t;
              
                d_flag=1;
              }
              if(array[i][2]==4)
              {
                Global.err[er][0]=array[i][1];
                Global.err[er][1]=t;
                
                e_flag=1;
              }
              i++;
          }
              //******************iF data is not available at time t at any booth **************************/

          if(a_flag==0)
          {
            Global.arr[ar][0]=-1;
            Global.arr[ar][1]=t;
          }
          if(b_flag==0)
          {
            Global.brr[b][0]=-1;
            Global.brr[b][1]=t;
          }
          if(c_flag==0)
          {
            Global.crr[cr][0]=-1;
            Global.crr[cr][1]=t;
          }
          if(d_flag==0)
          {
            Global.drr[dr][0]=-1;
            Global.drr[dr][1]=t;
          }
          if(e_flag==0)
          {
            Global.err[er][0]=-1;
               Global.err[er][1]=t;
          }

          

ar++;
b++;
cr++;
dr++;
er++;


       }

    //   for(int t=0;t<max_tym+1;++t)
     //  System.out.println(Global.crr[t][0]+" "+Global.crr[t][1]);


//****************Starting all the treads ********************************************* */     

        A aobj = new A();
        Thread ta = new Thread(aobj);           
        ta.start();                        //booth A starts
        B bobj = new B();
        Thread tb = new Thread(bobj);
        tb.start();                         //booth B starts
        C cobj = new C();
        Thread tc = new Thread(cobj);
        tc.start();                          //booth C starts
        D dobj = new D();
        Thread td = new Thread(dobj);
        td.start();                           //booth D starts
        E eobj = new E();
        Thread te = new Thread(eobj);
        te.start();                               //booth E starts

        tym tymobj=new tym();
        Thread tt=new Thread(tymobj);
        tt.start();                              //time thread starts

        ta.join();
        tb.join();
        tc.join();
        td.join();
        te.join();
        tt.join();
        

//*************************** printing count of each candidate ********************************************//

        System.out.println("Total vote count for X candidate is: "+Global.X_count);         
        System.out.println("Total vote count for Y candidate is: "+Global.Y_count);
        System.out.println("Total vote count for Z candidate is: "+Global.Z_count);



}



}