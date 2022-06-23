package ru.stqa.pft.rest.tests;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.rest.appmanager.ApplicationManager;
import ru.stqa.pft.rest.modul.Issue;

import java.io.IOException;
import java.util.Set;

public class TestBase {
  public static final ApplicationManager app = new ApplicationManager();

  @BeforeSuite//(alwaysRun = true)
  public void setUp() throws IOException {
    app.init();
  }

  public boolean isIssueOpen(int issueId) throws IOException {
    Set<Issue> issues = app.rest().getIssue(issueId);
    String status = issues.iterator().next().getStateName();
    if (status.equals("resolved") || status.equals("closed")) {
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
