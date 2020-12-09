package com.cooperado.assembleia.cors.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("assembleia")
public class AssembleiaApiProperty {

	private String originPermitida = "*";
	
	public String getOriginPermitida() {
		return originPermitida;
	}
	
	public void setOriginPermitida(String originPermitida) {
		this.originPermitida = originPermitida;
	}
}
