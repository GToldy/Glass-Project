import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.LogInPage;
import pages.WorkflowPage;
import util.TestParameters;
import util.WebDriverUtil;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;

public class WorkflowTests {

    LogInPage logInPage;

    WorkflowPage workflowPage;

    @BeforeEach
    public void setup() throws MalformedURLException {
        logInPage = new LogInPage();
        workflowPage = new WorkflowPage();
    }

    @AfterEach
    public void quit() {
        WebDriverUtil.quitWebDriver();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/workflowDetails.csv", numLinesToSkip = 1)
    public void workFlowDetailsAreVisualizedTest(int row, String fromStatus, String transitionName, String toStatus, String transitionScreen, String conditions, String validators, String postFunctions){
        logInPage.logInWithUser(TestParameters.loginPageUrl,TestParameters.correctUsername,TestParameters.correctPassword);
        workflowPage.clickToTransitionName();
        List<String> details = workflowPage.getAllDetails(row);
        System.out.println(details);
        System.out.println(Arrays.asList(fromStatus, transitionName, toStatus, transitionScreen, postFunctions));
        Assertions.assertEquals(fromStatus, details.get(0));
        Assertions.assertEquals(transitionName, details.get(1));
        Assertions.assertEquals(toStatus, details.get(2));
        Assertions.assertTrue(details.get(3).contains(transitionScreen));
        Assertions.assertEquals(postFunctions, details.get(4));
    }
}
