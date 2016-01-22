package cn.zy.apps.demo.web ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.DemoStock ;
import cn.zy.apps.demo.service.IDemoStockService ;
import cn.zy.apps.demo.units.search.bean.DemoStockSearchBean ;
import cn.zy.apps.tools.web.SelectPage ;

@Component("DemoStockAction")
@org.springframework.context.annotation.Scope(DemoUserAction.Scope)
public class DemoStockAction extends ABDemoSystemAction<DemoStock, DemoStockSearchBean> {

    private static final long serialVersionUID = 5097422831159826456L ;

    @Autowired
    @Qualifier(IDemoStockService.name)
    private IDemoStockService service ;

    // 记得要设置 Get Set 
    private DemoStock demostock ;

    public String save() throws Exception {
        try {

            this.result = service.saveUpdate(optType, demostock) ;
            writeObjectService.intToPrpertiesUnits(this.result) ;
        } catch (Exception e) {
            this.success = false ;
            this.msg = handError(e) ;
        }

        return SUCCESS ;
    }

    public String remove() throws Exception {
        try {
            service.remove(optType, demostock) ;
            writeObjectService.intToPrpertiesUnits(this.result) ;
        } catch (Exception e) {
            this.success = false ;
            this.msg = handError(e) ;
        }

        return SUCCESS ;
    }

    public String get() throws Exception {
        try {
            this.result = service.get(uuid) ;
            writeObjectService.intToPrpertiesUnits(this.result) ;

        } catch (Exception e) {
            this.success = false ;
            this.msg = handError(e) ;
        }

        return SUCCESS ;
    }

    public String list() throws Exception {
        try {
            SelectPage<DemoStock> selectPage = service.search(optType, searchBean, commSearchBean, start, limit) ;
            writeObjectService.intToPrpertiesUnits(selectPage) ;
            this.setSelectPage(selectPage) ;
        } catch (Exception e) {
            this.success = false ;
            this.msg = handError(e) ;
        }

        return SUCCESS ;
    }

    public DemoStock getDemostock() {
        return demostock ;
    }

    public void setDemostock(DemoStock demostock) {
        this.demostock = demostock ;
    }

}
