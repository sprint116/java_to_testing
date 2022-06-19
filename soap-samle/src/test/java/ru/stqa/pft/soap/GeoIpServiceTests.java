package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

  @Test
  public void testMyId(){
    String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("46.0.228.207");
    assertEquals(geoIp, "<GeoIP><Country>RU</Country><State>65</State></GeoIP>");
  }
}