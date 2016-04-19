public class main{
    public static Queue q = new Queue();
    public static void main(String[]args){
        MenuItem vodka = new MenuItem("Vodka");
        MenuItem whiskey = new MenuItem("Whiskey");
        MenuItem redbull = new MenuItem("Redbull");
        MenuItem pinacolada = new MenuItem("Pina Colada");
        MenuItem fruittingle = new MenuItem("Fruit Tingle");

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
    }
}