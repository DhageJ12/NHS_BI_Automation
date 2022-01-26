package com.tests;

import org.apache.log4j.BasicConfigurator;
import org.testng.TestNG;

public class RunnerClass {

        static TestNG testNg;
        public static void main(String[] args){
            BasicConfigurator.configure();
            testNg = new TestNG();
            testNg.setTestClasses(new Class[] {AccountsExecutor.class});
            testNg.run();
        }

}
