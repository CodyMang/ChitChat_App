/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chitchat13;
import java.util.HashMap;
/**
 *
 * @author cthom
 */

//This class is used for the data base.
//The store class gets the users ID and password
public class store {
    HashMap<String,String>info = new HashMap<String,String>();
    HashMap<String,String>user= new HashMap<String,String>();
    
    public store(){
       info.put("cthomp39", "DanOli0518");
       info.put("accdbwg@aol.com", "Danielb1!");
       info.put("cjthompson562@gmail.com", "$Ct042582");
       
    }
    public store(HashMap<String,String>input){
        user = input;
        userInformation in = new userInformation(user);
    }
    
    protected HashMap getstoredInfo(){
        return info;
    }
}
