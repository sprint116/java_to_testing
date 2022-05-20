package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test(enabled = false)
  public void testContactCreation() {
    app.getNavigationHelper().returnToMainPage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().createContact(app.cd);
    app.getNavigationHelper().returnToMainPage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);


    //app.cd.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(app.cd);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));

  }
}
