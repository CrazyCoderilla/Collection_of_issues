package repository;

import domain.Issue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class IssueRepository {
    private List<Issue> issues = new ArrayList<>();

    public Issue getIssueById(int id) {
        try {
            return issues.get(id - 1);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public boolean add(String title, Set<String> labels, String author) {
        return issues.add(new Issue(title, labels, author, issues.size() + 1));
    }

    public List<Issue> getOpened() {
        List<Issue> opened = new ArrayList<>();
        for (Issue i : issues) {
            if (!i.isClosed()) opened.add(i);
        }
        return opened;
    }

    public List<Issue> getClosed() {
        List<Issue> closed = new ArrayList<>();
        for (Issue i : issues) {
            if (i.isClosed()) closed.add(i);
        }
        return closed;
    }

    public List<Issue> filterByAuthor(String author) {
        List<Issue> authoredIssues = new ArrayList<>();
        if (author == null) {
            return authoredIssues;
        }
        for (Issue i : issues) {
            if (i.getAuthor().equals(author)) authoredIssues.add(i);
        }
        return authoredIssues;
    }

    public List<Issue> filterByAssignee(String assignee) {
        List<Issue> assigned = new ArrayList<>();
        for (Issue i : issues) {
            if (i.getAssignee().equals(assignee)) assigned.add(i);
        }
        return assigned;
    }

    public List<Issue> filterByLabels(Set<String> labels) {
        List<Issue> labeled = new ArrayList<>();
        if (labels == null) {
            return labeled;
        }
        for (Issue i : issues) {
            for (String label : labels) {
                if (i.getLabels().contains(label)) labeled.add(i);
            }
        }
        return labeled;
    }

    public List<Issue> sortByAuthor() {
        Collections.sort(issues);
        return issues;
    }

    public void closeIssue(int id) {
        try {
            issues.get(id - 1).setClosed(true);
        } catch (Exception e) {
            System.out.println("god damn, wtf with this id");
        }
    }

    public void openIssue(int id) {
        try {
            issues.get(id - 1).setClosed(false);
        } catch (Exception e) {
            System.out.println("god damn, wtf with this id");
        }
    }
}
