package com.kacha.wapproj.util;

import java.util.HashMap;
import java.util.Map;

public class Resp extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public Resp() {
		put("code", 200);
		put("msg", "success");
	}

	public static Resp error() {
		return error(500, "系统错误");
	}

	public static Resp error(String msg) {
		return error(500, msg);
	}

	public static Resp error(int code, String msg) {
		Resp r = new Resp();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static Resp ok(String msg) {
		Resp r = new Resp();
		r.put("msg", msg);
		return r;
	}


	public static Resp ok() {
		return new Resp();
	}

	public static Object ok(Object ob) {
		Resp r = new Resp();
		r.put("data", ob);
		return r;
	}
}
