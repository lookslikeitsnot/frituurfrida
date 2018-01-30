package be.vdab.filters;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class RequestFilter
 */
@WebFilter("*.htm")
public class RequestFilter implements Filter {
	private final static String STATISTIEK = "statistiek";

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			String url = ((HttpServletRequest) request).getRequestURL().toString();

			int index = url.indexOf(";jsessionid=");
			if (index != -1) {
				url = url.substring(0, index);
			}
			@SuppressWarnings("unchecked")
			ConcurrentHashMap<String, AtomicInteger> statistiek = (ConcurrentHashMap<String, AtomicInteger>) request
					.getServletContext().getAttribute(STATISTIEK);
			AtomicInteger aantalReedsAanwezig = statistiek.putIfAbsent(url, new AtomicInteger(1));
			if (aantalReedsAanwezig != null) {
				aantalReedsAanwezig.incrementAndGet();
			}

		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		fConfig.getServletContext().setAttribute(STATISTIEK, new ConcurrentHashMap<String, AtomicInteger>());
	}

}
