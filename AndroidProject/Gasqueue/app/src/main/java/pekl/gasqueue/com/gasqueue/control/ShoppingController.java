package pekl.gasqueue.com.gasqueue.control;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pekl.gasqueue.com.gasqueue.model.Cart;
import pekl.gasqueue.com.gasqueue.model.Menu;
import pekl.gasqueue.com.gasqueue.model.Product;
import pekl.gasqueue.com.gasqueue.service.FirebaseDatabaseManager;
import pekl.gasqueue.com.gasqueue.service.IDatabaseManager;
import pekl.gasqueue.com.gasqueue.service.IValueChangeListener;
import pekl.gasqueue.com.gasqueue.service.ValueChangeListener;

/**
 * Created by Kotex on 18/05/2016.
 */
public class ShoppingController {
    private static ShoppingController shoppingController;

    private Menu menu;
    private Product chosenProduct;
    private Product.Category chosenCategory;
    private List<Product> allProducts;
    private Cart cart;
    private boolean isBartender = false;
    private boolean isCreatingProduct;
    private IValueChangeListener listener;
    private IDatabaseManager<Firebase> dbMenuManager;
    private List<HashMap> productHashmap;

    private ShoppingController() {
        dbMenuManager = new FirebaseDatabaseManager("https://dazzling-torch-9680.firebaseio.com/");
        allProducts= new ArrayList<>();
        cart = new Cart();
        productHashmap=new ArrayList<>();
        initListener();
    }

    public static ShoppingController getInstance() {
        if (shoppingController == null) {
            shoppingController = new ShoppingController();
        }
        return shoppingController;
    }

    public void removeChosenProductFromMenu(){ menu.removeProduct(chosenProduct); }

    public List<Product> getProductSameCategory() { return menu.getProductsSameCategory(chosenCategory); }

    public String getProductName(Product p) { return p.getName();}

    public Product getChosenProduct()
    {
        return chosenProduct;
    }

    public void setChosenProduct(Product p) { chosenProduct = p; }

    public String getChosenProductName(){return chosenProduct.getName();}

    public int getChosenProductPrice(){return chosenProduct.getPrice();}

    public Product.Category getChosenProductCategory(){return chosenProduct.getCategory();}

    public void setChosenProductChanges(String newName,Product.Category newCategory, int newPrice){ chosenProduct.setChanges(newName,newCategory,newPrice);}

    public int getTotalOfCart(){return cart.getTotal();}

    public Product.Category getChosenCategory() { return chosenCategory; }

    public void setChosenCategory(Product.Category c) {  chosenCategory = c;  }

    public void incQuantity(Product p){ cart.addProduct(p,1); }

    public void decQuantity(Product p) { cart.addProduct(p,-1); }

    public void addProductToCart(Product p , int g) { cart.addProduct(p,g);  }

    public void removeProductFromCart(Product p) { cart.removeProduct(p); }

    public HashMap<Product,Integer> getCart() { return cart.getCart(); }

    public int getTotalOfProduct(Product p) { return cart.getTotalOfProduct(p);  }

    public int getQuantityOfProduct(Product p) {
        if (cart.getCart().containsKey(p)) {
            return cart.getCart().get(p);
        }
        else return 0;
    }

    public void setTypeOfUser(boolean isBartender) { this.isBartender = isBartender; }

    public boolean getTypeOfUser() { return isBartender; }

    public void setIntention(boolean isCreatingProduct) { this.isCreatingProduct = isCreatingProduct; }

    public boolean getIntention() { return isCreatingProduct; }

    public void addProductToMenu(String n, Product.Category c, int p) { menu.addProduct(new Product(n,c,p)); }


    public void changeProduct(String name, Product.Category category, int price) { chosenProduct.setChanges(name,category,price); }

    public void createProduct(String name, Product.Category category, int price)
    {
        Product temp = new Product(name,category,price);
        allProducts.add(temp);
    }


    private void initListener(){
        listener = new ValueChangeListener("https://dazzling-torch-9680.firebaseio.com/Menus") {
            @Override
            public void dataChanged(DataSnapshot data) {

                for(DataSnapshot menuSnapshot:data.getChildren()){
                        setProduct(menuSnapshot.getValue(Menu.class).getProducts());
                    }
                }
        };

    }


    public HashMap<Product, Integer> convertStringToProduct(HashMap<String, Integer> orderString) {
        return menu.stringToProduct(orderString);
    }


    public void sendMenu(String barKey, String customerKey){
        Map menuMap = new HashMap<>();
        menuMap.put(barKey,menu);
        menuMap.put(customerKey,menu);
        dbMenuManager.saveMap("Menus",menuMap);
    }
    private void setProduct(List<Product> list){
        menu= new Menu(list);
    }
}
