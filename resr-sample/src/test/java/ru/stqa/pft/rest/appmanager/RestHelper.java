package ru.stqa.pft.rest.appmanager;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import ru.stqa.pft.rest.modul.Issue;

import java.io.IOException;
import java.util.Set;

public class RestHelper {
  private ApplicationManager app;

  public RestHelper(ApplicationManager app) {
    this.app = app;
  }

  public Executor getExecutor() {
    return Executor.newInstance().auth(app.getProperty("rest.key"),app.getProperty("rest.key.pass"));
  }

  public Set<Issue> getIssues() throws IOException {
    String response = getExecutor().execute(Request.Get(app.getProperty("rest.baseUrl") + "/issues.json"))
            .returnContent().asString();
    JsonElement issues = JsonParser.parseString(response).getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
  }

  //issues/{issue_id}.{format}

  public String getIssue(int issueId) throws IOException {
    String response = getExecutor().execute(Request.Get(String.format(app.getProperty("rest.baseUrl") + "/issues/%s.json", issueId)))
            .returnContent().asString();
    JsonParser parser = new JsonParser();
    JsonElement jsonTree = parser.parse(response);
    JsonObject jsonObject = jsonTree.getAsJsonObject();
    JsonElement issues = jsonObject.get("issues").getAsJsonArray().get(0);
    jsonObject = issues.getAsJsonObject();
    return jsonObject.get("state_name").getAsString();
  }

  public int createIssue(Issue newIssue) throws IOException {
    String response = getExecutor().execute(Request.Post(app.getProperty("rest.baseUrl") + "/issues.json")
                    .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                            new BasicNameValuePair("description", newIssue.getDescription())))
            .returnContent().asString();
    return JsonParser.parseString(response).getAsJsonObject().get("issue_id").getAsInt();
  }

}
