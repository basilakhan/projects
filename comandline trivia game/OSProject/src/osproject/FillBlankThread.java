/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package osproject;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import static osproject.TriviaApp.iter;
import static osproject.TriviaApp.score;

/**
 *
 * @author basil
 */
public class FillBlankThread extends Thread {

    private Semaphore synch;                //a semaphore to synchronize the global variable and the orgnization of the output
    private int num;                        //an integer to specify the number of questions to be printed
    private FillBlank fB;                   //an object of class FillBlank to store questions and answers                         

    public FillBlankThread(Semaphore s, int n, FillBlank f) {
        super();                            //calls Thread's class constructor
        synch = s;                          //intialize the semaphore synch to the given parameter
        num = n;                            //intialize the integer num to the given parameter
        fB = f;                             //intialize the object fB to the given parameter

    }

    @Override
    public void run() {
        Scanner in = new Scanner(System.in);        //create an object in of class Scanner to deal with user input
        Random rand = new Random();                 //cereate an object rand of class Random
        for (int i = 0; i < num; i++) {             //a loop that iterates to the specified number of question wated to be printed
            try {                                   //try-catch to deal with inturrupted thread exception

                synch.acquire();                    //acquires an access to the critical section and decrement semaphore when granted else wait 

                int r = rand.nextInt(Math.abs(fB.getNQ()));     //generates a random integer and stores it in r
                System.out.println(iter + "- " + fB.take(r));   //prints the current iteration of all printed questions, then prints a question using r as the index
                iter++;                                         //increments the global variable iter                                         

                String answer = in.nextLine();                  //accepts an input from the user that is the answer to the question
                if (answer.equalsIgnoreCase(fB.getAnswer(r))) { //checks if it is equal to the correct answer 
                    score++;                                    //increments the score if it is correct
                    System.out.println("Correct!!\n");

                } else {
                    System.out.println("Sorry thats incorrect \n");
                }
                synch.release();                                //releases the the access by incrementing the semaphore
                Thread.sleep(20);                               //waits for other threads to give them a chance to switch 

            } catch (InterruptedException ex) {
                Logger.getLogger(RiddlesThread.class.getName()).log(Level.SEVERE, null, ex);        //the interrupted exception
            }

        }

    }
}
