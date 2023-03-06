package edu.pdx.cs410J.smahato;

import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.smahato.exception.AirportCodeException;
import edu.pdx.cs410J.smahato.exception.XMLException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.Reader;
import java.time.DateTimeException;
import java.util.Calendar;

import static edu.pdx.cs410J.smahato.constants.DateFormatConstants.TWELVE_HOUR_TIME_FORMAT;
import static edu.pdx.cs410J.smahato.constants.ErrorMessages.FLIGHT_NUMBER_MUST_BE_AN_INTEGER;
import static edu.pdx.cs410J.smahato.constants.XMLConstants.*;

/**
 * <code>XmlParser</code> class for Project 5 parses XML and creates airline object.
 */
public class XmlParser extends AirlineXmlHelper implements AirlineParser<Airline> {
  private final Reader reader;

  /**
   * Creates a new <code>XmlParser</code> object.
   *
   * @param reader The reader from which the airline will be read
   */
  public XmlParser(Reader reader) {
    this.reader = reader;
  }

  /**
   * Parses the XML and creates airline object.
   *
   * @return Airline object
   * @throws ParserException If there is an error while parsing the XML
   */
  @Override
  public Airline parse() throws ParserException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setValidating(true);
    factory.setIgnoringElementContentWhitespace(true);
    factory.setIgnoringComments(true);
    DocumentBuilder builder;
    Document doc;
    try {
      builder = factory.newDocumentBuilder();
    } catch (ParserConfigurationException e) {
      throw new XMLException("Error while creating XML document builder");
    }
    builder.setErrorHandler(this);
    builder.setEntityResolver(this);
    try {
      doc = builder.parse(new InputSource(this.reader));
    } catch (SAXException e) {
      throw new XMLException("Error while parsing XML document");
    } catch (IOException e) {
      throw new XMLException("Error while reading XML document");
    }
    Element root = (Element) doc.getChildNodes().item(1);
    return getAirline(root);
  }

  private Airline getAirline(Element root) {
    NodeList entries = root.getChildNodes();
    Airline airline = null;
    for (int i = 0; i < entries.getLength(); i++) {
      Node node = entries.item(i);
      Element element = (Element) node;
      switch (element.getNodeName()) {
        case AIRLINE_NAME:
          airline = new Airline(extractString(element));
          break;
        case FLIGHT:
          try {
            Flight flight = getFlight(element);
            airline.addFlight(flight);
          } catch (NumberFormatException e) {
            throw new XMLException(FLIGHT_NUMBER_MUST_BE_AN_INTEGER);
          } catch (DateTimeException | AirportCodeException | NullPointerException e) {
            throw new XMLException(e.getMessage());
          }
          break;
      }
    }
    return airline;
  }

  private Flight getFlight(Element root) {
    NodeList elements = root.getChildNodes();
    String flightNumber = null, src = null, dest = null, depatureString = null, arrivalString = null;
    for (int i = 0; i < elements.getLength(); i++) {
      Node node = elements.item(i);
      Element element = (Element) node;
      switch (element.getNodeName()) {
        case FLIGHT_NUMBER:
          flightNumber = extractString(element);
          break;
        case SOURCE:
          src = extractString(element);
          break;
        case DESTINATION:
          dest = extractString(element);
          break;
        case DEPARTURE:
          depatureString = extractDateTimeString(element);
          break;
        case ARRIVAL:
          arrivalString = extractDateTimeString(element);
          break;
      }
    }
    return new Flight(flightNumber, src, dest, depatureString, arrivalString);
  }

  private String extractDateTimeString(Element root) {
    NodeList elements = root.getChildNodes();
    Calendar cal = Calendar.getInstance();
    for (int i = 0; i < elements.getLength(); i++) {
      Node node = elements.item(i);
      Element element = (Element) node;
      switch (element.getNodeName()) {
        case DATE:
          setDate(cal, element);
          break;
        case TIME:
          setTime(cal, element);
          break;
      }
    }
    return TWELVE_HOUR_TIME_FORMAT.format(cal.getTime()).toLowerCase();
  }

  private static void setTime(Calendar cal, Element element) {
    try {
      cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(element.getAttribute(HOUR)));
    } catch (NumberFormatException ex) {
      throw new XMLException(element.getAttribute(HOUR) + " " + HOUR + " is not valid");
    }
    try {
      cal.set(Calendar.MINUTE, Integer.parseInt(element.getAttribute(MINUTE)));
    } catch (NumberFormatException ex) {
      throw new XMLException(element.getAttribute(MINUTE) + " " + MINUTE + " is not valid");
    }
  }

  private static void setDate(Calendar cal, Element element) {
    try {
      cal.set(Calendar.DATE, Integer.parseInt(element.getAttribute(DAY)));
    } catch (NumberFormatException ex) {
      throw new XMLException(element.getAttribute(DAY) + " " + DAY + " is not valid");
    }
    try {
      cal.set(Calendar.MONTH, Integer.parseInt(element.getAttribute(MONTH)));
    } catch (NumberFormatException ex) {
      throw new XMLException(element.getAttribute(MONTH) + " " + MONTH + " is not valid");
    }
    try {
      cal.set(Calendar.YEAR, Integer.parseInt(element.getAttribute(YEAR)));
    } catch (NumberFormatException ex) {
      throw new XMLException(element.getAttribute(YEAR) + " " + YEAR + " is not valid");
    }
  }

  private static String extractString(Node node) {
    return node.getFirstChild().getNodeValue();
  }

}
