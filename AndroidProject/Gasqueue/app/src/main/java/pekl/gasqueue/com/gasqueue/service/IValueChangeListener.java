package pekl.gasqueue.com.gasqueue.service;


//for ValueEventListener

/**
 * Created by Petros on 2016-05-19.
 */
public interface IValueChangeListener<T> {

    public void dataChanged(T data);

}
