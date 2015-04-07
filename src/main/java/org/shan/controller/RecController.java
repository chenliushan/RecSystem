package org.shan.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.shan.db.DbUtil;
import org.shan.domain.DbSetting;
import org.shan.domain.AnalysedModel;
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

import com.test.TestRecommend;
import com.test.UserDAOImpl;

@Controller
public class RecController {
	
	private static DataModel dataModel;
	
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
	String dbTable(DbSetting dbSetting,HttpServletRequest request,Model model
			) {
		try {
			String rs=
					DbUtil.myExecuateQuery("select * from try1",dbSetting.getDatabaseDriver(), dbSetting.getDatabaseURL(), dbSetting.getDatabaseUser(), dbSetting.getDatabasePassword());
			
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
//		model.addAttribute("name", name);
		return "set_db";
	}
	
	@RequestMapping("/set_db_done")
	public String setDbDone(DbSetting dbSetting,HttpServletRequest request,Model model){
		DatabaseService dbService=new DatabaseService(dbSetting);
		this.dataModel=dbService.newModel();
//		model.addAttribute("recommend", dbService.newRecommend(201225030, 2));
		model.addAttribute("dbSetting", dbSetting);
     return "db_done";
	}
	@RequestMapping("/ok")
	public String setDbOk(){
				
     return "select_model";
	}
	
	@RequestMapping("/model_selected")
	@ResponseBody
	String modelSelected(AnalysedModel modelSelected,HttpServletRequest request,Model model) {
		String CollaborativeFiltering=request.getParameter("collaborative_filtering");
		System.out.println(CollaborativeFiltering+"modelSelected.getCollaborativeFiltering()"+modelSelected.getCollaborative_filtering());
		System.out.println(""+modelSelected.getNeighbourhoods());
		System.out.println(""+modelSelected.getSimilarity());
		
		
		ModelSelectedService modelSelectedService=new ModelSelectedService(modelSelected, dataModel);
		return modelSelectedService.newRecommend(201225030, 2);
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
