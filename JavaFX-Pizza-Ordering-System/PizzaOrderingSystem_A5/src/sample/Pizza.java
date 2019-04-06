package sample;

import java.io.Serializable;
import java.text.DecimalFormat;


/**
 * A class to store pizza information.
 * Oct. 22, 2018
 * <p>
 * The size, the amount of cheese, pineapple, green pepper, and ham on the pizza are recorded.
 * This class will throw an IllegalPizza exception if an illegal value is supplied.
 * </p>
 * <p> This class can also return the cost of a pizza, the pizza as a string,
 * compare the pizza to other pizzas, and clone the pizza object.
 * @author Danielle Mott
 * </p>
 *
 */
public class Pizza implements Serializable {

    private static final long serialVersionUID = 5311090087899604148L;
    private String size;
    private String cheese;
    private String pineapple;
    private String greenPepper;
    private String ham;

    /**
     * Full parameter constructor.
     * @param size; small, medium, or large.
     * @param cheese; single, double, or triple cheese.
     * @param pineapple; yes, or no.
     * @param greenPepper; yes or no.
     * @param ham; yes or no.
     * @throws IllegalPizza; if arguments are not legal.
     */

    public Pizza(String size, String cheese, String pineapple, String greenPepper, String ham) throws IllegalPizza{
        setSize(size);
        setCheese(cheese);
        setHam(ham);
        setPineapple(pineapple);
        setGreenPepper(greenPepper);
        checkPineappleAndGreenPepper();
    }//end Pizza parameter constructor


    /**
     * Default pizza is small and has single cheese and single ham.
     * @param cheese
     * @param ham
     */
    public Pizza() throws IllegalPizza {
        this("small", "single", "none", "none", "single");
    }//end default constructor

    /**
     * Sets the size of the pizza.
     * @param size The pizza size.
     * @throws IllegalPizzaException if the pizza is not "small", "medium", or "large".
     */
    public void setSize(String size) throws IllegalPizza {
        if (size == null)
            throw new IllegalPizza("Null pointer.");
        if (!size.equalsIgnoreCase("small") && !size.equalsIgnoreCase("medium") && !size.equalsIgnoreCase("large"))
            throw new IllegalPizza("Illegal size: " + size);
        this.size = size;
    }//end setSize mutator

    /**
     * Sets the amount of cheese.
     * @param cheese; the amount of cheese (single, triple, or double).
     * @throws IllegalPizza; if the pizza doesn't have "single", "double", or "triple" cheese.
     */
    public void setCheese(String cheese) throws IllegalPizza {
        if (cheese == null)
            throw new IllegalPizza("Null pointer.");
        if (!cheese.equalsIgnoreCase("single") && !cheese.equalsIgnoreCase("double") && !cheese.equalsIgnoreCase("triple"))
            throw new IllegalPizza("Illegal cheese specification: " + size);
        this.cheese = cheese;
    }//end setCheese mutator

    /**
     * Sets the ham specification.
     * @param ham; single or none.
     * @throws IllegalPizza; if ham not single or none.
     */
    public void setHam(String ham) throws IllegalPizza {
        if (ham == null)
            throw new IllegalPizza("Null pointer.");
        if (!ham.equalsIgnoreCase("none") && !ham.equalsIgnoreCase("single"))
            throw new IllegalPizza("Illegal ham specification: "  + ham);
        this.ham = ham;
    }//end setHam mutator

    /**
     * Sets pineapple specification.
     * @param pineapple; single or none.
     * @throws IllegalPizza; if pineapple is not single or none.
     */
    public void setPineapple(String pineapple) throws IllegalPizza {
        if (pineapple == null)
            throw new IllegalPizza("Null pointer.");
        if (!pineapple.equalsIgnoreCase("none") && !pineapple.equalsIgnoreCase("single")) {
            throw new IllegalPizza("Illegal pineapple specification: " + pineapple);
        }
        this.pineapple = pineapple;
    }//end setPineapple mutator

    /**
     * Sets green pepper specification.
     * @param greenPepper; single or none.
     * @throws IllegalPizza; if green pepper is not single or none.
     */
    public void setGreenPepper(String greenPepper) throws IllegalPizza {
        if (greenPepper == null)
            throw new IllegalPizza("Null pointer.");
        if (!greenPepper.equalsIgnoreCase("none") && !greenPepper.equalsIgnoreCase("single"))
            throw new IllegalPizza("Illegal green pepper specification: " + greenPepper);
        this.greenPepper = greenPepper;
    }//end setGreenPepper mutator

    /**
     * Checks pineapple and ham specifications.
     * To have pineapple or green pepper, one must have ham.
     */
    public void checkPineappleAndGreenPepper() throws IllegalPizza {
        if (ham.equalsIgnoreCase("none")) {
            if (greenPepper.equalsIgnoreCase("single") || pineapple.equalsIgnoreCase("single")) {
                throw new IllegalPizza("Must have ham to have green peppper or pineapple.");
            }
        }
    }//end checkPineappleAndGreenPepper


    /**
     * Calculates and returns the cost of a pizza.
     * @return The cost of a pizza.
     */
    public double getCost() {
        double cost = 0.00;
        if (size.equalsIgnoreCase("small"))
            cost += 7.00;
        if (size.equalsIgnoreCase("medium"))
            cost += 9.00;
        if (size.equalsIgnoreCase("large"))
            cost += 11.00;
        if (cheese.equalsIgnoreCase("double"))
            cost += 1.50;
        if (cheese.equalsIgnoreCase("triple"))
            cost += 3.00;
        if (ham.equalsIgnoreCase("single"))
            cost += 1.50;
        if (pineapple.equalsIgnoreCase("single"))
            cost += 1.50;
        if (greenPepper.equalsIgnoreCase("single"))
            cost += 1.50;
        return cost;
    } //end getCost accessor

    /**
     * A string representation of the current object.
     * @return A string representation of the contents of the pizza and its cost.
     */
    //Overrides the toString method of the Object class.
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.00");
        String myString = "";
        myString += size + " pizza, ";
        myString += cheese + " cheese";
        if (pineapple.equalsIgnoreCase("single"))
            myString += ", pineapple";
        if (greenPepper.equalsIgnoreCase("single"))
            myString += ", green pepper";
        if (ham.equalsIgnoreCase("single"))
            myString += ", ham";
        double cost = getCost();
        myString += ". Cost: $" + df.format(cost) + " each.";
        return myString;
    }//end toString


    /**
     * A comparison of the attributes of the two pizza objects.
     * @param otherObject; a different object.
     * @return boolean; returns true if all attributes are identical and false otherwise.
     */
    //Overrides the equals method in the Object class.
    @Override
    public boolean equals(Object otherObject) {
        if (otherObject instanceof Pizza) {
            Pizza otherP = (Pizza)otherObject;
            if (!otherP.size.equalsIgnoreCase(size) || !otherP.cheese.equalsIgnoreCase(cheese) || !otherP.pineapple.equalsIgnoreCase(pineapple)
                    || !otherP.ham.equalsIgnoreCase(ham) || !otherP.greenPepper.equalsIgnoreCase(greenPepper))
                return false;
            return true;
        }
        return false;
    } //end equals


    /**
     * Returns a copy of the current Pizza object.
     * @return A copy of the object.
     */
    //Overrides the clone method in the Object class.
    @Override
    public Pizza clone() {
        Pizza pzCopy = null;
        try {
            pzCopy = new Pizza(size, cheese, pineapple, greenPepper, ham);
        }
        catch (IllegalPizza e) {
            return null;
        }
        return pzCopy;
    }//end clone


}//end Pizza class