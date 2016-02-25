package com.pr.crawl1.crawler1;

import org.jsoup.nodes.Document;

public class MailInfo {
	private String from;
	private String subject;
	private String date;
	private String content;
	Document doc;

	MailInfo(Document doc) {
		this.doc = doc;
		from = doc.select("from").text();
		subject = doc.select("subject").text();
		date = doc.select("date").text();
		date = date + ".txt";
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
