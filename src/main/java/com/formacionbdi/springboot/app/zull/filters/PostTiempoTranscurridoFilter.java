package com.formacionbdi.springboot.app.zull.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PostTiempoTranscurridoFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(PostTiempoTranscurridoFilter.class);
	@Override
	public boolean shouldFilter() {
		// TODO si es true se valida se se va ejecutar o no el filtro si el false no se ejecuta 
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		// aqui se resuelve la logica del filtro
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		log.info("Entrando a Post");
		Long tiempoInicio = (long) request.getAttribute("tiempoInicio");
		Long tiempoFinal = System.currentTimeMillis();
		Long tiempoTranscurrido = tiempoFinal - tiempoInicio;
		
		log.info(String.format("Tiempo transcurrido en segundos: %s seg.", tiempoTranscurrido.doubleValue()/1000.00));
		log.info(String.format("Tiempo transcurrido en segundos: %s ms.", tiempoTranscurrido));
		return null;
	}

	@Override
	public String filterType() {
		// TODO se llama con esta esa palabra clave 
		return "post";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 1;
	}

}
