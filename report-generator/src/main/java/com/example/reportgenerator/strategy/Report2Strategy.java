package com.example.reportgenerator.strategy;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.reportgenerator.dataset.IntermediateDatasetManager;

@Component
public class Report2Strategy implements ReportStrategy {

    @Autowired
    private IntermediateDatasetManager datasetManager;
    
    @Autowired
    SparkSession spark;

    @Override
    public void generateReport() {

        Dataset<Row> employees = datasetManager.loadOrGetDataset("F1").select("employee_id", "first_name", "last_name", "department_id");
        Dataset<Row> locations = datasetManager.loadOrGetDataset("F4").select("location_id", "location_name");

        Dataset<Row> departments = datasetManager.loadOrGetDataset("F2").select("department_id", "location_id");

        Dataset<Row> report = employees.join(departments, "department_id")
                                       .join(locations, "location_id")
                                       .select("employee_id", "first_name", "last_name", "location_name").repartition(2);

        report.coalesce(1).write().mode("overwrite").option("header", "true").csv("path/to/output/2/report2.csv");
    }
}
