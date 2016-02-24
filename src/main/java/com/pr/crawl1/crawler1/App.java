package com.pr.crawl1.crawler1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Hello world!
 *
 */
class App implements Runnable {
	/**
	 * 
	 */
	String url;
	String year;

	App() {

	}

	App(String url) {
		this.url = url;
	}

	public void run() {
		downloadEmails(url);
	}

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
		int n = 0;
		final String[] urls = new String[12];
		DeleteFiles df = new DeleteFiles();
		df.deleteFiles("/home/sanjeevn/arena/crawler1", ".txt");
		Elements threadLinks = doc.select("a[href]");
		for (Element threadLink : threadLinks) {
			if (threadLink.toString().contains(year)
					&& threadLink.toString().contains("thread")) {
				url = url.concat(threadLink.attr("href").toString());
				urls[n++] = url;
				url = url.substring(0, 53);
			}
		}
		createThreads(urls, n);
	}

	public void createThreads(String[] urls, int n) {
		Thread[] threads = new Thread[12];
		App[] app = new App[12];
		for (int i = 0; i < n; i++) {
			app[i] = new App(urls[i]);
			threads[i] = new Thread(app[i]);
			threads[i].start();
		}
	}

	public void downloadEmails(String url) {
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
				CollectStates cs = new CollectStates(emailDoc);
				try {
					File file = new File(cs.getDate());
					FileWriter fileWriter = new FileWriter(
							file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fileWriter);
					bw.write("From :" + cs.getFrom());
					bw.write("\n\nsubject:" + cs.getSubject());
					bw.write("\n\ncontents:" + cs.getContent());
					bw.close();
					System.out.println(cs.getDate() + " is created");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				url = url.substring(0, 69);
			}
		}
	}
}