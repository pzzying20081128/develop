package cn.zy.apps.tools.units.moneys ;

import java.text.DecimalFormat ;

import cn.zy.apps.tools.units.ComputeToolsUntis ;

public class BuildMoneyFactory implements IBuildMoneyFactory {

    public int Default_Decimal_Digit = 4 ;

    private DecimalFormat decimalFormat ;

    public BuildMoneyFactory() {
        String decimalFormat_String = "###,###.00" ;

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
        
        if(moneyLong ==null) return null;

        Double result = ComputeToolsUntis.computePercent(precision, moneyLong, Default_Decimal_Digit) ;

        if (resultType.equals(Double.class)) {
            return (V) result ;
        }

        if (resultType.equals(String.class)) {
            return (V) decimalFormat.format(result) ;
        }

        throw new RuntimeException("switchLongPrecisionToMoney   resultType  error  is  [Double   ,  String ]  ") ;
    }

    public static void main(String[] args) {

        IBuildMoneyFactory buildMoneyFactory = new BuildMoneyFactory() ;
        
        Long xx=4952L;

        String  result = buildMoneyFactory.switchLongPercentToRate(xx,String.class);

//        String result_ = buildMoneyFactory.switchLongPrecisionToMoney(result, String.class) ;

        System.out.println("==2>   " + result.toString()) ;

    }

    @Override
    public Long switchRateToLongPrecision(Double rate) {
        Long result = ComputeToolsUntis.multiplyGetLong(rate, percent, 0) ;
        return result ;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <V> V switchLongPercentToRate(Long rateLong, Class<V> resultType) {
        if(rateLong ==null) return null;
        Double result = ComputeToolsUntis.computePercent(percent, rateLong, 4) ;
        
        if (resultType.equals(Double.class)) {
            return (V) result ;
        }
        if (resultType.equals(String.class)) {
            return (V) decimalFormat.format(result) ;
        }

        throw new RuntimeException("switchLongPrecisionToMoney   resultType  error  is  [Double   ,  String ]  ") ;
    }

    //    @Override
    //    public Long computeRate(Long a, Long aend) {
    //
    //        double result = ComputeToolsUntis.computePercent(a, aend, 4) ;
    //
    //        return ComputeToolsUntis.multiplyGetLong(result, percent, 0) ;
    //
    //    }

}
