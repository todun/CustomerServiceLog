package com.todun.customerservicelogs;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for resource located on server
 */
public class CustomerServiceLogsTest extends AbstractBaseTest {

    protected static final boolean IS_NOT_A_FILE = false;
    private static Server server;

    /**
     * Setup for resource served from server
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        resourceProtocol = "http://";
        resourcePath = "127.0.0.1";
        port = "7070";
        contextPath = "/";

        baseUrl = buildBaseUri(resourceProtocol, resourcePath, port, IS_NOT_A_FILE);

        server = new Server(Integer.parseInt(port));
        server.setStopAtShutdown(true);

        // Handler for multiple web apps
        HandlerCollection handlers = new HandlerCollection();

        // Creating the plugin web application context
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setResourceBase(RESOURCE_BASE_DIRECTORY);
        webAppContext.setContextPath(contextPath);
        webAppContext.setWelcomeFiles(new String[]{RESOURCE_FILE_NAME});
        handlers.addHandler(webAppContext);

        // Adding the handlers to the server
        server.setHandler(handlers);

        // Starting the Server
        server.start();

        super.setUp();
    }

    @Test
    public void testPluginTestCase() throws Exception {
        clickDifferentLinksScenarioTestCase();
    }

    /**
     * Server is stopped after test is run
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        server.stop();
        super.tearDown();
    }
}
