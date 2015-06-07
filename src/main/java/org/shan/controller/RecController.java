package org.shan.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.shan.db.DbUtil;
import org.shan.domain.AnalysedModel;
import org.shan.domain.DbSetting;
import org.shan.recommender.ItemBaseRecommender;
import org.shan.recommender.UserBasedRecommender;
import org.shan.service.DatabaseService;
import org.shan.service.ModelSelectedService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.test.UserDAOImpl;

@Controller
public class RecController {

	private static DataModel dataModel;
	private static ModelSelectedService modelSelectedService;

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

	@RequestMapping("/test/db")
	@ResponseBody
	String dbTable(DbSetting dbSetting, HttpServletRequest request, Model model) {
		try {
			String rs = DbUtil.myExecuateQuery("select * from try1",
					dbSetting.getDatabaseDriver(), dbSetting.getDatabaseURL(),
					dbSetting.getDatabaseUser(),
					dbSetting.getDatabasePassword());

			return rs;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "fail";
	}

	@RequestMapping("/set_db")
	public String set_db(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		// model.addAttribute("name", name);
		return "set_db";
	}

	// @RequestMapping("/blank")
	// public String blankPage(Model model) {
	// List<String> nameList = new ArrayList<String>();
	// nameList.add("aaa");
	// nameList.add("bbb");
	// nameList.add("ccc");
	// nameList.add("ddd");
	// model.addAttribute("nameList", nameList);
	// return "blank";
	// }

	@RequestMapping("/db_done")
	public String setDbDone(
			 DbSetting dbSetting,
			HttpServletRequest request, Model model) {
		if (dbSetting == null) {
			return "set_db";
		} else {

			DatabaseService dbService = new DatabaseService(dbSetting);
			this.dataModel = dbService.newModel();
			System.out.println("dataModel" + dataModel);
			if (dataModel != null) {
				model.addAttribute("conn_message", "success");
			} else {
				model.addAttribute("conn_message", "fail");
			}
			model.addAttribute("dbSetting", dbSetting);
			return "db_done";
		}
	}

	@RequestMapping("/select_model")
	public String setDbOk() {

		return "select_model";
	}

	@RequestMapping("/model_selected")
	String modelSelected(AnalysedModel modelSelected,
			HttpServletRequest request, Model model) {
		String CollaborativeFiltering = request
				.getParameter("collaborative_filtering");
		System.out.println(CollaborativeFiltering
				+ "modelSelected.getCollaborativeFiltering()"
				+ modelSelected.getCollaborative_filtering());
		System.out.println("modelSelected.getNeighbourhoods():"
				+ modelSelected.getNeighbourhoods());
		System.out.println("modelSelected.getSimilarity():"
				+ modelSelected.getSimilarity());
		System.out.println("dataModel" + dataModel);

		if(dataModel==null){
			return "set_db";
		}
		this.modelSelectedService = new ModelSelectedService(modelSelected,
				dataModel);
		model.addAttribute("modelSelected", modelSelected);
		return "recommend";
	}

	@RequestMapping("/recommend_results")
	public String recommendPage(
			@RequestParam(value = "num", required = false, defaultValue = "5") String num,
			@RequestParam(value = "userID", required = false, defaultValue = "201225032") int userID,
			Model model) {
		if(model!=null){
			model.addAttribute("num", num);
			model.addAttribute("userID", userID);
			return "recommended_results";
		}else{
			return "model_selected";
		}
		
		
	}

	@RequestMapping("/recommend")
	@ResponseBody
	public String recommend(
			@RequestParam(value = "num", required = false, defaultValue = "5") String num,
			@RequestParam(value = "userID", required = false, defaultValue = "201225032") int userID,
			Model model) {
		// model.addAttribute("results",
		// modelSelectedService.newRecommend(userID, Integer.valueOf(num)));
		// return "recommend_results";

		if(modelSelectedService!=null){
			return modelSelectedService.newRecommend(userID, Integer.valueOf(num));
		}else{
			return "select_model";
		}
		
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

	@RequestMapping("/")
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}

	@RequestMapping("/home")
	String home_page() {
		return "index";
	}

	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}

}
