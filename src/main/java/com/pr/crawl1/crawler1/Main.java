package com.pr.crawl1.crawler1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Main {

	public Document connectUrl(String url) {
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
		return doc;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String url = "http://mail-archives.apache.org/mod_mbox/maven-users/";
		System.out.println("enter the year");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Main m = new Main();
		String year = br.readLine();
		Document actualDoc = m.connectUrl(url);
		CollectLinks cl = new CollectLinks();
		String[] urls = cl.collectLinks(actualDoc, url, year);
		CreateThreads ct = new CreateThreads();
		ct.createThreads(urls, cl.n);
		DeleteFiles df = new DeleteFiles();
		df.deleteFiles("/home/sanjeevn/arena/crawler1", ".txt");
	}
}
