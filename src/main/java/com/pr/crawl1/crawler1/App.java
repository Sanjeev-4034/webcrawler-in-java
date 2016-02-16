package com.pr.crawl1.crawler1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Hello world!
 *
 */class App extends DeleteFiles implements Serializable
 {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public Document connect_to_url(String url)
{
	Document doc=null;
	try
	{
		doc=Jsoup.connect(url).get();
	}
	catch(IOException ie)
	{
		ie.printStackTrace();
	}
	return doc;
}
public void collect_links(Document doc,String url) throws IOException
{
	int i=0;
	System.out.println("enter the year");
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	String year=br.readLine();
	/*File file = new File(year);
	if (!file.exists()) {
		if (file.mkdir()) {
			System.out.println("Directory is created!");
		} else {
			System.out.println("Failed to create directory!");
		}
	}*/
	DeleteFiles df=new DeleteFiles();
    df.deleteFiles("/home/sanjeevn/arena/crawler1", ".txt");
	Elements links=doc.select("a[href]");
	for(Element link : links)
	{
		if(link.toString().contains(year) && link.toString().contains("thread"))
		{
		url=url.concat(link.attr("href").toString());
		Document temp_doc_1=connect_to_url(url);
		Elements temp_links_1=temp_doc_1.select("a[href]");
		for(Element temp_link_1 : temp_links_1)
		{
			if(temp_link_1.toString().contains("%"))
			{
				url=url.replaceAll("/thread","/raw/");
				url=url.concat(temp_link_1.attr("href").toString());
				Document temp_doc_2=connect_to_url(url);
				String file_name="file"+i+".txt";
				i++;
				FileOutputStream fileOut=null;
				ObjectOutputStream objectOut;
				try {
					 fileOut = new FileOutputStream(file_name);
					objectOut = new ObjectOutputStream(fileOut);
					objectOut.writeObject(temp_doc_2.text());
					System.out.println("file is created");
					} 
			    catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				url=url.substring(0,69);
			}
		}
		url=url.substring(0,53);
		}
	}
}

public static void main(String[] args) throws IOException  {
	String url="http://mail-archives.apache.org/mod_mbox/maven-users/";
	App app1=new App();
	Document actual_doc=app1.connect_to_url(url);
		app1.collect_links(actual_doc,url);
	
}	
}