import React from 'react';
import {connect} from 'react-redux';
import {withRouter} from 'react-router-dom';
import {
  Panel,
  ButtonToolbar,
  Button,
  FormGroup,
  FormControl,
  Image,
  Jumbotron
} from 'react-bootstrap';

/**
 * css
 * @type {Object}
 */
const mainStyle = {
  margin: "71px 0 0 0",
  float: "left",
  width: "100%",
  height: "100%"
};

/**
 * 欢迎页
 * @method IndexPage
 */
const IndexPage = () => {
  return (<div style={mainStyle
}>
    <Jumbotron style={{
        width: "95%",
        margin: "20px auto",
        textAlign: "center"
      }}>
      <h1>欢迎使用</h1>
      <p>萍乡市公安局业务评测系统</p>
    </Jumbotron>
  </div>);
};

export default withRouter(connect()(IndexPage));