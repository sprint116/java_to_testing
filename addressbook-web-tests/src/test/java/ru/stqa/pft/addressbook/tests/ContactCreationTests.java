package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.initContactsCreation();
    app.fillContactsForm(app.gd);
    app.submitContactsCreation();
    app.returnToMainPage();
  }
}
