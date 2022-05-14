package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().createGroup(app.gd);
    List<GroupData> after = app.getGroupHelper().getGroupList();
    app.getNavigationHelper().logout();
    Assert.assertEquals(after.size(), before.size() + 1);

    int max = 0;
    for (GroupData g : after) {
      if (g.getId() > max) {
        max = g.getId();
      }
    }
    app.gd.setId(max);
    before.add(app.gd);
    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
  }
}
