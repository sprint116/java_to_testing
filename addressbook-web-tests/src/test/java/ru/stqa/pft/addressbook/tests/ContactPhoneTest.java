package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactPhoneTest extends TestBase{
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().mainPage();
    if (app.contact().all().size() == 0) {
      app.contact().create(app.cd);
      app.goTo().mainPage();
    }
  }

  @Test
  public void testContactPhones(){
    ContactData contact = app.contact().all().iterator().next();
    //ContactData contactIngoFromEditForm = app.contact().contactIngoFromeEditForm(contact);
  }
}
