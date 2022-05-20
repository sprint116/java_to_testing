package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().gotoGroupPage();
    if (app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(app.gd);
    }
  }

  @Test
  public void testGroupModification() {
    List<GroupData> before = app.getGroupHelper().getGroupList();
    int randomGroup = (int) (Math.random() * before.size());
    GroupData group = app.gd;
    app.groupHelper.modifyGroup(randomGroup, group);
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(randomGroup);
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
