package pekl.gasqueue.com.gasqueue.model;

import android.util.Log;

import com.firebase.client.DataSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pekl.gasqueue.com.gasqueue.service.IValueChangeListener;
import pekl.gasqueue.com.gasqueue.service.ValueChangeListener;

/**
 * Created by Kotex on 03/05/2016.
 */
public class Menu {
    private String bartenderCode;
    private String customerCode;
    private String barName;
    private static HashMap<String, Product> stringKey = new HashMap<>();;
    private List<Product> allProducts;

   /* private static final Product vodka = new Product("vodka", Product.Category.DRINK,45);
=======
    private IValueChangeListener listener;

    private static final Product vodka = new Product("vodka", Product.Category.DRINK,45);
>>>>>>> Stashed changes
    private static final Product whiskey = new Product("whiskey", Product.Category.DRINK,45);
    private static final Product chicken = new Product("kyckling", Product.Category.FOOD,30);
    private static final Product sommarby = new Product("sommarby", Product.Category.CIDER, 25);
    private static final Product vatten = new Product("vatten", Product.Category.NON_ALCOHOLIC,10);
    private static final Product falcon = new Product("falcon", Product.Category.BEER,20);
    private static final Product brookly = new Product("brookly", Product.Category.BEER,25);*/



    public Menu(String barName,String barCode, String guestCode)
    {
        this.barName = barName;
        bartenderCode = barCode;
        customerCode = guestCode;
        allProducts = new ArrayList<>();
    }
    public Menu(List<Product> list){
        allProducts=new ArrayList<>();
        allProducts=list;
    }

    public Menu()
    {
        allProducts = new ArrayList<Product>();
        bartenderCode = "0000";
        customerCode = "1234";
       // allProducts.add(vodka);
    }

    public static HashMap<Product, Integer> stringToProduct(HashMap<String, Integer> orderString) {
     /*   stringKey.put("vodka",vodka);
=======
        allProducts.add(vodka);
        initListener();
    }

    private void initListener() {
        listener = new ValueChangeListener("https://dazzling-torch-9680.firebaseio.com/Menus") {
            @Override
            public void dataChanged(DataSnapshot data) {

                for (DataSnapshot menuSnapshot : data.getChildren()) {
                    if ("1234".equals(menuSnapshot.getKey())) {
                        //allProducts=menuSnapshot.getValue(Menu.class).getProducts();
                        //Log.v("PRODUCTPLEASE",allProducts.get(0).getName());
                        setProduct(menuSnapshot.getValue(Menu.class).getProducts());
                        break;
                    }

                }
            }
        };
    }
        public static HashMap<Product, Integer> stringToProduct(HashMap<String, Integer> orderString) {
        stringKey.put("vodka",vodka);
>>>>>>> Stashed changes
        stringKey.put("whiskey",whiskey);
        stringKey.put("kyckling",chicken);
        stringKey.put("sommarby",sommarby);
        stringKey.put("vatten",vatten);
        stringKey.put("falcon",falcon);
        stringKey.put("brookly",brookly);
*/
        Set<String> products = orderString.keySet();
        HashMap<Product,Integer> orderProduct = new HashMap<>();
        System.out.println(orderString.size());
        System.out.println(products.size());

        for (String e:products) {
            orderProduct.put( stringKey.get(e), (Integer) orderString.get(e));
        }
        return orderProduct;
    }

    public void setProduct(List<Product> list)
    {
        allProducts = list;
    }

    public void addProduct(Product p)
    {
        allProducts.add(p);
    }

    public void removeProduct(Product p)
    {
        if (allProducts.contains(p)) {
            allProducts.remove(p);
        }
    }

    public List<Product> getProducts()
    {
        return allProducts;
    }

    public List<Product> getProductsSameCategory(Product.Category c){
        List<Product> productsSameCategory = new ArrayList<Product>();
        if (allProducts.size() >0) {
            for (int i = 0; i < allProducts.size(); i++) {
                if (allProducts.get(i).getCategory().equals(c))
                    productsSameCategory.add(allProducts.get(i));
            }
        }
        return productsSameCategory;
    }

}
