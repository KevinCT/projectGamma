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
    private Product chosenProduct;
    private TextView nameLabel;
    private TextView priceLabel;
    private TextView totalLabel;
    private EditText quantity;
    private TextView amount;
    private Button addBtn;
    private CustomerDBController customerDB = CustomerDBController.getInstance();

    private ShoppingController shoppingController = new ShoppingController();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        initializeViews();

        amount.setText(shoppingController.getQuantityOfProduct(chosenProduct) + " st in cart");
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

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quantity.length() >0)
                {
                    shoppingController.addProductToCart( chosenProduct,Integer.parseInt(quantity.getText().toString()));
                    amount.setText(shoppingController.getQuantityOfProduct(chosenProduct) + " st in cart");
                    /**
                     * customerDB.addToCart(chosenProduct,Integer.parseInt(quantity.getText().toString()));
                     * amount.setText(customerDB.itemAmountInCart(chosenProduct) + " st in cart");
                     */
                }
                else {

                }
            }
        });
    }

    private void initializeViews()
    {
        nameLabel = (TextView) findViewById(R.id.nameLabel);
        priceLabel = (TextView) findViewById(R.id.priceLabel);
        totalLabel = (TextView) findViewById(R.id.totalLabel);
        quantity = (EditText) findViewById(R.id.quantity);
        amount = (TextView) findViewById(R.id.amountLbl);
        chosenProduct = shoppingController.getChosenProduct();
        addBtn = (Button) findViewById(R.id.addBtn);
    }
}
