package ru.stqa.pft.rest.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.rest.modul.Issue;

import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestAssuredTests {

  @BeforeClass
  public void init(){
    RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490","");
  }

  @Test
  public void testCreateIssue(){
    Set<Issue> oldIssues = getIssues();
    Issue newIssue = new Issue().withSubject("Test issue").withDescription("Hallow, world");
    int issueId = createIssue(newIssue);
    Set<Issue> newIssues = getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues,oldIssues);
  }

  private Set<Issue> getIssues(){
    String response = RestAssured.get("https://bugify.stqa.ru/api/issues.json").body().asString();

    JsonElement issues = JsonParser.parseString(response).getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
  }

  private int createIssue(Issue newIssue){
    String response = RestAssured.given()
            .parameter("subject", newIssue.getSubject())
            .parameter("description", newIssue.getDescription())
            .post("https://bugify.stqa.ru/api/issues.json").body().asString();

    return JsonParser.parseString(response).getAsJsonObject().get("issue_id").getAsInt();
  }

}
