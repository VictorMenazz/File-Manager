package FONTS.src.Domain.Controllers.Stubs;

import FONTS.src.Domain.Classes.Document;

/**
 * @file DocumentStub.java
 * @brief Stub <em>Document</em>
 */

/**
 * @brief Stub of Document class
 *
 * @author Marc Navarro Acosta
 */

public class DocumentStub extends Document {

    public DocumentStub() {
        super("TitleTest","AuthorTest", new ContentStub(), "ENG");
    }

    public String getTitle(){
        return "TitleTest";
    }

    public String getAuthor(){
        return "AuthorTest";
    }

    public String getContent(){
        return "Simple Content Test";
    }

    public String getLanguage() {
        return "ENG";
    }

    public boolean isProtected(){
        return false;
    }
}

