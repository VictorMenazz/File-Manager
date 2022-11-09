package Code.src.Domain.Classes;

import java.util.ArrayList;

/**
 * @brief Class Author that contains the information of an Author
 * @author Jordi Soley Masats
 */
public class Author {
    /**
     * @brief Name of the Author
     */
    String name;

    /**
     * @brief Titles of the documents of the Author
     */
    ArrayList<String> docs;

    /**
     * @brief Creator with name assignment
     * @param n name that the created Author will have
     */
    public Author(String n) {
        name = n;
    }

    /**
     * @brief Changes the name of an Author
     * @param new_name string that contains the new name for the Author
     */
    public void setName(String new_name) {
        name = new_name;
    }

    /**
     * @brief Return the name of the Author
     * @return name of the Author
     */
    public String getName() {
        return name;
    }

    /**
     * @brief Return a list of Author's titles of his documents
     * @return List of documents titles of the Author
     */
    public ArrayList<String> getTitles() {
        return docs;
    }

    /**
     * @brief Return the number of titles of the author's documents
     * @return integer, number of titles
     */
    public int getNumTitles() {
        return docs.size();
    }

    /**
     * @brief Add a title document to the Author
     * @param docTitle title of the Document we add
     */
    public void addTitle(String docTitle) {
        docs.add(docTitle);
    }

    /**
     * @brief Deletes a title document of the Author
     * @param docTitle References to the title to be deleted
     */
    public void delTitle(String docTitle) {
        docs.remove(docTitle);
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
