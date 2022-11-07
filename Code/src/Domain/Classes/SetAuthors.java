package Code.src.Domain.Classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @brief Class SetAuthors that contains a set of Authors
 * @author Jordi Soley Masats
 */
public class SetAuthors {
    HashMap<String, Author> authors;

    /**
     * @brief Add a new Author
     * @param name
     * @param a
     */
    public void addAuthor(String name, Author a) {
        authors.put(name, a);
    }

    /**
     * @brief Remove a Author
     * @param name
     */
    public void delAuthor(String name) {
        authors.remove(name);
    }

    /**
     * @brief Returns the total number of authors
     * @return
     */
    public int size() {
        return authors.size();
    }

    /**
     * @brief Returns a vector of the names of authors starting with a given prefix
     * @param prefix
     * @return ArrayList of names of authors starting with a given prefix
     */
    public ArrayList<String> namesAuthorsMatch(String prefix) {
        ArrayList<String> res = new ArrayList<String>();
        for (Author a : authors.values()) {
            if (a.matchesPrefix(prefix)) {
                res.add(a.getName());
            }
        }
        return res;
    }

}

