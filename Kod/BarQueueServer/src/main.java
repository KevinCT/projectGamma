public class main{
    public static Queue q = new Queue();
    public static void main(String[]args){
        Product vodka = new Product("Vodka","Alcohol",20);
        Product whiskey = new Product("Whiskey","Alcohol",20);
        Product redbull = new Product("Redbull","Alcohol",20);
        Product pinacolada = new Product("Pina Colada","Alcohol",20);
        Product fruittingle = new Product("Fruit Tingle","Alcohol",20);

        Customer kevin = new Customer("Kevin");
        Customer nguyen = new Customer("Long");
        Customer carlos = new Customer("Carlos");
        Customer petros = new Customer("Petros");
        Customer jenny= new Customer("Jenny");

        kevin.addItem(vodka);
        kevin.addItem(redbull);
        nguyen.addItem(whiskey);
        carlos.addItem(pinacolada);
        carlos.addItem(redbull);
        petros.addItem(whiskey);
        jenny.addItem(fruittingle);
        jenny.addItem(vodka);

        kevin.removeItem(vodka);
        jenny.removeItem(fruittingle);

        jenny.sendOrder();
        kevin.sendOrder();
        nguyen.sendOrder();
        petros.sendOrder();
        carlos.sendOrder();

        carlos.showOrder();
        petros.showOrder();
        jenny.showOrder();

        System.out.println(q.peek());

        for (int i=1;i<=q.getSize();i++) {
            System.out.print("Position "+i+": ");
            System.out.println(q.getCustomer(i-1));
        }

        carlos.resetOrder();

        jenny.startTimer();
    }
}