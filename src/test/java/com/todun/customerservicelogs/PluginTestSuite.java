package com.todun.customerservicelogs;

import junit.framework.Test;
import junit.framework.TestSuite;

public class PluginTestSuite {

    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(CustomerServiceLogsTest.class);
        return suite;
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}
