package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.xml"));
    String xml = "";
    String line = reader.readLine();
    while (line !=null){
      xml += line;
      line = reader.readLine();
    }
    XStream xStream = new XStream();//xml
    xStream.processAnnotations(ContactData.class);
    List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);//xml
    return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
  }

  @DataProvider
  public Iterator<Object[]> validGroupsFromJson() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
    String json = "";
    String line = reader.readLine();
    while (line != null){
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<ContactData> groups = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
    return groups.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
  }

  @Test(dataProvider = "validGroupsFromJson")
  public void testContactCreation(ContactData contact) {
    app.goTo().mainPage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/1.jpg");
    app.contact().create(contact.withPhoto(photo));
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testBadContactCreation() {
    app.goTo().mainPage();
    Contacts before = app.contact().all();
    app.contact().create(app.cd.withLastName("test2'"));
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }
}
