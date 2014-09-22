package com.liuyu.common.ast;

/**
 * Hello world!
 *
 */
public class App 
{
	junit.framework.TestCase testCase1;
	TestCase myTestCase;
	String str1;
	
	public void test1(){
		str1 = "aaa";
		System.out.println(str1.toCharArray());
	}
	
	public void test2(){
		TestCase myTestCase ;
		myTestCase = new TestCase();
		myTestCase.f();
	}
	
	class TestCase {
		public void f(){
			
		}
	}
}
