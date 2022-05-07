package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification() {
    app.getNavigationHelper().returnToMainPage();
    if (app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(app.cd, false);
    }
    app.getNavigationHelper().returnToMainPage();
    app.getContactHelper().selectContact();
    app.getContactHelper().changeModificationContact();
    app.getContactHelper().fillContactsForm(app.cd, false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToMainPage();
    app.getNavigationHelper().logout();
  }
}
