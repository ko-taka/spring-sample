package jp.co.cntroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.dao.UserMasterDao;
import jp.co.entity.UserMaster;
import jp.co.model.IndexForm;


@Controller
@EnableAutoConfiguration
public class UserController {
	@Autowired
	private UserMasterDao userDao;


	@RequestMapping(value = { "/", "/users" })
	public String index(Model model) {
		List<UserMaster> users = userDao.selectAll();
		model.addAttribute("users", users);
		return "home/index";
	}


	@RequestMapping(value="/users/new", method=RequestMethod.GET)
	public String newUser(Model model) {
		model.addAttribute("indexForm", new IndexForm());
		return "home/new";
	}

    @RequestMapping(value="/users/create", method=RequestMethod.POST)
    public String create(@ModelAttribute IndexForm indexForm, Model model) {

    	UserMaster usermaster = new UserMaster();
    	usermaster.name = indexForm.getName();
    	usermaster.email = indexForm.getEmail();
    	usermaster.password = indexForm.getPassword();
    	usermaster.remarks = indexForm.getRemarks();

        userDao.insert(usermaster);
        return "redirect:/users";
    }
}
