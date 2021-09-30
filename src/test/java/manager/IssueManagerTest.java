package manager;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IssueManagerTest {
    @Nested
    public class Empty {
        @Test
        public void addIssueTest() {
            IssueManager manager = new IssueManager();
            assertEquals(0, manager.getIssues().size());
        }

        @Test
        public void getClosedTest() {
            IssueManager manager = new IssueManager();
            assertEquals(0, manager.getClosedIssues().size());
        }

        @Test
        public void filterByAuthorTest() {
            IssueManager manager = new IssueManager();
            assertEquals(0, manager.filterByAuthor("auth").size());
        }

        @Test
        public void sortByAuthorTest() {
            IssueManager manager = new IssueManager();
            assertEquals(0, manager.sortByAuthor().size());
        }

        @Test
        public void closeIssueTest() {
            IssueManager manager = new IssueManager();
            manager.closeIssue(-100);
            assertNull(manager.getIssue(-100));
        }
    }

    @Nested
    public class Single {
        @Test
        public void addIssueTest() {
            IssueManager manager = new IssueManager();
            manager.addIssue("1", null, "auth");
            assertEquals(1, manager.getIssues().size());
        }

        @Test
        public void getClosedTest() {
            IssueManager manager = new IssueManager();
            manager.addIssue("1", null, "auth");
            assertEquals(0, manager.getClosedIssues().size());
            manager.closeIssue(1);
            assertEquals(1, manager.getClosedIssues().size());
        }

        @Test
        public void filterByAuthorTest() {
            IssueManager manager = new IssueManager();
            manager.addIssue("1", null, "auth");
            assertEquals(0, manager.filterByAuthor("123").size());
            assertEquals(1, manager.filterByAuthor("auth").size());
        }

        @Test
        public void sortByAuthorTest() {
            IssueManager manager = new IssueManager();
            manager.addIssue("1", null, "auth");
            assertEquals("auth", manager.sortByAuthor().get(0).getAuthor());
        }

        @Test
        public void closeIssueTest() {
            IssueManager manager = new IssueManager();
            manager.addIssue("1", null, "auth");
            manager.closeIssue(-100);
            assertNull(manager.getIssue(-100));

            manager.closeIssue(1);
            assertTrue(manager.getIssue(1).isClosed());
        }
    }

    @Nested
    class Many {
        @Test
        public void addIssueTest() {
            IssueManager manager = new IssueManager();
            manager.addIssue("1", null, "auth");
            manager.addIssue("2", null, "auth");
            assertEquals(2, manager.getIssues().size());
        }

        @Test
        public void getClosedTest() {
            IssueManager manager = new IssueManager();
            manager.addIssue("1", null, "auth");
            assertEquals(0, manager.getClosedIssues().size());
            manager.closeIssue(1);
            assertEquals(1, manager.getClosedIssues().size());
            manager.addIssue("2", null, "auth");
            assertEquals(1, manager.getClosedIssues().size());
            manager.closeIssue(2);
            assertEquals(2, manager.getClosedIssues().size());
        }

        @Test
        public void filterByAuthorTest() {
            IssueManager manager = new IssueManager();
            manager.addIssue("1", null, "auth");
            manager.addIssue("2", null, "auth");
            manager.addIssue("3", null, "asd");

            assertEquals(0, manager.filterByAuthor("123").size());
            assertEquals(2, manager.filterByAuthor("auth").size());
            assertEquals(1, manager.filterByAuthor("asd").size());
        }

        @Test
        public void sortByAuthorTest() {
            IssueManager manager = new IssueManager();
            manager.addIssue("1", null, "auth");
            manager.addIssue("2", null, "auth");
            manager.addIssue("3", null, "asd");

            assertEquals("auth", manager.sortByAuthor().get(0).getAuthor());
            assertEquals("asd", manager.sortByAuthor().get(2).getAuthor());
        }

        @Test
        public void closeIssueTest() {
            IssueManager manager = new IssueManager();
            manager.addIssue("1", null, "auth");
            manager.addIssue("2", null, "auth");
            manager.addIssue("3", null, "asd");

            manager.closeIssue(-100);
            assertNull(manager.getIssue(-100));

            manager.closeIssue(1);
            assertTrue(manager.getIssue(1).isClosed());

            manager.closeIssue(2);
            assertTrue(manager.getIssue(2).isClosed());
        }
    }
}
