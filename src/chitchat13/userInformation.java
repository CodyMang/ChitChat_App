/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chitchat13;
import java.util.*;
/**
 *
 * @author cthom
 */
public class userInformation {
    HashMap<String,String> userIn = new HashMap<String,String>();
      
     public userInformation(HashMap<String,String>info){
         userIn = info;
    }
    public boolean compare(HashMap<String,String>input){
        if(userIn.equals(input)){
            return true;
        }
        return false;
    }
    
protected HashMap getUserInformation(){
    return userIn;
}           

}
