package com.liuyu.common.ast;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.PackageDeclaration;

/**
 * java源文件解析操作
 * @author 彭姚
 */
public class JdtAst {

	private ASTParser astParser = ASTParser.newParser(AST.JLS3);//非常慢
	
	/**
	 * 获得java源文件的结构CompilationUnit
	 * @param javaFilePath java文件的绝对路径
	 * @return CompilationUnit
	 * @throws Exception
	 */
    public CompilationUnit getCompilationUnit(String javaFilePath) throws Exception {
    	
        BufferedInputStream bufferedInputStream = new BufferedInputStream(
                new FileInputStream(javaFilePath));
        byte[] input = new byte[bufferedInputStream.available()];
        bufferedInputStream.read(input);
        bufferedInputStream.close();
        
        
        Properties prop = System.getProperties();
        String [] classPathEntries = prop.getProperty("java.class.path").split(":"); 
        String [] sourcePathEntries = new String[]{"C:/tmp/ast"};
        astParser.setEnvironment(classPathEntries, sourcePathEntries, null, true); //does not work with "false" either
        astParser.setResolveBindings(true);
        astParser.setKind(ASTParser.K_COMPILATION_UNIT);
        Map options = JavaCore.getOptions(); 
        JavaCore.setComplianceOptions(JavaCore.VERSION_1_7, options);
        astParser.setCompilerOptions(options);
        
        this.astParser.setSource(new String(input).toCharArray());
        /**/
        CompilationUnit result = (CompilationUnit) (this.astParser.createAST(null));//很慢
        
        //package
        PackageDeclaration packageDec = result.getPackage();
        printPackageDeclaration(packageDec);
        
        //imports
        List imports = result.imports();
        
 
        return result;
    
    }
    
    private void printPackageDeclaration(PackageDeclaration dec){
    	System.out.println(dec.getName());
    }
    
    public static void main(String[] args) throws Exception {
    	JdtAst ast = new JdtAst();
    	ast.getCompilationUnit("C:/tmp/ast/com/liuyu/common/ast/App.java");
	}
}

