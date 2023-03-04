package utils;

import java.util.Map;

public class Data {

    private String suiteName;
    private Map<String, DataElements> dataList;

    public Data(String testSuiteName, Map<String, DataElements> dataList) {
        this.suiteName = testSuiteName;
        this.dataList = dataList;
    }

    public String getSuiteName() {
        return suiteName;
    }

    public Map<String, DataElements> getElementList() {
        return dataList;
    }

}
