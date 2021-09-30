package manager;

import domain.Issue;
import repository.IssueRepository;

import java.util.List;
import java.util.Set;

public class IssueManager {
    private IssueRepository repository = new IssueRepository();

    public void addIssue(String title, Set<String> labels, String author) {
        repository.add(title, labels, author);
    }

    public List<Issue> getIssues() {
        return repository.getIssues();
    }

    public List<Issue> getOpenedIssues() {
        return repository.getOpened();
    }

    public List<Issue> getClosedIssues() {
        return repository.getClosed();
    }

    public List<Issue> filterByLabels(Set<String> labels) {
        return repository.filterByLabels(labels);
    }

    public List<Issue> filterByAssignee(String assignee) {
        return repository.filterByAssignee(assignee);
    }

    public List<Issue> filterByAuthor(String author) {
        return repository.filterByAuthor(author);
    }

    public List<Issue> sortByAuthor() {
        return repository.sortByAuthor();
    }

    public Issue getIssue(int id) {
        return repository.getIssueById(id);
    }

    public void closeIssue(int id) {
        repository.closeIssue(id);
    }

    public void openIssue(int id) {
        repository.openIssue(id);
    }
}