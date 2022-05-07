package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getContactHelper().initContactsCreation();
    app.getContactHelper().fillContactsForm(app.cd);
    app.getContactHelper().submitContactsCreation();
    app.getNavigationHelper().returnToMainPage();
    app.getNavigationHelper().logout();
  }
}
