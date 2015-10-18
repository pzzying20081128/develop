package cn.zy.apps.tools.dev.compjs;

import java.io.File ;
import java.util.ArrayList ;
import java.util.List ;

import cn.zy.apps.tools.logger.Loggerfactory ;



public class BuildJSModule implements IBuildJSModule {

	private static final org.apache.log4j.Logger LOG = Loggerfactory.instance("BuildJSModule");

	private String outPath;

	private List<String> excludedFiles;

	private List<String> containFiles;

	public BuildJSModule(String outPath, List<String> containFiles, List<String> excludedFiles) {
		super();
		this.outPath = outPath;
		this.excludedFiles = excludedFiles;
		this.containFiles = containFiles;
	}

	public void setExcludedFiles(List<String> excludedFiles) {
		this.excludedFiles = excludedFiles;
	}

	@Override
	public void build(File basePath, String outFile) throws Exception {
		List<File> srcfiles = new ArrayList<File>();
		String outPath_ = outPath + "/" + outFile;
		File outFileDir = buildOutPath(outPath_);
		CompileJavaSscrpt compileJavaSscrpt = new CompileJavaSscrpt(outFileDir);
		if (containFiles == null)
			containFiles = new ArrayList<String>();
		if (excludedFiles == null)
			excludedFiles = new ArrayList<String>();
		copyjs(basePath, containFiles, excludedFiles, srcfiles);
		if (srcfiles.size() > 0)
			compileJavaSscrpt.build(srcfiles);

	}

	@Override
	public void build(String basePath, String outFile) throws Exception {
		File outFileDir = new File(basePath);
		build(outFileDir, outFile);
	}

	private void copyjs(File src, List<String> containFile, List<String> excludedFiles, List<File> srcfiles) throws Exception {
		if (src.listFiles() == null)
			return;
		for (File base_ : src.listFiles()) {

			if (base_.isFile() && base_.getName().endsWith(".js")) {

				if (containFile.size() > 0 && !containFile.contains(base_.getPath())) {
					continue;
				}

				if (excludedFiles.size() > 0 && excludedFiles.contains(base_.getPath())) {
					continue;
				}
				// System.out.println("+>  add "+base_.getPath()) ;
				System.out.println("==copyjs >   " + base_);
				srcfiles.add(base_);
			} else {

				copyjs(base_, containFile, excludedFiles, srcfiles);
			}
		}
	}

	private File buildOutPath(String outPath) throws Exception {

		File outFileDir = new File(outPath);
		// if (!outFileDir.exists()) {
		// if (outFileDir.mkdirs()) {
		// logger.info("buildOutPath " + outPath + " success ") ;
		// } else {
		// throw new Exception("buildOutPath " + outPath + " success ") ;
		// }
		// }
		return outFileDir;
	}

	public List<String> getExcludedFiles() {
		return excludedFiles;
	}

	public void setContainFiles(List<String> containFiles) {
		this.containFiles = containFiles;
	}

}
