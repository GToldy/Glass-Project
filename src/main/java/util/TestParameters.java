package util;

public class TestParameters {

    // Test user credentials
    public static String correctUsername = System.getProperty("correctUsername", "codecooladmin");
    public static String correctPassword = System.getProperty("correctPassword", "AutoExpert20?");

    // Define the test execution environment
    public static String testExecutor = System.getProperty("testExecutor", "local");
    public static String browserType = System.getProperty("browserType", "chrome");

    // Selenium Grid properties
    public static String gridUsername = System.getProperty("gridUsername");
    public static String gridAccessKey = System.getProperty("gridAccessKey");
    public static String gridURL = System.getProperty("gridUrl", "seleniumhub.codecool.metastage.net");
    public static String nodeURL = "https://" + gridUsername + ":" + gridAccessKey + "@" + gridURL + "/wd/hub";


    // Other properties
    public static final long timeout = Long.parseLong(System.getProperty("timeout", "3000"));
    public static String baseUrl = System.getProperty("baseUrl", "https://jira-expert.codecool.metastage.net");

    public static String loginPageUrl = baseUrl + "/login.jsp";
}
