package Code.src.Domain.Classes;

import java.io.IOException;
import java.util.HashMap;
/**
 * @file Document.java
 *
 * @brief Class <em>Document</em>
 */

/**
 * @brief Class Document representing a structure able to save certain
 * @brief content and identified by a Title and an Author.
 *
 * @author Marc Navarro Acosta
 */
public class Document {
        /**
         * @brief Title of the Document.
         * @param title, String representing the Title of the Document.
         * */
        private String title;

        /**
         * @brief Author of the Document.
         * @param author, String representing the Author of the Document.
         */
        private String author;

        /**
         * @brief Contains the Content of the Document.
         * @param cont, Represents the Content of a Document.
         * */
        private Content cont;

        /**
         * @brief Contains the language of the Document
         * @param cont, Represents the language of the Document, could be ESP, CAT or ENG.
         * */
        private String language;

        /**
         * @brief Represent the password for an encrypted Document.
         * @param password, String that identifies the key of a Document.
         * */
        private String password;

    /**
        * @brief ENUM that differ the possible type for a Document.
        * */
        enum DOC_TYPE{
            XML,
            PLAIN_TEXT
        }

        /**
         * @brief Indicates the format of the Document imported.
         * @param format, ENUM that differ the possible kinds of formats.
         * */
        private DOC_TYPE format;


        //Creators
        /**
         * @brief Constructor for the Object Document.
         * @param ttl, Represents the title of the Document.
         * @param auth, Represents the author of the Document.
         * @param c, Represents the content of a Document.
         * @param lang, Represents the text language of the Document.
         * */
        public Document(String ttl, String auth, Content c, String lang){
            title = ttl;
            author = auth;
            cont = c;
            language = lang;
            password = null;
        }

        /**
         * @brief Constructor for the Object Document.
         * @param ttl, Represents the title of the Document.
         * @param auth, Represents the author of the Document.
         * @param text, Represents text conforming the Content of a Document.
         * @param lang, Represents the text language of the Document.
         * */
        public Document (String ttl, String auth, String text, String lang) throws IOException {
            title = ttl;
            author = auth;
            language = lang;
            cont = new Content(text, lang);
            password = null;
        }

    //Setters
        /**
         * @brief Set the Author of a Document.
         * @param auth, String that will be allocated to the Author of this Document.
         * */
        public void setAuthor(String auth){
            author = auth;
        }

        /**
         * @brief Set the Title of a Document.
         * @param titl, String that will be allocated to the Title of this Document.
         * */
        public void setTitle(String titl){
            title = titl;
        }

        /**
         * @brief Set the Content of a Document.
         * @param c, String that will be allocated to the Author of this Document.
         * */
        public void setContent(Content c){
            cont = c;
        }

        public void setLanguage(String lang){
            language = lang;
        }

        public void protectDocument(String passw) {
            password = passw;
        }

        //Getters
        /**
         * @brief Gets the Title of the Document.
         * @return title, String that represents the title of the document.
         * */
        public String getTitle(){
            return title;
        }

        /**
         * @brief Gets the Author of the Document.
         * @return title, String that represents the author of the document.
         * */
        public String getAuthor(){
            return author;
        }

        /**
         * @brief Gets the Content of the Document.
         * @return title, String that represents the content of the document.
         * */
        public String getContent(){
            return cont.getText();
        }

        public Content getContentInstance() {return cont;}

        /**
         * @brief Gets the Language of the Document.
         * @return title, String that represents the Language of the Document Content.
         * */
        public String getLanguage() {
            return language;
        }

        public HashMap<String, Integer> contentSearch() {
            return cont.getVector();
        }

    //Consultants
        /**
         * @brief Check if Document is protected.
         * @return title, String that represents the content of the document.
         * */
        public boolean isProtected(){
            return (password!=null);
        }
}

