package com.ls.brand.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.ls.brand.domain.User;
import com.ls.brand.dto.request.UserRequest;
import com.ls.brand.dto.response.UserResp;
import com.ls.brand.util.MyMapper;

@Mapper
public interface UserMapper extends MyMapper<User> {

	@Select("<script>" + "SELECT id,username,age,create_time,update_time" + " FROM user where 1=1 "
			+ "<if test=\"username != null and username !=''\"> "
			+ "AND username like CONCAT('%',#{username,jdbcType=VARCHAR},'%') " + "</if> "
			+ "<if test=\"age != null \"> " + "AND age = #{age}  " + "</if> "
			+ "<if test=\"createTimeStart !=null and createTimeStart!='' and createTimeEnd != null and createTimeEnd != ''\"> "
			+ "AND create_time between #{createTimeStart} and  #{createTimeEnd}  " + "</if> " + "</script>")
	@Results(id = "userResult", value = { @Result(property = "createTime", column = "create_time"),
			@Result(property = "updateTime", column = "update_time") })
	public List<UserResp> page(@Param(value = "username") String username, @Param(value = "age") Integer age,
			@Param(value = "createTimeStart") String createTimeStart,
			@Param(value = "createTimeEnd") String createTimeEnd);

	@Select("<script>" + "SELECT id,username,age,create_time,update_time" + " FROM user where 1=1 "
			+ "<if test=\"userRequest.username != null and userRequest.username !=''\"> "
			+ "AND username like CONCAT('%',#{userRequest.username},'%') " + "</if> "
			+ "<if test=\"userRequest.age != null \"> " + "AND age = #{userRequest.age} " + "</if> "
			+ "<if test=\"userRequest.createTimeStart != null and userRequest.createTimeStart != '' and userRequest.createTimeEnd != null and userRequest.createTimeEnd != ''\"> "
			+ "AND create_time between #{userRequest.createTimeStart} and #{userRequest.createTimeEnd} " + "</if> "
			+ "</script>")
	@ResultMap(value = "userResult")
	public List<UserResp> page2(@Param(value = "userRequest") UserRequest userRequest);

}
