package Domain.Classes;

import java.util.HashMap;

public class SetAuthors {
    HashMap<String, Author> authors;

    // Add a new Author
    void addAuthor(String name, Author a) {
        authors.put(name, a);
    }

    // Remove a Author
    void delAuthor(String name) {
        authors.remove(name);
    }

    // Returns the total number of authors
    int size() {
        return authors.size();
    }
}

