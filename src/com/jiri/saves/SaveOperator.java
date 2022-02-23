package com.jiri.saves;


import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import com.jiri.entities.Player;
import com.jiri.entities.items.Item;
import org.xml.sax.*;
import org.w3c.dom.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class SaveOperator {
    public Player readXML(String fileName) {
        Document dom;
        // Make an  instance of the DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // use the factory to take an instance of the document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // parse using the builder to get the DOM mapping of the
            // XML file
            dom = db.parse(fileName);

            Element doc = dom.getDocumentElement();

            return true;

        } catch (ParserConfigurationException | SAXException pce) {
            System.out.println(pce.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }

        return false;
    }

    public static void saveToXML(String fileName, Player player) {
        Document dom;

        // instance of a DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // use factory to get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // create instance of DOM
            dom = db.newDocument();
            // create the root element
            Element rootEle = dom.createElement("db");
            // create data elements and place them under root
            Element playerElement = dom.createElement("player");
            rootEle.appendChild(playerElement);
            Element health = dom.createElement("health");
            health.appendChild(dom.createTextNode(String.valueOf(player.health)));
            playerElement.appendChild(health);
            Element fireRate = dom.createElement("fire_rate");
            fireRate.appendChild(dom.createTextNode(String.valueOf(player.fireRate)));
            playerElement.appendChild(fireRate);
            Element speed = dom.createElement("speed");
            speed.appendChild(dom.createTextNode(String.valueOf(player.speed)));
            playerElement.appendChild(speed);
            Element enableGravity = dom.createElement("enableGravity");
            enableGravity.appendChild(dom.createTextNode(String.valueOf(player.enableGravity)));
            playerElement.appendChild(enableGravity);
            Element gravity = dom.createElement("gravity");
            gravity.appendChild(dom.createTextNode(String.valueOf(player.gravity)));
            playerElement.appendChild(gravity);
            Element level = dom.createElement("level");
            level.appendChild(dom.createTextNode(player.currentLevel.name));
            playerElement.appendChild(level);
            Element backpack = dom.createElement("backpack");
            for (Item item : player.backpack.items.keySet()) {
                Element itemElement = dom.createElement("item");
                Element name = dom.createElement("name");
                itemElement.appendChild(name);
                name.appendChild(dom.createTextNode(item.name));
                Element count = dom.createElement("count");
                itemElement.appendChild(count);
                count.appendChild(dom.createTextNode(String.valueOf(player.backpack.items.get(item))));
                backpack.appendChild(itemElement);
            }
            playerElement.appendChild(backpack);


            dom.appendChild(rootEle);

            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "save.dtd");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                // send DOM to file
                tr.transform(new DOMSource(dom),
                        new StreamResult(new FileOutputStream(fileName)));

            } catch (TransformerException | IOException te) {
                System.out.println(te.getMessage());
            }
        } catch (ParserConfigurationException pce) {
            System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
        }
    }

}
