package Code.src.Domain.Controllers.Stubs;

import Code.src.Domain.Classes.Content;
import Code.src.Domain.Classes.Document;

import java.io.IOException;

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

    public Content getContent(){
        return (new ContentStub());
    }

    public String getLanguage() {
        return "ENG";
    }

    public boolean isProtected(){
        return false;
    }
}

