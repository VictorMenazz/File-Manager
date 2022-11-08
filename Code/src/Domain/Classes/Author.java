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
     * @brief Creator with name assignment
     * @param n name that the created Author will have
     */
    public Author(String n) {
        name = n;
    }

    /**
     * @brief Return the name of the Author
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @brief Changes the name of an Author
     * @param new_name string that contains the new name for the Author
     */
    public void setName(String new_name) {
        name = new_name;
    }

    /**
     * @brief Return a ArrayList of Author's Documents
     * @return
     */
    public ArrayList<Document> getDocuments() {
        return docs;
    }

    /**
     * @brief Add a document to the Author
     * @param doc instance of the class Document we add
     */
    public void addDocument(Document doc) {
        docs.add(doc);
    }

    /**
     * @brief Checks if the author's name starts with a given prefix
     * @param prefix
     * @return true if yes, false if not
     */
    public boolean matchesPrefix(String prefix) {
        int size = prefix.length();
        if (size > name.length()) return false;
        for (int i = 0; i < size; ++i) {
            if (prefix.charAt(i) != name.charAt(i)) return false;
        }
        return true;
    }

}
