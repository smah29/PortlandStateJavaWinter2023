package edu.pdx.cs410J.smahato;

import edu.pdx.cs410J.AirlineDumper;
import edu.pdx.cs410J.smahato.exception.XMLException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

import static edu.pdx.cs410J.smahato.constants.XMLConstants.*;

/**
 * <code>XmlDumper</code> class for Project 4 dumps airline object in XML file.
 */
public class XmlDumper extends AirlineXmlHelper implements AirlineDumper<Airline> {
  private final PrintWriter pw;

  /**
   * Constructor for XmlDumper class.
   *
   * @param pw PrintWriter object
   */
  public XmlDumper(PrintWriter pw) {
    this.pw = pw;
  }

  /**
   * Dumps the contents of the airline object in XML file.
   *
   * @param airline Airline object to be dumped
   */
  @Override
  public void dump(Airline airline) throws IOException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setValidating(true);
    DocumentBuilder builder;
    try {
      builder = factory.newDocumentBuilder();
    } catch (ParserConfigurationException e) {
      throw new XMLException("Error while creating XML document builder");
    }
    builder.setErrorHandler(this);
    builder.setEntityResolver(this);
    DOMImplementation dom = builder.getDOMImplementation();
    DocumentType docType = dom.createDocumentType(AIRLINE, PUBLIC_ID, SYSTEM_ID);
    Document doc = dom.createDocument(null, AIRLINE, docType);

    updateDocWithAirlineDetails(airline, doc);

    writeXMLDoc(doc);
  }

  private static void updateDocWithAirlineDetails(Airline airline, Document doc) {
    Element airlineElement = doc.getDocumentElement();

    Element airlineName = doc.createElement(AIRLINE_NAME);
    airlineElement.appendChild(airlineName);
    airlineName.appendChild(doc.createTextNode(airline.getName()));

    for (Flight flight : airline.getFlights()) {

      Element flightElement = doc.createElement(FLIGHT);
      airlineElement.appendChild(flightElement);

      Element flightNumber = doc.createElement(FLIGHT_NUMBER);
      flightElement.appendChild(flightNumber);
      flightNumber.appendChild(doc.createTextNode(String.valueOf(flight.getNumber())));

      Element src = doc.createElement(SOURCE);
      flightElement.appendChild(src);
      src.appendChild(doc.createTextNode(flight.getSource()));

      Element depart = doc.createElement(DEPARTURE);
      flightElement.appendChild(depart);
      depart.appendChild(getDateElement(doc, flight.getDeparture()));
      depart.appendChild(getTimeElement(doc, flight.getDeparture()));

      Element dest = doc.createElement(DESTINATION);
      flightElement.appendChild(dest);
      dest.appendChild(doc.createTextNode(flight.getDestination()));

      Element arrive = doc.createElement(ARRIVAL);
      flightElement.appendChild(arrive);
      arrive.appendChild(getDateElement(doc, flight.getArrival()));
      arrive.appendChild(getTimeElement(doc, flight.getArrival()));
    }
  }

  private void writeXMLDoc(Document doc) {
    Source src = new DOMSource(doc);
    Result res = new StreamResult(this.pw);

    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer;
    try {
      transformer = transformerFactory.newTransformer();
    } catch (TransformerConfigurationException e) {
      throw new XMLException("Error while creating XML transformer");
    }
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, SYSTEM_ID);
    transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, PUBLIC_ID);
    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
    transformer.setOutputProperty(OutputKeys.ENCODING, "ASCII");
    try {
      transformer.transform(src, res);
    } catch (TransformerException e) {
      throw new XMLException("Error while transforming XML document");
    }
  }

  private static Element getDateElement(Document doc, Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    Element dateElement = doc.createElement(DATE);

    int month = cal.get(Calendar.MONTH);
    int day = cal.get(Calendar.DATE);
    int year = cal.get(Calendar.YEAR);

    dateElement.setAttribute(DAY, String.valueOf(day));
    dateElement.setAttribute(MONTH, String.valueOf(month));
    dateElement.setAttribute(YEAR, String.valueOf(year));
    return dateElement;
  }

  private static Element getTimeElement(Document doc, Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    Element time = doc.createElement(TIME);

    int hour = cal.get(Calendar.HOUR_OF_DAY);
    int minute = cal.get(Calendar.MINUTE);

    time.setAttribute(HOUR, String.valueOf(hour));
    time.setAttribute(MINUTE, String.valueOf(minute));
    return time;
  }

}
