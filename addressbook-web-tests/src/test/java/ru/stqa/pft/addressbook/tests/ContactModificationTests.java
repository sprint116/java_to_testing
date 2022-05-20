package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().MainPage();
    if (app.contact().list().size() == 0) {
      app.contact().create(app.cd);
      app.goTo().MainPage();
    }
  }

  @Test(enabled = false)
  public void testContactModification() {
    List<ContactData> before = app.contact().list();
    int randomContact = (int) (Math.random() * before.size());
    ContactData contact = new ContactData(
            before.get(randomContact).getId(),
            "TestName " + randomContact + "/",
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
    app.contact().modify(randomContact, contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(randomContact);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
