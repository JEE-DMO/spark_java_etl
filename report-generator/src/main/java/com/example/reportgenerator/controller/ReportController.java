package com.example.reportgenerator.controller;

import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reportgenerator.strategy.ReportTypeFactory;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

	@Autowired
	SparkSession spark;
	
	
    @GetMapping("/generate/{reportName}")
	public String generateReport(@PathVariable String reportName) {
		try {
			ReportTypeFactory.getReportStrategy(reportName).generateReport();
			return "Report " + reportName + " generated successfully.";
		} catch (Exception e) {
			return "Error generating report _: " + e.getStackTrace();
		}
	}

	@GetMapping("/health")
	public String healthCheck() {
		return "Application is running.";
	}

	@GetMapping("/sparkUI")
	public String sparkUI() {
		return spark.sparkContext().uiWebUrl().toString();
	}
}
