package br.com.forum.config;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@SuppressWarnings("deprecation")
public class InterceptorOfAccess extends HandlerInterceptorAdapter {

	public static List<Acesso> acessos = new ArrayList<>();

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object Handler) throws Exception {

		Acesso acesso = new Acesso();
		acesso.path = req.getRequestURI();
		acesso.date = LocalDateTime.now();
		req.setAttribute("acesso", acesso);

		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception ex)
			throws Exception {

		Acesso acesso = (Acesso) req.getAttribute("acesso");
		acesso.duration = Duration.between(acesso.date, LocalDateTime.now());
		req.setAttribute("acesso", acesso);

		acessos.add(acesso);

	}

	public static class Acesso {
		public String path;
		public LocalDateTime date;
		public Duration duration;

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public LocalDateTime getDate() {
			return date;
		}

		public void setDate(LocalDateTime date) {
			this.date = date;
		}

		public Duration getDuration() {
			return duration;
		}

		public void setDuration(Duration duration) {
			this.duration = duration;
		}

		@Override
		public String toString() {

			return "{ path: " + this.path + ", Date: " + this.date + ", Duration: " + this.duration + "}";
		}
	}
}
