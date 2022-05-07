package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().returnToMainPage();
    app.getContactHelper().selectContact();
    app.getContactHelper().buttonDelContact();
    app.getContactHelper().deletionConfirmationContact();
    app.getNavigationHelper().logout();
  }
}
