package cn.zy.apps.tools.networks.http.cores ;

import java.io.IOException ;
import java.io.UnsupportedEncodingException ;
import java.util.ArrayList ;
import java.util.Collection ;
import java.util.List ;
import java.util.Map ;

import org.apache.commons.lang.StringUtils ;
import org.apache.http.Header ;
import org.apache.http.HttpException ;
import org.apache.http.HttpResponse ;
import org.apache.http.HttpStatus ;
import org.apache.http.NameValuePair ;
import org.apache.http.client.config.CookieSpecs ;
import org.apache.http.client.config.RequestConfig ;
import org.apache.http.client.config.RequestConfig.Builder ;
import org.apache.http.client.entity.UrlEncodedFormEntity ;
import org.apache.http.client.methods.HttpGet ;
import org.apache.http.client.methods.HttpPost ;
import org.apache.http.client.methods.HttpRequestBase ;
import org.apache.http.client.protocol.HttpClientContext ;
import org.apache.http.cookie.Cookie ;
import org.apache.http.entity.StringEntity ;
import org.apache.http.impl.client.CloseableHttpClient ;
import org.apache.http.impl.client.HttpClientBuilder ;
import org.apache.http.impl.client.HttpClients ;
import org.apache.http.message.BasicNameValuePair ;
import org.apache.http.util.EntityUtils ;

import cn.zy.apps.tools.logger.Loggerfactory ;
import cn.zy.apps.tools.networks.http.HttpRequestResult ;
import cn.zy.apps.tools.networks.http.HttpRequestServiceException ;
import cn.zy.apps.tools.networks.http.base.IRequest ;
import cn.zy.apps.tools.units.ToolsUnits ;

