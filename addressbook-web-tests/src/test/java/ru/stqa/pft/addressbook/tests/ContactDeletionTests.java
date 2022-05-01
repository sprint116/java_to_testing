package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().returnToMainPage();
    app.getContactHellper().selectContact();
    app.getContactHellper().buttonDelContact();
    app.getContactHellper().deletionСonfirmationСontact();
    app.getNavigationHelper().logout();
  }
}
