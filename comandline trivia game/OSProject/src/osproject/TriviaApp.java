/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package osproject;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

/**
 *
 * @author basil
 */
public class TriviaApp {

    public static int iter = 1;                 //a global variable to keep track of which question we are on
    public static int score = 0;                //a global variable to keep track of the score

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        Semaphore s = new Semaphore(1);         //a semaphore object to intialized to 1 ,
        //and its purpose is to synchronize the global variables

        Scanner input = new Scanner(System.in); //a scanner object used for input

        Riddles r = null;                       //an object of class Riddles inialized to null
        FillBlank b = null;                     //an object of class FillBlank inialized to null
        GKQ g = null;                           //an object of class GKQ inialized to null

        System.out.println("Welcome to a game of trivia puzzle");
        System.out.println("\nDo you want to enter the question by yourself or do you want me to set the questions for you ? ");
        System.out.println("(type true if you want to do it yourself or type false if you dont want to) : ");

        boolean who = input.nextBoolean();      //asks the user to enter a boolean value to in the variable, 
                                                //that will decied if the questions are filled by the user or by the program

        if (who) {                              //if true then the questions are filled by the user
            System.out.println("\nOh good for you. so how many questions are you going to enter ?");
            int n = input.nextInt();            //asks the user to input an integer value to determine the size of the arrays
            input.nextLine();                   //sort of a garbage input(insignificant) to make sure that the other next line inputs works properly

            r = new Riddles(n);                     //object r's arrays are initialized to size n
            System.out.print("Enter the file path for the riddle questions : ");
            r.fill(input.nextLine());               //asks the user to enter a file path that has riddle questions 
            System.out.print("Enter the file path for the riddle answers : ");
            r.fillAnswers(input.nextLine());        //asks the user to enter a file path that has riddle answers
            
            b = new FillBlank(n);                   //object b's arrays are initialized to size n
            System.out.print("Enter the file path for the fill the blank questions : ");
            b.fill(input.nextLine());               //asks the user to enter a file path that has fill in the blank questions
            System.out.print("Enter the file path for the fill the blank answers : ");
            b.fillAnswers(input.nextLine());        //asks the user to enter a file path that has fill in the blank answers
            
            g = new GKQ(n);                         //object g's arrays are initialized to size n
            System.out.print("Enter the file path for the general knowledge questions : ");
            g.fill(input.nextLine());               //asks the user to enter a file path that has general knowledge questions
            System.out.print("Enter the file path for the general knowledge answers : ");
            g.fillAnswers(input.nextLine());        //asks the user to enter a file path that has general knowledge answers(chars)

            System.out.println("\nGood job and thank you very much for you cooperation");

        } else {                                //else the program fills the question by  the already provided files 
            System.out.println("\nOk then no problem i am on it\n");
            
            r = new Riddles(10);                //object r's arrays are initialized to size 10
            r.fill("C:\\Users\\basil\\OneDrive\\Documents\\OSproject\\RiddlesSampleQuetions.txt");
            r.fillAnswers("C:\\Users\\basil\\OneDrive\\Documents\\OSproject\\RiddlesSampleAnswers.txt");
            
            b = new FillBlank(10);              //object b's arrays are initialized to size 10
            b.fill("C:\\Users\\basil\\OneDrive\\Documents\\OSproject\\FillBlankSampleQuetions.txt");
            b.fillAnswers("C:\\Users\\basil\\OneDrive\\Documents\\OSproject\\FillBlankSampleAnswers.txt");
            
            g = new GKQ(10);                    //object g's arrays are initialized to size 10
            g.fill("C:\\Users\\basil\\OneDrive\\Documents\\OSproject\\GKSampleQuetions.txt");
            g.fillAnswers("C:\\Users\\basil\\OneDrive\\Documents\\OSproject\\GKSampleAnswers.txt");

        }
        
        System.out.println("-----------------------------WELCOME----------------------------");
        System.out.println("The game consists of fill in the blank questions riddles\nand general knowledge questions. both fill in the blank and riddles\nare answerd manually(you write the word) and the general knowledege questions\nyou will be shown choice and you just write the character of the right choice");
        System.out.println("------------------------------ENJOY-----------------------------\n");

        RiddlesThread rt = new RiddlesThread(s, 2, r);              //constructs a thread rt of RiddlesThread with semaphore s and number of questions 2 and riddles r
        FillBlankThread fbt = new FillBlankThread(s, 4, b);         //constructs an thread fbt of FillBlankThread with semaphore s and number of questions 4 and FillBlank b
        GKQThread gkt = new GKQThread(s, 4, g);                     //constructs an thread gkt of GKQThread with semaphore s and number of questions 4 and GKQ g

        rt.start();                 //starts the thread rt

        fbt.start();                //starts the thread fbt

        gkt.start();                //starts the thread gkt

        rt.join();                  //waits for thread rt to finish

        fbt.join();                 //waits for thread fbt to finish

        gkt.join();                 //waits for thread gkt to finish

        System.out.println("You scored " + score + " out of " + (iter - 1));        //prints the score out of the number of questions

    }

}
