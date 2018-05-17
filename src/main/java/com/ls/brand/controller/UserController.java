package com.ls.brand.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ls.brand.dto.JSONResult;
import com.ls.brand.dto.request.UserRequest;
import com.ls.brand.dto.response.PageUserResp;
import com.ls.brand.dto.response.UserResp;
import com.ls.brand.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController//web项目  return需要返回到某个页面    使用@Controller
@Api("商标管理")
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	/**
	 * 注：@GetMapping("/{id}")是spring 4.3的新注解等价于：
	 * 
	 * @RequestMapping(value = "/id", method = RequestMethod.GET)
	 *                       类似的注解还有@PostMapping等等
	 * @param id
	 * @return user信息
	 */
	@GetMapping("/user/{id}")
	@ApiOperation(value = "主键查询", notes = "主键查询", response = UserResp.class, tags = { "user"})
	public JSONResult<UserResp> findById(@PathVariable(value="id") Long id) {
		return JSONResult.ok(userService.findOne(id));
	}

	
	@PostMapping("/user")
	@ApiOperation(value = "新增", notes = "新增", tags = { "user"})
	public JSONResult<?> add(@RequestBody UserRequest userRequest){
		userService.add(userRequest);
		return JSONResult.ok();
	}
	
	@PutMapping("/user")
	@ApiOperation(value = "修改", notes = "修改", tags = { "user"})
	public JSONResult<?> update(@Validated @RequestBody UserRequest userRequest){
		userService.update(userRequest);
		return JSONResult.ok();
	}
	
	@DeleteMapping("/user/{id}")
	@ApiOperation(value = "删除", notes = "删除", tags = { "user"})
	public JSONResult<?> del(@PathVariable(value="id") Long id){
		userService.del(id);
		return JSONResult.ok();
	}
	
	@PostMapping("/page")
	@ApiOperation(value = "分页条件查询", notes = "分页条件查询", tags = { "user"})
	public JSONResult<PageUserResp> page(@RequestParam(value="pageNum",required=true)Integer pageNum,
										 @RequestParam(value="pageSize",required=true)Integer pageSize,
										 @RequestBody UserRequest userRequest){
		return JSONResult.ok(userService.page(pageNum,pageSize,userRequest));
	}
	
	@PostMapping("/page2")
	@ApiOperation(value = "分页条件查询2", notes = "分页条件查询2", tags = { "user"})
	public JSONResult<PageUserResp> page2(@RequestParam(value="pageNum",required=true)Integer pageNum,
										 @RequestParam(value="pageSize",required=true)Integer pageSize,
										 @Validated @RequestBody UserRequest userRequest){
		return JSONResult.ok(userService.page2(pageNum,pageSize,userRequest));
	}
	
}
