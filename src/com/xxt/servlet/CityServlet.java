package com.xxt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 城市二级联动，servlet
 */
@WebServlet("/CityServlet")
public class CityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CityServlet() {
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String country = request.getParameter("country");
		String dataType = request.getParameter("dataType");
		String sendType = request.getParameter("sendType");
		StringBuffer sb=new StringBuffer("");
		//get方式进行请求头的转码，因为country是中文
		if (!"post".equals(sendType)) {
			country = new String(request.getParameter("country").getBytes("ISO-8859-1"), "utf-8");
		}
		
		if(!"json".equals(dataType)){  //对xml的处理
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><root>");
			if ("中国".equals(country)) {
				sb.append("<city>北京</city>").append("<city>上海</city>").append("<city>广州</city>").append("<city>深圳</city>");
			} else if ("美国".equals(country)) {
				sb.append("<city>华盛顿特区</city>").append("<city>纽约</city>").append("<city>洛杉矶</city>").append("<city>芝加哥</city>");
			}
			sb.append("</root>");
			response.setContentType("text/xml;charset=utf-8");
		}else{						   //对json的处理
			sb.append("{");
			sb.append("\"cities\":[");
			if ("中国".equals(country)) {
				sb.append("{\"city\":\"北京\"},{\"city\":\"上海\"},{\"city\":\"广州\"},{\"city\":\"深圳\"}");
			} else if ("美国".equals(country)) {
				sb.append("{\"city\":\"华盛顿特区\"},{\"city\":\"纽约\"},{\"city\":\"洛杉矶\"},{\"city\":\"芝加哥\"}");
			}
			sb.append("]}");
			response.setContentType("text/html;charset=utf-8");
		}
		PrintWriter out=response.getWriter();
		out.println(sb.toString());
		out.flush();
		out.close();
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
