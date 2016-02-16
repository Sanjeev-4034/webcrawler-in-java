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

public Document connectUrl(String url)
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
public void collectLinks(Document doc,String url) throws IOException
{
	int i=0;
	System.out.println("enter the year");
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	String year=br.readLine();
	DeleteFiles df=new DeleteFiles();
    df.deleteFiles("/home/sanjeevn/arena/crawler1", ".txt");
	Elements links=doc.select("a[href]");
	for(Element link : links)
	{
		if(link.toString().contains(year) && link.toString().contains("thread"))
		{
		url=url.concat(link.attr("href").toString());
		Document tempDoc1=connectUrl(url);
		Elements tempLinks1=tempDoc1.select("a[href]");
		for(Element tempLink1 : tempLinks1)
		{
			if(tempLink1.toString().contains("%"))
			{
				url=url.replaceAll("/thread","/raw/");
				url=url.concat(tempLink1.attr("href").toString());
				Document tempDoc2=connectUrl(url);
				String fileName="file"+i+".txt";
				i++;
				FileOutputStream fileOut=null;
				ObjectOutputStream objectOut;
				try {
					 fileOut = new FileOutputStream(fileName);
					objectOut = new ObjectOutputStream(fileOut);
					objectOut.writeObject(tempDoc2.text());
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
	Document actualDoc=app1.connectUrl(url);
		app1.collectLinks(actualDoc,url);
	
}	
}