import React, {Component} from 'react';
import {connect} from 'react-redux';
import {withRouter} from 'react-router-dom';

import {
  Button,
  Menu,
  Layout,
  Breadcrumb,
  Tabs,
  Icon,
  Table,
  Checkbox,
  InputNumber,
  Modal,
  Form,
  Input,
  Popconfirm,
  Divider,
  Tooltip,
  Pagination,
  Select,
  Tag,
} from 'antd';
//
//
//
import store from '../../../../Store.js';
import * as UserActions from '../../../user/UserActions.js';
import * as QuestionnaireActions from '../../../question/QuestionnaireActions.js';
import * as QuestionActions from '../../../question/QuestionActions.js';
//
//
//
const FormItem = Form.Item;
const {Column, ColumnGroup,} = Table;
const Option = Select.Option;
//
////
////

class Model_Questionnaire extends Component {
  constructor(props, context) {
    super(props, context);
    this.storeChanged = this.storeChanged.bind(this);

    this.state = {
      questionnaireModalVisible: false,
      questionnaireDTO: {
        serviceDefinitionDTO: {},
        questionServiceDTOList: []
      }
    }

  }

  componentDidMount() {
    store.subscribe(this.storeChanged);

  }

  storeChanged() {
    if (this.state.questionnaireModalVisible !== store.getState()["QuestionReducer"]["Model_Questionnaire"]["questionnaireModalVisible"]) {
      this.setState({
        questionnaireModalVisible: store.getState()["QuestionReducer"]["Model_Questionnaire"]["questionnaireModalVisible"]
      });
    }
    if (this.state.questionnaireDTO !== store.getState()["QuestionReducer"]["Model_Questionnaire"]["questionnaireDTO"]) {
      this.setState({
        questionnaireDTO: store.getState()["QuestionReducer"]["Model_Questionnaire"]["questionnaireDTO"]
      });
    }
  }
  //
  //
  //
  render() {

    return (<Modal width="90%" title="业务问卷" visible={this.state.questionnaireModalVisible} onCancel={() => {
        store.dispatch(QuestionnaireActions.set_questionnaireModalVisible(false));
      }} footer={[
        <Button onClick={() => {
            store.dispatch(QuestionnaireActions.set_questionnaireModalVisible(false));
          }}>返回</Button>,
      ]}>
      {
        (typeof this.state.questionnaireDTO.questionServiceDTOList === "undefined")
          ? <div></div>
          : <Table size="small" bordered={true} pagination={false} dataSource={this.state.questionnaireDTO.questionServiceDTOList}>
              <Column title="问题" dataIndex="question.question_describe" align="center" render={(text, record) => {
                  return (<div>{text}</div>);
                }}/>
              <Column title="问题类型" width="60px" dataIndex="question.question_type" align="center" render={(text, record) => {
                  return (<div>
                    {
                      (text === "1")
                        ? <Tag color="#108ee9">选择题</Tag>
                        : <Tag color="#87d068">开放题</Tag>
                    }
                  </div>);
                }}/>
              <Column title="选项" dataIndex="optionList" align="center" render={(text, record) => {
                  return (<div>
                    {
                      (record.question.question_type === "1")
                        ? <Table size="small" bordered={true} pagination={false} dataSource={text}>
                            <Column title="选项" dataIndex="option_describe" align="center" render={(text, record) => {
                                return (<div>{text}</div>);
                              }}/>
                            <Column title="分值" dataIndex="option_grade" align="center" render={(text, record) => {
                                return (<div>{text}</div>);
                              }}/>
                            <Column title="操作" width="150px" dataIndex="mypcxt_option_id" align="center" render={(text, record) => {
                                return (<div>
                                  <a onClick={() => {}}><Icon type="edit"/></a>
                                  <Divider type="vertical"/>
                                  <a onClick={() => {
                                      store.dispatch(QuestionActions.moveOption(text, "2"));
                                    }}><Icon type="arrow-up"/></a>
                                  <Divider type="vertical"/>
                                  <a onClick={() => {
                                      store.dispatch(QuestionActions.moveOption(text, "1"));
                                    }}><Icon type="arrow-down"/></a>
                                  <Divider type="vertical"/>
                                  <a onClick={() => {}}><Icon type="delete"/></a>
                                </div>);
                              }}/>
                          </Table>
                        : <div>无</div>
                    }
                  </div>);
                }}/>
              <Column title="操作" width="150px" dataIndex="question.mypcxt_question_id" align="center" render={(text, record) => {
                  return (<div>
                    <a onClick={() => {}}><Icon type="edit"/></a>
                    <Divider type="vertical"/>
                    <a onClick={() => {
                        store.dispatch(QuestionActions.moveQuestion(text, "2"));
                      }}><Icon type="arrow-up"/></a>
                    <Divider type="vertical"/>
                    <a onClick={() => {
                        store.dispatch(QuestionActions.moveQuestion(text, "1"));
                      }}><Icon type="arrow-down"/></a>
                    <Divider type="vertical"/>
                    <a onClick={() => {}}><Icon type="delete"/></a>
                  </div>);
                }}/>
            </Table>
      }
    </Modal>);
  }
}

export default withRouter(Model_Questionnaire);