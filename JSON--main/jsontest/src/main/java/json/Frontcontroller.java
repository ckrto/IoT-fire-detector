package json;


import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class controller
 */
@WebServlet("/controllers/*")
public class Frontcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private int fireStatus = 0;
    /**
     * @see HttpServlet#HttpServlet()
     */
	private void checkURL(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURI();
		request.setCharacterEncoding("UTF-8");
		//http://localhost:8080/controllers/json
		if (url.equals("/controllers/json")) {
			this.json(request, response);
		}
	}
	
	private void json(HttpServletRequest request, HttpServletResponse response) {
		int i=2;
		BaseMap basemap = new BaseMap();
		int[][] resultmap = basemap.getMap_arr();
		System.out.println("ss");
		JSONObject jsonobject = new JSONObject();
		JSONArray jsonmap = new JSONArray();
		jsonmap.put(resultmap);
		jsonobject.put("map", jsonmap);
		System.out.println("json");
		try {
			response.getWriter().println(jsonobject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public Frontcontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.checkURL(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.checkURL(request, response);
	}

}
