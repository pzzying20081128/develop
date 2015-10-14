package cn.zy.apps.tools.dev.compjs;

import java.util.ArrayList ;
import java.util.List ;

public class BuildJS {

	public static void main(String[] args) throws Exception {
		List<String> xx = new ArrayList<String>();
		xx.add("/home/you/workspace/jERP/WebContent/ext3/my-ext-all-debug.js");
		xx.add("/home/you/workspace/jERP/WebContent/ext3/ext-basex-debug.js");

		IBuildJSModule module = new BuildJSModule("/home/you/Documents/1/1", xx, null);
		//
		module.build("/home/you/workspace/jERP/WebContent/ext3", "ext_base_tools.min.js");
	}

}
