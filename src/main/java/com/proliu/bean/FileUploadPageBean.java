package com.proliu.bean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
public class FileUploadPageBean implements Serializable{

	/**
	 * @author duliu
	 */
	private static final long serialVersionUID = 1L;
	
	private UploadedFile uploadedFile ;

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	
	/**
	 * This is basic file upload function, so don't add the primefaces file filter
	 * For fileupload1.xhtml page
	 * @throws IOException
	 */
	public void upload() throws IOException{
		System.out.println(uploadedFile.getFileName());
		saveFile(uploadedFile.getInputstream(), new File("c:/test.jpg"));
	}
	
	/**
	 * use primefaces file upload
	 * @param event
	 * @throws IOException 
	 */
	public void handleFileUpload(FileUploadEvent event) throws IOException{
		uploadedFile = event.getFile() ;
		System.out.println(event.getFile().getFileName());
		saveFile(event.getFile().getInputstream(), new File("C:/" + event.getFile().getFileName()));
	}
	
	
	public void saveFile(InputStream inputStream, File file){
		OutputStream out = null;
		try {
			inputStream = uploadedFile.getInputstream();
			out = new FileOutputStream(file);
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {
					out.write(bytes, 0, read);
			}
			out.flush();
		} catch (IOException e) {
			//e.printStackTrace();
		}finally{
			try {
				if(out != null)
					out.close();
				if(inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				//e.printStackTrace();
			}
		}
	}
}
