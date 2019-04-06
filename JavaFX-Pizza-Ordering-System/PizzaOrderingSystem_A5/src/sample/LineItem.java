package sample;

import java.io.Serializable;

/**
 * Oct. 23, 2018
 *
 * A class to store a pizza line item.
 * <p> It includes the number of the pizza objects
 * as well as the total cost which potentially includes
 * a bulk discount. It can also compare the LineItem object
 * to another LineItem object and return a string that represents
 * the LineItem object.
 * </p>
 *
 * @author Danielle Mott
 *
 */
public class LineItem implements Comparable<LineItem>, Serializable {

    private static final long serialVersionUID = 2619982520298568152L;
    private Pizza pizza1;
    private int numPizzas;

    /**
     * Full parameter constructor.
     * @param pizza1; the pizza object.
     * @param numPizzas; the number of the pizza objects.
     * @throws IllegalPizza; if a null pizza object is supplied.
     */
    public LineItem(int numPizzas, Pizza pizza1) throws IllegalPizza {
        if (pizza1 == null) {
            throw new IllegalPizza("Null pizza.");
        }
        this.pizza1 = pizza1;
        setNumber(numPizzas);
    }//end full parameter constructor

    /**
     * Constructor that defaults to a single pizza for the order.
     * @param pizza1; the pizza object.
     * @throws IllegalPizza; if null pizza object is supplied.
     */
    public LineItem(Pizza pizza1) throws IllegalPizza {
        this(1, pizza1);
    }//end default LineItem constructor

    /**
     * Sets the number of pizzas.
     * @param numPizzas; the number of pizzas.
     * @throws IllegalPizza; if number of pizzas is < 1 or > 100.
     */
    public void setNumber(int numPizzas) throws IllegalPizza{
        if (numPizzas < 1 || numPizzas > 100) {
            throw new IllegalPizza("Illegal number of pizzas ordered.");
        }
        this.numPizzas = numPizzas;
    }//end setNumber mutator


    /**
     * Returns the integer number of pizzas.
     * @return The number of pizzas.
     */
    public int getNumber() {
        return numPizzas;
    }//end getNumber accessor

    /**
     * Returns the pizza object.
     * @return The pizza object.
     */
    public Pizza getPizza() {
        return pizza1;
    }//end getPizza accessor


    /**
     * Calculates discount and returns total order cost.
     * @return Cost of total pizza order.
     */
    public double getCost() {
        double pizzaCost = pizza1.getCost();
        float discount = 1F;
        if (numPizzas >= 10 && numPizzas <= 20) {
            discount = 0.9F;
        }
        else if (numPizzas > 20) {
            discount = 0.85F;
        }
        return pizzaCost * discount * numPizzas;
    }//end getCost accessor

    /**
     * Creates string to of the number of pizza objects ordered.
     * @return String of the number of pizza objects.
     */
    //Overrides the toString method of the Object class.
    @Override
    public String toString() {
        int numPizzas = getNumber();
        String lineItemString;
        if (numPizzas < 10)
            lineItemString = " " + numPizzas + " " + pizza1.toString();
        else
            lineItemString = numPizzas + " " + pizza1.toString();
        return lineItemString;
    }//end toString

    /**
     * Compares the total cost of the two LineItem objects.
     * @return 0 if the difference between the two LineItem cost is < $1.
     * 1 if the current object cost is greater than
     * the other object cost, and -1 if not.
     */
    public int compareTo(LineItem otherP) {
        double first = this.getCost();
        double second = otherP.getCost();
        if ((first - second) < 1 && (first - second) > - 1)
            return 0;
        return (Integer)Double.compare(second,first);
    }//end compareTo

}