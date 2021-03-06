package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import JDBCDAOs.JDBCUserDAO;
import Models.User;

@CrossOrigin
@RestController 
public class UserController {

	@Autowired
	JDBCUserDAO userDao;
	
	@RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
	public User getUserById(@PathVariable long id) {
		return null;
	}
}
