package com.info.saude.dto;

public class EmailTemplateDTO {

	private String subject;

	private String to;

	private String templatePath;

	private String templateObjectName;

	public EmailTemplateDTO() {
		super();
	}

	public EmailTemplateDTO(String subject, String to, String templatePath, String templateObjectName) {
		super();
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

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
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
