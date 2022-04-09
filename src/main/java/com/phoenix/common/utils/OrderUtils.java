package com.phoenix.common.utils;

import java.math.BigDecimal;


public class OrderUtils {
	
	/**
	 * 元转分
	 * @return
	 */
	public static BigDecimal yuanToFen(Object amountYuan) {
		if (null == amountYuan) {
			return BigDecimal.ZERO;
		}
		return new BigDecimal(amountYuan.toString()).multiply(new BigDecimal(100));
	}
	
	public static int yuanToFenInt(Object amountYuan) {
		return yuanToFen(amountYuan).intValue();
	}
	
	public static String yuanToFenString(Object amountYuan) {
		return String.valueOf(yuanToFenInt(amountYuan));
	}
	
	/**
	 * 分转元
	 * @param amountYuan
	 * @return
	 */
	public static BigDecimal fenToYuan(Object amountFen) {
		if (null == amountFen) {
			return BigDecimal.ZERO;
		}
		return new BigDecimal(amountFen.toString()).divide(new BigDecimal(100));
	}
	
	public static float fenToYuanFloat(Object amountFen) {
		return fenToYuan(amountFen).floatValue();
	}
	
	public static String fenToYuanString(Object amountFen) {
		return String.valueOf(fenToYuanFloat(amountFen));
	}
}
