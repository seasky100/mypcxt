import React, {Component} from 'react';
import {connect} from 'react-redux';
import {Link, withRouter, Route, Switch,} from 'react-router-dom';
import PropTypes from 'prop-types';
import {
  Button,
  Menu,
  Layout,
  Breadcrumb,
  Tabs,
  Card,
} from 'antd';
//
//
//
import store from '../../../Store.js';
import UnitPage from '../../unit/views/UnitPage.js';
import ServicePage from '../../service/views/ServicePage.js';
import ErrorPage from '../../route/views/ErrorPage.js';
import UserPage from '../../user/views/UserPage.js';
import QuestionPage from '../../question/views/QuestionPage.js';

import * as ManageActions from '../../manage/ManageActions.js';
//
//
//
const TabPane = Tabs.TabPane;
//
//
const WelcomeManage = () => {
  return (<div style={{
      margin: "50px auto 0",
      textAlign: "center",
    }}>
    <h1>系统管理</h1>
    <h2>请点击左侧标签进入具体的管理模块</h2>
  </div>);
}

class ManagePage extends Component {
  constructor(props, context) {
    super(props, context);
    this.storeChanged = this.storeChanged.bind(this);

    this.state = {
      tabsKey: "1",
      ManageCardLoading: false,
    }

  }

  static contextTypes = {
    router: PropTypes.object
  }

  componentDidMount() {
    store.subscribe(this.storeChanged);
    if (this.context.router.history.location.pathname.includes("/NavbarPage/ManagePage/ServicePage")) {
      this.setState({tabsKey: "3"})
    } else if (this.context.router.history.location.pathname.includes("/NavbarPage/ManagePage/QuestionPage")) {
      this.setState({tabsKey: "5"})
    } else {
      switch (this.context.router.history.location.pathname) {
        case "/NavbarPage/ManagePage":
          {
            this.setState({tabsKey: "1"})
            break;
          }
        case "/NavbarPage/ManagePage/UnitPage":
          {
            this.setState({tabsKey: "2"})
            break;
          }
        case "/NavbarPage/ManagePage/ServicePage":
          {
            this.setState({tabsKey: "3"})
            break;
          }
        case "/NavbarPage/ManagePage/UserPage":
          {
            this.setState({tabsKey: "4"})
            break;
          }
        case "/NavbarPage/ManagePage/QuestionPage":
          {
            this.setState({tabsKey: "5"})
            break;
          }
        default:
          {
            this.setState({tabsKey: "1"})
          }
      }
    }
  }
  storeChanged() {}

  render() {
    return (<Card loading={this.state.ManageCardLoading} title="管理" style={{
        float: "left",
        width: "100%",
      }}>
      {/************************************************/}
      {/************************************************/}
      {/************************************************/}
      <Tabs activeKey={this.state.tabsKey} tabPosition="left" style={{
          float: "left"
        }} onTabClick={(key) => {
          this.setState({
            tabsKey: key
          }, () => {
            switch (this.state.tabsKey) {
              case "1":
                {
                  this.context.router.history.push("/NavbarPage/ManagePage");
                  break;
                }
              case "2":
                {
                  this.context.router.history.push("/NavbarPage/ManagePage/UnitPage");
                  break;
                }
              case "3":
                {
                  if (!this.context.router.history.location.pathname.includes("/NavbarPage/ManagePage/ServicePage/")) {
                    this.context.router.history.push("/NavbarPage/ManagePage/ServicePage/ServiceInstance");
                  }
                  break;
                }
              case "4":
                {
                  this.context.router.history.push("/NavbarPage/ManagePage/UserPage");
                  break;
                }
              case "5":
                {
                  if (!this.context.router.history.location.pathname.includes("/NavbarPage/ManagePage/QuestionPage")) {
                    this.context.router.history.push("/NavbarPage/ManagePage/QuestionPage/QuestionnairePage");
                  }
                  break;
                }
            }
          })

        }}>
        <TabPane tab="管理" key="1"></TabPane>
        <TabPane tab="问卷" key="5"></TabPane>
        <TabPane tab="业务" key="3"></TabPane>
        <TabPane tab="警员" key="4"></TabPane>
        <TabPane tab="单位" key="2"></TabPane>
      </Tabs>
      {/************************************************/}
      {/************************************************/}
      {/************************************************/}
      <div style={{
          float: "left",
          width: "calc( 100% - 160px )",
          margin: "0 0 0 20px",
          minHeight: "calc( 100vh - 66px - 81px - 56px - 48px - 69px )  ",
        }}>
        <Switch>
          <Route path="/NavbarPage/ManagePage/UnitPage" component={UnitPage}></Route>
          <Route path="/NavbarPage/ManagePage/QuestionPage" component={QuestionPage}></Route>
          <Route path="/NavbarPage/ManagePage/ServicePage" component={ServicePage}></Route>
          <Route path="/NavbarPage/ManagePage/UserPage" component={UserPage}></Route>
          <Route path="/NavbarPage/ManagePage" component={WelcomeManage}></Route>

        </Switch>
      </div>
      {/************************************************/}
      {/************************************************/}
      {/************************************************/}
    </Card>);
  }
}

export default withRouter(ManagePage);
