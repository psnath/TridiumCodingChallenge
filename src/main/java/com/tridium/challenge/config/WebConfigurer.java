package com.tridium.challenge.config;

import java.io.File;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.MimeMappings;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Configuration of web application with Servlet 3.0 APIs.
 */
@Configuration
public class WebConfigurer implements ServletContextInitializer, EmbeddedServletContainerCustomizer {

    private final Logger log = LoggerFactory.getLogger(WebConfigurer.class);

    private final Environment env;

    

    public WebConfigurer(Environment env) {
        this.env = env;        
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        if (env.getActiveProfiles().length != 0) {
            log.info("Web application configuration, using profiles: {}", (Object[]) env.getActiveProfiles());
        }
        //initH2Console(servletContext);
        if (env.acceptsProfiles(Constants.SPRING_PROFILE_DEVELOPMENT)) {
            initH2Console(servletContext);
        }
        log.info("Web application fully configured");
    }

    /**
     * Customize the Servlet engine: Mime types, the document root, the cache.
     */
    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
        // IE issue, see https://github.com/jhipster/generator-jhipster/pull/711
        mappings.add("html", "text/html;charset=utf-8");
        // CloudFoundry issue, see https://github.com/cloudfoundry/gorouter/issues/64
        mappings.add("json", "text/html;charset=utf-8");
        container.setMimeMappings(mappings);
        // When running in an IDE or with ./mvnw spring-boot:run, set location of the static web assets.
        setLocationForStaticAssets(container);

        /*
         * Enable HTTP/2 for Undertow - https://twitter.com/ankinson/status/829256167700492288
         * HTTP/2 requires HTTPS, so HTTP requests will fallback to HTTP/1.1.
         * See the JHipsterProperties class and your application-*.yml configuration files
         * for more information.
         */
//        if (jHipsterProperties.getHttp().getVersion().equals(Constants.Version.V_2_0) &&
//            container instanceof UndertowEmbeddedServletContainerFactory) {
//
//            ((UndertowEmbeddedServletContainerFactory) container)
//                .addBuilderCustomizers(builder ->
//                    builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true));
//        }
    }

    private void setLocationForStaticAssets(ConfigurableEmbeddedServletContainer container) {
        File root;
        String prefixPath = resolvePathPrefix();
        root = new File(prefixPath + "target/www/");
        if (root.exists() && root.isDirectory()) {
            container.setDocumentRoot(root);
        }
    }

    /**
     *  Resolve path prefix to static resources.
     */
    private String resolvePathPrefix() {
        String fullExecutablePath = this.getClass().getResource("").getPath();
        String rootPath = Paths.get(".").toUri().normalize().getPath();
        String extractedPath = fullExecutablePath.replace(rootPath, "");
        int extractionEndIndex = extractedPath.indexOf("target/");
        if(extractionEndIndex <= 0) {
            return "";
        }
        return extractedPath.substring(0, extractionEndIndex);
    }

    /**
     * Initializes the caching HTTP Headers Filter.
     */
//    private void initCachingHttpHeadersFilter(ServletContext servletContext,
//                                              EnumSet<DispatcherType> disps) {
//        log.debug("Registering Caching HTTP Headers Filter");
//        FilterRegistration.Dynamic cachingHttpHeadersFilter =
//            servletContext.addFilter("cachingHttpHeadersFilter",
//                new CachingHttpHeadersFilter(jHipsterProperties));
//
//        cachingHttpHeadersFilter.addMappingForUrlPatterns(disps, true, "/content/*");
//        cachingHttpHeadersFilter.addMappingForUrlPatterns(disps, true, "/app/*");
//        cachingHttpHeadersFilter.setAsyncSupported(true);
//    }


    /**
     * Initializes H2 console.
     */
    private void initH2Console(ServletContext servletContext) {
        log.debug("Initialize H2 console");
        ServletRegistration.Dynamic h2ConsoleServlet = servletContext.addServlet("H2Console", new org.h2.server.web.WebServlet());
        h2ConsoleServlet.addMapping("/h2-console/*");
        h2ConsoleServlet.setInitParameter("-properties", "src/main/resources/");
        h2ConsoleServlet.setLoadOnStartup(1);
    }

//    @Autowired(required = false)
//    public void setMetricRegistry(MetricRegistry metricRegistry) {
//        this.metricRegistry = metricRegistry;
//    }
}
