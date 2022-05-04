package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification() {
    app.getNavigationHelper().returnToMainPage();
    app.getContactHellper().selectContact();
    app.getContactHellper().changeModificationContact();
    app.getContactHellper().fillContactsForm(app.cd);
    app.getContactHellper().submitContactModification();
    app.getNavigationHelper().gotoContactsPage();
  }
}