/* *
 *类名：HttpProtocolHandler
 *功能：HttpClient方式访问
 *详细：获取远程HTTP数据
 *版本：3.3
 *日期：2012-08-17
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class HttpProtocolService implements IHttpProtocolService {

    //    private RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY).build() ;

    private CloseableHttpClient httpclient ;

    /**
     * 私有的构造方法
     */
    public HttpProtocolService() {

        Builder requestConfigBuilder = RequestConfig.custom() ;

        requestConfigBuilder.setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY) ;

        requestConfigBuilder.setConnectionRequestTimeout(defaultConnectionTimeout) ;

        requestConfigBuilder.setConnectTimeout(defaultConnectionTimeout) ;

        requestConfigBuilder.setSocketTimeout(defaultSoTimeout) ;

        RequestConfig globalConfig = requestConfigBuilder.build() ;

        HttpClientBuilder httpClientBuilder = HttpClients.custom() ;

        httpClientBuilder.setDefaultRequestConfig(globalConfig) ;

        CloseableHttpClient httpclient = httpClientBuilder.build() ;

        this.httpclient = httpclient ;

    }

    /**
     * 执行Http POST请求
     * 
     * @param request
     *            请求数据
     * @param strParaFileName
     *            文件类型的参数名
     * @param strFilePath
     *            文件路径
     * @return
     * @throws HttpException
     *             , IOException
     */

    private void addCommTequestHeader(HttpRequestBase entityEnclosingMethod, IRequest request) {
        entityEnclosingMethod.setHeader("Accept", IHttpProtocolService.Accept) ;
        entityEnclosingMethod.setHeader("Accept-Language", IHttpProtocolService.Accept_Language) ;
        entityEnclosingMethod.setHeader("Connection", IHttpProtocolService.Connection) ;
        entityEnclosingMethod.setHeader("User-Agent", IHttpProtocolService.User_Agent) ;
    }

    private void addPrivateRequestHeader(HttpRequestBase entityEnclosingMethod, IRequest request) {
        for (Map.Entry<String, String> entry : request.getHeader().entrySet()) {
            if (ToolsUnits.isNOtNulll(entry.getValue())) entityEnclosingMethod.setHeader(entry.getKey(), entry.getValue()) ;
        }
    }

    /**
     * 将传入的键/值对参数转换为NameValuePair参数集
     * 
     * @param paramsMap
     *            参数集, 键/值对
     * @return NameValuePair参数集
     */
    private List<NameValuePair> getParamsList(Map<String, String> paramsMap) {
        if (paramsMap == null || paramsMap.size() == 0) {
            return null ;
        }
        List<NameValuePair> params = new ArrayList<NameValuePair>() ;
        for (Map.Entry<String, String> map : paramsMap.entrySet()) {

            params.add(new BasicNameValuePair(map.getKey(), map.getValue())) ;
        }
        return params ;
    }

    public HttpRequestResult post(String url, IRequest request) throws HttpRequestServiceException {
        HttpPost postMethod = new HttpPost(url) ;

        addCommTequestHeader(postMethod, request) ;
        addPrivateRequestHeader(postMethod, request) ;

        try {
            if (request.getRequestParams().size() > 0) {
                UrlEncodedFormEntity formEntity = null ;
                if (StringUtils.isEmpty(request.getCharEncoded().name())) {
                    formEntity = new UrlEncodedFormEntity(getParamsList(request.getRequestParams())) ;
                } else {
                    formEntity = new UrlEncodedFormEntity(getParamsList(request.getRequestParams()), request.getCharEncoded().name()) ;
                }
                postMethod.setEntity(formEntity) ;
            }
            if (ToolsUnits.isNOtNulll(request.getPostSingleContent())) {

                if (StringUtils.isEmpty(request.getCharEncoded().name())) {

                    postMethod.setEntity(new StringEntity(request.getPostSingleContent(), "UTF-8")) ;
                } else {
                    System.out.println("-- >  request.getPostSingleContent()request.getPostSingleContent()request.getPostSingleContent()             " + request.getPostSingleContent()) ;
                    postMethod.setEntity(new StringEntity(request.getPostSingleContent(), request.getCharEncoded().name())) ;
                }

            }

            logger.info("http protocol service  post    request  URL : " + url) ;

            addCookies(request, postMethod) ;

            printRequestHeader(postMethod.getAllHeaders()) ;

            // postMethod.getParams().setContentCharset(request.getCharEncoded());
            HttpClientContext context = HttpClientContext.create() ;
            HttpResponse httpResponse = httpclient.execute(postMethod, context) ;
            logger.info("http protocol service  post  status " + httpResponse.getStatusLine().getStatusCode()) ;
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK || httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_MOVED_TEMPORARILY) {
                Loggerfactory.info(logger, "=================print response  Header======================") ;
                printRequestHeader(httpResponse.getAllHeaders()) ;
                String xx = EntityUtils.toString(httpResponse.getEntity(), request.getCharEncoded() == null ? default_CharSet : request.getCharEncoded().name()) ;
                HttpRequestResult httpRequestResult = new HttpRequestResult() ;
                httpRequestResult.setResponse(xx) ;
                httpRequestResult.getCookie().addAll(context.getCookieStore().getCookies()) ;
                httpRequestResult.setHeaders(httpResponse.getAllHeaders()) ;
                Loggerfactory.info(logger, "=================print HttpResponse  Cookies======================") ;
                printCookies(httpRequestResult.getCookie()) ;
                Loggerfactory.info(logger, "=================print HttpResponse Cookies======================") ;
                return httpRequestResult ;
            } else {
                throw new HttpRequestServiceException("Request  Failure  status : " + httpResponse.getStatusLine().getStatusCode()) ;
            }
        } catch (UnsupportedEncodingException e) {
            throw new HttpRequestServiceException(e) ;
        } catch (IOException e) {
            throw new HttpRequestServiceException(e) ;
        } finally {
            postMethod.releaseConnection() ;
        }
    }

    public HttpRequestResult get(String url, IRequest request) throws HttpRequestServiceException {
        logger.info("Get : " + url + " request " + request.getCharEncoded()) ;
        HttpGet postMethod = new HttpGet(url) ;
        // postMethod.setConfig(globalConfig);
        addCommTequestHeader(postMethod, request) ;
        addPrivateRequestHeader(postMethod, request) ;

        try {
            logger.info("http protocol service  get   request  URL : " + url) ;

            addCookies(request, postMethod) ;

            printRequestHeader(postMethod.getAllHeaders()) ;

            HttpClientContext context = HttpClientContext.create() ;

            HttpResponse httpResponse = httpclient.execute(postMethod, context) ;

            logger.info("http protocol service  get   request   status " + httpResponse.getStatusLine().getStatusCode()) ;
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                String xx = EntityUtils.toString(httpResponse.getEntity(), request.getCharEncoded() == null ? default_CharSet : request.getCharEncoded().name()) ;
                HttpRequestResult httpRequestResult = new HttpRequestResult() ;
                httpRequestResult.setResponse(xx) ;
                httpRequestResult.getCookie().addAll(context.getCookieStore().getCookies()) ;
                Loggerfactory.info(logger, "=================print HttpResponse  Cookies======================") ;
                printCookies(httpRequestResult.getCookie()) ;
                Loggerfactory.info(logger, "=================print HttpResponse Cookies======================") ;
                logger.info("http protocol service  get   request  URL : " + url + " end") ;

                return httpRequestResult ;

            } else {

                throw new HttpRequestServiceException("Request  Failure  status : " + httpResponse.getStatusLine().getStatusCode()) ;

            }
        } catch (UnsupportedEncodingException e) {
            throw new HttpRequestServiceException(e) ;
        } catch (IOException e) {
            throw new HttpRequestServiceException(e) ;
        } finally {
            postMethod.releaseConnection() ;
        }
    }

    public HttpRequestResult getByteArrayInputStream(String url, IRequest request) throws HttpRequestServiceException {
        HttpGet postMethod = new HttpGet(url) ;
        try {
            logger.info("Get : " + url) ;
            // addCommTequestHeader(postMethod);
            // addPrivateRequestHeader(postMethod, request);
            postMethod.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8") ;
            postMethod.setHeader("Accept-Language", "zh-cn,en-us;q=0.7,en;q=0.3") ;
            postMethod.setHeader("Connection", "keep-alive") ;
            postMethod.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.115 Safari/537.36") ;
            postMethod.setHeader("Accept-Encoding", "gzip, deflate, sdch") ;
            postMethod.setHeader("Cache-Control", "max-age=0") ;
            // postMethod.setHeader("Cache-Control", "Host:www.hshscm.com");

            addCookies(request, postMethod) ;

            printRequestHeader(postMethod.getAllHeaders()) ;
            HttpClientContext context = HttpClientContext.create() ;
            HttpResponse httpResponse = httpclient.execute(postMethod, context) ;
            // byte[] result1 =
            // EntityUtils.toByteArray(httpResponse.getEntity());
            // System.out.println("==> "+result1.length);w
            // writeFile(result1);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                byte[] result = EntityUtils.toByteArray(httpResponse.getEntity()) ;
                HttpRequestResult httpRequestResult = new HttpRequestResult() ;
                httpRequestResult.setResponseBtye(result) ;
                logger.info("http protocol service  get   request  URL : " + url + " end") ;
                return httpRequestResult ;
            } else {
                throw new HttpRequestServiceException("Request  Failure  status : " + httpResponse.getStatusLine().getStatusCode() + "  url  " + url) ;
            }
        } catch (UnsupportedEncodingException e) {
            throw new HttpRequestServiceException(e) ;
        } catch (IOException e) {
            throw new HttpRequestServiceException(e) ;
        } finally {
            postMethod.releaseConnection() ;
        }
    }

    private void addCookies(IRequest request, HttpRequestBase postMethod) {
        // RequestConfig localConfig =
        // RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY).build();
        // postMethod.setConfig(localConfig);
    }

    private void printRequestHeader(Header[] headers) {
        Loggerfactory.info(logger, "=================print  Header======================") ;
        for (Header header : headers) {
            Loggerfactory.info(logger, " Request  Header  key " + header.getName() + " value : " + header.getValue()) ;
        }
        Loggerfactory.info(logger, "=================print  Header======================") ;
    }

    private void printCookies(Collection<Cookie> cookies) {

        for (Cookie header : cookies) {
            Loggerfactory.info(logger, " Cookie    key " + header.getName() + " value : " + header.getValue()) ;
        }
    }

}
