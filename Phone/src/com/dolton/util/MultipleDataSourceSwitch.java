package com.dolton.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultipleDataSourceSwitch extends AbstractRoutingDataSource {

	private static final ThreadLocal<String> dataSourceKey = new ThreadLocal<String>();

	public static void setDataSourceKey(String dataSource) {
		dataSourceKey.set(dataSource);
	}

	@Override
	protected Object determineCurrentLookupKey() {
		 return dataSourceKey.get();
	}

}
