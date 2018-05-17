package com.ls.brand.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.ls.brand.constants.ResultCode;
import com.ls.brand.domain.User;
import com.ls.brand.dto.request.UserRequest;
import com.ls.brand.dto.response.PageUserResp;
import com.ls.brand.dto.response.UserResp;
import com.ls.brand.exception.BrandException;
import com.ls.brand.mapper.UserMapper;
import com.ls.brand.util.SnowflakeIdWorker;

import tk.mybatis.mapper.entity.Condition;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public UserResp findOne(Long id) {
		User user = userMapper.selectByPrimaryKey(id);
		if (user == null)
			return null;
		UserResp resp = new UserResp();
		BeanUtils.copyProperties(user, resp);
		return resp;
	}

	@Transactional
	public int add(UserRequest userRequest) {
		if (userRequest == null)
			throw new BrandException(ResultCode.PARAM_NOT_NULL);
		User user = new User();
		BeanUtils.copyProperties(userRequest, user);
		user.setId(SnowflakeIdWorker.getSnowflakeIdWorker().nextId());
		Date date = new Date();
		user.setCreateTime(date);
		user.setUpdateTime(date);
		return userMapper.insertSelective(user);
	}

	@Transactional
	public void update(UserRequest userRequest) {
		if (userRequest == null || StringUtils.isEmpty(userRequest.getId()))
			throw new BrandException(ResultCode.PARAM_NOT_NULL);
		User user = userMapper.selectByPrimaryKey(userRequest.getId());
		if (user == null)
			throw new BrandException(ResultCode.USER_IS_NULL);
		user.setUsername(userRequest.getUsername());
		user.setAge(userRequest.getAge());
		Date date = new Date();
		user.setUpdateTime(date);
		userMapper.updateByPrimaryKeySelective(user);
	}

	@Transactional
	public void del(Long id) {
		userMapper.deleteByPrimaryKey(id);
	}

	@SuppressWarnings("rawtypes")
	public PageUserResp page(Integer pageNum, Integer pageSize, UserRequest userRequest) {
		PageUserResp resp = new PageUserResp();
		Page page = PageHelper.startPage(pageNum, pageSize);
		Condition condition = new Condition(User.class);
		if (!StringUtils.isEmpty(userRequest.getUsername())) {
			condition.and().andLike("username", "%" + userRequest.getUsername() + "%");
		}

		if (!StringUtils.isEmpty(userRequest.getAge())) {
			condition.and().andEqualTo("age", userRequest.getUsername());
		}

		if (!StringUtils.isEmpty(userRequest.getCreateTimeStart())
				&& !StringUtils.isEmpty(userRequest.getCreateTimeEnd())) {
			condition.and().andBetween("createTime", userRequest.getCreateTimeStart(), userRequest.getCreateTimeEnd());
		}

		List<User> list = userMapper.selectByExample(condition);
		if (CollectionUtils.isEmpty(list))
			return resp;
		resp.setTotalPage(page.getTotal());
		List<UserResp> resultList = Lists.newArrayListWithCapacity(pageSize);
		for (User user : list) {
			UserResp userResp = new UserResp();
			BeanUtils.copyProperties(user, userResp);
			resultList.add(userResp);
		}
		resp.setList(resultList);
		return resp;
	}

	@SuppressWarnings("rawtypes")
	public PageUserResp page2(Integer pageNum, Integer pageSize, UserRequest userRequest) {
		PageUserResp resp = new PageUserResp();
		Page page = PageHelper.startPage(pageNum, pageSize);
		// List<UserResp> list =
		// userMapper.page(userRequest.getUsername(),userRequest.getAge(),userRequest.getCreateTimeStart(),userRequest.getCreateTimeEnd());
		List<UserResp> list = userMapper.page2(userRequest);
		resp.setTotalPage(page.getTotal());
		resp.setList(list);
		return resp;
	}
}
