package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactAddToGroupTest extends TestBase{
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0){
      app.contact().create(app.cd);
      app.goTo().mainPage();
    }
    if (app.db().groups().size() == 0) {
      app.group().create(app.gd);
    }
  }

  @Test
  public void testContactAddToGroup() {
    ContactData selectContact = app.db().contacts().iterator().next();

    app.contact().addToGroup(selectContact);
  }
}
