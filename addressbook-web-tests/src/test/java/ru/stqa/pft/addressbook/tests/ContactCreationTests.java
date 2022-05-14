package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().returnToMainPage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().createContact(app.cd);
    app.getNavigationHelper().returnToMainPage();
    List<ContactData> after = app.getContactHelper().getContactList();
    app.getNavigationHelper().logout();
    Assert.assertEquals(after.size(), before.size() + 1);
  }
}
