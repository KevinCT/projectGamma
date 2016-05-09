package pekl.gasqueue.com.gasqueue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kotex on 03/05/2016.
 */
public class Menu {
    private String barCode;
    private String barName;
    private List<Product> allProducts;
    private List<Product> productsSameCategory;

    //The category user chooses in CategoryActivity
    private static Product.Category chosenCategory;

    public void downloadMenu() {
        allProducts = new ArrayList<>();
        //allProducts.add()
    }

    public Menu()
    {
        allProducts = new ArrayList<Product>();
        final Product vodka = new Product("vodka", Product.Category.DRINK,45);
        final Product whiskey = new Product("whiskey", Product.Category.DRINK,45);
        final Product chicken = new Product("kyckling", Product.Category.FOOD,30);
        final Product sommarby = new Product("sommarby", Product.Category.CIDER, 25);
        final Product vatten = new Product("vatten", Product.Category.NON_ALCOHOLIC,10);
        final Product falcon = new Product("falcon", Product.Category.BEER,20);
        final Product brookly = new Product("brookly", Product.Category.BEER,25);
        allProducts.add(vodka);
        allProducts.add(whiskey);
        allProducts.add(chicken);
        allProducts.add(sommarby);
        allProducts.add(vatten);
        allProducts.add(falcon);
        allProducts.add(brookly);
    }

    public List<Product> getProductsSameCategory() {
        productsSameCategory = new ArrayList<>();
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getCategory().equals(chosenCategory))
                productsSameCategory.add(allProducts.get(i));
        }
        return productsSameCategory;
    }

    public void setChosenCategory(Product.Category category)
    {
        chosenCategory = category;
    }

    public List<Product> getMenu()
    {
        return allProducts;
    }
}
