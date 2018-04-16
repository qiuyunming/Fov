package org.ifsim.vairline.portal.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.ifsim.vairline.common.consts.ImgData;
import org.ifsim.vairline.common.storage.QiniuStorage;
import org.ifsim.vairline.common.util.WebUtil;
import org.ifsim.vairline.core.article.domain.Article;
import org.ifsim.vairline.core.article.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PortalController {

	@Autowired
	private IArticleService articleService;

	@RequestMapping("/")
	public String index(Map<String, Object> view) {
		//获取一些类别的文章
		Article articleEntity = new Article();
		articleEntity.setTypeId(3);
		List<Article> activeList = articleService.get(articleEntity);
		view.put("activeList", activeList);
		articleEntity.setTypeId(2);
		List<Article> newsList = articleService.get(articleEntity);
		view.put("newsList", newsList);
		articleEntity.setTypeId(17);
		List<Article> noticeList = articleService.get(articleEntity);
		view.put("noticeList", noticeList);

		List<Article> allArticleList = new ArrayList<Article>();
		allArticleList.addAll(activeList);
		allArticleList.addAll(noticeList);
		allArticleList.addAll(newsList);

		Collections.sort(allArticleList, new ArticleComparator());
		view.put("allArticleList", allArticleList);

		return "index";
	}

	@RequestMapping("/error")
	public String error() {
		return "error";
	}

	/**
	 * @Description: 搜索
	 */
	@RequestMapping(value = "/search", method = { RequestMethod.GET })
	public String search(@Param("keyStr") String keyStr, Map<String, Object> view) {
		if (keyStr.trim().equals("")) {
			return "error";
		}
		keyStr = WebUtil.delSpecialChar(keyStr);
		// 空格分割
		String keys[] = keyStr.split(" ");
		for (int i = 0; i < keys.length; i++) {
			keys[i] = keys[i].trim();
		}

		List<Article> articleResult = articleService.searchKey(keys);
		view.put("keyStr", keyStr);
		view.put("articleResult", articleResult);
		return "search/search";
	}

	/**
	 * @Description: 上传图片
	 */
	@RequestMapping("/upload_img")
	public @ResponseBody ImgData uploadImg(@RequestParam String alt, @RequestParam MultipartFile image) {
		ImgData imgData = new ImgData();
		try {
			String key = QiniuStorage.uploadImage(image.getBytes());
			imgData.setUrl(QiniuStorage.getUrl(key));
			imgData.setSuccess(true);
		} catch (IOException e) {
			e.printStackTrace();
			imgData.setSuccess(false);
		}
		return imgData;
	}

	private class ArticleComparator implements Comparator<Article> {

		@Override
		public int compare(Article o1, Article o2) {
			if (o2.getCreateTime().getTime() - o1.getCreateTime().getTime() > 0) {
				return 1;
			} else if (o2.getCreateTime().getTime() - o1.getCreateTime().getTime() < 0) {
				return -1;
			} else {
				return 0;
			}
		}

	}

}
