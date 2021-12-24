package com.kh.devrun.shop.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.devrun.common.DevrunUtils;
import com.kh.devrun.promotion.model.service.PromotionService;
import com.kh.devrun.promotion.model.vo.Promotion;
import com.kh.devrun.shop.model.service.ShopService;
import com.kh.devrun.shop.model.vo.Attachment;
import com.kh.devrun.shop.model.vo.Review;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/shop")
public class ShopController {
	
	@Autowired
	private PromotionService promotionService;
	
	@Autowired
	private ShopService shopService;
	
	@Autowired
	ServletContext application;
	
	
//--------------------주입-------------------------------------	
	

	@GetMapping("/shopMain.do")
	public String shopMain() {
		
		return "shop/shopMain";
		
	}
	
	
	
	@GetMapping("/shopSearch.do")
	public void shopSearch() {}
	
	@GetMapping("/shopCategory.do")
	public void shopCategory() {}

	@GetMapping("/itemDetail.do")
	public void itemDetail(Model model) {
		
		List<Review> reviewList = shopService.selectAllReview();
		int reviewTotal = shopService.countAllList();
		log.debug("총 리뷰 갯수{}", reviewTotal);
		model.addAttribute("reviewList", reviewList);
		model.addAttribute("reviewTotal", reviewTotal);
		
		
		
	}
	
	
	
	@PostMapping("/review.do")
	public String review (Review review, MultipartFile upFile, RedirectAttributes redirectAttr) throws IllegalStateException, IOException {
		log.debug("{}", review);
	
		log.debug("아이디 못 받아? {} ", review.getId());
		String saveDirectory = application.getRealPath("/resources/upload/review");
		
		if(!upFile.isEmpty() && upFile.getSize()!= 0) {
			String originalFilename = upFile.getOriginalFilename();
			String renamedFilename = DevrunUtils.getRenamedFilename(originalFilename);
		
			//1.서버 컴퓨터에 저장
			File dest = new File(saveDirectory, renamedFilename);//여기에다가 파일 저장해주세요임.
			upFile.transferTo(dest);
			
			// 2.DB에 attachment 레코드 등록
			Attachment attach = new Attachment();
			attach.setOriginalFilename(originalFilename);
			attach.setRenamedFilename(renamedFilename);
			review.setAttach(attach);
		}
		
		//업무로직
		int result = shopService.insertReview(review);
		String msg = (result>0)?"리뷰 등록 성공" : "리뷰 등록 실패";
		redirectAttr.addFlashAttribute("msg", msg);
		
		return "redirect:/shop/itemDetail.do";
		
		
	}
	
	
	/**
	 * 혜진 작업 시작 
	 */
	@GetMapping("/promotion.do")
	public void promotion(Model model) {
		try {
			Map<String, List<Promotion>> map = promotionService.selectDevidedPromotionList();
			
			model.addAttribute("currentPromotionList", map.get("currentPromotionList"));
			model.addAttribute("endPromotionList", map.get("endPromotionList"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/promotionDetail.do")
	public void promotionDetail(@RequestParam String promotionCode, Model model) {
		log.debug("promotionCode = {}", promotionCode);
		
		try {
			Promotion promotion = promotionService.selectPromotionByPromotionCode(promotionCode);
			model.addAttribute("promotion", promotion);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 혜진 작업 끝
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */

}

