package ec;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BuyDataBeans;
import beans.ItemDataBeans;
import dao.BuyDAO;
import dao.BuyDetailDAO;

/**
 * 購入履歴画面
 * @author d-yamaguchi
 *
 */
@WebServlet("/UserBuyHistoryDetail")
public class UserBuyHistoryDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String buy_id = (String)request.getParameter("buy_id");

		BuyDataBeans bdb = new BuyDataBeans();
		BuyDAO buyDao = new BuyDAO();
		try {
			bdb = buyDao.getBuyDataBeansByBuyId(buy_id);
		} catch (SQLException | ParseException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		ArrayList<ItemDataBeans> BuyItemList = new ArrayList<ItemDataBeans>();
		BuyDetailDAO bdd = new BuyDetailDAO();

			 try {
				BuyItemList = bdd.getItemDataBeansListByBuyId(buy_id);
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}


		request.setAttribute("bdb", bdb);
		request.setAttribute("BuyItemList", BuyItemList);

		request.getRequestDispatcher(EcHelper.USER_BUY_HISTORY_DETAIL_PAGE).forward(request, response);

	}
}
