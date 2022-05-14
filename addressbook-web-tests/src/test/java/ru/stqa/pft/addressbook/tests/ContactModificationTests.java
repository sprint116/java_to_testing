package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification() {
    app.getNavigationHelper().returnToMainPage();
    if (app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(app.cd);
      app.getNavigationHelper().returnToMainPage();
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    int randomContact = (int) (Math.random() * before.size());
    app.getContactHelper().selectContact(randomContact);
    app.getContactHelper().changeModificationContact();
    ContactData contact = new ContactData(
            before.get(randomContact).getId(),
            "TestName",
            "LastName",
            "testing",
            "Testing",
            "+79999999999",
            "test@test.test",
            "17",
            "January",
            "1990",
            "TestingAddress"
    );
    app.getContactHelper().fillContactsForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToMainPage();
    List<ContactData> after = app.getContactHelper().getContactList();
    app.getNavigationHelper().logout();
    Assert.assertEquals(after.size(), before.size());

    before.remove(randomContact);
    before.add(contact);
    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
  }
}
