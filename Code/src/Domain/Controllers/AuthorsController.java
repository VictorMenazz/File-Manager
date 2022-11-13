package Code.src.Domain.Controllers;

import Code.src.Domain.Classes.Author;
import Code.src.Domain.Classes.Document;
import Code.src.Domain.Classes.Folder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Jordi Soley Masats
 */
public class AuthorsController {
    HashMap<String, Author> authors;

    /**
     * @brief Default creator
     */
    public AuthorsController() {
        authors = new HashMap<String, Author>();
    }

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
     * @brief Returns a list of the authors' names
     * @return list of strings of authors' names
     */
    public ArrayList<String> getAuthorsName() {
        ArrayList<String> list = new ArrayList<String>();
        for (String aut : authors.keySet()) {
            list.add(aut);
        }
        return list;
    }

    /**
     * @brief Returns a vector of the names of authors starting with a given prefix
     * @param prefix
     * @return ArrayList of names of authors starting with a given prefix
     */
    public ArrayList<String> searchAuthorsPrefix(String prefix) {
        ArrayList<String> res = new ArrayList<String>();
        for (Author a : authors.values()) {
            if (a.matchesPrefix(prefix)) {
                res.add(a.getName());
            }
        }
        return res;
    }

    /**
     * @brief Returns the Documents titles of an Author
     * @param authorName, References to the name of the Author
     * @return A list of document titles of a specific Author
     */
    public ArrayList<String> searchAuthorDocuments(String authorName) {
        if (authors.containsKey(authorName)) {
            return authors.get(authorName).getTitles();
        }
        return null;
    }

    /**
     * @brief Add a document title given to a specific Author, if the author does not exist it is created
     * @param authorName, References to the Author's name
     * @param title, References to the title of the document
     */
    public void addTitleAuthor(String authorName, String title) {
        // if the Author did not exist, it is created
        if (!authors.containsKey(authorName)) {
            authors.put(authorName, new Author(authorName));
        }
        authors.get(authorName).addTitle(title);
    }

    /**
     * @brief Deletes a document title given of a specific Author, if once deleted, it has 0 docs, Author is also removed
     * @param authorName, References to the Author's name
     * @param title, References to the title of the document
     */
    public void delTitleAuthor(String authorName, String title) {
        if (authors.containsKey(authorName)) {
            authors.get(authorName).delTitle(title);
            // if the Author runs out of documents, it is deleted
            if (authors.get(authorName).getNumTitles() == 0) {
                authors.remove(authorName);
            }
        }
    }

}

