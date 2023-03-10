package FONTS.src.Domain.Classes;

import FONTS.src.DocumentsException;

import java.io.IOException;
import java.util.HashMap;
/**
 * @file Document.java
 *
 *   Class <em>Document</em>
 */

/**
 * Class Document representing a structure able to save certain
 * content and identified by a Title and an Author.
 *
 * @author Marc Navarro Acosta
 */
public class Document {
        /**
         * Title of the Document.
         * @param title, String representing the Title of the Document.
         * */
        private String title;

        /**
         * Author of the Document.
         * @param author, String representing the Author of the Document.
         */
        private String author;

        /**
         * Contains the Content of the Document.
         * @param cont, Represents the Content of a Document.
         * */
        private Content cont;

        /**
         * Contains the language of the Document
         * @param cont, Represents the language of the Document, could be ESP, CAT or ENG.
         * */
        private String language;

        /**
         * Represent the password for an encrypted Document.
         * @param password, String that identifies the key of a Document.
         * */
        private String password;

        /**
        * ENUM that differ the possible type for a Document.
        * */
        enum DOC_TYPE{
            XML,
            PLAIN_TEXT
        }

        /**
         * Indicates the format of the Document imported.
         * @param format, ENUM that differ the possible kinds of formats.
         * */
        private DOC_TYPE format;


        //Creators
        /**
         * Constructor for the Object Document.
         * @param ttl, Represents the title of the Document.
         * @param auth, Represents the author of the Document.
         * @param c, Represents the content of a Document.
         * @param lang, Represents the text language of the Document.
         * */
        public Document(String ttl, String auth, Content c, String lang) throws DocumentsException {
            if (!lang.equals("CAT") && !lang.equals("ESP") && !lang.equals("ENG")) throw new DocumentsException("Invalid language");
            title = ttl;
            author = auth;
            cont = c;
            language = lang;
            password = null;
        }

        /**
         * Constructor for the Object Document.
         * @param ttl, Represents the title of the Document.
         * @param auth, Represents the author of the Document.
         * @param text, Represents text conforming the Content of a Document.
         * @param lang, Represents the text language of the Document.
         * */
        public Document (String ttl, String auth, String text, String lang) throws IOException, DocumentsException {
            if (!lang.equals("CAT") && !lang.equals("ESP") && !lang.equals("ENG")) throw new DocumentsException("Invalid language");
            title = ttl;
            author = auth;
            language = lang;
            cont = new Content(text, lang);
            password = null;
        }

    //Setters
        /**
         * Set the Author of a Document.
         * @param auth, String that will be allocated to the Author of this Document.
         * */
        public void setAuthor(String auth){
            author = auth;
        }

        /**
         * Set the Title of a Document.
         * @param titl, String that will be allocated to the Title of this Document.
         * */
        public void setTitle(String titl){
            title = titl;
        }

        /**
         * Set the Content of a Document.
         * @param c, String that will be allocated to the Content of this Document.
         * */
        public void setContent(Content c){
            cont = c;
        }

        /**
         * Set the Language of a Document.
         * @param lang, String that will be allocated to the Language of this Document.
         * */
        public void setLanguage(String lang) throws DocumentsException {
            if (!lang.equals("CAT") && !lang.equals("ESP") && !lang.equals("ENG")) throw new DocumentsException("Invalid language");
            language = lang;
        }

        /**
         * Protect the Document with a password
         * @param passw, String that will protect this Document
         * */
        public void protectDocument(String passw) {
            password = passw;
        }

        //Getters
        /**
         * Gets the Title of the Document.
         * @return title, String that represents the title of the document.
         * */
        public String getTitle(){
            return title;
        }

        /**
         * Gets the Author of the Document.
         * @return author's name, String that represents the author of the document.
         * */
        public String getAuthor(){
            return author;
        }

        /**
         * Gets the Content of the Document.
         * @return text, String that represents the content of the document.
         * */
        public String getContent(){
            return cont.getText();
        }

        /**
         * Gets the Content of the Document.
         * @return Content Instance of the Document.
         * */
        public Content getContentInstance() {return cont;}

        /**
         * Gets the Language of the Document.
         * @return language, String that represents the Language of the Document Content.
         * */
        public String getLanguage() {
            return language;
        }

        /**
         * Gets the frequency's vector of the Document's content.
         * @return HashMap that represents this vector.
         * */
        public HashMap<String, Integer> contentSearch() {
            return cont.getVector();
        }

    //Consultants
        /**
         * Check if Document is protected.
         * @return boolean that represents true if document is protected, otherwise false.
         * */
        public boolean isProtected(){
            return (password!=null);
        }
}

