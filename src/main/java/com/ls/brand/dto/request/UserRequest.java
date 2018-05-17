package com.ls.brand.dto.request;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class UserRequest implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1044412886977897773L;

	
	@ApiModelProperty(value="ID")
	private Long id;
	
	@ApiModelProperty(value="用户名")
	@NotBlank(message="username不能为空")
	@Length(min = 1, max = 64, message = "请输入1~64长度的用户名")
	private String username;
	
	@ApiModelProperty(value="年龄")
	@NotNull(message="age不能为空")
	@Min(1)
    @Max(999)
	private Integer age;
	
	@ApiModelProperty(value = "开始时间")
	@JsonProperty("createTimeStart")
	@Pattern(regexp="[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}",message="时间格式为yyyy-MM-dd HH:mm:ss")
	private String createTimeStart;
	@ApiModelProperty(value = "结束时间")
	@Pattern(regexp="[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}",message="时间格式为yyyy-MM-dd HH:mm:ss")
	@JsonProperty("createTimeEnd")
	private String createTimeEnd;
	
}
