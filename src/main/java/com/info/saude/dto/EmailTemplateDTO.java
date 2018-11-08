package com.info.saude.dto;

public class EmailTemplateDTO {

	private String subject;

	private StringBuffer to;

	private String templatePath;

	private String templateObjectName;

	public EmailTemplateDTO() {
		super();
	}

	public EmailTemplateDTO(String subject, StringBuffer to, String templatePath, String templateObjectName) {
		this.subject = subject;
		this.to = to;
		this.templatePath = templatePath;
		this.templateObjectName = templateObjectName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public StringBuffer getTo() {
		return to;
	}

	public void setTo(StringBuffer to) {
		this.to = to;
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	public String getTemplateObjectName() {
		return templateObjectName;
	}

	public void setTemplateObjectName(String templateObjectName) {
		this.templateObjectName = templateObjectName;
	}

}
