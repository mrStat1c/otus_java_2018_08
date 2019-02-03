package ru.otus.frontend.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.otus.db.cache.CacheEngine;
import ru.otus.frontend.LoadingEmulator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class AdminServlet extends HttpServlet {

    private static final String DEFAULT_USER_NAME = "UNKNOWN";
    private static final String ADMIN_PAGE_TEMPLATE = "admin.html";

    @Autowired
    private TemplateProcessor templateProcessor;

//    @Autowired
//    private CacheEngine cacheEngine;

    @Autowired
    private LoadingEmulator loadingEmulator;
    // инжектируется для того, чтобы объект эмулятора был создан и автоматически при этом запустился

    public AdminServlet() {
    }

    @SuppressWarnings("WeakerAccess")
    public AdminServlet(CacheEngine cacheEngine, TemplateProcessor templateProcessor) {
//        this.cacheEngine = cacheEngine;
        this.templateProcessor = templateProcessor;
    }

    @SuppressWarnings("WeakerAccess")
    public AdminServlet(CacheEngine cacheEngine) {
        this(cacheEngine, new TemplateProcessor());
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("method", request.getMethod());
        pageVariables.put("URL", request.getRequestURL().toString());
        pageVariables.put("locale", request.getLocale());
        pageVariables.put("sessionId", request.getSession().getId());
        pageVariables.put("parameters", request.getParameterMap().toString());
//        pageVariables.put("CacheHitCount", cacheEngine.getHitCount());
//        pageVariables.put("CacheMissCount", cacheEngine.getMissCount());

        //let's get login from session
        String login = (String) request.getSession().getAttribute(LoginServlet.LOGIN_PARAMETER_NAME);
        pageVariables.put("login", login != null ? login : DEFAULT_USER_NAME);
        pageVariables.put("loginOk", login != null);

        return pageVariables;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        Map<String, Object> pageVariables = createPageVariablesMap(request);

        if (Boolean.TRUE.equals(pageVariables.get("loginOk"))) {
            response.setContentType("text/html;charset=utf-8");
            String page = templateProcessor.getPage(ADMIN_PAGE_TEMPLATE, pageVariables);
            response.getWriter().println(page);
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendRedirect("/login");
        }
    }
}
