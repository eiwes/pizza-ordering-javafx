package sample;

/* Oct. 23, 2018
 * CISC 124 Assignment 3
 *
 * Exception class that captures String message.
 *
 * @author Danielle Mott
 */

public class IllegalPizza extends Exception {

    public IllegalPizza(String message) {
        super(message);
    } //end constructor
}//end IllegalPizza class