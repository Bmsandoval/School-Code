package com.example.sandman.xml_reader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import android.util.Log;

/**
 * Created by Sandman on 11/16/2014.
 */
public class parser extends DefaultHandler{
    private ArrayList<Planet> pList;
    private int currPlanet = 0;
    public parser() {
        super();
        pList = new ArrayList<Planet>();
    }

    public void startDocument () { Log.i("DataHandler", "Start of XML document"); }

    public void endDocument () { Log.i("DataHandler", "End of XML document"); }

    public void startElement (String uri, String name, String qName, Attributes atts) {
        if(qName.equals("planet") || qName.equals("dwarfPlanet")) {
            currPlanet++;
            pList.add(new Planet(atts.getValue("name"), atts.getValue("moonCount"), atts.getValue("distanceFromSun"), atts.getValue("diameterKm")));
            String temp = atts.getValue("name") + ", " + atts.getValue("moonCount") + ", " + atts.getValue("distanceFromSun") + ", " + atts.getValue("diameterKm");
            Log.i("Planet", temp);
        }
        else if(qName.equals("satellite")) {
            pList.get(currPlanet-1).addSat(atts.getValue("name"), atts.getValue("diameterKm"));
            String temp = atts.getValue("name") + ", " + atts.getValue("diameterKm");
            Log.i("satellite", temp);
        }
    }

    public void endElement (String uri, String name, String qName){}

    public String getData()
    {
        //take care of SAX, input and parsing errors
        try
        {
            //set the parsing driver
            System.setProperty("org.xml.sax.driver","org.xmlpull.v1.sax2.Driver");
            //create a parser
            SAXParserFactory parseFactory = SAXParserFactory.newInstance();
            SAXParser xmlParser = parseFactory.newSAXParser();
            //get an XML reader
            XMLReader xmlIn = xmlParser.getXMLReader();
            //instruct the app to use this object as the handler
            xmlIn.setContentHandler(this);
            //provide the name and location of the XML file **ALTER THIS FOR YOUR FILE**
            URL xmlURL = new URL("http://universe.tc.uvu.edu/cs3680/project/p6/planets.xml");
            //open the connection and get an input stream
            URLConnection xmlConn = xmlURL.openConnection();
            InputStreamReader xmlStream = new InputStreamReader(xmlConn.getInputStream());
            //build a buffered reader
            BufferedReader xmlBuff = new BufferedReader(xmlStream);
            //parse the data
            xmlIn.parse(new InputSource(xmlBuff));
        }
        catch(SAXException se) { Log.e("AndroidTestsActivity",
                "SAX Error " + se.getMessage()); }
        catch(IOException ie) { Log.e("AndroidTestsActivity",
                "Input Error " + ie.getMessage()); }
        catch(Exception oe) { Log.e("AndroidTestsActivity",
                "Unspecified Error " + oe.getMessage()); }
        //return the parsed product list
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< pList.size(); i++) {
            sb.append(pList.get(i).toString());
        }
        return sb.toString();
    }
}
