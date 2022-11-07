package Code.src.Domain.Controllers.Stubs;

import Code.src.Domain.Classes.Content;

import java.io.IOException;

public class DocumentStub {

    private String title;

    private String author;

    private Content cont;

    private String language;

    private String password;

    enum DOC_TYPE{
        XML,
        PLAIN_TEXT
    }

    private DOC_TYPE format;
/*
    public Document(){
        retun
    }
*/
    public void setAuthor(String auth){
        author = auth;
    }

    public void setTitle(String titl){
        title = titl;
    }


    public void setContent(Content c){
        cont = c;
    }

    public void setLanguage(String lang){
        language = lang;
    }

    public void protectDocument(String passw) {
        password = passw;
    }

    public String getTitle(){
        return title;
    }

    public String getAuthor(){
        return author;
    }

    public Content getContent(){
        return cont;
    }

    public String getLanguage() {
        return language;
    }

}
