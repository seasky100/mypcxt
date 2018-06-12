export const set_questionnaireModalVisible = (questionnaireModalVisible) => ({type: 'set_questionnaireModalVisible', questionnaireModalVisible: questionnaireModalVisible,})
export const set_questionnaireModalState = (questionnaireDTO) => ({type: 'set_questionnaireModalState', questionnaireDTO: questionnaireDTO,})
export const setQuestionnaireTableLoading = (tableLoading) => ({type: 'setQuestionnaireTableLoading', tableLoading: tableLoading,})
export const updateQuestionnaireVO = (questionnaireVO) => ({type: 'updateQuestionnaireVO', questionnaireVO: questionnaireVO,})
export const getQuestionnaireVO = () => {
  return(dispatch) => {
    dispatch(setQuestionnaireTableLoading(true));
    fetch('/mypcxt/Question/getQuestionnaireVO', {
      method: 'POST',
      headers: {}
    }).then((response) => {
      if (response.status === 200) {
        response.json().then((responseJson) => {
          dispatch(updateQuestionnaireVO(responseJson));
          dispatch(setQuestionnaireTableLoading(false));
        }).catch((error) => {
          console.error(error);
        });
      } else {
        console.error(response.status);
      }
    }).catch((error) => {
      console.error(error);
    });
  };
}
export const getquestionnaireDTO_byServiceDefinitionID = (mypcxt_service_definition_id) => {
  return(dispatch) => {
    let formData = new FormData();
    formData.append("service_definition.mypcxt_service_definition_id", mypcxt_service_definition_id);
    fetch('/mypcxt/Question/getquestionnaireDTO_byServiceDefinitionID', {
      method: 'POST',
      body: formData
    }).then((response) => {
      if (response.status === 200) {
        response.json().then((responseJson) => {
          dispatch(set_questionnaireModalState(responseJson));
        }).catch((error) => {
          console.error(error);
        });
      } else {
        console.error(response.status);
      }
    }).catch((error) => {
      console.error(error);
    });
  };
}