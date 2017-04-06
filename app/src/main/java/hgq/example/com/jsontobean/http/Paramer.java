package hgq.example.com.jsontobean.http;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/4/6.
 */
public class Paramer extends HashMap<String,Object>{

    public Paramer add(String key,String value){
        put(key,value);
        return this;
    }
}
