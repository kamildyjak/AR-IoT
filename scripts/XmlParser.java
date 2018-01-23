package project;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class XmlParser implements Parser {

    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;
    private Document doc;

    public XmlParser() {
        dbFactory = DocumentBuilderFactory.newInstance();
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void setDocument(String input) {
        try {
            doc = dBuilder.parse(new InputSource(new ByteArrayInputStream(input.getBytes("utf-8"))));
            doc.getDocumentElement().normalize();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Disk> getDisksInfo() {
        ArrayList<Disk> result = new ArrayList<>();

        NodeList disks = doc.getElementsByTagName("disk");

        for (int index = 0; index < disks.getLength(); index++) {

            Node diskInfo = disks.item(index);

            if (diskInfo.getNodeType() == Node.ELEMENT_NODE) {

                Element eDisk = (Element) diskInfo;
                Disk disk = new Disk();
                String val;

                disk.setId(Integer.valueOf(eDisk.getAttribute("id")));

                val = eDisk.getElementsByTagName("connected").item(0).getTextContent();
                disk.addInformation("Connected", val);

                if (!eDisk.getElementsByTagName("connected").item(0).getTextContent().equals("0")) {

                    val = eDisk.getElementsByTagName("name").item(0).getTextContent();
                    disk.addInformation("Name", val);

                    val = eDisk.getElementsByTagName("temp").item(0).getTextContent();
                    disk.addInformation("Temp", val);

                    val = eDisk.getElementsByTagName("size").item(0).getTextContent();
                    disk.addInformation("Size", val);

                    val = eDisk.getElementsByTagName("healthy").item(0).getTextContent();
                    disk.addInformation("Healthy", val);

                }
                result.add(disk);
            }
        }

        return result;
    }

    public ArrayList<Raid> getRaidsInfo() {
        ArrayList<Raid> result = new ArrayList<>();

        NodeList raids = doc.getElementsByTagName("raid");

        for (int index = 0; index < raids.getLength(); index++) {

            Node raidInfo = raids.item(index);

            if (raidInfo.getNodeType() == Node.ELEMENT_NODE) {

                Element eDisk = (Element) raidInfo;
                Raid raid = new Raid();
                String val;

                val = eDisk.getElementsByTagName("id").item(0).getTextContent();
                raid.setId(Integer.valueOf(val));

                val = eDisk.getElementsByTagName("level").item(0).getTextContent();
                raid.addInformation("Level", val);

                val = eDisk.getElementsByTagName("chunk_size").item(0).getTextContent();
                raid.addInformation("Chunk_size", val);

                val = eDisk.getElementsByTagName("size").item(0).getTextContent();
                raid.addInformation("Size", val);

                val = eDisk.getElementsByTagName("used_size").item(0).getTextContent();
                raid.addInformation("Used_size", val);

                val = eDisk.getElementsByTagName("state").item(0).getTextContent();
                raid.addInformation("State", val);

                val = eDisk.getElementsByTagName("num_of_active_disks").item(0).getTextContent();
                raid.addInformation("Num_of_active_disks", val);

                val = eDisk.getElementsByTagName("num_of_working_disks").item(0).getTextContent();
                raid.addInformation("Num_of_working_disks", val);

                result.add(raid);
            }
        }

        return result;
    }

    public ArrayList<Vol> getVolumesInfo() {
        ArrayList<Vol> result = new ArrayList<>();

        NodeList volumes = doc.getElementsByTagName("vol");

        for (int index = 0; index < volumes.getLength(); index++) {

            Node raidInfo = volumes.item(index);

            if (raidInfo.getNodeType() == Node.ELEMENT_NODE) {

                Element eDisk = (Element) raidInfo;
                Vol volume = new Vol();
                String val;

                volume.setId(Integer.valueOf(eDisk.getAttribute("id")));

                val = eDisk.getElementsByTagName("name").item(0).getTextContent();
                volume.addInformation("Name", val);

                val = eDisk.getElementsByTagName("unlocked").item(0).getTextContent();
                volume.addInformation("Unlocked", val);

                val = eDisk.getElementsByTagName("mounted").item(0).getTextContent();
                volume.addInformation("Mounted", val);

                val = eDisk.getElementsByTagName("size").item(0).getTextContent();
                volume.addInformation("Size", val);

                val = eDisk.getElementsByTagName("used_size").item(0).getTextContent();
                volume.addInformation("Used_size", val);

                val = eDisk.getElementsByTagName("raid_level").item(0).getTextContent();
                volume.addInformation("Raid_level", val);

                val = eDisk.getElementsByTagName("raid_state").item(0).getTextContent();
                volume.addInformation("Raid_state", val);

                result.add(volume);
            }
        }

        return result;
    }

    public HashMap<String, String> getDeviceInfo() {
        HashMap<String, String> result = new HashMap<>();

        NodeList volumes = doc.getElementsByTagName("vols");

        for (int index = 0; index < volumes.getLength(); index++) {

            Node raidInfo = volumes.item(index);

            if (raidInfo.getNodeType() == Node.ELEMENT_NODE) {

                Element eDisk = (Element) raidInfo;
                String val;

                val = eDisk.getElementsByTagName("total_size").item(0).getTextContent();
                result.put("Total_size", val);

                val = eDisk.getElementsByTagName("total_used_size").item(0).getTextContent();
                result.put("Total_used_size", val);

                val = eDisk.getElementsByTagName("total_unused_size").item(0).getTextContent();
                result.put("Total_unused_size", val);

            }
        }

        return result;
    }


}



