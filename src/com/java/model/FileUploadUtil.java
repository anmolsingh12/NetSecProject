package com.java.model;

import com.java.classes.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

import org.apache.commons.fileupload.FileItem;

public class FileUploadUtil {
	
	String fileName = null;

	private static String generateFileName(FileItem multiPart) {
        return  new Date().getTime() + "-" +new Random().nextLong()+ multiPart.getName().replace(" ", "_");
    }
	
	public static String uploadFile(FileItem multipartFile) {
        String fileName = generateFileName(multipartFile);
        try {
            uploadFileToLocalStorage(fileName, multipartFile);
        } catch (Exception e) {
           e.printStackTrace();
        }

        return "images/"+fileName;
    }
	
    private static boolean uploadFileToLocalStorage(String fileName,FileItem file) throws IOException, ClassNotFoundException, SQLException {
        File convFile = new File("/home/anmol/images/",fileName);
        long size = file.getSize();
        String fileSize = Long.toString(size);
		String fileType = file.getContentType();
		String filePath = "localhost:8080/NetSecProj/images/download/"+fileName;
		boolean result = false;
		System.out.println(fileName);
		System.out.println(fileSize);
		System.out.println(fileType);
		
		
		UserDAO newReq = new UserDAO();
		result = newReq.fileUploadInfo(fileName, fileSize, fileType, filePath);
        try {
            file.write(convFile);
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
