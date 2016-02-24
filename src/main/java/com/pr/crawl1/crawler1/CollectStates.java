package com.pr.crawl1.crawler1;

import org.jsoup.nodes.Document;

public class CollectStates {
	private String from;
	private String subject;
	private String date;
	private String content;
	Document doc;

	CollectStates(Document doc) {
		this.doc = doc;
		setFrom();
		setSubject();
		setDate();
		setContent();
	}

	public void setFrom() {
		from = doc.select("from").text();
	}

	public void setSubject() {
		subject = doc.select("subject").text();
	}

	public void setDate() {
		date = doc.select("date").text();
		date = date + ".txt";
	}

	public void setContent() {
		content = doc.select("contents").text();
	}

	public String getFrom() {
		return from;
	}

	public String getSubject() {
		return subject;
	}

	public String getDate() {
		return date;
	}

	public String getContent() {
		return content;
	}

}
