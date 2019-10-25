package edu.mass.doe.cap.config;

import java.net.URI;
import org.apache.http.HttpHost;
import org.apache.http.client.AuthCache;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
 
/**
 * The Class HttpComponentsClientHttpRequestFactoryBasicAuth.
 */
public class HttpComponentsClientHttpRequestFactoryBasicAuth 
  extends HttpComponentsClientHttpRequestFactory {
 
    HttpHost host;
 
    /**
     * Instantiates a new http components client http request factory basic auth.
     *
     * @param host the host
     */
    public HttpComponentsClientHttpRequestFactoryBasicAuth(HttpHost host) {
        super();
        this.host = host;
    }
 
    /* (non-Javadoc)
     * @see org.springframework.http.client.HttpComponentsClientHttpRequestFactory#createHttpContext(org.springframework.http.HttpMethod, java.net.URI)
     */
    protected HttpContext createHttpContext(HttpMethod httpMethod, URI uri) {
        return createHttpContext();
    }
     
    /**
     * Creates the http context.
     *
     * @return the http context
     */
    private HttpContext createHttpContext() {
        AuthCache authCache = new BasicAuthCache();
 
        BasicScheme basicAuth = new BasicScheme();
        authCache.put(host, basicAuth);
 
        BasicHttpContext localcontext = new BasicHttpContext();
        localcontext.setAttribute(HttpClientContext.AUTH_CACHE, authCache);
        return localcontext;
    }
}