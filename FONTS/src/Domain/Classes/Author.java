package FONTS.src.Domain.Classes;

import FONTS.src.DocumentsException;

import java.util.ArrayList;
/**
 * @file Author.java
 * @brief Class <em>Author</em>
 */

/**
 * @brief Class Author that contains the information of an Author
 *
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
        docs = new ArrayList<String>();
    }

    /**
     * @brief Add a title document to the Author
     * @param docTitle title of the Document we add
     */
    public void addTitle(String docTitle) throws DocumentsException {
        if (docs.contains(docTitle)) {
            throw new DocumentsException("Author already has a document with this title");
        }
        docs.add(docTitle);
    }

    /**
     * @brief Deletes a title document of the Author
     * @param docTitle References to the title to be deleted
     */
    public void delTitle(String docTitle) throws DocumentsException {
        if (!docs.contains(docTitle)) {
            throw new DocumentsException("Attempt to de-assign a document to an author who doesn't own it");
        }
        docs.remove(docTitle);
    }

    /**
     * @brief Checks if the author's name starts with a given prefix
     * @param prefix, string contained in authors' names
     * @return true author's name starts with prefix, otherwise false
     */
    public boolean matchesPrefix(String prefix) {
        int size = prefix.length();
        if (size > name.length()) return false;
        for (int i = 0; i < size; ++i) {
            if (prefix.charAt(i) != name.charAt(i)) return false;
        }
        return true;
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
}
