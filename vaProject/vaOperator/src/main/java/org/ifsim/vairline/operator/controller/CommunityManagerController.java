package org.ifsim.vairline.operator.controller;

import java.util.List;
import java.util.Map;

import org.ifsim.vairline.common.consts.WebState;
import org.ifsim.vairline.core.article.domain.Article;
import org.ifsim.vairline.core.article.domain.ArticleType;
import org.ifsim.vairline.core.article.service.IArticleService;
import org.ifsim.vairline.core.article.service.IArticleTypeService;
import org.ifsim.vairline.operator.vo.TableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/community")
public class CommunityManagerController {

	@Autowired
	private IArticleTypeService articleTypeService;

	@Autowired
	private IArticleService articleService;

	@Autowired
	private WebState webState;

	/*
	 * 板块管理
	 * 
	 */
	@RequestMapping("/type_manager")
	public String articleTypeManager() {
		return "community/type_manager";
	}

	@RequestMapping("/get_all_type")
	public @ResponseBody TableVO<ArticleType> getAllType() {
		List<ArticleType> articleTypeList = articleTypeService.get(null);
		TableVO<ArticleType> tableVO = new TableVO<ArticleType>(articleTypeList);
		return tableVO;
	}

	@RequestMapping("/update_type")
	public @ResponseBody WebState updateType(ArticleType targetArticleType) {
		articleTypeService.update(targetArticleType);
		return webState;
	}

	@RequestMapping("/del_type")
	public @ResponseBody WebState deleteType(ArticleType targetArticleType) {
		articleTypeService.delete(targetArticleType);
		return webState;
	}

	/*
	 * 文章管理
	 * 
	 */
	@RequestMapping("/article_manager/{typeId}")
	public String articleManager(@PathVariable Integer typeId, Map<String, Object> view) {
		view.put("typeId", typeId);
		return "community/article_manager";
	}

	@RequestMapping("/get_all_article")
	public @ResponseBody TableVO<Article> getAllArticle() {
		List<Article> articleList = articleService.get(null);
		TableVO<Article> tableVO = new TableVO<Article>(articleList);
		return tableVO;
	}

	@RequestMapping("/get_article")
	public @ResponseBody TableVO<Article> getArticle(Article entity) {
		List<Article> articleList = articleService.get(entity);
		TableVO<Article> tableVO = new TableVO<Article>(articleList);
		return tableVO;
	}

	@RequestMapping("/update_article")
	public @ResponseBody WebState updateArticle(Article targetArticle) {
		articleService.update(targetArticle);
		return webState;
	}

	@RequestMapping("/del_article")
	public @ResponseBody WebState deleteArticle(Article targetArticle) {
		articleService.delete(targetArticle);
		return webState;
	}

}
