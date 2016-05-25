package pekl.gasqueue.com.gasqueue.Activitiy.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;

import pekl.gasqueue.com.gasqueue.control.ShoppingController;
import pekl.gasqueue.com.gasqueue.model.Cart;
import pekl.gasqueue.com.gasqueue.model.Product;
import pekl.gasqueue.com.gasqueue.R;

/**
 * Created by Kotex on 16/05/2016.
 */
public class HashMapAdapter extends BaseAdapter {
    private ShoppingController tempCart;
    private Product[] mKeys;
    private Product temp;
    private View result;
    private Button incButton;
    private Button decButton;
    private Button removeBtn;
    private TextView quantity;
    private TextView totalLbl;

    public HashMapAdapter(){
        tempCart = new ShoppingController();
        mKeys = tempCart.getCart().keySet().toArray(new Product[tempCart.getCart().size()]);
    }

    @Override
    public int getCount() {
        return tempCart.getCart().size();
    }

    @Override
    public Object getItem(int position) {
        return tempCart.getCart().get(mKeys[position]);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view,ViewGroup viewGroup) {
        temp = mKeys[position];

        if (view == null) {
            result = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_product, viewGroup, false);
        }
        else {
            result = view;
        }

        ((TextView) result.findViewById(R.id.nameLbl)).setText(temp.getName());
        quantity = (TextView) result.findViewById(R.id.quantityLbl);
        quantity.setText(tempCart.getCart().get(temp).toString());
        totalLbl = (TextView) result.findViewById(R.id.totalLbl);
        totalLbl.setText(tempCart.getTotalOfProduct(temp) + " kr");
        incButton = (Button) result.findViewById(R.id.incBtn);
        decButton = (Button) result.findViewById(R.id.decBtn);
        removeBtn = (Button) result.findViewById(R.id.removeBtn);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId())
                {
                    case R.id.incBtn:
                        tempCart.incQuantity(temp);
                        quantity.setText("" + tempCart.getQuantityOfProduct(temp));
                        totalLbl.setText(tempCart.getTotalOfProduct(temp) + " kr");
                        break;
                    case R.id.decBtn:
                        tempCart.decQuantity(temp);
                        quantity.setText("" + tempCart.getQuantityOfProduct(temp));
                        totalLbl.setText(tempCart.getTotalOfProduct(temp) + " kr");
                        break;
                    case R.id.removeBtn:
                        tempCart.removeProductFromCart(temp);
                        Log.v("temp",temp.getName().toString());
                        mKeys = tempCart.getCart().keySet().toArray(new Product[tempCart.getCart().size()]);
                        notifyDataSetChanged();
                }
            }
        };

        incButton.setOnClickListener(onClickListener);
        decButton.setOnClickListener(onClickListener);
        removeBtn.setOnClickListener(onClickListener);
        return result;
    }
}
