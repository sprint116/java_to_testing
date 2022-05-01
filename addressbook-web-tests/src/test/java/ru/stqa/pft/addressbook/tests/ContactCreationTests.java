package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getContactHellper().initContactsCreation();
    app.getContactHellper().fillContactsForm(app.gd);
    app.getContactHellper().submitContactsCreation();
    app.getNavigationHelper().returnToMainPage();
    app.getNavigationHelper().logout();
  }
}
