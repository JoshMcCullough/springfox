package springdox.documentation.swagger.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import springdox.documentation.spring.web.AbstractPathProvider;
import springdox.documentation.swagger.common.SwaggerPluginSupport;

import javax.servlet.ServletContext;

@Component
public class AbsolutePathProvider extends AbstractPathProvider {

  private final ServletContext servletContext;

  @Autowired
  public AbsolutePathProvider(ServletContext servletContext) {
    this.servletContext = servletContext;
  }

  @Override
  protected String applicationPath() {
    return getAppRoot()
            .build()
            .toString();
  }

  @Override
  protected String getDocumentationPath() {
    return getAppRoot()
            .path(SwaggerPluginSupport.DOCUMENTATION_BASE_PATH)
            .build()
            .toString();
  }

  private UriComponentsBuilder getAppRoot() {
    return UriComponentsBuilder.fromHttpUrl("http://localhost:8080")
            .path(servletContext.getContextPath());
  }
}