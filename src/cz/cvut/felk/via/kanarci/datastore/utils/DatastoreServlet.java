package cz.cvut.felk.via.kanarci.datastore.utils;

import java.io.IOException;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;

import cz.cvut.felk.via.kanarci.datastore.objects.Address;
import cz.cvut.felk.via.kanarci.datastore.objects.Contact;



@SuppressWarnings("serial")
public class DatastoreServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
				
		String action = req.getParameter("submit");
	    if (action == null) {
	    	
	    }
	    else if (action.equals("Add")) {	
	    	// Address operation DEMO
//	    	DatastoreServletUtil.createNewAddress(req.getParameter("Street")
//	    			, Integer.parseInt(req.getParameter("co"))
//	    			, Integer.parseInt(req.getParameter("cp"))
//	    			, req.getParameter("City"), Integer.parseInt(req.getParameter("ZIP")));
	    	DatastoreUtil.createCustomer( new Contact( req.getParameter("FirstName")
	    			, req.getParameter("SureName"), Integer.parseInt(req.getParameter("Phone"))
	    			, req.getParameter("CorporationName"), req.getParameter("Email")
	    			, req.getParameter("Department"), new Address(req.getParameter("Street")
	    					, Integer.parseInt(req.getParameter("co")), Integer.parseInt(req.getParameter("cp"))
	    					, req.getParameter("City"), Integer.parseInt(req.getParameter("ZIP")))));
	    }
	    else if (action.equals("Add Category")) {
	    	Key key = null;
	    	if(!req.getParameter("SumpremeCategory").equals("")){
	    		key = KeyFactory.stringToKey(req.getParameter("SumpremeCategory"));
	    	} else {
		    	key = KeyFactory.createKey("Category", "Root");
	    	}
	    	DatastoreUtil.createCategory(req.getParameter("Name")
	    			, key, req.getParameter("ParameterName"), req.getParameter("ParameterValue"));
	    }
	    else if(action.equals("Add Goods")){
	    	
	    	DatastoreUtil.createGoods(req.getParameter("GoodsName")
	    			, new Text(req.getParameter("Description")), Double.parseDouble(req.getParameter("Price"))
	    			, Integer.parseInt(req.getParameter("numOfPieces")), Boolean.FALSE
	    			, KeyFactory.stringToKey(req.getParameter("Category")));
	    }
	    else if (action.equals("Remove")) {
	    	
	    }
	    else if (action.equals("Remove All")) {
	    	DatastoreUtil.removeAllAddress();
	    	DatastoreUtil.removeAllContacts();
	    	DatastoreUtil.removeAllCategories();
	    }
	    
//	    resp.sendRedirect("/AddressInput.jsp");
//	    resp.sendRedirect("/CustomerInput.jsp");
	    resp.sendRedirect("/GoodsInput.jsp");
	}
}
