package pekl.gasqueue.com.gasqueue.service;

/**
 * Created by Petros on 2016-05-22.
 */
public interface IChildChangeListener<T> {

    public void onChildAdded(T data, String s);

    public void onChildChanged(T data, String s);

    public void onChildRemoved(T data);

    public void onChildMoved(T data, String s);

}
