(function () {
  $.MsgBox = {
    Alert: function (title, msg) {
	  $("#wzq_confrimBtnID").remove();
      GenerateHtml("alert", title, msg);
      btnOk(); //alert只是弹出消息，因此没必要用到回调函数callback
      btnNo();
    },
    Confirm: function (title, msg, callback) {
	  $("#wzq_confrimBtnID").remove();
      GenerateHtml("confirm", title, msg);
      btnOk(callback);
      btnNo();
    },
    SubmitConfirm:function (title, msg, callback) {
	      $("#wzq_confrimBtnID").remove();
	      GenerateHtml("submitConfirm", title, msg);
	      btnOk(callback);
	}
  }
 
  //生成Html
  var GenerateHtml = function (type, title, msg) {
 
    var _html = "";
 
    _html += '<div id="wzq_confrimBtnID" class="lo_mask_box">';
    _html += '<div class="content-tan">';
    _html += '<div class="title_txt">'+title+':</div>';
    _html += '<div class="txt_content">'+msg+'</div>';
    _html += '';
    _html += '<div class="button_btn">';
    
      if (type == "alert") {
    	  _html += '<input type="button" class="btn btn-small btn-blue" id="mb_btn_ok"  style="cursor: pointer;"value="确定 "/>';	 
    	  _html += '';
      }
      if (type == "confirm") {
        _html += '<input type="button" class="btn btn-small btn-blue" id="mb_btn_ok"  style="cursor: pointer;margin-right:6px;" value="确定" />';
        _html += '<input type="button" class="btn btn-small btn-gray" id="mb_btn_no" style="cursor: pointer;" value="取消"/>';
      }
      if (type == "submitConfirm") {
          _html += '<input type="button" class="btn btn-small btn-blue" id="mb_btn_ok"  style="cursor: pointer;" value="确定" />';
          _html += '';
        }
    _html += '</div">';
    _html += '</div">';
    _html += '</div">';
     
    //必须先将_html添加到body，再设置Css样式
    $("body").append(_html);
  }
 
  //确定按钮事件
  var btnOk = function (callback) {
    $("#mb_btn_ok").click(function () {
      $("#wzq_confrimBtnID").remove();
      if (typeof (callback) == 'function') {
        callback();
      }
    });
  }
  //取消按钮事件
  var btnNo = function () {
    $("#mb_btn_no").click(function () {
      $("#wzq_confrimBtnID").remove();
    });
  }
})();