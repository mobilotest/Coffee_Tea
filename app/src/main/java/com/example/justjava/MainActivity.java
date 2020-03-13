package com.example.justjava;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int quantity = 0;
    int pricePerCup = 5;

    /**
     * This method for increse the quantity.
     */
    public void increse(View view) {
        quantity++;
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);
    }

    /**
     * This method for decrese the quantity.
     */
    public void decrese(View view) {
        quantity--;
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText inputName = (EditText)findViewById(R.id.enter_name_edit_text_view);
        String userNameInput = inputName.getText().toString();
        boolean toppingCheckBox = ((CheckBox) findViewById(R.id.topping_checkbox)).isChecked();
        boolean chocolateCheckBox = ((CheckBox) findViewById(R.id.chocolate_checkbox)).isChecked();
        int price = calculatePrice();
        Log.v("MainActivity", "The price is " + price);
        displayMessage(createOrderSummary(userNameInput, price, toppingCheckBox, chocolateCheckBox));
    }

    /**
     * Calculates the price of the order.
     */
    private int calculatePrice() {
        int modifiedPricePerCup = pricePerCup;
        boolean toppingCheckBox = ((CheckBox) findViewById(R.id.topping_checkbox)).isChecked();
        if (toppingCheckBox == true){
            modifiedPricePerCup += 1;
        }
        boolean chocolateCheckBox = ((CheckBox) findViewById(R.id.chocolate_checkbox)).isChecked();
        if(chocolateCheckBox == true){
            modifiedPricePerCup += 2;
        }
        return quantity * modifiedPricePerCup;
    }

    /**
     * Create order summary
     *
     * @param toppingCheckBox is whether or not the user wants whipped cream topping
     * @param chocolateCheckBox is whether or not the user wants chocolate topping
     * @param price of the order
     * @return text summary
     */
    private String createOrderSummary(String userNameInput, int price, boolean toppingCheckBox, boolean chocolateCheckBox){
        String priceMessage = userNameInput;;
        priceMessage += "\nAdd whiphed cream? " + toppingCheckBox;
        priceMessage += "\nAdd chocolate? " + chocolateCheckBox;
        priceMessage += "\nQuantity = "+ quantity;
        priceMessage += "\nTotal $" + price;
        priceMessage += "\nThank you!";
        return priceMessage;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}
