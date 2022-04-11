package com.jiri.saves;


import com.jiri.entities.Player;
import com.jiri.level.CompanyFight;
import com.jiri.level.Level;
import com.jiri.level.Streamer;
import com.jiri.level.StreetFight;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class SaveOperator {
    public static Level getLevelFromName(String levelName, Streamer streamer) {
        Level level = null;
        try {
            switch (levelName) {
                case "Escape" -> level = new CompanyFight(streamer.width, streamer.height, streamer);
                case "Street Fight" -> level = new StreetFight(streamer.width, streamer.height, streamer);
            }
        } catch (Level.InvalidTemplateMap e) {
            e.printStackTrace();
        }
        return level;
    }

    /**
     * @param fileName file name
     * @param streamer streamer to load to
     * @return player
     */
    public static Player loadSave(String fileName, Streamer streamer) {
        File fXmlFile = new File(fileName);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = null;
        try {
            doc = dBuilder.parse(fXmlFile);
        } catch (SAXException | IOException e) {
            //e.printStackTrace();
            return null;
        }
        doc.getDocumentElement().normalize();

        float health = Float.parseFloat(doc.getElementsByTagName("health").item(0).getTextContent());
        int fireRate = Integer.parseInt(doc.getElementsByTagName("fire_rate").item(0).getTextContent());
        float speed = Float.parseFloat(doc.getElementsByTagName("speed").item(0).getTextContent());
        boolean enableGravity = Boolean.parseBoolean(doc.getElementsByTagName("enableGravity").item(0).getTextContent());
        float gravity = Float.parseFloat(doc.getElementsByTagName("gravity").item(0).getTextContent());
        String levelName = doc.getElementsByTagName("level").item(0).getTextContent();
        Level level = getLevelFromName(levelName, streamer);
        Player player = new Player(level, (int) health, speed, fireRate);
        player.enableGravity = enableGravity;
        player.gravity = gravity;
        return player;

    }

    /**
     * @param fileName file name
     * @param player   player to save
     */
    public static void saveGame(String fileName, Player player) {
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
            for (String item : player.backpack.items.keySet()) {
                Element itemElement = dom.createElement("item");
                Element name = dom.createElement("name");
                itemElement.appendChild(name);
                name.appendChild(dom.createTextNode(player.backpack.items.get(item).first.itemName));
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
