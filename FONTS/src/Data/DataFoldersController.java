package FONTS.src.Data;

import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Attr;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.dom.DOMSource;
import java.io.*;
import java.util.ArrayList;

public class DataFoldersController {

    /**
     * Saves physically the serialized Folder System in JSON format.
     * @param docs, Representing the Documents Serialized.
     * @return A Boolean, that indicates if the process was correct.
     */
    public Boolean saveFolder(String docs){
        String basicPath = "data/Folders/rootFolder";
        File folder = new File(basicPath);
        if (!folder.exists()) folder.mkdir();
        File[] folders = folder.listFiles();
        String identifier = String.format("%04d", folders.length);
        String defPath = basicPath + File.separator + identifier + "rootFolder.json";
        return writeDoc(defPath, docs);
    }


    /**
     * Reads the physic the serialized Folder System in JSON format.
     * @return A String, representing the last log saved.
     */
    public String getFoldersSerialized() {
        String basicPath = "data/Folders/rootFolder";
        File folder = new File(basicPath);
        if (!folder.exists()) folder.mkdir();
        File[] folders = folder.listFiles();
        String identifier = String.format("%04d", folders.length - 1);
        String result = readDoc(basicPath + File.separator + identifier + "rootFolder.json");
        return result;
    }

    /**
     * [Private] Writes the physic file of the serialized Folder System in JSON format.
     * @param path, A String identifying the future physic location of the resource.
     * @param cont, A String identifying the content that must be written.
     * @return A Boolean, that indicates if the process was correct.
     */
    private Boolean writeDoc(String path, String cont) {
        FileWriter file = null;
        try {
            file = new FileWriter(path);
            PrintWriter documentWr = new PrintWriter(file);
            documentWr.print(cont);
            documentWr.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * [Private] Reads the physic file of the serialized Folder System in JSON format.
     * @param path, A String identifying the physic location of the resource.
     * @return A Boolean, that indicates if the process was correct.
     */
    private String readDoc(String path) {
        FileReader reader = null;
        String result = "";
        try {
            reader = new FileReader(path);
            BufferedReader br = new BufferedReader(reader);
            String iterator = "";
            while ((iterator = br.readLine()) != null) {
                result += iterator;
            }
        } catch (FileNotFoundException e) {
            return result;
        } catch (IOException ex) {
            return result;
        }
        return result;
    }

    /**
     * Reads a Document physic File and loads it in Domain.
     * @param path, Representing the physic path to read the Doc.
     * @param docT, Representing the type of Document it is.
     * @return A Boolean indicating if the process finshed well.
     */
    public ArrayList<String> importDocument(String path, String docT) {
        String title = null;
        String auth = null;
        String cont = null;
        ArrayList<String> result = new ArrayList<String>();
        if (docT.equals("xml") || docT.equals("txt")) {
            if (docT.equals("xml")) {
                String doc = readDoc(path);
                //Parsing
                DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
                try {
                    DocumentBuilder builder = fact.newDocumentBuilder();
                    Document document = null;
                    try {
                        document = builder.parse(new InputSource(new StringReader(doc)));
                    } catch (SAXException e) {
                        return result;
                    } catch (IOException e) {
                        return result;
                    }
                    Element root = document.getDocumentElement();
                    auth = getAtt("Author", root);
                    title = getAtt("Title", root);
                    cont = getAtt("Content", root);
                } catch (ParserConfigurationException e) {
                    return result;
                }
            } else {
                FileReader reader = null;
                try {
                    reader = new FileReader(path);
                    BufferedReader br = new BufferedReader(reader);
                    auth = br.readLine();
                    title = br.readLine();
                    cont = "";
                    String iterator = "";
                    while ((iterator = br.readLine()) != null) {
                        cont += iterator;
                    }
                } catch (FileNotFoundException e) {
                    return result;
                } catch (IOException ex) {
                    return result;
                }
            }
            result.add(auth);
            result.add(title);
            result.add(cont);
            return result;
        } else if (docT.equals("json")){
            String doc = readDoc(path);
            result.add(doc);
            return result;
        }
        else return result;
    }

    /**
     * Gets an Attribute from the xml file
     * @param tagName, A String identifying the tag.
     * @param element, An Element identifying the Parent Tag.
     * @return A String, identifying the content of the tag.
     */
    private String getAtt(String tagName, Element element) {
        NodeList list = element.getElementsByTagName(tagName);
        if (list != null && list.getLength() > 0) {
            NodeList subList = list.item(0).getChildNodes();
            if (subList != null && subList.getLength() > 0) {
                return subList.item(0).getNodeValue();
            }
        }
        return null;
    }

    /**
     * Writes a Document onto a physic File out of the System.
     * @param path, Representing the physic path to write the Doc.
     * @param Content, Representing the Content to write onto the new physic File.
     * @return A Boolean indicating if the process finshed well.
     */
    public Boolean exportDocument(String path, String author, String title, String Content, String docT) {
        path += File.separator + title + '.' + docT;
        if (docT.equals("xml") || docT.equals("txt")) {
            if (docT.equals("xml")) {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = null;
                try {
                    docBuilder = docFactory.newDocumentBuilder();
                } catch (ParserConfigurationException e) {
                    return false;
                }

                Document doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("Document");
                doc.appendChild(rootElement);

                //Author tag
                Element authorE = doc.createElement("Author");
                rootElement.appendChild(authorE);

                Attr attr = doc.createAttribute("Author");
                attr.setValue(author);
                authorE.setAttributeNode(attr);

                //Title tag
                Element titleE = doc.createElement("Title");
                rootElement.appendChild(titleE);

                Attr attrT = doc.createAttribute("Title");
                attrT.setValue(title);
                titleE.setAttributeNode(attrT);

                //Content tag
                Element ContentE = doc.createElement("Content");
                rootElement.appendChild(ContentE);

                Attr attrC = doc.createAttribute("Content");
                attrC.setValue(Content);
                ContentE.setAttributeNode(attrC);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = null;
                try {
                    transformer = transformerFactory.newTransformer();
                } catch (TransformerConfigurationException e) {
                    return false;
                }
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(path));
                try {
                    transformer.transform(source, result);
                } catch (TransformerException e) {
                    return false;
                }
            } else {
                FileWriter file = null;
                try {
                    file = new FileWriter(path);
                    PrintWriter documentWr = new PrintWriter(file);
                    documentWr.println(author);
                    documentWr.println(title);
                    documentWr.print(Content);
                    documentWr.close();
                } catch (IOException e) {
                    return false;
                }
                return true;
            }
        } else if(docT.equals("json")){
            //case JSON
            return writeDoc(path, Content);
        }
        return false;
    }
}
