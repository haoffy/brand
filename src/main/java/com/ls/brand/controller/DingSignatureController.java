package com.ls.brand.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dingtalk.open.client.api.model.corp.CorpUserDetail;
import com.ls.brand.dingSdk.AuthHelper;
import com.ls.brand.dingSdk.UserHelper;
import com.ls.brand.dto.JSONResult;
import com.ls.brand.dto.response.DingSignatureResp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("钉钉免登陆签名接口")
public class DingSignatureController {
	
	
	@GetMapping("/signature")
	@ApiOperation(value = "钉钉签名", notes = "钉钉签名", response = DingSignatureResp.class, tags = { "dingding"})
	public JSONResult<DingSignatureResp> signature(@RequestParam String url) {
		return JSONResult.ok(AuthHelper.getConfig(url));
	}
	
	
	
	@GetMapping("/dingUserInfo")
	@ApiOperation(value = "获取钉钉用户信息", notes = "获取钉钉用户信息", response = CorpUserDetail.class, tags = { "dingding"})
	public JSONResult<CorpUserDetail> getDingUserInfo(@RequestParam String code) throws Exception {
		String accessToken = AuthHelper.getAccessToken();
        CorpUserDetail user = UserHelper.getUser(accessToken, UserHelper.getUserInfo(accessToken, code).getUserid());
		return JSONResult.ok(user);
	}
	

}
