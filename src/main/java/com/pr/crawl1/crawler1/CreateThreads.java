package com.pr.crawl1.crawler1;

/**
 * Hello world!
 * 
 * @param <E>
 *
 */
class CreateThreads implements Runnable {
	/**
	 * 
	 */
	String url;

	CreateThreads() {

	}

	CreateThreads(String url) {
		this.url = url.toString();
	}

	public void run() {
		DownloadEmails de = new DownloadEmails();
		de.downloadEmails(url.toString());
	}

	public void createThreads(String[] urls, int n) {
		Thread[] threads = new Thread[12];
		CreateThreads[] app = new CreateThreads[12];
		for (int i = 0; i < n; i++) {
			app[i] = new CreateThreads(urls[i]);
			threads[i] = new Thread(app[i]);
			threads[i].start();
		}
	}
}