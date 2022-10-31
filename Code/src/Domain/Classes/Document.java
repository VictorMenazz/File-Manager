package Code.src.Domain.Classes;

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
         * */
        public Document(String ttl, String auth, Content c){
            title = ttl;
            author = auth;
            cont = c;
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
        public Content getContent(){
            return cont;
        }

        //Consultants

}

