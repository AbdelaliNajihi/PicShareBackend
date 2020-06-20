package com.pic.share.service.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/pictures")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PicturesRestController {
	@Value("${posts}")
	private String posts;
	@Value("${users}")
	private String users;
	
	@GetMapping(value = "/user", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getUserPicture(@RequestParam(name = "userId") Long userId) throws FileNotFoundException, IOException {
		File file = new File(users+userId);
		if (!file.exists()) {
			throw new FileNotFoundException("File Not Found !");
		}
		return IOUtils.toByteArray(new FileInputStream(file));
	}
	
	@GetMapping(value = "/post", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPostPicture(@RequestParam(name = "postId") Long postId) throws FileNotFoundException, IOException {
		File file = new File(posts+postId);
		if (!file.exists()) {
			throw new FileNotFoundException("File Not Found !");
		}
		return IOUtils.toByteArray(new FileInputStream(file));
	}

}
