package com.example.reportgenerator.strategy;

public enum ReportType {
	P1("P1", "P1 Report"), 
	P2("P2", "P2 Report"), 
	P3("P3", "P3 Report"), 
	PI("PI", "PI Report");

	private final String code;
	private final String description;

	ReportType(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static ReportType fromCode(String code) {
		for (ReportType type : ReportType.values()) {
			if (type.getCode().equalsIgnoreCase(code)) {
				return type;
			}
		}
		throw new IllegalArgumentException("Unknown code: " + code);
	}
}
