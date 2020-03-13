package com.example.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    int quantity = 1;
    int pricePerCup = 5;
    String subject = "Coffee & Tea order for ";

    /**
     * This method for increse the quantity.
     */
    public void increse(View view) {
        if(quantity == 100){
            Toast.makeText(this, getText(R.string.more_100), Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);
    }

    /**
     * This method for decrese the quantity.
     */
    public void decrese(View view) {
        if(quantity == 1){
            Toast.makeText(this, getText(R.string.less_1), Toast.LENGTH_SHORT).show();
            return;
        }
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
        composeEmail(createOrderSummary(userNameInput, price, toppingCheckBox, chocolateCheckBox), userNameInput);
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
        String priceMessage = getString(R.string.name, userNameInput);
        priceMessage += "\n" + getString(R.string.add_whip) + toppingCheckBox;
        priceMessage += "\n" + getString(R.string.add_choco) + chocolateCheckBox;
        priceMessage += "\n"+ getString(R.string.total_quantity) + quantity;
        priceMessage += "\n" + getString(R.string.total_price) + price;
        priceMessage += "\n"+ getString(R.string.thanks);
        return priceMessage;
    }

    /**
     * This method launch the Gmail intent and populates email's Subject and Body.
     */

    public void composeEmail(String priceMessage, String userNameInput) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, subject +" "+ userNameInput);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
}
