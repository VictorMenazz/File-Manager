package Code.src.Domain.Classes;

import java.util.ArrayList;

/**
 * @brief Class Author that contains the information of a Author
 * @author Jordi Soley Masats
 */
public class Author {
    String name;
    ArrayList<Document> docs;

    /**
     * @brief Return the name of the Author
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @brief Return a ArrayList of Author's Documents
     * @return
     */
    ArrayList<Document> getDocuments() {
        return docs;
    }

    /**
     * @brief Checks if the author's name starts with a given prefix
     * @param prefix
     * @return true if yes, false if not
     */
    boolean matchesPrefix(String prefix) {
        int size = prefix.length();
        if (size > name.length()) return false;
        for (int i = 0; i < size; ++i) {
            if (prefix.charAt(i) != name.charAt(i)) return false;
        }
        return true;
    }

}
