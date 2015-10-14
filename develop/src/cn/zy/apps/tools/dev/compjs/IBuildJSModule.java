package cn.zy.apps.tools.dev.compjs;

import java.io.File ;

public interface IBuildJSModule {

	public void build(File basePath, String outFile) throws Exception;

	public void build(String basePath, String outFile) throws Exception;

}
