package hjkim.java.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class WebApplicationInitializerImpl implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext springServletContext = new AnnotationConfigWebApplicationContext();
		springServletContext.register(ServletConfig.class);
		
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("servlet", new DispatcherServlet(springServletContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("*.do");
		
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		servletContext.addFilter("Encoding Filter", characterEncodingFilter);
	}
}