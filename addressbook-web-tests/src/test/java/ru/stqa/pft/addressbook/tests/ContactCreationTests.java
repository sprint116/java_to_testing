package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.Set;

public class ContactCreationTests extends TestBase {

  @Test//(enabled = false)
  public void testContactCreation() {
    app.goTo().MainPage();
    Set<ContactData> before = app.contact().all();
    app.contact().create(app.cd);
    app.goTo().MainPage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    app.cd.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(app.cd);
    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));

  }
}
