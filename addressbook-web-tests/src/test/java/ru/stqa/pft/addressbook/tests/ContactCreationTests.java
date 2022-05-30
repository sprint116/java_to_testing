package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    List<Object[]> list = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.csv"));
    String line = reader.readLine();
    while (line !=null){
      String[] split = line.split(";");
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

  @Test(dataProvider = "validContacts")
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
