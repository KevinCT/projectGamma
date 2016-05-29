package pekl.gasqueue.com.gasqueue.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;

import pekl.gasqueue.com.gasqueue.R;
import pekl.gasqueue.com.gasqueue.model.Product;

/**
 * Created by Kotex on 23/05/2016.
 */
public class HostListViewAdapter extends BaseAdapter {
    private HashMap<Product,Integer> order = new HashMap<Product,Integer>();
    private Product[] mKeys;
    private View result;
    private TextView nameTV;
    private TextView quantityTV;
    private TextView totalTV;

    private Product product;

    public HostListViewAdapter(HashMap<Product,Integer> hashMap)
    {
        order = hashMap;
        mKeys = hashMap.keySet().toArray(new Product[hashMap.size()]);

    }

    @Override
    public int getCount() {
        return order.size();
    }

    @Override
    public Object getItem(int position) {
        return order.get(mKeys[position]);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        product = mKeys[position];

        if (view == null) {
            result = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.host_cart_list, viewGroup, false);
        }
        else {
            result = view;
        }
        nameTV =(TextView) result.findViewById(R.id.nameTV);
        nameTV.setText(product.getName());
        quantityTV = (TextView) result.findViewById(R.id.quantityTV);
        quantityTV.setText(order.get(product).toString() + "");
        totalTV = (TextView) result.findViewById(R.id.totalTV);
        totalTV.setText(order.get(product)*product.getPrice() + " kr");
        return result;
    }
}
