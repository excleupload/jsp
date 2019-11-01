package com.salesupload.common;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorPageHandler implements ErrorViewResolver {

	@Override
	public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
		Object statusCodeObj = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		ModelAndView mView = new ModelAndView();
		if (status != null) {
			Integer statusCode = Integer.valueOf(statusCodeObj.toString());

			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				mView.setViewName("common/404");
				return mView;
			} else if (statusCode == HttpStatus.BAD_REQUEST.value()) {
				mView.setViewName("common/400");
				return mView;
			} else if (statusCode == HttpStatus.FORBIDDEN.value()) {
				mView.setViewName("common/403");
				return mView;
			} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				mView.setViewName("common/500");
				return mView;
			}
		}
		return null;
	}

}
