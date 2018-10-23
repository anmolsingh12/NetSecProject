package com.java.servlet;

import java.io.*;
import java.sql.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.java.model.FileUploadUtil;

@WebServlet("/FileUpload")
@MultipartConfig(maxFileSize = 16177215)
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InputStream inputStream = null; // input stream of the upload file
 
			String fileName = null;
			String fileSize = null;
			String fileType = null;

			
			if(ServletFileUpload.isMultipartContent(request)) {
	            try {
	                String fname = null;
	                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
	                for(FileItem item : multiparts){
	                    if(!item.isFormField()) {
	                        fileName = FileUploadUtil.uploadFile(item);
	                        System.out.println(fileName);
	                    }
	                }
	                response.sendRedirect("uploadSuccess.jsp");
	            } catch (Exception ex) {
	                System.out.println("CATCH");
	            }
	        }
	}
}
