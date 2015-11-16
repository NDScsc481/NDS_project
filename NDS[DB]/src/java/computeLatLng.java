package java;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

public class computeLatLng {
	  public static LatLng getLatLongPositions(final String address) throws Exception
	  {
	    int responseCode = 0;
	    String api = "http://maps.googleapis.com/maps/api/geocode/xml?address=" + URLEncoder.encode(address, "UTF-8") + "&sensor=true";
	    URL url = new URL(api);
	    HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
	    httpConnection.connect();
	    responseCode = httpConnection.getResponseCode();
	    if(responseCode == 200)
	    {
	    	System.out.println("in computeLatLng responsecode 200");
	      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();;
	      Document document = builder.parse(httpConnection.getInputStream());
	      XPathFactory xPathfactory = XPathFactory.newInstance();
	      XPath xpath = xPathfactory.newXPath();
	      XPathExpression expr = xpath.compile("/GeocodeResponse/status");
	      String status = (String)expr.evaluate(document, XPathConstants.STRING);
	      if(status.equals("OK"))
	      {
		    	System.out.println("in computeLatLng status OK");

	         expr = xpath.compile("//geometry/location/lat");
	         expr = xpath.compile("//geometry/location/lat");
	         String latitude = (String)expr.evaluate(document, XPathConstants.STRING);
	         expr = xpath.compile("//geometry/location/lng");
	         String longitude = (String)expr.evaluate(document, XPathConstants.STRING);

	         double dLat = Double.parseDouble(latitude);//double(latitude);
	         double dLng = Double.parseDouble(longitude);
	         LatLng newCoor = new LatLng(dLat, dLng);
	         System.out.print("hello from computerLatLng   Lat string: " + latitude + " lat double: " + newCoor.lat);
	         return newCoor;
	        // return new String[] {latitude, longitude};
	      }
	      else
	      {
		    	System.out.println("in computeLatLng exception");

	         throw new Exception("Error from the API - response status: "+status);
	      }
	    }
	    return null;
	  }

}
