package utils;

public class DataElements {

    private String params = null;
    public DataElements(String testMethodName, String runStatus, String params) {
        this.params = params;
    }

    public String getParams() {
        return params;
    }
}
