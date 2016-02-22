package com.pr.crawl1.crawler1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Hello world!
 *
 */
class App {
	/**
	 * 
	 */
	String from;
	String subject;
	String date;
	String content;

	public Document connectUrl(String url) {
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
		return doc;
	}

	public void collectLinks(Document doc, String url) throws IOException {
		System.out.println("enter the year");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String year = br.readLine();
		DeleteFiles df = new DeleteFiles();
		df.deleteFiles("/home/sanjeevn/arena/crawler1", ".txt");
		Elements threadLinks = doc.select("a[href]");
		for (Element threadLink : threadLinks) {
			if (threadLink.toString().contains(year)
					&& threadLink.toString().contains("thread")) {
				url = url.concat(threadLink.attr("href").toString());
				Document threadDoc = connectUrl(url);
				Elements emailLinks = threadDoc.select("a[href]");
				for (Element emailLink : emailLinks) {
					if (emailLink.toString().contains("%")) {
						if (url.contains("thread")) {
							url = url.replaceAll("/thread", "/ajax/");
						} else {
							url = url.replace("/ajax", "/ajax/");
						}
						url = url.concat(emailLink.attr("href").toString());
						Document emailDoc = connectUrl(url);
						try {
							from = emailDoc.select("from").text();
							subject = emailDoc.select("subject").text();
							date = emailDoc.select("date").text();
							content = emailDoc.select("contents").text();
							date = date + ".txt";
							File file = new File(date);
							FileWriter fileWriter = new FileWriter(
									file.getAbsoluteFile());
							BufferedWriter bw = new BufferedWriter(fileWriter);
							bw.write("From :" + from);
							bw.write("\n\nsubject:" + subject);
							bw.write("\n\ncontents:" + content);
							bw.close();
							System.out.println("file is created");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						url = url.substring(0, 69);
					}
				}
				url = url.substring(0, 53);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		String url = "http://mail-archives.apache.org/mod_mbox/maven-users/";
		App app1 = new App();
		Document actualDoc = app1.connectUrl(url);
		app1.collectLinks(actualDoc, url);

	}
}