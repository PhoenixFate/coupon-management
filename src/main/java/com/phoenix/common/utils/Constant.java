package com.phoenix.common.utils;

public class Constant {
	
	public interface RefundFlag{
		/**
		 * 已退
		 */
		public static String yt = "1";
		
		/**
		 * 未退
		 */
		public static String wt = "0";
	}
	
	public interface ConsumeStatus{
		/**
		 * 核销成功
		 */
		public static String hxcg = "1";
		
		/**
		 * 撤销
		 */
		public static String cx = "0";
	}
	
	
	public interface CouponStatus{
		/**
		 * 未使用
		 */
		public static String WSY = "0";
		
		/**
		 * 已使用
		 */
		public static String YSY = "1";
		
		/**
		 * 已冻结
		 */
		public static String YDJ = "2";
		
		/**
		 * 已过期
		 */
		public static String YGQ = "3";
	}
	
	/**
	 * 提现标记， 1 已提现， 0 未提现
	 * @author tw
	 * 2019年7月24日
	 */
	public interface CashOutFlag{
		/**
		 * 已提现
		 */
		public static String ytx = "1";
		
		/**
		 * 未提现
		 */
		public static String wtx = "0";
	}
	
	
	/**
	 * 服务包状态
	 * @author tw
	 * 2019年7月29日
	 */
	public interface PackageStatus{
		
		/**
		 *未启用
		 */
		public static String wqy = "0";
		/**
		 * 启用
		 */
		public static String qy = "1";
		
		/**
		 * 停用
		 */
		public static String ty = "2";
	}
	
	/**
	 * 产品状态
	 * @author tw
	 * 2019年7月29日
	 */
	public interface ProductStatus{
		
		/**
		 *未启用
		 */
		public static String wqy = "0";
		/**
		 * 启用
		 */
		public static String qy = "1";
		
		/**
		 * 停用
		 */
		public static String ty = "2";
	}
	
	/**
	 * 
		 * 类描述 证件类型
	 	 * @version  1.0
		 * @author  phoenix
		 * @version  2019年2月28日 上午10:15:01
	 */
	public interface CardType{
		
		/**
		 * 身份证
		 */
		public static String SFZ = "1";
		
		/**
		 * 医保卡
		 */
		public static String YBK = "2";
		
		/**
		 * 院内就诊卡
		 */
		public static String YNJZK = "3";
		
		/**
		 * 居民健康卡
		 */
		public static String JMJKK = "4";
		
	}
	
	/**
	 * 
		 * 类描述 业务类型
	 	 * @version  1.0
		 * @author  phoenix
		 * @version  2019年6月20日 上午11:09:05
	 */
	public interface BizCode{
		
		/**
		 * 取号
		 */
		public static String QH = "1";
		
		/**
		 * 挂号
		 */
		public static String GH = "2";
		
		/**
		 * 缴费
		 */
		public static String JF = "3";
		
		/**
		 * 预约
		 */
		public static String YY = "4";
		
	}
	
	/**
	 * 
		 * 类描述 
	 	 * @version  1.0
		 * @author  phoenix
		 * @version  2019年2月28日 上午9:59:17
	 */
	public interface EnableFlag{
		/**
		 * 正常
		 */
		public static String ZC = "1";
		
		/**
		 * 冻结
		 */
		public static String DJ = "2";
		
		/**
		 * 停用
		 */
		public static String TY = "3";
	}
	
	
	/**
	 * 
		 * 类描述 充值状态
	 	 * @version  1.0
		 * @author  phoenix
		 * @version  2019年6月4日 上午10:26:03
	 */
	public interface RechargeStatus{
		/**
		 * 待充值
		 */
		public static String DCZ = "2";
		
		/**
		 * 成功
		 */
		public static String CG = "1";
		
		/**
		 * 失败
		 */
		public static String SB = "0";
	}
	
	/**
	 * 
		 * 类描述 退值状态
	 	 * @version  1.0
		 * @author  phoenix
		 * @version  2019年6月4日 下午1:29:12
	 */
	public interface ReturnStatus{

		
		/**
		 * 已退
		 */
		public static String YT = "1";
		
		/**
		 * 未退
		 */
		public static String WT = "0";
		
		/**
		 * 处理中
		 */
		public static String CLZ = "2";
		
		/**
		 * 放弃
		 */
		public static String FQ = "3";
	}
	
	/**
	 * 
		 * 类描述 支付方式
	 	 * @version  1.0
		 * @author  phoenix
		 * @version  2019年3月8日 下午2:53:02
	 */
	public static interface PAY_METHOD{
		
		/**
		 * 现金
		 */
		public static String XJ = "01";
		
		/**
		 * 支付宝
		 */
		public static String ZFB = "02";
		
		/**
		 * 微信
		 */
		public static String WX = "03";
		
		/**
		 * 银行卡
		 */
		public static String YH = "04";
		
		/**
		 * 医保
		 */
		public static String YB = "06";
		
		/**
		 * 医账通支付
		 */
		public static String YZT = "05";
		
		
		/**
		 * 银联聚合支付
		 */
		public static String YLJHZF = "11";
		
		/**
		 * 银联闪付APP支付
		 */
		public static String YLAPP = "12";
		
		/**
		 * 工行聚合支付
		 */
		public static String GHJHZF = "13";
		
	}

}
