package sample;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Controller {

    @FXML
    private ChoiceBox<String> sizeChoice = new ChoiceBox<>();

    @FXML
    private ChoiceBox<String> cheeseChoice = new ChoiceBox<>();

    @FXML
    private ChoiceBox<String> hamChoice = new ChoiceBox<>();

    @FXML
    private ChoiceBox<String> pineappleChoice = new ChoiceBox<>();

    @FXML
    private ChoiceBox<String> greenPepperChoice = new ChoiceBox<>();

    private ObservableList<String> sizeList = FXCollections.observableArrayList(
            "Small", "Medium", "Large");

    private ObservableList<String> cheeseList = FXCollections.observableArrayList(
            "Single", "Double", "Triple");

    private ObservableList<String> hamList = FXCollections.observableArrayList(
            "None", "Single");

    private ObservableList<String> pineappleList = FXCollections.observableArrayList(
            "None", "Single");

    private ObservableList<String> greenPepperList = FXCollections.observableArrayList(
            "None", "Single");

    private ObservableList<String> reducedPineappleList = FXCollections.observableArrayList(
            "None");

    private ObservableList<String> reducedGreenPepperList = FXCollections.observableArrayList(
            "None");

    @FXML
    private Button saveYourPizza = new Button();

    @FXML
    private Button addToTotal = new Button();

    @FXML
    private Label currentPizzaCost = new Label();

    @FXML
    private Label totalCurrentCost = new Label();

    @FXML
    private TextField quantityTextField = new TextField();

    @FXML
    private TextField totalOrderCost = new TextField();

    @FXML
    private TextArea orderSum = new TextArea();


    private Pizza thePizza;
    private LineItem theLineItem;



    @FXML
    void initialize() {

        //Size choice box
        sizeChoice.setItems(sizeList);
        sizeChoice.setValue("Small");

        //Cheese choice box
        cheeseChoice.setItems(cheeseList);
        cheeseChoice.setValue("Single");

        //Ham choice box
        hamChoice.setItems(hamList);
        hamChoice.setValue("Single");
        //Lambda function to restrict green pepper and pineapple choices based on ham choice
        hamChoice.valueProperty().addListener((observableValue, oldVal, newVal) ->
        {
            switch (newVal) {
                case "None" :
                    greenPepperChoice.setItems(reducedGreenPepperList);
                    pineappleChoice.setItems(reducedPineappleList);
                    pineappleChoice.setValue("None");
                    greenPepperChoice.setValue("None");
                    break;
                case "Single" :

                    greenPepperChoice.setItems(greenPepperList);
                    pineappleChoice.setItems(pineappleList);
                    pineappleChoice.setValue("None");
                    greenPepperChoice.setValue("None");
                    break;
            }
        });

        //Pineapple choice box
        pineappleChoice.setItems(pineappleList);
        pineappleChoice.setValue("None");

        //GreenPepper choice box
        greenPepperChoice.setItems(greenPepperList);
        greenPepperChoice.setValue("None");

        saveYourPizza.setOnAction(event ->
        {
            //initialize pizza object
            try {
                this.thePizza = new Pizza(sizeChoice.getValue().toLowerCase(), cheeseChoice.getValue().toLowerCase(), pineappleChoice.getValue().toLowerCase(), greenPepperChoice.getValue().toLowerCase(), hamChoice.getValue().toLowerCase());
                String cost = String.valueOf(thePizza.getCost()) + "0";
                currentPizzaCost.setText(cost);
                quantityTextField.setText("");
                totalCurrentCost.setText("");
            }
            catch (IllegalPizza e) {
                System.out.println("Illegal pizza error.");
            }
        });

        quantityTextField.textProperty().addListener((observableValue, oldVal, newVal) ->
                //Restrict entries to integers between 1 and 100
        {
            if (!newVal.matches("\\d{0,7}([\\.]\\d{0,4})?") || Double.parseDouble(newVal) < 1 || Double.parseDouble(newVal) > 100 || Double.parseDouble(newVal) - Math.floor(Double.parseDouble(newVal)) != 0)  {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText("Please enter an integer between 1 and 100.\n Try again.");
                alert.showAndWait();
                quantityTextField.setText(oldVal);
            }
            else {
                Double doubleTotalCurrentCost = Double.parseDouble(currentPizzaCost.getText()) * Double.parseDouble(newVal);
                totalCurrentCost.setText("$ " + String.valueOf(doubleTotalCurrentCost) + "0");

            }
        });

        addToTotal.setOnAction(event ->
        {
            try {
                //initialize LineItem
                int quantity = Integer.parseInt(quantityTextField.getText());
                this.theLineItem = new LineItem(quantity, thePizza);
                String old = orderSum.getText();
                orderSum.setText(old + "\n" + theLineItem.toString());
                double lineItemCost = Double.parseDouble(totalOrderCost.getText());
                totalOrderCost.setText(String.valueOf(lineItemCost + thePizza.getCost() * quantity));

            }
            catch (IllegalPizza e) {
                System.out.println("Illegal pizza error.");
            }
        });
    }//end initialize
}//end controller class
