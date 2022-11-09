package Code.src.Domain.Controllers.Stubs;

import Code.src.Domain.Classes.Content;

import java.util.*;

public class ContentStub extends Content {

    public ContentStub()
    {
    }

    public ContentStub(String textDoc, String language){
        String Cont = "Simple Content Test";
        language = "ENG";
    }

    public Map<String,Integer> getVector() {
        Map<String, Integer> test = null;
        test.put("Test",-1);
        return test;
    }

    public boolean wordContain(String wSearch) {
        return false;
    }
}
