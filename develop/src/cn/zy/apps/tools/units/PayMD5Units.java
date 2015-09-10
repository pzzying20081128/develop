package cn.zy.apps.tools.units ;

import java.io.UnsupportedEncodingException ;
import java.util.ArrayList ;
import java.util.Collections ;
import java.util.Comparator ;
import java.util.List ;
import java.util.Map ;
import java.util.Map.Entry ;

import org.apache.commons.codec.digest.DigestUtils ;

public class PayMD5Units {

    public static String buildParamsMd5(Map<String, String> params, String signKey, String input_charset) throws ToolsUnitsException {
        HttpMD5Prams httpMD5Prams = new HttpMD5Prams(null, "md5", "sign") ;
        return buildParamsMd5(params, httpMD5Prams, signKey, input_charset) ;
    }

    public static String buildParamsMd5(Map<String, String> params, HttpMD5Prams httpMD5Prams, String signKey, String input_charset) throws ToolsUnitsException {
        String paramsUrl = createSortParams(params, httpMD5Prams) ;
        String md5 = createMD5(paramsUrl, signKey, input_charset) ;
        return md5 ;
    }

    public static String sign(String text, String key, String input_charset) throws ToolsUnitsException {
        text = text + key ;
        return DigestUtils.md5Hex(getContentBytes(text, input_charset)) ;
    }

    private static byte[] getContentBytes(String content, String charset) throws ToolsUnitsException {
        if (charset == null || "".equals(charset)) {
            return content.getBytes() ;
        }
        try {
            return content.getBytes(charset) ;
        } catch (UnsupportedEncodingException e) {
            throw new ToolsUnitsException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset) ;
        }
    }

    public static String createMD5(String paramsUrl, String signKey, String input_charset) throws ToolsUnitsException {

        try {
            String urlParams = paramsUrl.trim() + signKey ;
            String md5 = sign(urlParams, signKey, input_charset) ;
            return md5 ;

        } catch (Exception e) {
            throw new ToolsUnitsException(e) ;
        }
    }

    private static String createSortParams(Map<String, String> params, HttpMD5Prams httpMD5Prams) {
        List<Map.Entry<String, String>> keys = new ArrayList<Map.Entry<String, String>>() ;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey() ;
            String value = entry.getValue() ;
            if (value == null || value.equals("") || key.equalsIgnoreCase(httpMD5Prams.getSignName()) || key.equalsIgnoreCase(httpMD5Prams.getSignTypeName()) || key.equalsIgnoreCase(httpMD5Prams.getInputCharName())) continue ;
            keys.add(entry) ;
        }

        Collections.sort(keys, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Entry<String, String> o1, Entry<String, String> o2) {
                return o1.getKey().compareTo(o2.getKey()) ;
            }
        }) ;

        StringBuffer sb = new StringBuffer() ;
        for (int i = 0; i < keys.size(); i++) {
            Map.Entry<String, String> xx = keys.get(i) ;
            String key = xx.getKey() ;
            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                sb.append(key).append("=").append(xx.getValue()) ;
            } else {
                sb.append(key).append("=").append(xx.getValue()).append("&") ;
            }
        }
        return sb.toString().trim() ;
    }

}
