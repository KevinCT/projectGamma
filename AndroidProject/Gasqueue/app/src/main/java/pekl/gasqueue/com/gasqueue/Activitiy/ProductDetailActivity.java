package pekl.gasqueue.com.gasqueue.Activitiy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.IllegalFormatException;

import pekl.gasqueue.com.gasqueue.control.CustomerDBController;
import pekl.gasqueue.com.gasqueue.model.Cart;
import pekl.gasqueue.com.gasqueue.model.Product;
import pekl.gasqueue.com.gasqueue.R;
import pekl.gasqueue.com.gasqueue.control.ShoppingController;


/**
 * RÖR EJ DEN KOMMENTERADE KODEN I DENNA FIL!!!!!!!!!!!!!!
 */
//RÖR EJ DEN KOMMENTERADE KODEN I DENNA FIL!!!!!!!!!!!!!!
//RÖR EJ DEN KOMMENTERADE KODEN I DENNA FIL!!!!!!!!!!!!!!
//RÖR EJ DEN KOMMENTERADE KODEN I DENNA FIL!!!!!!!!!!!!!!
//RÖR EJ DEN KOMMENTERADE KODEN I DENNA FIL!!!!!!!!!!!!!!
//RÖR EJ DEN KOMMENTERADE KODEN I DENNA FIL!!!!!!!!!!!!!!
//RÖR EJ DEN KOMMENTERADE KODEN I DENNA FIL!!!!!!!!!!!!!!
//RÖR EJ DEN KOMMENTERADE KODEN I DENNA FIL!!!!!!!!!!!!!!
/**
 * RÖR EJ DEN KOMMENTERADE KODEN I DENNA FIL!!!!!!!!!!!!!!
 */

public class ProductDetailActivity extends AppCompatActivity {
    private CustomerDBController customerDB = CustomerDBController.getInstance();
    private Product chosenProduct = new Product();

    private ShoppingController shoppingController = new ShoppingController();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        chosenProduct = shoppingController.getChosenProduct();
        TextView nameLabel = (TextView) findViewById(R.id.nameLabel);
        //TextView descriptionLabel = (TextView) findViewById(R.id.descriptionLabel);
        TextView priceLabel = (TextView) findViewById(R.id.priceLabel);
        final TextView totalLabel = (TextView) findViewById(R.id.totalLabel);
        final EditText quantity = (EditText) findViewById(R.id.quantity);
        final TextView amount = (TextView) findViewById(R.id.amountLbl);
        amount.setText(shoppingController.getProductQuantity() + " st in cart");

        totalLabel.setText(chosenProduct.getPrice() + " kr");
        nameLabel.setText(chosenProduct.getName());
        priceLabel.setText("* " + chosenProduct.getPrice() + " kr = ");
        quantity.setText("1", TextView.BufferType.EDITABLE);

        quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence text, int i, int i1, int i2) {
                if (text.length() > 0) {
                    try {
                        totalLabel.setText((Integer.parseInt(text.toString()) * chosenProduct.getPrice()) + " kr");
                    } catch (IllegalFormatException l) {
                        System.out.println("ange ett nummer, tack");
                    }
                }
                else
                {
                    totalLabel.setText("0 kr");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        Button addBtn = (Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quantity.length() >0)
                {
                    /**
                     * customerDB.addToCart(chosenProduct,Integer.parseInt(quantity.getText().toString()));
                     * amount.setText(customerDB.itemAmountInCart(chosenProduct) + " st in cart");
                     */
                    shoppingController.addProduct( chosenProduct,Integer.parseInt(quantity.getText().toString()));
                    amount.setText(shoppingController.getProductQuantity() + " st in cart");
                }
                else {

                }
            }
        });
    }
}
