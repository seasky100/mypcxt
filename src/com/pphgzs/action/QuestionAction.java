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
import com.pphgzs.domain.DO.mypcxt_answer_choice;
import com.pphgzs.domain.DO.mypcxt_option;
import com.pphgzs.domain.DO.mypcxt_question;
import com.pphgzs.domain.DO.mypcxt_service_definition;
import com.pphgzs.domain.DO.mypcxt_unit;
import com.pphgzs.domain.DTO.QuestionServiceDTO;
import com.pphgzs.domain.DTO.QuestionnaireDTO;
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
	private mypcxt_service_definition service_definition;
	private int moveQuestionAction;
	private QuestionnaireDTO questionnaireDTO;
	private QuestionServiceDTO questionServiceDTO;
	private mypcxt_answer_choice AnswerChoice;
	private String 	BatchQuestionID;//删除问题的字符串ID

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
		String ServiceDefinitionId = questionService.saveQuestion(question);
		if (ServiceDefinitionId!=null) {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setPrettyPrinting();// 格式化json数据
			Gson gson = gsonBuilder.create();
			http_response.setContentType("text/html;charset=utf-8");
			http_response.getWriter().write(gson.toJson(ServiceDefinitionId));
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
		String QuestionId = questionService.addOption(option);
		if (QuestionId!=null) {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setPrettyPrinting();// 格式化json数据
			Gson gson = gsonBuilder.create();
			http_response.setContentType("text/html;charset=utf-8");
			http_response.getWriter().write(gson.toJson(QuestionId));
		} else {
			http_response.setContentType("text/html;charset=utf-8");
			http_response.getWriter().write("-1");
		}
	}

	/*
	 * 移动选择题选项
	 */
	public void moveOption() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		String QuestionId = questionService.moveOption(moveOptionAction, option.getMypcxt_option_id());
		if (QuestionId != null) {

			http_response.setContentType("text/html;charset=utf-8");
			http_response.getWriter().write(gson.toJson(QuestionId));
		} else {
			http_response.setContentType("text/html;charset=utf-8");
			http_response.getWriter().write("-1");
		}
	}

	/*
	 * 修改问题
	 */
	public void updateQuestion() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		String ServiceDefinitionId = questionService.updateQuestion(question);
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(ServiceDefinitionId));
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
	 * 根据业务定义ID得到业务问卷
	 */
	public void getquestionnaireDTO_byServiceDefinitionID() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		questionnaireDTO = questionService
				.getquestionnaireDTO_byServiceDefinitionID(service_definition.getMypcxt_service_definition_id());
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(questionnaireDTO));
	}

	/*
	 * 移动问题
	 */
	public void moveQuestion() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		String ServiceDefinitionId = questionService.moveQuestion(moveQuestionAction, question.getMypcxt_question_id());
		if (ServiceDefinitionId != null) {
			http_response.setContentType("text/html;charset=utf-8");
			http_response.getWriter().write(gson.toJson(ServiceDefinitionId));
		} else {
			http_response.setContentType("text/html;charset=utf-8");
			http_response.getWriter().write("-1");
		}
	}

	/*
	 * 更新选择题选项
	 */
	public void updateOption() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		String service_definition_id = questionService.updateOption(option);
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(service_definition_id));
	}

	/*
	 * 根据问题Id获得业务问题
	 */
	public void getquestionServiceDTO_byQuestionID() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		questionServiceDTO = questionService
				.getQuestionServiceDTO_byQuestionID(questionServiceDTO.getQuestion().getMypcxt_question_id());
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(questionServiceDTO));
	}
	/*
	 * 删除问题
	 */
	public void deleteQuestion() throws IOException{
		if(questionService.deleteQuestion(BatchQuestionID)){
			http_response.setContentType("text/html;charset=utf-8");
			http_response.getWriter().write("1");
		}else{
			http_response.setContentType("text/html;charset=utf-8");
			http_response.getWriter().write("-1");
		}
	}
	/*
	 * 添加选择题回答
	 */
	public void addAnswerChoice() throws IOException{
		if(questionService.addAnswerChoice(AnswerChoice)){
			http_response.setContentType("text/html;charset=utf-8");
			http_response.getWriter().write("1");
		}else{
			http_response.setContentType("text/html;charset=utf-8");
			http_response.getWriter().write("-1");
		}
	}
   /*
    * 通过ID得到相应的选项
    */
     public void getOptionByID() throws IOException{
    	 GsonBuilder gsonBuilder = new GsonBuilder();
 		gsonBuilder.setPrettyPrinting();// 格式化json数据
 		Gson gson = gsonBuilder.create();
 		option = questionService.getOptionByID(option.getMypcxt_option_id());
 		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(option));
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

	public mypcxt_service_definition getService_definition() {
		return service_definition;
	}

	public void setService_definition(mypcxt_service_definition service_definition) {
		this.service_definition = service_definition;
	}

	public int getMoveQuestionAction() {
		return moveQuestionAction;
	}

	public void setMoveQuestionAction(int moveQuestionAction) {
		this.moveQuestionAction = moveQuestionAction;
	}

	public void setQuestionnaireVO(QuestionnaireVO questionnaireVO) {
		this.questionnaireVO = questionnaireVO;
	}

	public QuestionnaireDTO getQuestionnaireDTO() {
		return questionnaireDTO;
	}

	public void setQuestionnaireDTO(QuestionnaireDTO questionnaireDTO) {
		this.questionnaireDTO = questionnaireDTO;
	}

	public QuestionServiceDTO getQuestionServiceDTO() {
		return questionServiceDTO;
	}

	public void setQuestionServiceDTO(QuestionServiceDTO questionServiceDTO) {
		this.questionServiceDTO = questionServiceDTO;
	}

	public mypcxt_answer_choice getAnswerChoice() {
		return AnswerChoice;
	}

	public void setAnswerChoice(mypcxt_answer_choice answerChoice) {
		AnswerChoice = answerChoice;
	}

	public String getBatchQuestionID() {
		return BatchQuestionID;
	}

	public void setBatchQuestionID(String batchQuestionID) {
		BatchQuestionID = batchQuestionID;
	}

	/*
	 * 
	 */
}
