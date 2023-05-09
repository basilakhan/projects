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
public class Questions {

    private String[] q;                 //an array to store fill the blank questions
    private int nQ;                     //a variable to keep track of how many quetions in the array

    public Questions(int size) {        //constructor
        q = new String[size];           //intializing the array with the specified size
        nQ = 0;                         //intializing the number of questions as zero
    }

    public String getQuestion(int index) {     //provides a question from the array at a specified index
        return q[index];

    }

    public void insert(String in) {          //inserts a new question at the end of the array
        if (nQ == q.length) {               //check if the array is full
            increaseSize();                 //if true increases the size of te array
        }
        q[nQ] = in;
        nQ++;                               //increments the number of questions

    }

    public String take(int index) {               //deletes a question from the array at a specified index and returns it 
        String temp = q[index];                     //store the question that will be deleted in a temporary variable

        for (int i = index; i < nQ - 1; i++) {      //the questions after the deleted one are shifted left
            q[i] = q[i + 1];

        }

        nQ--;                                       //decrements the number of questions
        return temp;

    }

    public void fill(String fileName) throws FileNotFoundException {            //fills the array from a file
        reset();                                                                //resets the array before filling it
        Scanner file = new Scanner(new File(fileName));                         //creates a scanner object that accepts elements from a file
        while (file.hasNextLine()) {                                            //check if there still a question to be inserted
            String line = file.nextLine();                                      //assigns the current question in the file to the varible line
            line = line.replace("\\n", "\n");                                   //eplaces the modified escape char to the correct one   
  
            insert(line);                                                       //inserts the line assigned to the variable into the array
        }
    }

    public void reset() {
        nQ = 0;                 //resets the number of questions to zero
    }

    private String[] increaseSize() {                       //increases the size of the array
        String[] copy = new String[q.length * 2];           //create an array variable called copy and sets it as double the capacity of the original array
        for (int i = 0; i < nQ; i++) {                      //a for loop to copy the elements from the old array to the new array
            copy[i] = q[i];
        }
        q = copy;                                           //changes the reference of the old array  to the new array
        return q;

    }

    public int getNQ() {
        return nQ;
    }


    
    

}
