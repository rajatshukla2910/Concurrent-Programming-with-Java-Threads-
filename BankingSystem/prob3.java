//import for generating random numbers
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
 
import java.io.*; 
//import for Atomic intergers so hat all operation should be atomic
import java.util.concurrent.atomic.AtomicInteger;
//importing scanner for taking input from user..
import java.util.Scanner;
//main class for problem 3
public class prob3{
   // public static int num_customer=50;
    //main function of global class
    public static void main(String[] args) throws IOException {
      /// System.out.println("In main");
      //object of banking class

File file=new File("problem_3_logs");

      final bank_server bank_object = new bank_server();
      Scanner sc = new Scanner(System.in);//defining Scanner class object
     // System.out.println("Enter the number of customer..");
      final int num_customer = sc.nextInt();//get number of customer from user
	 
     try{
         //defining a thread for bank server
      Thread t2 = new Thread(new Runnable(){
        
        @Override//overrrind the run methhod of runnable class
        public void run() {
            try {
                bank_object.bank(num_customer);//running bank service 
                
            } catch (Exception e) {
                e.printStackTrace();//if error occurs print the error.
            }
        }
    });
    t2.start();//start of baking thread.
}
catch(Exception e)
{
e.printStackTrace();//if failed to create tyhread than print the error.
}
       //creating N threads
        for(int i=0 ; i<num_customer ; i++){
           final int j = i;
            try{
                //creating a thread for each customer
                Thread t1 = new Thread(new Runnable() { 
                    @Override//overrridng runnable class so thyat for each thread it can run customer funcvtion
                    public void run() 
                    { 
                        
                        try{
                            bank_object.customer(j);//calling to customer function of bank server
                        }
                        catch(Exception e){}
                        
                    } 
                });
                t1.start();//start of customer thread
             // t1.join();//join the thread;
            }
            catch(Exception e){
                e.printStackTrace();//if error is ther print its stack trace
            }
            if(i==num_customer-1)
            return;
        }
       
    }
    //defining a bank sewrver class
    public static class bank_server{
        private AtomicInteger lock = new AtomicInteger();//creating a lock of type atomic integer
	private AtomicInteger count = new AtomicInteger();  //for getting count how many customers are serviced
        Random random = new Random();//creating a random class object
        int voucher_no;//for voucher number
     
        int serv = -1;
        //constructor
        bank_server(){
            
            lock.set(1);//setting lock to 1
	count.set(0);
        }

      
        void customer(int i) throws IOException
        {
           
            try{     
            
            int req_serv = random.nextInt(97)%3;//each customer generates a service to request
            while(true){    
              // Creating a File object that represents the disk file. 
        //PrintStream o = new PrintStream(new File("sol3.txt")); 
  
        // Store current System.out before assigning a new value 
        //PrintStream console = System.out; 
  
        // Assign o to output stream 
        
               if(lock.compareAndSet(0, 1) && voucher_no!=0 && (serv == req_serv))
               {   
                //check for lock and servvice offered by bak as same as that of requested service
		 count.getAndIncrement();//increasing count ststes that one customer got serviced
                if(req_serv==0)   //if requested service is for draft
                {      
                    //String s="";
                    ///   System.out.println("Voucher ID: "+(count.get()*count.get()+1)+" is serviced to Customer ID: "+(i+1)+" for Draft Service");
                   // s=s+"Voucher ID  hiii: "+(count.get()*count.get()+1)+" is serviced to Customer ID: "+(i+1)+" for Draft Service";
                   // System.out.println(s);
               
                  // System.setOut(console); 
                //  FileWriter fs=new FileWriter("problem_3_logs.txt",true);
                 
                  System.out.println("Voucher ID: "+(count.get()*count.get()+1)+" is serviced to Customer ID: "+(i+1)+" for Draft Service");
                //  fs.close();
            
                }
                else if(req_serv==1) //if requested service is for cheque
                {

                   // System.setOut(o); 
                //   FileWriter fs=new FileWriter("problem_3_logs.txt",true);
                   System.out.println("Voucher ID: "+(count.get()*count.get()+1)+" is serviced to Customer ID: "+(i+1)+" for Cheque Service");
               // System.setOut(console); 
               // System.out.println("Voucher ID: "+(count.get()*count.get()+1)+" is serviced to Customer ID: "+(i+1)+" for Cheque Service\n");
           // fs.close();
            }
                else 
              {//FileWriter fs=new FileWriter("problem_3_logs.txt",true);
              System.out.println("Voucher ID:"+(count.get()*+count.get()+1)+" is serviced to Customer ID: "+(i+1)+" for Net Banking Service");
            // fs.close();
        }
                    return;
               
                }
                
              
            
            }
          
           
        }
        catch(Exception e)
        {}
        
        }


        void bank(int cust){
            while(true){
                //System.out.println("In bank");
                
                while(lock.get()==0)
                if(count.get() == cust) return;//if N customers services then exit
                voucher_no++;//generating a voucher number
                serv = random.nextInt(97)%3;//generating a service/
                lock.set(0);//setting lock
                
            }

        }


    
    


    }
}
