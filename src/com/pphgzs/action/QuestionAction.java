package com.pphgzs.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;
import com.pphgzs.domain.DO.mypcxt_option;
import com.pphgzs.domain.DO.mypcxt_question;
import com.pphgzs.domain.DTO.ServiceDefinitionDTO;
import com.pphgzs.domain.VO.QuestionServiceVO;
import com.pphgzs.domain.VO.QuestionnaireVO;
import com.pphgzs.service.QuestionService;

@SuppressWarnings("serial")
public class QuestionAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {

	private QuestionService questionService;
	private HttpServletResponse http_response;
	private HttpServletRequest http_request;

	/* 
	 * 
	 */
	private mypcxt_question question;
	private mypcxt_option option;
	private int moveOptionAction;
	private QuestionnaireVO questionnaireVO;

	/*
	 *  
	 */
	public void getQuestionServiceVO() throws IOException {

		QuestionServiceVO questionServiceVO = new QuestionServiceVO();

		questionServiceVO = questionService.getQuestionServiceVO();
		// System.out.println("questionServiceVO"+questionServiceVO);
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(questionServiceVO));

	}

	/*
	 * 创建问题
	 */
	public void addQuestion() throws IOException {
		if (questionService.saveQuestion(question)) {
			http_response.setContentType("text/html;charset=utf-8");
			http_response.getWriter().write("1");
		} else {
			http_response.setContentType("text/html;charset=utf-8");
			http_response.getWriter().write("-1");
		}
	}

	/*
	 * 获取任务定义列表
	 */
	public void getServiceDefinitionList() throws IOException {
		List<ServiceDefinitionDTO> ServiceDefinitionDTOList = new ArrayList<ServiceDefinitionDTO>();

		ServiceDefinitionDTOList = questionService.getServiceDefinitionDTOList();

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(ServiceDefinitionDTOList));
	}

	/*
	 * 创建选择题选项
	 * 
	 */
	public void addOption() throws IOException {
		if (questionService.addOption(option)) {
			http_response.setContentType("text/html;charset=utf-8");
			http_response.getWriter().write("1");
		} else {
			http_response.setContentType("text/html;charset=utf-8");
			http_response.getWriter().write("-1");
		}
	}

	/*
	 * 移动选择题选项
	 */
	public void moveOption() throws IOException {
		if (questionService.moveOption(moveOptionAction, option.getMypcxt_option_id())) {
			http_response.setContentType("text/html;charset=utf-8");
			http_response.getWriter().write("1");
		} else {
			http_response.setContentType("text/html;charset=utf-8");
			http_response.getWriter().write("-1");
		}
	}

	/*
	 * 修改问题
	 */
	public void updateQuestion() throws IOException {
		questionService.updateQuestion(question);
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write("1");
	}

	/*
	 * 获取问题列表
	 */
	public void getQuestionFatherList() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		List<mypcxt_question> questionList = questionService.getChoiceQuestionAll();
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(questionList));
	}

	/*
	 * 获取页面问卷
	 */
	public void getQuestionnaireVO() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		questionnaireVO = questionService.getQuestionnaireVO();
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(questionnaireVO));
	}

	/*
	 */
	@Override
	public void setServletRequest(HttpServletRequest http_request) {
		this.http_request = http_request;
	}

	@Override
	public void setServletResponse(HttpServletResponse http_response) {
		this.http_response = http_response;

	}

	public HttpServletResponse getHttp_response() {
		return http_response;
	}

	public void setHttp_response(HttpServletResponse http_response) {
		this.http_response = http_response;
	}

	public HttpServletRequest getHttp_request() {
		return http_request;
	}

	public void setHttp_request(HttpServletRequest http_request) {
		this.http_request = http_request;
	}

	public QuestionService getQuestionService() {
		return questionService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public mypcxt_question getQuestion() {
		return question;
	}

	public void setQuestion(mypcxt_question question) {
		this.question = question;
	}

	public mypcxt_option getOption() {
		return option;
	}

	public void setOption(mypcxt_option option) {
		this.option = option;
	}

	public int getMoveOptionAction() {
		return moveOptionAction;
	}

	public void setMoveOptionAction(int moveOptionAction) {
		this.moveOptionAction = moveOptionAction;
	}

	/*
	 * 
	 */
}
