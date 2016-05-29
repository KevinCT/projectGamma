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
import pekl.gasqueue.com.gasqueue.R;
import pekl.gasqueue.com.gasqueue.control.ShoppingController;



public class ProductDetailActivity extends AppCompatActivity {
    private TextView nameLabel;
    private TextView priceLabel;
    private TextView totalLabel;
    private EditText quantity;
    private TextView amount;
    private Button addBtn;
    private CustomerDBController customerDB = CustomerDBController.getInstance();

    private ShoppingController shoppingController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        shoppingController = ShoppingController.getInstance();
        initializeViews();

        amount.setText(shoppingController.getQuantityOfProduct(shoppingController.getChosenProduct()) + " st in cart");
        totalLabel.setText(shoppingController.getChosenProductPrice() + " kr");
        nameLabel.setText(shoppingController.getChosenProductName());
        priceLabel.setText("* " + shoppingController.getChosenProductPrice() + " kr = ");
        quantity.setText("1", TextView.BufferType.EDITABLE);
        quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence text, int i, int i1, int i2) {
                if (text.length() > 0) {
                    try {
                        totalLabel.setText((Integer.parseInt(text.toString()) * shoppingController.getChosenProductPrice()) + " kr");
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
                    shoppingController.addProductToCart( shoppingController.getChosenProduct(),
                            Integer.parseInt(quantity.getText().toString()));
                    amount.setText(shoppingController.getQuantityOfProduct(shoppingController.getChosenProduct()) + " st in cart");

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
        addBtn = (Button) findViewById(R.id.addBtn);
    }
}
