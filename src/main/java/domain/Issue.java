package domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Issue implements Comparable {
    private String title;
    private Set<String> labels;
    private int id;
    private List<Comment> comments;
    private final long dateCreated;
    private final String author;
    private String assignee;
    private boolean closed;

    public Issue(String title, Set<String> labels, String author, int id) {
        this.title = title;
        this.labels = labels;
        dateCreated = System.currentTimeMillis();
        this.author = author;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<String> getLabels() {
        return labels;
    }

    public void setLabels(Set<String> labels) {
        this.labels = labels;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public String getAuthor() {
        return author;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    @Override
    public int compareTo(Object o) {
        return ((Issue) o).getAuthor().compareTo(this.author);
    }
}
