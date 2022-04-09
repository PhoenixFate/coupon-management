package com.phoenix.core.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import org.apache.commons.lang3.StringUtils;

public class AmountUtil {
    public AmountUtil() {
    }

    public static String getYuanByFen(int... fens) {
        String format = "#0.00";
        BigDecimal bd1 = new BigDecimal(0);

        for(int i = 0; i < fens.length; ++i) {
            bd1 = bd1.add(new BigDecimal(fens[i]));
        }

        bd1 = bd1.divide(new BigDecimal(100.0D));
        bd1 = bd1.setScale(2, 4);
        DecimalFormat fmt = new DecimalFormat(format);
        return fmt.format(bd1.doubleValue());
    }

    public static String getFenByYuan(double... yuans) {
        String format = "##";
        BigDecimal bd1 = new BigDecimal(0);

        for(int i = 0; i < yuans.length; ++i) {
            bd1 = bd1.add(new BigDecimal(yuans[i]));
        }

        bd1 = bd1.multiply(new BigDecimal(100.0D));
        bd1 = bd1.setScale(0, 4);
        DecimalFormat fmt = new DecimalFormat(format);
        return fmt.format(bd1.doubleValue());
    }

    public static void main(String[] args) {
        String dd = null;
        System.out.println(formatFen("0.00"));
    }

    public static String getYuanByFen(String... fens) throws NumberFormatException {
        String format = "#0.00";
        int total = 0;

        for(int i = 0; i < fens.length; ++i) {
            total += Integer.parseInt(fens[i]);
        }

        DecimalFormat decimal = new DecimalFormat(format);
        return decimal.format((double)total / 100.0D);
    }

    public static String getFenByYuan(String... yuans) throws NumberFormatException {
        String format = "#";
        double total = 0.0D;

        for(int i = 0; i < yuans.length; ++i) {
            if (StringUtils.isNotEmpty(yuans[i])) {
                total += Double.parseDouble(yuans[i]);
            }
        }

        DecimalFormat decimal = new DecimalFormat(format);
        return decimal.format(total * 100.0D);
    }

    public static String fenToYuan(String total) {
        if (total != null && total.trim().length() != 0) {
            DecimalFormat df = new DecimalFormat("0.00");
            return df.format(Double.parseDouble(total) / 100.0D);
        } else {
            return "0";
        }
    }

    public static String yuanToFen(String total) {
        if (total != null && total.trim().length() != 0) {
            DecimalFormat df = new DecimalFormat("0");
            return df.format(Double.parseDouble(total) * 100.0D);
        } else {
            return "0";
        }
    }

    public static String formatFen(String total) {
        if (total != null && total.trim().length() != 0) {
            DecimalFormat df = new DecimalFormat("0");
            return df.format(Double.parseDouble(total));
        } else {
            return "0";
        }
    }

    public static String addMoney(String m1, String m2) {
        if (m1 != null && m1.trim().length() != 0) {
            if (m2 != null && m2.trim().length() != 0) {
                DecimalFormat df = new DecimalFormat("0.00");
                return df.format(Double.parseDouble(m1) + Double.parseDouble(m2));
            } else {
                return m1;
            }
        } else {
            return m2;
        }
    }

    public static String multiplyMoney(String m1, String m2) {
        if (m1 != null && m1.trim().length() != 0) {
            if (m2 != null && m2.trim().length() != 0) {
                DecimalFormat df = new DecimalFormat("0.00");
                return df.format(Double.parseDouble(m1) * Double.parseDouble(m2));
            } else {
                return m1;
            }
        } else {
            return m2;
        }
    }

    public static String getFormateFee(String fee, String format) {
        if (fee != null && !"".equals(fee)) {
            try {
                DecimalFormat df = new DecimalFormat(format);
                return df.format(Double.parseDouble(fee));
            } catch (IllegalArgumentException var3) {
                var3.printStackTrace();
                return "";
            }
        } else {
            return "";
        }
    }

    public static String getSubPrice(String price1, String price2) {
        double d1 = Double.parseDouble(price1);
        double d2 = Double.parseDouble(price2);
        DecimalFormat df = new DecimalFormat("0");
        return df.format(d1 - d2);
    }

    public static String getNeedPay(String balance, String price) {
        double d1 = Double.parseDouble(balance);
        double d2 = Double.parseDouble(price);
        if (d1 <= d2 && Double.compare(d1, d2) != 0) {
            DecimalFormat df = new DecimalFormat("0");
            return df.format(d2 - d1);
        } else {
            return "0";
        }
    }
}
