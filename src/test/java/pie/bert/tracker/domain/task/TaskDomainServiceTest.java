package pie.bert.tracker.domain.task;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

public class TaskDomainServiceTest {

    private static final String CAT = "CAT";
    private static final LocalDateTime START = LocalDateTime.parse("2020-07-03T10:15:30");
    private static final LocalDateTime END = START.plusHours(1);
    private static final String DESC = "DESC";
    private static final int TASK_ID = 1;

    private TaskRepositoryService repositoryServiceMock;
    private TaskValidator taskValidatorMock;

    private TaskDomainService testObj;

    @Before
    public void setUp() throws Exception {
        this.repositoryServiceMock = mock(TaskRepositoryService.class);
        this.taskValidatorMock = mock(TaskValidator.class);
        this.testObj = new TaskDomainService(repositoryServiceMock, taskValidatorMock);
    }

    @Test
    public void create_returnsTask_happyPath() {
        // given
        TaskUnsaved taskUnsaved = new TaskUnsaved(CAT, START, END, DESC);

        Task created = new Task(CAT, TASK_ID, START, END, DESC);
        given(repositoryServiceMock.create(any()))
                .willReturn(created);

        // when
        Task result = testObj.create(taskUnsaved);

        // then
        assertThat(result).isEqualTo(created);
    }

    @Test
    public void create_shouldUseCorrectMethods() {
        // given
        TaskUnsaved taskUnsaved = new TaskUnsaved(CAT, START, END, DESC);

        Task created = new Task(CAT, TASK_ID, START, END, DESC);
        given(repositoryServiceMock.create(any()))
                .willReturn(created);

        // when
        testObj.create(taskUnsaved);

        // then
        then(taskValidatorMock)
                .should(times(1))
                .validate(taskUnsaved);
        then(repositoryServiceMock)
                .should(times(1))
                .create(taskUnsaved);
    }

    @Test
    public void findAll_happyPath() {
        // given
        Collection<Task> returned = mock(Collection.class);
        given(repositoryServiceMock.findAll())
                .willReturn(returned);
        Collection<Task> expected = returned;

        // when
        Collection<Task> result = testObj.findAll();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void findAll_shouldUseCorrectMethods() {
        // given
        // when
        testObj.findAll();

        // then
        then(repositoryServiceMock)
                .should(times(1))
                .findAll();
    }

    @Test
    public void findByTaskIdentity_happyPath() {
        // given
        TaskIdentity taskIdentity = new TaskIdentity(CAT, TASK_ID);

        Task found = new Task(CAT, TASK_ID, START, END, DESC);
        given(repositoryServiceMock.findByTaskIdentity(any()))
                .willReturn(Optional.ofNullable(found));

        var expected = found;

        // when
        var result = testObj.findByTaskIdentity(taskIdentity);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void findByTaskIdentity_shouldThrowException_whenTaskNotFound() {
        // given
        TaskIdentity taskIdentity = new TaskIdentity(CAT, TASK_ID);

        Task found = null;
        given(repositoryServiceMock.findByTaskIdentity(any()))
                .willReturn(Optional.ofNullable(found));

        var expected = TaskNotFoundException.class;

        // when
        Throwable result = catchThrowable(() -> testObj.findByTaskIdentity(taskIdentity));

        // then
        assertThat(result).isInstanceOf(expected);
        assertThat(((TaskNotFoundException) result).getCode()).isEqualTo("TSK_0001");
    }

    @Test
    public void findByTaskIdentity_shouldInvokeCorrectMethod() {
        // given
        TaskIdentity taskIdentity = new TaskIdentity(CAT, TASK_ID);

        Task found = new Task(CAT, TASK_ID, START, END, DESC);
        given(repositoryServiceMock.findByTaskIdentity(any()))
                .willReturn(Optional.ofNullable(found));

        // when
        testObj.findByTaskIdentity(taskIdentity);

        // then
        then(repositoryServiceMock)
                .should(times(1))
                .findByTaskIdentity(taskIdentity);
    }
}
