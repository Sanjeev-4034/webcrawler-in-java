/**
 * 
 */
package com.pr.crawl1.crawler1;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @author sanjeevn
 *
 */
public class DeleteFiles {

	/**
	 * @param args
	 */
	public void deleteFiles(String dir,String ext)
	{
		ExtensionFilter extensionFilter=new ExtensionFilter(ext);
		File directory=new File(dir);
		String[] list=directory.list(extensionFilter);
		File file;
		if(list.length!=0){
		for(int i=0;i<list.length;i++)
		{
			System.out.println(dir + list[i]);
			file=new File(list[i]);
			boolean isdeleted=file.delete();
		}
		}
	}
	   class ExtensionFilter implements FilenameFilter {
		   
		     private String extension;
		 
		     public ExtensionFilter( String extension ) {
		       this.extension = extension;             
		     }

		     public boolean accept(File dir, String name) {
		         return (name.endsWith(extension));
		       }
		   }

}
