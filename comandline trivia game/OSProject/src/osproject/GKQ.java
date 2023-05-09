/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package osproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author basil
 */
public class GKQ extends Questions{

    private char[] answers;                     //an array to store the general knowledge questions answers(characters)
    private int nAnswers;                       //a variable to keep track of how many answers in the array

    public  GKQ(int size) {                     //constructor
        super(size);                            //calls the constructor of the parent(questions class)                         
        answers = new char[size];               //intializing the array with the specified size
        nAnswers = 0;                           //intializing the number of answers as zero
    }

    public void fillAnswers(String fileName) throws FileNotFoundException {     //fills the array from a file
        nAnswers = 0;                                                           //resets the array
        Scanner file = new Scanner(new File(fileName));                         //creates a scanner object that accepts elements from a file
        while (file.hasNextLine()) {                                                //check if there still an answer to be inserted
            char answer = file.nextLine().charAt(0);                                //assigns the current answer in the file to the varible answer
            insertAns(answer);

        }
        if (nAnswers != getNQ()) {                          //checks if the number of answere inserted are equal to the number of quetions
            System.out.println("The number of answers is not equal to the number of question");
            nAnswers = 0;                                   //resets the array because the values are wrong

        }
    }
    
    public void insertAns(char ans){                                //a method to insert answers
        if(nAnswers==answers.length){
            increaseAnsSize();
        }
        answers[nAnswers] = ans;
        nAnswers++;
    }
    
    public char getAnswer(int ind){

        char temp = answers[ind];                     //store the answer that will be deleted in a temporary variable
        for (int i = ind; i < nAnswers - 1; i++) {      //the answers after the deleted one are shifted left
            answers[i] = answers[i + 1];

        }
        nAnswers--;                                       //decrements the number of answers
        return temp;
    }
    
    private char[] increaseAnsSize() {                       //increases the size of the array
        char[] copy = new char[answers.length * 2];           //create an array variable called copy and sets it as double the capacity of the original array
        for (int i = 0; i < nAnswers; i++) {                      //a for loop to copy the elements from the old array to the new array
            copy[i] = answers[i];
        }
        answers = copy;                                           //changes the reference of the old array  to the new array
        return answers;

    }
}
