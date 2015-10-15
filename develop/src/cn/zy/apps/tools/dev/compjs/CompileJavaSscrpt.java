package cn.zy.apps.tools.dev.compjs ;

import java.io.File ;



import java.io.FileOutputStream ;
import java.io.IOException ;
import java.io.OutputStreamWriter ;
import java.nio.charset.Charset ;
import java.util.ArrayList ;
import java.util.List ;
import java.util.logging.Level ;
import java.util.logging.Logger ;

import com.google.javascript.jscomp.CompilationLevel ;
import com.google.javascript.jscomp.Compiler ;
import com.google.javascript.jscomp.CompilerOptions ;
import com.google.javascript.jscomp.LoggerErrorManager ;
import com.google.javascript.jscomp.MessageFormatter ;
import com.google.javascript.jscomp.Result ;
import com.google.javascript.jscomp.SourceFile ;
import com.google.javascript.jscomp.WarningLevel ;

public class CompileJavaSscrpt {

    private Logger logger = Logger.getLogger("CompileJavaSscrpt") ;

    private CompilerOptions.LanguageMode languageIn = CompilerOptions.LanguageMode.ECMASCRIPT5 ;

    private List<SourceFile> externs = new ArrayList<SourceFile>() ;

    private List<SourceFile> sources = new ArrayList<SourceFile>() ;

    private File outputFile ;

    private String outPutEncoding = "UTF-8" ;

    private String intPutEncoding = "UTF-8" ;

    private boolean manageDependencies = false ;

    private boolean prettyPrint = false ;

    private boolean printInputDelimiter = false ;

    private boolean generateExports = false ;

    private WarningLevel warningLevel = WarningLevel.DEFAULT ;

    private CompilationLevel compilationLevel = CompilationLevel.WHITESPACE_ONLY ;

    public CompileJavaSscrpt(File outputFile) {
        super() ;
        this.outputFile = outputFile ;
    }

    public void build(List<File> srcFiles) throws BuildException {
        Compiler.setLoggingLevel(Level.OFF) ;
        CompilerOptions options = createCompilerOptions() ;
        Compiler compiler = createCompiler(options) ;
        System.out.println("============== > " + srcFiles.size()) ;
        for (File srcFile : srcFiles) {
            sources.add(SourceFile.fromFile(srcFile, Charset.forName(intPutEncoding))) ;
        }

        Result result = compiler.compile(externs, sources, options) ;
        if (result.success) {
            String xx = compiler.toSource() ;
            writeResult(xx) ;
        } else {
            throw new BuildException("Compilation failed.") ;
        }
    }

    private void writeResult(String source) throws BuildException {
        if (!this.outputFile.getParentFile().exists() && !this.outputFile.getParentFile().mkdirs()) {
            throw new BuildException("create output file path : " + this.outputFile.getParentFile() + " failed") ;
        } else {
            // RunMessage.log("create output file path : " +
            // this.outputFile.getParentFile() + " success ") ;
        }

        try {
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(this.outputFile), outPutEncoding) ;
            out.append(source) ;
            out.flush() ;
            out.close() ;
            RunMessage.log("write  output file path : " + this.outputFile.getPath() + " success ") ;
        } catch (IOException e) {
            throw new BuildException(e) ;
        }

    }

    private CompilerOptions createCompilerOptions() {
        CompilerOptions options = new CompilerOptions() ;

        this.compilationLevel.setOptionsForCompilationLevel(options) ;
        this.warningLevel.setOptionsForWarningLevel(options) ;
        
        

        options.prettyPrint = this.prettyPrint ;
        options.printInputDelimiter = this.printInputDelimiter ;
        options.generateExports = this.generateExports ;

        options.setLanguageIn(this.languageIn) ;
        options.setOutputCharset(this.outPutEncoding) ;

        return options ;
    }

    private Compiler createCompiler(CompilerOptions options) {
        Compiler compiler = new Compiler() ;
        MessageFormatter formatter = options.errorFormat.toFormatter(compiler, false) ;

        LoggerErrorManager errorManager = new LoggerErrorManager(formatter, logger) ;
        compiler.setErrorManager(errorManager) ;
        return compiler ;
    }

}
