package repository;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class IssueRepositoryTest {
    @Test
    public void addTest() {
        IssueRepository repository = new IssueRepository();
        assertEquals(0, repository.getIssues().size());
        repository.add("123", null, "auth");
        assertEquals("123", repository.getIssues().get(0).getTitle());
        repository.add("123", null, "auth");
        assertEquals(2, repository.getIssues().size());
    }

    @Test
    public void getIssueByIdTest() {
        IssueRepository repository = new IssueRepository();
        repository.add("123", null, "auth");

        assertNull(repository.getIssueById(-1));
        assertEquals("auth", repository.getIssueById(1).getAuthor());
    }

    @Test
    void getOpenedTest() {
        IssueRepository repository = new IssueRepository();
        assertEquals(0, repository.getOpened().size());
        repository.add("123", null, "auth");
        assertEquals(1, repository.getOpened().size());
        repository.add("123", null, "auth");
        assertEquals(2, repository.getOpened().size());
        repository.closeIssue(1);
        assertEquals(1, repository.getOpened().size());
        repository.closeIssue(2);
        assertEquals(0, repository.getOpened().size());
    }

    @Test
    void getClosedTest() {
        IssueRepository repository = new IssueRepository();
        assertEquals(0, repository.getClosed().size());
        repository.add("123", null, "auth");
        assertEquals(0, repository.getClosed().size());
        repository.add("123", null, "auth");
        assertEquals(0, repository.getClosed().size());
        repository.closeIssue(1);
        assertEquals(1, repository.getClosed().size());
        repository.closeIssue(2);
        assertEquals(2, repository.getClosed().size());
    }

    @Test
    void filterByAuthorTest() {
        IssueRepository repository = new IssueRepository();
        assertEquals(0, repository.filterByAuthor("").size());
        repository.add("123", null, "auth");
        assertEquals("123", repository.filterByAuthor("auth").get(0).getTitle());
        repository.add("123", null, "asd");
        assertEquals("asd", repository.filterByAuthor("asd").get(0).getAuthor());
    }

    @Test
    void filterByAssigneeTest() {
        IssueRepository repository = new IssueRepository();
        assertEquals(0, repository.filterByAssignee("").size());
        repository.add("123", null, "auth");
        repository.getIssueById(1).setAssignee("as1");
        assertEquals("123", repository.filterByAssignee("as1").get(0).getTitle());
        repository.add("123", null, "asd");
        repository.getIssueById(2).setAssignee("as2");
        assertEquals("123", repository.filterByAssignee("as2").get(0).getTitle());
        assertEquals(0, repository.filterByAssignee("as3").size());
    }

    @Test
    void filterByLabelsTest() {
        IssueRepository repository = new IssueRepository();
        assertEquals(0, repository.filterByLabels(null).size());
        Set<String> labels = new HashSet<>();
        labels.add("top");
        labels.add("text");

        Set<String> oneLabel = new HashSet<>();
        oneLabel.add("one");
        repository.add("123", labels, "auth");
        assertEquals("123", repository.filterByLabels(labels).get(0).getTitle());
        assertEquals(0, repository.filterByLabels(oneLabel).size());

        repository.add("one", oneLabel, "auth");
//        assertEquals("one", repository.filterByLabels(labels).get(0).getTitle());
        assertEquals("one", repository.filterByLabels(oneLabel).get(0).getTitle());
        assertEquals(1, repository.filterByLabels(oneLabel).size());
        assertEquals(0, repository.filterByLabels(null).size());
    }

    @Test
    void sortByUserTest() {
        IssueRepository repository = new IssueRepository();
        assertEquals(0, repository.sortByAuthor().size());
        repository.add("123", null, "11");
        assertEquals("123", repository.sortByAuthor().get(0).getTitle());
        repository.add("456", null, "22");
        assertEquals("456", repository.sortByAuthor().get(0).getTitle());
    }

    @Test
    void closeIssueTest() {
        IssueRepository repository = new IssueRepository();
        repository.closeIssue(0);
        assertEquals(0, repository.getClosed().size());
        repository.add("123", null, "11");
        repository.getIssueById(1).setClosed(true);
        assertEquals(1, repository.getClosed().size());
    }

    @Test
    void openIssueTest() {
        IssueRepository repository = new IssueRepository();
        repository.openIssue(0);
        assertEquals(0, repository.getOpened().size());
        repository.add("123", null, "11");
        assertEquals(1, repository.getOpened().size());
        repository.getIssueById(1).setClosed(true);
        assertEquals(0, repository.getOpened().size());
        repository.getIssueById(1).setClosed(false);
        assertEquals(1, repository.getOpened().size());
        repository.add("123", null, "11");
        assertEquals(2, repository.getOpened().size());
    }
}
