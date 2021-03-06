package cn.zy.apps.tools.units.moneys ;

import java.text.DecimalFormat ;

import cn.zy.apps.tools.units.ComputeToolsUntis ;

public class BuildMoneyFactory implements IBuildMoneyFactory {

    private static IBuildMoneyFactory buildMoney = new BuildMoneyFactory() ;

    public static IBuildMoneyFactory getBuildMoney() {
        return buildMoney ;
    }

    public int Default_Decimal_Digit = 4 ;

    private DecimalFormat decimalFormat ;

    public BuildMoneyFactory() {
        String decimalFormat_String = "######0.00" ;

        for (int i = 0; i < Decimal_Digit - 2; i++) {
            decimalFormat_String = decimalFormat_String + "0" ;
        }

        DecimalFormat df = new DecimalFormat(decimalFormat_String) ;
        this.decimalFormat = df ;
    }

    @Override
    public Long switchMoneyToLongPrecision(Double inMoney) {

        Long result = ComputeToolsUntis.multiplyGetLong(inMoney, precision, 0) ;

        return result ;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <V> V switchLongPrecisionToMoney(Long moneyLong, Class<V> resultType) {

        if (moneyLong == null) return null ;

        Double result = ComputeToolsUntis.computePercent(precision, moneyLong, Default_Decimal_Digit) ;

        if (resultType.equals(Double.class)) {
            return (V) result ;
        }

        if (resultType.equals(String.class)) {
            return (V) decimalFormat.format(result) ;
        }

        throw new RuntimeException("switchLongPrecisionToMoney   resultType  error  is  [Double   ,  String ]  ") ;
    }

    //    public static void main(String[] args) {
    //
    //        IBuildMoneyFactory buildMoneyFactory = new BuildMoneyFactory() ;
    //
    //        Long xx = 4952L ;
    //
    //        String result = buildMoneyFactory.switchLongPercentToRate(xx, String.class) ;
    //
    //        //        String result_ = buildMoneyFactory.switchLongPrecisionToMoney(result, String.class) ;
    //
    //        System.out.println("==2>   " + result.toString()) ;
    //
    //    }

    @Override
    public Long switchRateToLongPercent(Double rate) {
        Long result = ComputeToolsUntis.multiplyGetLong(rate, percent, 0) ;
        return result ;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <V> V switchLongPercentToRate(Long rateLong, Class<V> resultType) {
        if (rateLong == null) return null ;
        Double result = ComputeToolsUntis.computePercent(percent, rateLong, 4) ;

        if (resultType.equals(Double.class)) {
            return (V) result ;
        }
        if (resultType.equals(String.class)) {
            return (V) decimalFormat.format(result) ;
        }

        throw new RuntimeException("switchLongPrecisionToMoney   resultType  error  is  [Double   ,  String ]  ") ;
    }

    @Override
    public Long jsNoTaxPrice(Long taxPrice, Long rate) {
        double s = ComputeToolsUntis.computePercent(rate, percent) ;

        double xx = 1 - s ;

        Long noTaxPrice = ComputeToolsUntis.multiplyGetLong(xx, taxPrice, 0) ;

        return noTaxPrice ;
    }

    @Override
    public Long jsGrossProfitRate(Long salesMoney, Long stockMomey) {
        Double r = ComputeToolsUntis.computePercent(salesMoney, (salesMoney - stockMomey), 4) ;
        Long x = ComputeToolsUntis.multiplyGetLong(r, IBuildMoneyFactory.precision, 0) ;
        return x ;
    }

    //    @Override
    //    public Long computeRate(Long a, Long aend) {
    //
    //        double result = ComputeToolsUntis.computePercent(a, aend, 4) ;
    //
    //        return ComputeToolsUntis.multiplyGetLong(result, percent, 0) ;
    //
    //    }

    public static void main(String[] args) {

//        double s = ComputeToolsUntis.computePercent(1700L, percent) ;
//
//        double xx = 1 - s ;
//
//        Long noTaxPrice = ComputeToolsUntis.multiplyGetLong(xx, 2640000L, 0) ;
//
//        System.out.println("==> " + noTaxPrice) ;
        BuildMoneyFactory xx= new BuildMoneyFactory();
        
        Long noTaxPrice =   xx.jsSumMoney(264L, 100);
        
        System.out.println("==> " + noTaxPrice) ;
    }

    @Override
    public Long jsSumMoney(Long price, Integer count) {
        Long result = ComputeToolsUntis.multiplyGetLong(price, count.longValue(), 0) ;
        return result ;
     
    }
}
