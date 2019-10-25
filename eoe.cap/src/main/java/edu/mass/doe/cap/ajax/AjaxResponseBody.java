package edu.mass.doe.cap.ajax;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonView;

public class AjaxResponseBody<T>  {

	@JsonView(Views.Public.class)
	private String msg;

	@JsonView(Views.Public.class)
	private int code;

	@JsonView(Views.Public.class)
	private T  body;

	public T getBody() {
		return body;
	}

	public void setBody(T mepid) {
		this.body = mepid;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}