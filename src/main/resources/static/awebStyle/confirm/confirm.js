(function () {
  $.MsgBox = {
    Alert: function (title, msg) {
      GenerateHtml("alert", title, msg);
      $(".lo_mask_box").addClass('active');
      btnOk(); //alert只是弹出消息，因此没必要用到回调函数callback
      btnNo();
    },
    Confirm: function (title, msg, callback) {
      GenerateHtml("confirm", title, msg);
     $(".lo_mask_box").addClass('active');
      btnOk(callback);
      btnNo();
    }
  }
 
  //生成Html
  var GenerateHtml = function (type, title, msg) {
 
    var _html = "";
 
    _html += '<div id="wzq_confrimBtnID" class="lo_mask_box">';
    _html += '<div class="content-tan">';
    _html += '<div class="content-text">';
    _html += '<div class="txt_content">'+msg+'</div>';
    _html +='</div>';
    _html += '';
    _html += '<div class="button_btn">';
    
      if (type == "alert") {
        _html += '<div type="button" class="one-btn" id="mb_btn_ok" >确定</div>';
    	  //_html += '<input type="button" class="ti_right_btn button" id="mb_btn_no" style="cursor: pointer;"value="取消"/>';
    	  _html += '';
      }
      if (type == "confirm") {
        _html += '<div type="button" class="ti_left_btn button" id="mb_btn_ok" >确定</div>';
        _html += '<div type="button" class="ti_right_btn button" id="mb_btn_no" >取消</div>';
      }
   
    _html += '</div">';
    _html += '</div">';
     
    //必须先将_html添加到body，再设置Css样式
    $("body").append(_html);
  }
 
  //确定按钮事件
  var btnOk = function (callback) {
    $("#mb_btn_ok").click(function () {
	  $(".lo_mask_box").removeClass('active');
      $("#wzq_confrimBtnID").remove();
      if (typeof (callback) == 'function') {
        callback();
      }
    });
  }
  //取消按钮事件
  var btnNo = function () {
    $("#mb_btn_no").click(function () {
	  $(".lo_mask_box").removeClass('active');
      $("#wzq_confrimBtnID").remove();
    });
  }
})();