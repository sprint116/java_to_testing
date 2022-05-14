package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().returnToMainPage();
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().createContact(app.cd);
    app.getNavigationHelper().returnToMainPage();
    int after = app.getContactHelper().getContactCount();
    app.getNavigationHelper().logout();
    Assert.assertEquals(after, ++before);
  }
}
