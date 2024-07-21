package com.example.reportgenerator.strategy;

public class ReportTypeFactory {

	private ReportTypeFactory() {
		super();
	}

	public static ReportStrategy getReportStrategy(String reportName) {
		ReportType type = ReportType.fromCode(reportName);

		switch (type) {
		case P1:
			return new Report1Strategy();
		case P2:
			return new Report2Strategy();
		case P3:
			return new Report3Strategy();
		case PI:
			return new Report3Strategy();

		default:
			throw new IllegalArgumentException("Unknown report type: " + type);
		}
	}
}
