package pekl.gasqueue.com.gasqueue.Activitiy.Adapter;

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
        final Product product = mKeys[position];
        final View result;
        if (view == null) {
            result = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_product, viewGroup, false);
        }
        else {
            result = view;
        }
        ((TextView) result.findViewById(R.id.nameTV)).setText(product.getName());
        ((TextView) result.findViewById(R.id.quantityTV)).setText(order.get(product).toString());
        ((TextView) result.findViewById(R.id.totalTV)).setText(order.get(product)*product.getPrice());
        return result;
    }
}
