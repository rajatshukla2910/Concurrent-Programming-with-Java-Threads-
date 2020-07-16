//import scanner class for taking input from keyboard
import java.util.Scanner;
//defining a global class
public class prob2 { 
    //main function of global class 
    public static void main(String[] args) 
		throws InterruptedException 
	{ //object of production class which is a subclass of global class
        final production prod = new production(); 

		// Creating a thread for Worker A 
		Thread t1 = new Thread(new Runnable() { 
			@Override//overriding a run function of Runnnable class to run a A function of the production class
			public void run() 
			{ 
				try { 
					prod.workerA();//calling function corresponding to Worker A 
				} 
				catch (InterruptedException e) { 
					e.printStackTrace(); //if Exception occurs then print the error.
				} 
			} 
		}); 

		// Creating a thread for Worker C 
		Thread t2 = new Thread(new Runnable() { 
			@Override//overriding a run function of Runnnable class to run a C function of the production class
			public void run() 
			{ 
				try { 
					prod.workerC(); //calling function corresponding to Worker C
				} 
				catch (InterruptedException e) { 
					e.printStackTrace(); //if Exception occurs then print the error.
				} 
			} 
		}); 
        // Creating a thread for Worker D
		Thread t3 = new Thread(new Runnable() { 
			@Override//overriding a run function of Runnnable class to run a D function of the producer class
			public void run() 
			{ 
				try { 
					prod.workerD(); //calling function corresponding to Worker D 
				} 
				catch (InterruptedException e) { 
					e.printStackTrace(); //if Exception occurs then print the error.
				} 
			} 
        });
        // Creating a thread for Worker B
        Thread t4 = new Thread(new Runnable() { 
			@Override//overriding a run function of Runnnable class to run a B function of the producer class
			public void run() 
			{ 
				try { 
					prod.workerB(); //calling function corresponding to Worker B 
				} 
				catch (InterruptedException e) { 
					e.printStackTrace(); //if Exception occurs then print the error.
				} 
			} 
        });
        Scanner sc = new Scanner(System.in);//Scanner class object definattion
        System.out.println("Enter number of cars to be manufactured");
        prod.N = sc.nextInt();//taking value of number of cars from keyboard
        
        //start of all threads..can be started in any order as they are running parallely
        t2.start(); 
        t1.start();
        t4.start();
        t3.start(); 
        //joining of all threads 
        t1.join();
        t4.join();
        t2.join();
        t3.join();

    } 
    //Definition of production class
    public static class production { 

        int N;//number of cars to be manufactured
        int fA=0,fC=0,fD=0;//since A,C,D can work in any order so flags to keep track of A,C,D work
        int turn = 1;//turn is used to identify the turn of worker
        int count = 0;//it will track how many cars are manufactured till now.

        //This function will print the jobs corresponding to each thread
        synchronized public void job(int i,int j){
            switch(i){
                case 1: //Start of production of jth car and B puts chasis
                        System.out.println("---------------------\nCar "+(j+1)+" is being Manufactured\n---------------------\nB added Chasis");
                        fA=0;//updating that A,C,D hasnt not done their work
                        fC=0;
                        fD=0;
                        turn = 2;//updating turn so that A,C,D get chance to do their work.
                        break;
                case 2: //when A got chance it put tyres and updates that he has completed his work
                        System.out.println("A added Tyres");
                        fA=1;
                        if((fA == 1) && (fC == 1) && (fD == 1)) turn =3;//if A,C,D has done thir work then update tun to 3
                        break;
                case 3: //when C got chance it added seats and updates that he has completed his work
                        System.out.println("C added Seats");
                        fC=1;
                        if((fA == 1) && (fC == 1) && (fD == 1)) turn =3;//if A,C,D has done thir work then update tun to 3
                        break;
                case 4: //when D got chance it added engine and updates that he has completed his work
                        System.out.println("D added Engine");
                        fD=1;
                        if((fA == 1) && (fC == 1) && (fD == 1)) turn =3;//if A,C,D has done thir work then update tun to 3
                        break;
                case 5: //when D got chance it added Top and updates that he has completed his work
                        System.out.println("D added Top"); 
                        turn =4; //update turn 
                        break;
                case 6: //when A got chance it painted Car and updates that Car manufacturing is completed and update turn to 1 so that B can start mnanufucturing of new car.
                        System.out.println("A Painted Car"); 
                        turn =1; 
                        break;


            }
        }
        //function corresponding to worker B
        public void workerB() throws InterruptedException 
		{ 
            //for manufaturing N cars
            for(int i=0 ; i<N ; i++){
    
                while(turn != 1)Thread.sleep(50);//waititng for its turn
                job(1,i);  //calling job function for case 1
            }
            
            System.out.println("B done it's Job");//if all cars gets manufactured it prints done .
            
        }
      
         //function corresponding to worker A
        public void workerA() throws InterruptedException 
		{ 
             //for manufaturing N cars and he gets 2 times work in a car
            for(int i=0 ; i<2*N ; i++){
                while((turn != 2 || fA == 1) && turn != 4)Thread.sleep(50); //waititng for its turn
                
                if(turn == 2)
                {
                    job(2,0);//for tyre adding
                }
                else
                {
                    job(6,0);//for painting
                }
             
            }

            
            System.out.println("A done it's Job");//if all cars gets manufactured it prints done .
		} 

         //function corresponding to worker C
        public void workerC() throws InterruptedException 
		{ 
             //for manufaturing N cars
            for(int i=0 ; i<N ; i++){
                while(turn != 2 || fC == 1)Thread.sleep(50); //waititng for its turn
                
                
                if(turn == 2)
                {
                    job(3,0);//adding seats
                }
                
               
                
            }

            
            System.out.println("C done it's Job");//if all cars gets manufactured it prints done .
		} 


         //function corresponding to worker D
        public void workerD() throws InterruptedException 
		{ 
             //for manufaturing N cars and he gets 2 times work in a car
            for(int i=0 ; i<2*N ; i++){
                while((turn != 2 || fD == 1) && turn != 3)Thread.sleep(50); //waititng for its turn
              
                if(turn == 2)
                {
                    job(4,0);//adding engine
                }
                 else{
                     job(5,0);//adding top
                }
                //
                
            }

            
            System.out.println("D done it's Job");//if all cars gets manufactured it prints done .
        }

           
    }
}