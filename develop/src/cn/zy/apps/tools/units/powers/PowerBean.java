package cn.zy.apps.tools.units.powers;

public class PowerBean {
    private String name;
    private Integer value;
    private String optName;

    public PowerBean(String name, Integer value) {
        super();

        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getOptName() {
        return optName;
    }

    public void setOptName(String optName) {
        this.optName = optName;
    }

}
