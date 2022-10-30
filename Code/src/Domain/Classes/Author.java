package Code.src.Domain.Classes;

import java.util.ArrayList;

public class Author {
    String name;
    ArrayList<Document> docs;

    // Return the name of the Author
    String getName() {
        return name;
    }

    // Return a ArrayList of Author's Documents
    ArrayList<Document> getDocuments() {
        return docs;
    }
}
