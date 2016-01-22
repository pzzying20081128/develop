package cn.zy.apps.demo.units.search.bean ;

import java.util.List ;

import cn.zy.apps.demo.pojos.DemoStock ;

public class DemoStockSearchBean extends DemoStock {

    private List<Integer> stockUserIds ;

    public List<Integer> getStockUserIds() {
        return stockUserIds ;
    }

    public void setStockUserIds(List<Integer> stockUserIds) {
        this.stockUserIds = stockUserIds ;
    }

}
