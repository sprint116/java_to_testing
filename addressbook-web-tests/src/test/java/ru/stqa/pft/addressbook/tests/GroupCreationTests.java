package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    Set<GroupData> before = app.group().all();
    app.group().create(app.gd);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    app.gd.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(app.gd);
    Assert.assertEquals(before, after);
  }
}
