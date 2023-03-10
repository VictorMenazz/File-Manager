package FONTS.src.Domain.Classes;

import FONTS.src.DocumentsException;

import java.util.ArrayList;
/**
 * @file Author.java
 *   Class <em>Author</em>
 */

/**
 * Class Author that contains the information of an Author
 *
 * @author Jordi Soley Masats
 */
public class Author {
    /**
     *   Name of the Author
     */
    String name;

    /**
     *   Titles of the documents of the Author
     */
    ArrayList<String> docs;

    /**
     * Creator with name assignment
     * @param n name that the created Author will have
     */
    public Author(String n) {
        name = n;
        docs = new ArrayList<String>();
    }

    /**
     * Add a title document to the Author
     * @param docTitle title of the Document we add
     * @throws DocumentsException
     */
    public void addTitle(String docTitle) throws DocumentsException {
        if (docs.contains(docTitle)) {
            throw new DocumentsException("Author already has a document with this title");
        }
        docs.add(docTitle);
    }

    /**
     * Deletes a title document of the Author
     * @param docTitle References to the title to be deleted
     * @throws DocumentsException
     */
    public void delTitle(String docTitle) throws DocumentsException {
        if (!docs.contains(docTitle)) {
            throw new DocumentsException("Attempt to de-assign a document to an author who doesn't own it");
        }
        docs.remove(docTitle);
    }

    /**
     * Checks if the author's name starts with a given prefix
     * @param prefix, string contained in authors' names
     * @return true author's name starts with prefix, otherwise false
     */
    public boolean matchesPrefix(String prefix) {
        prefix = prefix.toLowerCase();
        int size = prefix.length();
        if (size > name.length()) return false;
        String s = name.toLowerCase();
        for (int i = 0; i < size; ++i) {
            if (prefix.charAt(i) != s.charAt(i)) return false;
        }
        return true;
    }

    /**
     * Changes the name of an Author
     * @param new_name string that contains the new name for the Author
     */
    public void setName(String new_name) {
        name = new_name;
    }

    /**
     *   Return the name of the Author
     * @return name of the Author
     */
    public String getName() {
        return name;
    }

    /**
     * Return a list of Author's titles of his documents
     * @return List of documents titles of the Author
     */
    public ArrayList<String> getTitles() {
        return docs;
    }

    /**
     * Return the number of titles of the author's documents
     * @return integer, number of titles
     */
    public int getNumTitles() {
        return docs.size();
    }
}
