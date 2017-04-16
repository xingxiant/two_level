package com.xxt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ���ж���������servlet
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
		//get��ʽ��������ͷ��ת�룬��Ϊcountry������
		if (!"post".equals(sendType)) {
			country = new String(request.getParameter("country").getBytes("ISO-8859-1"), "utf-8");
		}
		
		if(!"json".equals(dataType)){  //��xml�Ĵ���
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><root>");
			if ("�й�".equals(country)) {
				sb.append("<city>����</city>").append("<city>�Ϻ�</city>").append("<city>����</city>").append("<city>����</city>");
			} else if ("����".equals(country)) {
				sb.append("<city>��ʢ������</city>").append("<city>ŦԼ</city>").append("<city>��ɼ�</city>").append("<city>֥�Ӹ�</city>");
			}
			sb.append("</root>");
			response.setContentType("text/xml;charset=utf-8");
		}else{						   //��json�Ĵ���
			sb.append("{");
			sb.append("\"cities\":[");
			if ("�й�".equals(country)) {
				sb.append("{\"city\":\"����\"},{\"city\":\"�Ϻ�\"},{\"city\":\"����\"},{\"city\":\"����\"}");
			} else if ("����".equals(country)) {
				sb.append("{\"city\":\"��ʢ������\"},{\"city\":\"ŦԼ\"},{\"city\":\"��ɼ�\"},{\"city\":\"֥�Ӹ�\"}");
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
