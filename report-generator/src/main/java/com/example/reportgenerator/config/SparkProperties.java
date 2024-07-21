package com.example.reportgenerator.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "spark")
public class SparkProperties {

    private String master;
    private String appName;
    private Map<String, String> files;
    private Map<String, Map<String, String[]>> columns;
    private Map<String, String[]> intermediate;
    private String reportName;

    // Getters and setters

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Map<String, String> getFiles() {
        return files;
    }

    public void setFiles(Map<String, String> files) {
        this.files = files;
    }

    public Map<String, Map<String, String[]>> getColumns() {
        return columns;
    }

    public void setColumns(Map<String, Map<String, String[]>> columns) {
        this.columns = columns;
    }

    public Map<String, String[]> getIntermediate() {
        return intermediate;
    }

    public void setIntermediate(Map<String, String[]> intermediate) {
        this.intermediate = intermediate;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }
}
