package com.pr.crawl1.crawler1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DownloadEmails {

	public void downloadEmails(String url) {
		Main m = new Main();
		Document threadDoc = m.connectUrl(url);
		Elements emailLinks = threadDoc.select("a[href]");
		for (Element emailLink : emailLinks) {
			if (emailLink.toString().contains("%")) {
				if (url.contains("thread")) {
					url = url.replaceAll("/thread", "/ajax/");
				} else {
					url = url.replace("/ajax", "/ajax/");
				}
				url = url.concat(emailLink.attr("href").toString());
				Document emailDoc = m.connectUrl(url);
				saveToFile(emailDoc, url);
				url = url.substring(0, 69);
			}
		}
	}

	public void saveToFile(Document Doc, String url) {
		MailInfo mi = new MailInfo(Doc);
		try {
			File file = new File(mi.getDate());
			FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fileWriter);
			bw.write("From :" + mi.getFrom());
			bw.write("\n\nsubject:" + mi.getSubject());
			bw.write("\n\ncontents:" + mi.getContent());
			bw.close();
			System.out.println(mi.getDate() + " is created");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
