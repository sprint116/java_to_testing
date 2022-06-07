package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDataToMinePageTest extends TestBase{
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().mainPage();
    if (app.db().contacts().size() == 0){
      app.contact().create(app.cd);
      app.goTo().mainPage();
    }
  }
//
  @Test
  public void testContactPhones(){
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactIngoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactIngoFromEditForm)));
    assertThat(contact.getAddress(), equalTo(contactIngoFromEditForm.getAddress()));
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactIngoFromEditForm)));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s)-> ! s.equals(""))
            .collect(Collectors.joining("\n"));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobile(), contact.getWorkPhone())
            .stream().filter((s)-> ! s.equals(""))
            .map(ContactDataToMinePageTest::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone){
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
