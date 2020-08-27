package com.informanage.user.feignlient;

import com.informanage.common.R;
import com.informanage.user.feignlient.Impl.OssClientImpl;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;



import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Component
@FeignClient(name = "manage-oss", fallback = OssClientImpl.class)
public interface OssClient {
	@PostMapping(value = "/manageoss/file/upload/{module}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public R upload(@RequestPart(value = "file", required = true) MultipartFile file,
					@PathVariable("module") String module);
	/**
	 * 引用配置类MultipartSupportConfig.并且实例化
	 */
	class MultipartSupportConfig {
		@Bean
		public Encoder multipartFormEncoder() {
			return new SpringFormEncoder(new SpringEncoder(new ObjectFactory<HttpMessageConverters>() {
				@Override
				public HttpMessageConverters getObject() throws BeansException {
					return new HttpMessageConverters(new RestTemplate().getMessageConverters());
				}
			}));
		}
		}


}
