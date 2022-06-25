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
// Re-opened In Progress Open
  public boolean isIssueOpen(int issueId) throws IOException {
    String status = app.rest().getIssue(issueId);
    if (status.equals("Re-opened") || status.equals("In Progress") || status.equals("Open")) {
      return true;
    }
    return false;
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}
