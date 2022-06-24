package ru.stqa.pft.rest.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.rest.modul.Issue;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests  extends TestBase{

  @Test
  public void testCreateIssue() throws IOException {
    skipIfNotFixed(3);
    Set<Issue> oldIssues = app.rest().getIssues();
    Issue newIssue = new Issue().withSubject("Test issue").withDescription("Hallow, world");
    int issueId = app.rest().createIssue(newIssue);
    Set<Issue> newIssues = app.rest().getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues,oldIssues);
  }
}
