package com.example.reportgenerator.dataset;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.reportgenerator.config.SparkProperties;

import java.util.HashMap;
import java.util.Map;

@Component
public class IntermediateDatasetManager {

    @Autowired
    private SparkProperties sparkProperties;
    
    @Autowired
    SparkSession spark;

    private final Map<String, Dataset<Row>> datasetCache = new HashMap<>();

    public Dataset<Row> loadOrGetDataset(String fileKey) {
        if (datasetCache.containsKey(fileKey)) {
            return datasetCache.get(fileKey);
        } else {

            String filePath = sparkProperties.getFiles().get(fileKey);
            Dataset<Row> dataset = spark
            	.read()
            	.option("header", true)
            	.csv(filePath);

            datasetCache.put(fileKey, dataset);
            return dataset;
        }
    }
}
