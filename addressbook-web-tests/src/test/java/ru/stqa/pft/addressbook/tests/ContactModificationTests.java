package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification() {
    app.getNavigationHelper().returnToMainPage();
    if (app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(app.cd);
      app.getNavigationHelper().returnToMainPage();
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    int randomGroup = (int) (Math.random() * before.size());
    app.getContactHelper().selectContact(randomGroup);
    app.getContactHelper().changeModificationContact();
    app.getContactHelper().fillContactsForm(app.cd, false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToMainPage();
    List<ContactData> after = app.getContactHelper().getContactList();
    app.getNavigationHelper().logout();
    Assert.assertEquals(after.size(), before.size() + 1);
  }
}
