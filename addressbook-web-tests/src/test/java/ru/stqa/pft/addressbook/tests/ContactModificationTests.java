package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification() {
    app.getNavigationHelper().returnToMainPage();
    int before = app.getContactHelper().getContactCount();
    if (app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(app.cd);
      before++;
      app.getNavigationHelper().returnToMainPage();
    }
    int randomGroup = (int) (Math.random() * before);
    app.getContactHelper().selectContact(randomGroup);
    app.getContactHelper().changeModificationContact();
    app.getContactHelper().fillContactsForm(app.cd, false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToMainPage();
    int after = app.getContactHelper().getContactCount();
    app.getNavigationHelper().logout();
    Assert.assertEquals(after, before);
  }
}
