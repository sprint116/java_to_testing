package ru.stqa.pft.rest.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
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

  public Set<Issue> getIssue(int issueId) throws IOException {
    String json = getExecutor().execute(Request.Get((app.getProperty("rest.baseUrl")+issueId+".json")))
            .returnContent().asString();
    JsonElement issues = JsonParser.parseString(json).getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
  }

  public int createIssue(Issue newIssue) throws IOException {
    String response = getExecutor().execute(Request.Post(app.getProperty("rest.baseUrl") + "/issues.json")
                    .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                            new BasicNameValuePair("description", newIssue.getDescription())))
            .returnContent().asString();
    return JsonParser.parseString(response).getAsJsonObject().get("issue_id").getAsInt();
  }

}
