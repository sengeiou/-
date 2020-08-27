package com.informanage.oss.controller;

import com.alibaba.fastjson.JSONObject;
import com.informanage.common.MyException;
import com.informanage.common.R;
import com.informanage.common.ResultCodeEnum;
import com.informanage.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

@Api(description = "阿里云文件管理")
@CrossOrigin
@RestController
@RequestMapping("/manageoss/file")
@Slf4j
public class FileController {

	@Autowired
	private FileService fileService;

	@ApiOperation("文件上传")
	@PostMapping("/upload/{module}")
	public R upload(@ApiParam(value = "文件", required = true) @RequestParam("file") MultipartFile file,
					@ApiParam(value = "模块", required = true) @PathVariable("module") String module) {
		try {
			InputStream inputStream = file.getInputStream();
			String originalFilename = file.getOriginalFilename();
			String uploadUrl = fileService.upload(inputStream, module, originalFilename);

			return R.ok().message("文件上传成功").data("url", uploadUrl);
		} catch (Exception e) {
			log.error(ExceptionUtils.getMessage(e));
			throw new MyException(ResultCodeEnum.FILE_UPLOAD_ERROR);
		}
	}
}
