1) Car Manufacturing System

A car  is  manufactured  at  each  stop  on  a  conveyor  belt  in  a  car  factory.  A  car  is  constructed  from  the following parts –chassis, tires, seats, engine (assume that this includes everything under the hood and the 
steering wheels), the  top cover, and  painting. Thus,  there  are  6 tasks  in manufacturing a  car. However, there are some constraintsthat are to be followed while making a car. The constraints are:
•Tires, seats or the engine cannot be added until the chassis is placed on the belt.
•The car top cannot beadded until tires, seats and the engine are put in.
•The car cannot be painted until the top is put on.
A stop on the conveyor belt at the car factory has four skilled technicians assigned to it-A, B, C and D. 
Each of these four technicians has different skills as mentioned below:
1.A is skilled at adding tires and painting the car,
2.Bis skilled in putting the chassis on the belt,
3.Cis skilled in attaching the seats,
4.D is skilled in adding the engine and putting top cover.
Implement the functionality of all the  workers i.e.  A, B, C, and  D to be  able to work on a  car, without violating the task order outlined above.



Input to the program: The program will take a number N as input from the command line, which is the number  of  cars  that  is  to  be  built  at  the  stopin  the  factory.  Your  program  should  implement  the functionality of the workers in the form of threads. And finally, your program should be able to create N cars without violating the conditions.

Output of the program: For a car, wheneveran intermediatetask is completed i.e. engine is put on, print a message that the task is completed (with description of the task) along with the technician who has completed the task.

Example:Correct sequence for building a car would be:
•B put chassis on the belt.
•C attached the seat.
•D put engine.
•A added tires.
•D put top cover.
•A painted the car.

And incorrect sequencefor building the a would be:
•B put chassis on the belt.
•D put engine.
•A added tires
•D put top cover.
•C attached the seat.
•A painted the car.


2) BankingSystem

Consider a banking system that can serve N number of customers.The banking system can provide three typesof services: draft processing, cheque processing and net-banking. Each customer continuouslykeepson requesting  one  of  the  three  servicesand the bankingsystem  keepsonservicing  those  requests indefinitely by offering one of the servicesand providinga voucher number toit. The customer who is requesting the same service gets its request serviced with a unique voucher number and signal the banking system oncompletion. The banking system than again offer new service and a new voucher number with it.

Following requirements are to be fulfilled in the program:
I.The banking system should randomly offer one of the servicesand generate a unique vouchernumber toit.
II.For eachtime a service is offered, maintain a log by writing the service being offered and voucher number in a file on the disk. 
Also write the customer’s information who has availed that service.


3) AUTOMATED VOTING SYSTEM

The city of Dreamland has witnessed the sad news of the sudden demise of their mayor who suffered from  cardiac  arrest.  After  a  few  days  of  regional  grief  and condolence,  the  election  commission decided  to  conduct  a  by-election.  In  the  by-election,  three  candidates  contested  for  the  position  of mayor, viz., X, Y and Z. There are five polling booths in different localities, viz., A, B, C, D and E. To  make  the  election  process  smooth,  fast  and  hassle-free,  the  election  commission  has  decided  to automate the polling and counting process as follows:

I.Each booth has an EVM (Electronic Voting Machine) with a controller. A voter cast his/her vote by clicking a button corresponding to the candidate of his/her choice (X or Y or Z).

II.Each EVM has a controller which is connected to a CCM (Central Counting Machine).

III.The CCM records the vote count for each candidate.

IV.Once a voter cast his/her vote, the controller sends the response to the CCM and updates the record file. 

V.Note that all the polling booths operate simultaneously.

VI.Each booth controller sends a poll completion message at the end of the poll in that booth. VII.After receiving poll completion message from each of the polling booths, the CCM displays the final result.

Implement the automated system using concurrency and synchronization with java.
