//package cn.zy.apps.tools.networks.http;
//
//import cn.zy.apps.tools.logger.Loggerfactory ;
//import cn.zy.apps.tools.networks.http.cores.HttpProtocolService ;
//
//public class ImageGetRequestActivity extends HttpRequestResponseActivity {
//
//	public ImageGetRequestActivity(HttpProtocolService httpProtocolService, String url) {
//		super(httpProtocolService, url);
//
//	}
//
//	@Override
//	public void doActive(PostGetResponse request) throws HttpRequestServiceException {
//		Loggerfactory.info(logger, "do active url " + getUrl());
//		HttpProtocolService httpProtocolService = getHttpProtocolService();
//
//		String url = getUrl();
//		String prams = buildParams(request.getRequestParams());
//		boolean isc = url.contains("?");
//		if (isc) {
//			if (ToolsUnilts.isNOtNulll(prams))
//				url = url + "&" + prams;
//		} else {
//			if (ToolsUnilts.isNOtNulll(prams))
//				url = url + "?" + prams;
//		}
//		HttpRequestResult response = httpProtocolService.getByteArrayInputStream(url, request);
////          xx(response.getResponseBtye());
//		request.setResponse(response);
//
//	}
//
//
//	//
//	//
//	//
//	//
//	// // ImageIO.write( image, "jpg", new File("/home/you/xz/s.jpg"));
//	// }
//
//}
