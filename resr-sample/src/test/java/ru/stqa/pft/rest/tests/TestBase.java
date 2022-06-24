package ru.stqa.pft.rest.tests;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.rest.appmanager.ApplicationManager;

import java.io.IOException;

public class TestBase {
  public static final ApplicationManager app = new ApplicationManager();

  @BeforeSuite//(alwaysRun = true)
  public void setUp() throws IOException {
    app.init();
  }

  public boolean isIssueOpen(int issueId) throws IOException {
    String status = app.rest().getIssue(issueId);
    if (status.equals("Resolved") || status.equals("Closed") || status.equals("Deleted")) {
      return false;
    }
    return true;
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}
