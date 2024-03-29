package jp.co.internous.twinkle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.internous.twinkle.model.domain.MstUser;
import jp.co.internous.twinkle.model.form.UserForm;
import jp.co.internous.twinkle.model.mapper.MstUserMapper;
import jp.co.internous.twinkle.model.session.LoginSession;

@Controller
@RequestMapping("/twinkle/user")
public class UserController {

	@Autowired
	private MstUserMapper userMapper;

	@Autowired
	private LoginSession loginSession;

	@RequestMapping("/")
	public String index(Model m) {
		m.addAttribute("loginSession",loginSession);
		return "register_user";
	}

	@RequestMapping("/duplicatedUserName")
	@ResponseBody
	public boolean duplicatedUserName(@RequestBody UserForm f) {
		int count = userMapper.findCountByUserName(f.getUserName());
		return count > 0;
	}

	@RequestMapping("/register")
	@ResponseBody
	public boolean register(@RequestBody UserForm f) {
		MstUser user = new MstUser(f);
		int count = userMapper.insert(user);
		return count > 0;
	}
}