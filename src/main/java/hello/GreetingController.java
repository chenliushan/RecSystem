package hello;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.test.TestRecommend;
import com.test.UserDAOImpl;

@Controller
public class GreetingController {

    @RequestMapping("/1")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
    
    
    @RequestMapping("/2")
    @ResponseBody
    String home() {
    	
    	return TestRecommend.testRecommend();
//        return "Hello World!";
    }
    @RequestMapping("/testdb")
    @ResponseBody
    String testdb() {
    	UserDAOImpl u = new UserDAOImpl();
		System.out.println("u.finduserbyCid():"+u.finduserbyCid());
    	return u.finduserbyCid();
//        return "Hello World!";
    }
  
    
    @RequestMapping("/3")
    public String testDB(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
       
    	model.addAttribute("name", name);
        return "greeting";
    }

    
    @RequestMapping(value="/4")
	public ModelAndView login(String username,String password){
//		if(this.checkParams(new String[]{username,password})){
			ModelAndView mav = new ModelAndView("succ");
			mav.addObject("username",username);
			mav.addObject("password", password);
			return mav;
//		}
//		return new ModelAndView("home");
	}
    @RequestMapping(value="5",method=RequestMethod.POST)
	public ModelAndView login(String username,String password,HttpServletRequest request){
		request.setAttribute("username", username);
		request.setAttribute("password", password);
		return new ModelAndView("succ");
	}
    
 
    @RequestMapping("6")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}
   
}
