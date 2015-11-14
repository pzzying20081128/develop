package cn.zy.apps.tools.units.moneys ;

public interface IBuildMoneyFactory {

    //精度
    public Long precision = 10000L ;

    public Long percent = precision / 100L ;

    // 小数位数
    public int Decimal_Digit = 0 ;

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
    public Long switchRateToLongPercent(Double rate) ;

    public <V> V switchLongPrecisionToMoney(Long moneyLong, Class<V> resultType) ;

    public <V> V switchLongPercentToRate(Long rateLong, Class<V> resultType) ;

    //计算不函数价格
    public Long jsNoTaxPrice(Long taxPrice, Long rate) ;
    
    //计算毛利率
    public Long jsGrossProfitRate(Long salesMoney, Long stockMomey) ;
    
    public Long  jsSumMoney(Long price, Integer count) ;

}
