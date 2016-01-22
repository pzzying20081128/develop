package cn.zy.apps.demo.web ;

import cn.zy.apps.tools.units.PrpertiesSetValueService ;
import cn.zy.apps.tools.units.moneys.BuildMoneyFactory ;
import cn.zy.apps.tools.units.moneys.IBuildMoneyFactory ;

;

public class DemoPrpertiesSetValueService extends PrpertiesSetValueService {

    private static IBuildMoneyFactory buildMoneyFactory = BuildMoneyFactory.getBuildMoney() ;

    public DemoPrpertiesSetValueService(String regexPackage) {
        super(regexPackage) ;
    }

    @Override
    protected void handField(String fieldName, Object result) {

        if (fieldName.endsWith("MYShow")) {
            handMoney(fieldName, result) ;
        }

    }

    private void handMoney(String fieldName, Object result) {

        String money_field = fieldName.substring(0, fieldName.length() - 9) ;

        Long money = readFieldValue(money_field, result) ;

        String money_show = buildMoneyFactory.switchLongPrecisionToMoney(money, String.class) ;

        writeFieldValue(fieldName, result, money_show) ;

    }

}
