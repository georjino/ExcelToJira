import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.IssueType;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

import io.atlassian.util.concurrent.Promise;


public class App {
    public static void main(String[] args) throws Exception {

            createIssue();
    }

    public static void createIssue() throws Exception
    {

        String email = "your email";
        String pat = "xxxxxx";
        String auth = email + ":" + pat;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
        BearerHttpAuthenticationHandler handler = new BearerHttpAuthenticationHandler(pat);
        String reporter ="f89745";
        String assignee ="f89745";
        String title ="test";
        String priority ="Medium";
        String summary ="test to be deleted";
        String description ="test to be deleted";
        //Datetime duedate = Date.valueOf("2025-08-30");
        String project ="54864";
        Long issuetype = Long.valueOf(12302);
        String components ="61396";
        String customfield_13728 ="f89745";

        final URI jiraServerUri = new URI("your jira server url");

        System.out.println("\n connecting to create a bug... ");

        JiraRestClientFactory restClientFactory = new AsynchronousJiraRestClientFactory();
        JiraRestClient restClient = restClientFactory.createWithAuthenticationHandler(jiraServerUri, handler);
        IssueRestClient issueClient = restClient.getIssueClient();

        IssueInputBuilder issueBuilder = new IssueInputBuilder(project, issuetype, summary);
        //BasicUser user= BasicUser
       // issueBuilder.setReporter( reporter ="f89745";
        //String assignee ="f89745";
        issueBuilder.setSummary(summary);        
        issueBuilder.setDescription(description);   
        //issueBuilder.setDueDate(duedate);
        //BasicComponent bc=
        //issueBuilder.setComponents(61396)
        issueBuilder.setFieldValue("customfield_13728", "f89745");

        IssueType it = new IssueType(jiraServerUri, issuetype, summary, false, description, null);
        issueBuilder.setIssueType(it);
        IssueInput issueInput = issueBuilder.build();

        Promise<BasicIssue> promise = restClient.getIssueClient().createIssue(issueInput);
        BasicIssue basicIssue = promise.claim();
        //Promise<Issue> promiseJavaIssue = restClient.getIssueClient().getIssue(basicIssue.getKey());

        //Issue issue = promiseJavaIssue.claim();
        //System.out.println(String.format("New issue created is: %s\r\n", issue.getSummary()));        


        //return issue;
    }

}