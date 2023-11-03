package co.yedam.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.board.web.AddBoardControl;
import co.yedam.board.web.BoardFormControl;
import co.yedam.board.web.BoardListControl;
import co.yedam.board.web.GetBoardControl;

// uri : *.do
public class FrontController extends HttpServlet{
	// init -> service
	
	Map<String, Command> map = new HashMap<>();
	@Override
	public void init(ServletConfig config) throws ServletException {
		
//		map.put("/FirstServlet.do", new FirstControl());
//		map.put("/second.do", new ScondControl());
		map.put("/boardList.do", new BoardListControl());
		map.put("/getBoard.do", new GetBoardControl());
		// 등록화면
		map.put("/boardForm.do", new BoardFormControl());
		map.put("/addBoard.do", new AddBoardControl());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("FrontController");
		String uri = req.getRequestURI();  // http://localhost:8080/helloJSP/??.do
		String context = req.getServletContext().getContextPath(); // helloJSP
		String page = uri.substring(context.length());
		System.out.println(page);
		
		Command controllor = map.get(page);
		controllor.execute(req, resp);
		
	}
}