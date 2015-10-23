package cn.zy.apps.tools.units.moneys ;

public interface IBuildMoneyFactory {

    public Long precision = 10000L ;

    public Long percent = 10000L / 100L ;
    
    // 小数位数
    public int  Decimal_Digit = 0;

    /**
     * 把 double  转换成  long 
     * @param inMoney
     * @return
     */
    public Long switchMoneyToLongPrecision(Double inMoney) ;

    /**
     * 把 rate  转换成  long 
     * @param inMoney
     * @return
     */
    public Long switchRateToLongPrecision(Double rate) ;
    
    
    public <V> V switchLongPrecisionToMoney(Long moneyLong , Class<V> resultType) ;
    
   
    public <V> V switchLongPercentToRate(Long rateLong,Class<V> resultType) ;



}
