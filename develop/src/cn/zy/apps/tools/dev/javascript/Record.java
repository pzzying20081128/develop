package cn.zy.apps.tools.dev.javascript ;

public class Record {
    
    private boolean canShow;
    
    private String name ;
    
    private String showName ;

    private String    mapping ;
    
    private Integer  showWidth;
    
    private boolean canSort;
    
    private String    rendererValueKey ;
    
    private String  classfiled;
    
    

    public String getName() {
        return name ;
    }

    public void setName(String name) {
        this.name = name ;
    }

    public String getMapping() {
        return mapping ;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping ;
    }

    public String getShowName() {
        return showName ;
    }

    public void setShowName(String showName) {
        this.showName = showName ;
    }

    public Integer getShowWidth() {
        return showWidth ;
    }
 
    public void setShowWidth(Integer showWidth) {
        this.showWidth = showWidth ;
    }

    public boolean isCanShow() {
        return canShow ;
    }

    public void setCanShow(boolean canShow) {
        this.canShow = canShow ;
    }

    public boolean isCanSort() {
        return canSort ;
    }

    public void setCanSort(boolean canSort) {
        this.canSort = canSort ;
    }

    public String getRendererValueKey() {
        return rendererValueKey ;
    }

    public void setRendererValueKey(String rendererValueKey) {
        this.rendererValueKey = rendererValueKey ;
    }

    public String getClassfiled() {
        return classfiled ;
    }

    public void setClassfiled(String classfiled) {
        this.classfiled = classfiled ;
    }

   

}
