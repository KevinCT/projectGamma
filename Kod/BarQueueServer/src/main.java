public class main{
    public static void main(String[]args){
        MenuItem vodka = new MenuItem("Vodka");
        MenuItem whiskey = new MenuItem("Whiskey");
        MenuItem redbull = new MenuItem("Redbull");
        MenuItem pinacolada = new MenuItem("Pina Colada");
        MenuItem fruittingle = new MenuItem("Fruit Tingle");

        Queue q = new Queue();
        Customer kevin = new Customer("Kevin");
        Customer nguyen = new Customer("Long");
        Customer carlos = new Customer("Carlos");
        Customer petros = new Customer("Petros");
        Customer jenny= new Customer("Jenny");

        q.enqueue(kevin);
        q.enqueue(nguyen);
        q.enqueue(carlos);
        q.enqueue(petros);
        q.enqueue(jenny);

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

        carlos.showOrder();
    }
}