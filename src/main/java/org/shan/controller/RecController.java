package org.shan.controller;

import java.util.List;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.shan.recommender.ItemBaseRecommender;
import org.shan.recommender.UserBasedRecommender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.test.TestRecommend;
import com.test.UserDAOImpl;

@Controller
public class RecController {
	@RequestMapping("/test/itemcf")
	@ResponseBody
	String itemCF(
			@RequestParam(value = "userID", required = false, defaultValue = "201225032") long userID,
			@RequestParam(value = "howMany", required = false, defaultValue = "2") int howMany) {
		try {
			Recommender recommender = new ItemBaseRecommender();
			System.out.println("recommender:" + recommender.toString());
			List<RecommendedItem> items = recommender
					.recommend(userID, howMany);
			return items.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "fail";
	}

	@RequestMapping("/test/usercf")
	@ResponseBody
	String userCF(
			@RequestParam(value = "userID", required = false, defaultValue = "201225032") long userID,
			@RequestParam(value = "howMany", required = false, defaultValue = "2") int howMany) {
		try {
			Recommender recommender = new UserBasedRecommender();
			System.out.println("recommender:" + recommender.toString());
			List<RecommendedItem> items = recommender
					.recommend(userID, howMany);
			return items.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "fail";
	}

	@RequestMapping("/test")
	@ResponseBody
	String item() {
		UserDAOImpl u = new UserDAOImpl();
		return u.finduserbyCid();
	}

	@RequestMapping("/shan")
	public String greeting(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}

	@RequestMapping("/charts")
	public String charts() {

		return "charts";
	}

	@RequestMapping("/tables")
	public String tables() {

		return "tables";
	}

	@RequestMapping("/")
	@ResponseBody
	String home() {

		return TestRecommend.testRecommend();
		// return "Hello World!";
	}

	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}

}
