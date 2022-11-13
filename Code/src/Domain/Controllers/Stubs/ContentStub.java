package Code.src.Domain.Controllers.Stubs;

import Code.src.Domain.Classes.Content;

import java.util.*;

/**
 * @file ContentStub.java
 * @brief Stub <em>Content</em>
 */

/**
 * @brief Stub of Content class
 *
 * @author Marc Navarro Acosta
 */
public class ContentStub extends Content {

    public ContentStub()
    {
    }

    public ContentStub(String textDoc, String language){
        String Cont = "Simple Content Test";
        language = "ENG";
    }

    public HashMap<String,Integer> getVector() {
        HashMap<String, Integer> test = null;
        test.put("Test",-1);
        return test;
    }

    public String getText(){
        return "Simple Content Test";
    }

    public boolean wordContain(String wSearch) {
        return false;
    }
}
