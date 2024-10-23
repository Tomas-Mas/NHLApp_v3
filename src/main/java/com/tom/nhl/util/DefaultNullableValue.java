package com.tom.nhl.util;

public class DefaultNullableValue {
	
	private final static int DEFAULT_INT_VALUE_WHEN_NULL = 0;
	
	public static int getInt(Integer integer) {
		return integer == null ? DEFAULT_INT_VALUE_WHEN_NULL : integer;
	}

}
