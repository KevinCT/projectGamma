package pekl.gasqueue.com.gasqueue.Activitiy.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

import pekl.gasqueue.com.gasqueue.model.Product;
import pekl.gasqueue.com.gasqueue.R;

/**
 * Created by Kotex on 16/05/2016.
 */
public class HashMapAdapter extends BaseAdapter {
    private HashMap<Product, Integer> cart = new HashMap<Product,Integer>();
    private Product[] mKeys;
    public HashMapAdapter(HashMap<Product,Integer> temp){
        cart  = temp;
        mKeys = cart.keySet().toArray(new Product[temp.size()]);
    }

    @Override
    public int getCount() {
        return cart.size();
    }

    @Override
    public Object getItem(int position) {
        return cart.get(mKeys[position]);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Product temp = mKeys[position];

        final View result;
        if (convertView == null) {
            result = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_product, parent, false);
        }
        else {
            result = convertView;
        }
        ((TextView) result.findViewById(R.id.nameLbl)).setText(temp.getName());
        final TextView quantity = (TextView) result.findViewById(R.id.quantityLbl);
        if (cart.get(temp) > 0) {
            quantity.setText(cart.get(temp).toString());
            ((TextView) result.findViewById(R.id.totalLbl)).setText(temp.getPrice() * Integer.parseInt(cart.get(temp).toString()) + " kr");
        }
        else
        {
            quantity.setText("0");
            ((TextView) result.findViewById(R.id.totalLbl)).setText("0 kr");
        }

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentQuantity = Integer.parseInt(quantity.getText().toString());
                switch(view.getId())
                {
                    case R.id.incBtn:
                        currentQuantity++;
                        quantity.setText("" + currentQuantity);
                        break;
                    case R.id.decBtn:
                        currentQuantity--;
                        quantity.setText("" + currentQuantity);
                        break;
                }
            }
        };

        Button incButton = (Button) result.findViewById(R.id.incBtn);
        incButton.setOnClickListener(onClickListener);
        Button decButton = (Button) result.findViewById(R.id.decBtn);
        decButton.setOnClickListener(onClickListener);
        return result;
    }
}
