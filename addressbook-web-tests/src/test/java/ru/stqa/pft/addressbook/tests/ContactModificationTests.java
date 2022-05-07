package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification() {
    app.getNavigationHelper().returnToMainPage();
    app.getContactHelper().selectContact();
    app.getContactHelper().changeModificationContact();
    app.getContactHelper().fillContactsForm(app.cd);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoContactsPage();
  }
}
