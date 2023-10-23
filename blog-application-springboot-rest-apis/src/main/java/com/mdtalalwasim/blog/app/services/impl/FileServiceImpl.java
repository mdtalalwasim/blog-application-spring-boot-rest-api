package com.mdtalalwasim.blog.app.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mdtalalwasim.blog.app.services.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		
		//File name
		String name = file.getOriginalFilename();
		
		
		//random file name generator
		String randomID = UUID.randomUUID().toString();
		//abc.png
		String fileName1 = randomID.concat(name.substring(name.lastIndexOf(".")));
		
		
		//Fullpath
		String filePath = path + File.separator + fileName1;
		
		//create folder if not created
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}
		
		//File copy
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		
		
		return fileName1;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		
		String fullPath = path + File.separator+fileName;
		
		InputStream inputStream = new FileInputStream(fullPath);
		
		return inputStream;
	}

}
