package com.ls.brand.dto.response;

import java.io.Serializable;

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
public class DingSignatureResp implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6555296812012687167L;

	@ApiModelProperty(value="签名")
	private String signature;
	
	@ApiModelProperty(value="随机串")
	private String nonstr;
	
	@ApiModelProperty(value="时间戳")
	private Long timestamp;
	
	@ApiModelProperty(value="企业id")
	@JsonProperty("corpId")
	private String corpId;
	
	@ApiModelProperty(value="agentId")
	@JsonProperty("agentId")
	private String agentId;
	
	
}
