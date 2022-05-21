package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().MainPage();
    if (app.contact().all().size() == 0) {
      app.contact().create(app.cd);
      app.goTo().MainPage();
    }
  }

  @Test//(enabled = false)
  public void testContactModification() {
    Set<ContactData> before = app.contact().all();
    ContactData modifyContact = before.iterator().next();
    app.contact().modify(app.cd.withId(modifyContact.getId()));
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifyContact);
    before.add(app.cd);
    Assert.assertEquals(before, after);
  }


}
