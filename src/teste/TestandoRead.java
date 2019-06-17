package teste;

import java.sql.SQLException;

import model.bean.Produto;
import model.dao.ProdutoDAO;

public class TestandoRead {

	public static void main(String[] args) {
		ProdutoDAO pd = new ProdutoDAO();
		try {
			for(Produto p:pd.readOrder()) {
				System.out.println(p.getCodProd());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
