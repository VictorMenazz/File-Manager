package Code.src.Domain.Controllers.Stubs;

import Code.src.Domain.Classes.Author;

import java.util.ArrayList;

public class AuthorStub extends Author {

    public AuthorStub(String n) {
        super(n);
    }
/*
    public String getName() {
        return name;
    }

    public ArrayList<String> getTitles() {
        return docs;
    }


    public int getNumTitles() {
        return docs.size();
    }


    public void addTitle(String docTitle) {
        docs.add(docTitle);
    }


    public void delTitle(String docTitle) {
        docs.remove(docTitle);
    }


    public boolean matchesPrefix(String prefix) {
        int size = prefix.length();
        if (size > name.length()) return false;
        for (int i = 0; i < size; ++i) {
            if (prefix.charAt(i) != name.charAt(i)) return false;
        }
        return true;
    }
 */
}
