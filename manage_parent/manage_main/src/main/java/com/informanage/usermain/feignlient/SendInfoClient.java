package com.informanage.usermain.feignlient;

import com.informanage.common.R;
import com.informanage.usermain.feignlient.Impl.SendInfoClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "manage-sendinfo", fallback = SendInfoClientImpl.class)
public interface SendInfoClient {
	@PostMapping(value = "/sendinfo/sendApplyResult/{email}")
	public R sendApplyResult(@PathVariable("email") String email, @RequestParam String message);
}
