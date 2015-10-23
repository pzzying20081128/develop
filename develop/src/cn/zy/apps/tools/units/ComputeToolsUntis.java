package cn.zy.apps.tools.units ;

import java.math.BigDecimal ;
import java.math.MathContext ;
import java.math.RoundingMode ;
import java.text.DecimalFormat ;

public class ComputeToolsUntis {

    public static double multiply(double multiply, double multiplycand, int scale) {

        MathContext mc = new MathContext(scale, RoundingMode.HALF_UP) ;

        BigDecimal multiply_ = new BigDecimal(multiply) ;

        BigDecimal multiplycand_ = new BigDecimal(multiplycand) ;

        BigDecimal result = multiply_.multiply(multiplycand_) ;

        BigDecimal xxx = result.setScale(scale, BigDecimal.ROUND_HALF_UP) ;
        //        System.out.println("==>  " +xxx.toString() ) ;
        return Double.valueOf(xxx.toString()) ;

    }
    
    public static double multiply(Long multiply, Long multiplycand, int scale) {

        MathContext mc = new MathContext(scale, RoundingMode.HALF_UP) ;

        BigDecimal multiply_ = new BigDecimal(multiply) ;

        BigDecimal multiplycand_ = new BigDecimal(multiplycand) ;

        BigDecimal result = multiply_.multiply(multiplycand_) ;

        BigDecimal xxx = result.setScale(scale, BigDecimal.ROUND_HALF_UP) ;
        //        System.out.println("==>  " +xxx.toString() ) ;
        return Double.valueOf(xxx.toString()) ;

    }

    public static Long multiplyGetLong(double multiply, Long multiplycand, int scale) {

        MathContext mc = new MathContext(scale, RoundingMode.HALF_UP) ;

        BigDecimal multiply_ = new BigDecimal(multiply) ;

        BigDecimal multiplycand_ = new BigDecimal(multiplycand) ;

        BigDecimal result = multiply_.multiply(multiplycand_) ;

        BigDecimal xxx = result.setScale(scale, BigDecimal.ROUND_HALF_UP) ;
       
        return xxx.longValue() ;

    }

    /**
     * 除
     * @param divisor  除数
     * @param dividend 被除数
     * @param scale  精度 保留的小数位
     * @return
     */
    public static double computePercent(Long divisor, Long dividend, int scale) {

        if (divisor == null || dividend.intValue() == 0) return 0.00 ;

        BigDecimal divisor_ = new BigDecimal(divisor) ;

        BigDecimal dividend_ = new BigDecimal(dividend) ;

        BigDecimal result_ = dividend_.divide(divisor_, scale, BigDecimal.ROUND_HALF_UP) ;

        return result_.doubleValue() ;
    }

    public static double computePercent(Long divisor, Long dividend) {

        return computePercent(divisor, dividend, 2) ;
    }

    public static void main(String[] args) {
        double xx = ComputeToolsUntis.computePercent(100L, 1200L, 2) ;

        //        DecimalFormat df = new DecimalFormat("######.00"); // 保留几位小数

        System.out.println("  " + xx) ;
        //        xx = ComputeToolsUntis.multiply(xx , 100.00,2);
        //        System.out.println("  "+xx) ;
    }

}
