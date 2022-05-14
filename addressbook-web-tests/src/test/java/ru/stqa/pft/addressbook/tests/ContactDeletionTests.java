package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().returnToMainPage();
    if (app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(app.cd);
      app.getNavigationHelper().returnToMainPage();
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    int randomGroup = (int) (Math.random() * before.size());
    app.getContactHelper().selectContact(randomGroup);
    app.getContactHelper().buttonDelContact();
    app.getContactHelper().deletionConfirmationContact();
    app.getNavigationHelper().returnToMainPage();
    List<ContactData> after = app.getContactHelper().getContactList();
    app.getNavigationHelper().logout();
    Assert.assertEquals(after.size(), before.size() + 1);
  }
}
