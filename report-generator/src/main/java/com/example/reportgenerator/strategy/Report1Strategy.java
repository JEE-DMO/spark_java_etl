package com.example.reportgenerator.strategy;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.reportgenerator.dataset.IntermediateDatasetManager;

@Component
public class Report1Strategy implements ReportStrategy {

	@Autowired
	private IntermediateDatasetManager datasetManager;

	@Autowired
	SparkSession spark;

	@Override
	public void generateReport() {

		Dataset<Row> employees = datasetManager.loadOrGetDataset("F1").select("employee_id", "first_name", "last_name", "department_id");
		Dataset<Row> departments = datasetManager.loadOrGetDataset("F2").select("department_id", "department_name");

		Dataset<Row> report = employees
			.join(departments, employees.col("department_id").equalTo(departments.col("department_id")))
			.select("employee_id", "first_name", "last_name", "department_name");

		report.coalesce(1).write().mode("overwrite").option("header", "true").csv("path/to/output/1/report1.csv");
	}
}
