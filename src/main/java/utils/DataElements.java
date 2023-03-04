package utils;

public class DataElements {

    private String testMethodName = null;
    private String params = null;
    private String runStatus = null;

    public DataElements(String testMethodName, String runStatus, String params) {
        this.testMethodName = testMethodName;
        this.runStatus = runStatus;
        this.params = params;
    }

    public String getParams() {
        return params;
    }
}
