package com.pr.crawl1.crawler1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.jsoup.nodes.Document;

public class Tester {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String url = "http://mail-archives.apache.org/mod_mbox/maven-users/";
		App app1 = new App();
		System.out.println("enter the year");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		app1.year = br.readLine();
		Document actualDoc = app1.connectUrl(url);
		app1.collectLinks(actualDoc, url);

	}
}
