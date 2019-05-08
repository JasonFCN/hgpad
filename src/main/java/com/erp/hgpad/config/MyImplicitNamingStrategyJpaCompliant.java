package com.erp.hgpad.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.spi.MetadataBuildingContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyImplicitNamingStrategyJpaCompliant extends ImplicitNamingStrategyJpaCompliantImpl{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2667884290347986145L;

	@Override
	protected Identifier toIdentifier(String stringForm, MetadataBuildingContext buildingContext) {
		// TODO Auto-generated method stub
		return super.toIdentifier("f_"+stringForm, buildingContext);
	}
	
}
