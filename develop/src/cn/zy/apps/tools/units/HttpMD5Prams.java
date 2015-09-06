package cn.zy.apps.tools.units ;

public class HttpMD5Prams {

    /**
     * 
     */
    public String inputCharName ;

    /**
     * MD5 
     */
    public String signTypeName ;

    /**
     *  sign 
     */
    public String signName ;

    public HttpMD5Prams(String inputCharName, String signTypeName, String signName) {
        super() ;
        this.inputCharName = inputCharName ;
        this.signTypeName = signTypeName ;
        this.signName = signName ;
    }

    public String getInputCharName() {
        return inputCharName ;
    }

    public String getSignTypeName() {
        return signTypeName ;
    }

    public String getSignName() {
        return signName ;
    }

}
