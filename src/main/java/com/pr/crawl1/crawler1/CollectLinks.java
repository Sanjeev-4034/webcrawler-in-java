package com.pr.crawl1.crawler1;

import java.io.IOException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CollectLinks {
	int n = 0;

	public String[] collectLinks(Document doc, String url, String year)
			throws IOException {
		String[] urls = new String[12];
		Elements threadLinks = doc.select("a[href]");
		for (Element threadLink : threadLinks) {
			if (threadLink.toString().contains(year)
					&& threadLink.toString().contains("thread")) {
				url = url.concat(threadLink.attr("href").toString());
				urls[n++] = url;
				url = url.substring(0, 53);
			}
		}
		return urls;
	}
}
