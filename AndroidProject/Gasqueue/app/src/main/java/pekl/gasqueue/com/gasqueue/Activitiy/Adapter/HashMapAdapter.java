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

import pekl.gasqueue.com.gasqueue.model.Cart;
import pekl.gasqueue.com.gasqueue.model.Product;
import pekl.gasqueue.com.gasqueue.R;

/**
 * Created by Kotex on 16/05/2016.
 */
public class HashMapAdapter extends BaseAdapter {
    private Cart tempCart = new Cart();
    private Product[] mKeys;
    public HashMapAdapter(){
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
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        final Product temp = mKeys[position];
        final View result;
        if (convertView == null) {
            result = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_product, parent, false);
        }
        else {

            result = convertView;
        }

        ((TextView) result.findViewById(R.id.nameLbl)).setText(temp.getName());
        final TextView quantity = (TextView) result.findViewById(R.id.quantityLbl);
        quantity.setText(tempCart.getCart().get(temp).toString());
        final TextView totalLbl = (TextView) result.findViewById(R.id.totalLbl);
        totalLbl.setText(tempCart.getTotalOfProduct(temp) + " kr");

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId())
                {
                    case R.id.incBtn:
                        tempCart.incQuantity(temp);
                        quantity.setText("" + tempCart.getQuantity(temp));
                        totalLbl.setText(tempCart.getTotalOfProduct(temp) + " kr");
                        break;
                    case R.id.decBtn:
                        tempCart.decQuantity(temp);
                        quantity.setText("" + tempCart.getQuantity(temp));
                        totalLbl.setText(tempCart.getTotalOfProduct(temp) + " kr");
                        break;
                    case R.id.removeBtn:
                        tempCart.removeProduct(temp);
                        Log.v("temp",temp.getName().toString());
                        mKeys = tempCart.getCart().keySet().toArray(new Product[tempCart.getCart().size()]);
                        notifyDataSetChanged();
                }
            }
        };

        Button incButton = (Button) result.findViewById(R.id.incBtn);
        incButton.setOnClickListener(onClickListener);
        Button decButton = (Button) result.findViewById(R.id.decBtn);
        decButton.setOnClickListener(onClickListener);
        Button removeBtn = (Button) result.findViewById(R.id.removeBtn);
        removeBtn.setOnClickListener(onClickListener);
        return result;
    }

}
