/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickorder.filters;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import static java.lang.System.out;
import java.net.InetAddress;
import java.net.URL;
import java.util.Properties;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.UserAgent;

/**
 *
 * @author Marcelo
 */
public class FiltroURLs implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public FiltroURLs() {
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest re = (HttpServletRequest) request;
        
/**********************Se carga el archivo de configuracion de webServices*************************************/
        if (re.getSession().getAttribute("wsurl") == null) {
            Properties prop = new Properties();
            String propFileName = System.getProperty("user.home") + "/config.properties";
            try {
                InputStream inputStream = new FileInputStream(propFileName);
                prop.load(inputStream);
                if (prop.getProperty("hostName") == null || prop.getProperty("port") == null)
                    throw new IOException();
                re.getSession().setAttribute("wsurl",  prop.getProperty("hostName") + ":" +  prop.getProperty("port"));
            } catch (IOException ex) {
                try (PrintWriter writer = new PrintWriter(propFileName, "UTF-8")) {
                    writer.println("publicadorMovil=" + InetAddress.getLocalHost().getHostName() + "\nport=9128");
                    re.getSession().setAttribute("wsurl", InetAddress.getLocalHost().getHostName() + ":9128");
                }
            }
        }
/************************************************************************************************************************/
        
        URL urlws = new URL("http://" + (re.getSession().getAttribute("wsurl") + "/publicadorRegistro?wsdl"));
        publicar.PublicadorRegistroService service = new publicar.PublicadorRegistroService(urlws);
        publicar.PublicadorRegistro port = service.getPublicadorRegistroPort();
 
        //IP
        String ipAddress = re.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = re.getRemoteAddr();
        }

        //URL
        String url = re.getRequestURL().toString();

        //SO Y BROWSER
        String userAgentString = re.getHeader("User-Agent");
        if (userAgentString != null) {
            UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
            OperatingSystem os = userAgent.getOperatingSystem();
            Browser br = userAgent.getBrowser();
            port.addRegistroAcceso(ipAddress, url, os.toString(), br.toString());
        }
        //System.err.println("Datos = " + os.toString() + "   " + br.toString() + "  " + url + "  " + ipAddress);
        String requestURI = re.getRequestURI();
        System.err.println(requestURI);
//        String newURI = "/QuickOrder" + requestURI;
//        System.err.println(newURI);
//        request.getRequestDispatcher(newURI).forward(request, response);
        try {
            chain.doFilter(request, response);
        } catch (IOException | ServletException t) {
        }

    }

    /**
     * Return the filter configuration object for this filter.
     *
     * @return
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
    }

    /**
     * Init method for this filter
     *
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("prueba:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("prueba()");
        }
        StringBuilder sb = new StringBuilder("prueba(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                try (PrintStream ps = new PrintStream(response.getOutputStream()); PrintWriter pw = new PrintWriter(ps)) {
                    pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                    // PENDING! Localize this for next official release
                    pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                    pw.print(stackTrace);
                    pw.print("</pre></body>\n</html>"); //NOI18N
                }
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                try (PrintStream ps = new PrintStream(response.getOutputStream())) {
                    t.printStackTrace(ps);
                }
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
