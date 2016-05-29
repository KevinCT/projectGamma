

package pekl.gasqueue.com.gasqueue.model;

import java.util.LinkedList;

import java.util.LinkedList;

public class Queue {
    public LinkedList list;
    public Object deletedGuest;

    public Queue(){
        list = new LinkedList();
    }

    public void enqueue(Object item)
    {
        list.add(item);
    }

    public Object dequeue(){
        Object item = list.get(0);
        deletedGuest = item;
        list.remove(0);

        return item;
    }

    public Object peek(){
        return list.get(0);
    }

    public int getSize(){
        return list.size();
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public void removeGuest(Object object) {
        list.remove(object);
    }

    public Object getCustomer(int i){
        return list.get(i);
    }
    public void resetDeletedCustomer(){
        deletedGuest = null;
    }
    public void RemoveGuestOrder(String key){
        list.remove(key);
    }
}