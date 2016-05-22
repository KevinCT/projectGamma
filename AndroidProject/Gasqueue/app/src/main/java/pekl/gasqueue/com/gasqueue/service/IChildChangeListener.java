package pekl.gasqueue.com.gasqueue.service;

/**
 * Created by Petros on 2016-05-22.
 */
public interface IChildChangeListener<T> {

    public void childAdded(T data, String s);

    public void childChanged(T data, String s);

    public void childRemoved(T data);

    public void childMoved(T data, String s);

}
