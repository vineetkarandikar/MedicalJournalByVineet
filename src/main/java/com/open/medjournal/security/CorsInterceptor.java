package com.open.medjournal.security;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author vineetkarandikar@gmail.com
 * @createdDate 19/06/2016
 * @purpose This class is responsible for cross origin functionality.
 */
public class CorsInterceptor extends HandlerInterceptorAdapter {

  public static final String REQUEST_ORIGIN_NAME = "Origin";
  public static final String CREDENTIALS_NAME = "Access-Control-Allow-Credentials";
  public static final String ORIGIN_NAME = "Access-Control-Allow-Origin";
  public static final String METHODS_NAME = "Access-Control-Allow-Methods";
  public static final String HEADERS_NAME = "Access-Control-Allow-Headers";
  public static final String MAX_AGE_NAME = "Access-Control-Max-Age";

  @Autowired
  private Environment environment;

  /**
   * This method handle handles cross origin request.
   */
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String originNames = environment.getProperty("cors.mappings", String.class);
    List<String> origins = Arrays.asList(originNames.trim().split("( )*,( )*"));

    response.setHeader(CREDENTIALS_NAME, "true");
    response.setHeader(METHODS_NAME, "GET, OPTIONS, POST, PUT, DELETE");
    response.setHeader(HEADERS_NAME, "Origin, X-Requested-With, Content-Type, Accept");
    response.setHeader("accept", "application/json");
    response
        .setHeader(MAX_AGE_NAME, environment.getProperty("server.sessionTimeout", String.class));
    String origin = request.getHeader(REQUEST_ORIGIN_NAME);
    if (origins.contains(origin)) {
      response.setHeader(ORIGIN_NAME, origin);
    } else {
      response.setHeader(ORIGIN_NAME, origins.iterator().next());
    }
    return true;
  }
}
