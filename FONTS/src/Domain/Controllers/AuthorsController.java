package FONTS.src.Domain.Controllers;

import FONTS.src.DocumentsException;
import FONTS.src.Domain.Classes.Author;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @file AuthorsController.java
 * Class <em>Authors Controller</em>
 */

/**
 * Class AuthorsController that delegates functions to the Domain Object Author.
 *
 * @author Jordi Soley Masats
 */
public class AuthorsController {
    HashMap<String, Author> authors;

    /**
     * Default creator of AuthorsController
     */
    public AuthorsController() {
        authors = new HashMap<String, Author>();
    }

    /**
     * Add new Author to authors
     * @param name, name of the author to add
     * @param a, Instance of the author
     */
    public void addAuthor(String name, Author a) throws DocumentsException {
        if (!authors.containsKey(name)) {
            authors.put(name, a);
        }
        else {
            throw new DocumentsException("There is already an Author with this name");
        }
    }

    /**
     * Remove Author of authors list
     * @param name, name of the author to delete
     */
    public void delAuthor(String name) throws DocumentsException {
        if (!authors.containsKey(name)) {
            throw new DocumentsException("Attempt to remove an author who doesn't exist");
        }
        authors.remove(name);
    }

    /**
     * Returns the total number of authors
     * @return Integer of the total authors in the system
     */
    public int size() {
        return authors.size();
    }

    /**
     * Returns a list of the authors' names
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
     * Returns a vector of the names of authors starting with a given prefix
     * @param prefix, string contained in authors' names
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
     * Returns the Documents titles of an Author
     * @param authorName, References to the name of the Author
     * @return A list of document titles of a specific Author
     */
    public ArrayList<String> searchAuthorDocuments(String authorName) throws DocumentsException {
        if (!authors.containsKey(authorName)) {
            throw new DocumentsException("There is no author with this name");
        }
        return authors.get(authorName).getTitles();
    }

    /**
     * Add a document title given to a specific Author, if the author does not exist it is created
     * @param authorName, References to the Author's name
     * @param title, References to the title of the document
     */
    public void addTitleAuthor(String authorName, String title) throws DocumentsException {
        // if the Author did not exist, it is created
        if (!authors.containsKey(authorName)) {
            authors.put(authorName, new Author(authorName));
        }
        try {
            authors.get(authorName).addTitle(title);
        }
        catch (DocumentsException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a document title given of a specific Author, if once deleted, it has 0 docs, Author is also removed
     * @param authorName, References to the Author's name
     * @param title, References to the title of the document
     */
    public void delTitleAuthor(String authorName, String title) throws DocumentsException {
        if (authors.containsKey(authorName)) {
            try {
                authors.get(authorName).delTitle(title);
                // if the Author runs out of documents, it is deleted
                if (authors.get(authorName).getNumTitles() == 0) {
                    authors.remove(authorName);
                }
            }
            catch (DocumentsException e) {
                e.printStackTrace();
            }
        }
        else {
            throw new DocumentsException("There is no author with this name");
        }
    }

    /**
     * Generates a JSON with a log of Authors System.
     */
    public String saveAuthorsStructure() {
        Gson gson = new Gson();
        return gson.toJson(authors);
    }

    /**
     * Recovers system from a JSON log.
     */
    public void recoverFoldersStructure(String authorsJSON) {
        Gson gson = new Gson();
        Type authorsList = new TypeToken<HashMap<String, Author>>(){}.getType();
        authors = gson.fromJson(authorsJSON, authorsList);
    }
}

