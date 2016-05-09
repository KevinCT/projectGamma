package pekl.gasqueue.com.gasqueue;

/**
 * Created by Kevin on 2016-05-08.
 */
// might be useless..
public class Authenticator {
    private String password;
    private String databaseRef;
    public Authenticator(){


    }

    public void setPassword(String password){
        this.password=password;
    }
    public String getPassword(){
        return this.password;
    }
    public String getDatabaseRef(){
        return databaseRef;
    }

    public void setDatabaseRef(String databaseRef) {
        this.databaseRef = databaseRef;
    }

}
