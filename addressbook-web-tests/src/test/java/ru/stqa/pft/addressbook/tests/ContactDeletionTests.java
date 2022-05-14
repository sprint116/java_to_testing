package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().returnToMainPage();
    int before = app.getContactHelper().getContactCount();
    if (app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(app.cd);
      app.getNavigationHelper().returnToMainPage();
      before++;
    }
    int randomGroup = (int) (Math.random() * before);
    app.getContactHelper().selectContact(randomGroup);
    app.getContactHelper().buttonDelContact();
    app.getContactHelper().deletionConfirmationContact();
    app.getNavigationHelper().returnToMainPage();
    int after = app.getContactHelper().getContactCount();
    app.getNavigationHelper().logout();
    Assert.assertEquals(after, --before);
  }
}
