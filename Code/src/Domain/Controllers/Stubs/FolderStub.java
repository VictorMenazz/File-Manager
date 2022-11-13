package Code.src.Domain.Controllers.Stubs;

import Code.src.Domain.Classes.Folder;
import Code.src.Domain.Classes.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class FolderStub extends Folder {
    public FolderStub() {
        super(0, "rootFolder");
    }

    public String getName() {
        return "rootFolder";
    }

    public int getDocumentAmount() {
        return 2;
    }

    public HashMap<Pair<String, String>, HashMap<String,Integer>> getMapsDocs(){
        return null;
    }

    public HashMap<Pair<String, String>, ArrayList<String>> getAllContent(){
        return null;
    }


}
