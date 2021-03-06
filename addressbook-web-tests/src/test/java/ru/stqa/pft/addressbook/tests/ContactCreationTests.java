package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsFromCsv() throws IOException {
    List<Object[]> list = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/genContactsFile/contacts.csv")))){
      String line =reader.readLine();
      while (line != null){
        String[] split =line.split(";");
        list.add(new Object[]{new ContactData()
                .withFirstName(split[0])
                .withLastName(split[1])
                .withNickname(split[2])
                .withAddress(split[3])
                .withHomePhone(split[4])
                .withMobile(split[5])
                .withWorkPhone(split[6])
                .withEmail(split[7])
                .withBirthdayDay(split[8])
                .withBirthdayMonth(split[9])
                .withBirthdayYear(split[10])
                .withAddress2(split[11])
        });
        line = reader.readLine();
      }
      return list.iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/genContactsFile/contacts.xml")))){
      String xml = "";
      String line = reader.readLine();
      while (line !=null){
        xml += line;
        line = reader.readLine();
      }
      XStream xStream = new XStream();//xml
      xStream.allowTypes(new Class[]{ContactData.class});
      xStream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);//xml
      return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try(BufferedReader reader = new BufferedReader(new FileReader(new File("./src/test/resources/genContactsFile/contacts.json")))){
      String json = "";
      String line = reader.readLine();
      while (line != null){
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      Collection<ContactData> contacts = gson.fromJson(json, new TypeToken<Collection<ContactData>>() {}.getType());
      return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.group().create(app.gd);
    }
  }

  @Test(dataProvider = "validContactsFromCsv")
  public void testContactCreation(ContactData contact) {
    Groups groups = app.db().groups();
    ContactData newContact = app.cd;
    app.goTo().mainPage();
    Contacts before = app.db().contacts();
    app.contact().create(contact.inGroup(groups.iterator().next()));
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    verifyContactListInUi();
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
