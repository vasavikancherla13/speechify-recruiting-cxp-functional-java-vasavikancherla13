package com.speechify;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;

public class Ssml {

    // Parses SSML string to DOM Node
    public static Node parseSSML(String ssml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document doc = factory.newDocumentBuilder()
                              .parse(new java.io.ByteArrayInputStream(ssml.getBytes()));
        doc.getDocumentElement().normalize();
        return doc.getDocumentElement();
    }

    // Recursively converts SSML node tree to plain text
    public static String ssmlNodeToText(Node node) {
        StringBuilder sb = new StringBuilder();

        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            switch (child.getNodeType()) {
                case Node.TEXT_NODE:
                    sb.append(child.getNodeValue());
                    break;
                case Node.ELEMENT_NODE:
                    sb.append(ssmlNodeToText(child)); // recurse
                    break;
                default:
                    break;
            }
        }

        return sb.toString();
    }
}
