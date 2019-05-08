function dealImage(path, obj, callback){
	alert(obj);
			 var img = new Image();
			 img.src = path;
			 img.onload = function(){
			  var that = this;
			  // 默认按比例压缩
			  var w = that.width,
			   h = that.height,
			   scale = w / h;
			   w = obj.width || w;
			   h = obj.height || (w / scale);
			  var quality =0.1;  // 默认图片质量为0.7
			  //生成canvas
			  var canvas = document.createElement('canvas');
			  var ctx = canvas.getContext('2d');
			  // 创建属性节点
			  var anw = document.createAttribute("width");
			  anw.nodeValue = w;
			  var anh = document.createAttribute("height");
			  anh.nodeValue = h;
			  canvas.setAttributeNode(anw);
			  canvas.setAttributeNode(anh); 
			  ctx.drawImage(that, 0, 0, w, h);
			  // 图像质量
			  if(obj.quality && obj.quality <= 1 && obj.quality > 0){
			   quality = obj.quality;
			  }
			  // quality值越小，所绘制出的图像越模糊
			  var base64 = canvas.toDataURL('image/jpeg');
			  // 回调函数返回base64的值
			  callback(base64);
			 }
			}
var dealImage2=function(path, obj){
	var img = new Image();
	img.src = path;
	 if (img.complete) { // 如果图片已经存在于浏览器缓存，直接调用回调函数     
		 var that = this;
			// 默认按比例压缩
			var w = that.width,
			h = that.height,
			scale = w / h;
			w = obj.width || w;
			h = obj.height || (w / scale);
			var quality =0.1;  // 默认图片质量为0.7
			//生成canvas
			var canvas = document.createElement('canvas');
			var ctx = canvas.getContext('2d');
			// 创建属性节点
			var anw = document.createAttribute("width");
			anw.nodeValue = w;
			var anh = document.createAttribute("height");
			anh.nodeValue = h;
			canvas.setAttributeNode(anw);
			canvas.setAttributeNode(anh); 
			ctx.drawImage(that, 0, 0, w, h);
			// 图像质量
			if(obj.quality && obj.quality <= 1 && obj.quality > 0){
				quality = obj.quality;
			}
			// quality值越小，所绘制出的图像越模糊
			var base64 = canvas.toDataURL('image/jpeg');
			// 回调函数返回base64的值
			return base64;
	 }     
	    
	
	img.onload = function(){
		var that = this;
		// 默认按比例压缩
		var w = that.width,
		h = that.height,
		scale = w / h;
		w = obj.width || w;
		h = obj.height || (w / scale);
		var quality =0.1;  // 默认图片质量为0.7
		//生成canvas
		var canvas = document.createElement('canvas');
		var ctx = canvas.getContext('2d');
		// 创建属性节点
		var anw = document.createAttribute("width");
		anw.nodeValue = w;
		var anh = document.createAttribute("height");
		anh.nodeValue = h;
		canvas.setAttributeNode(anw);
		canvas.setAttributeNode(anh); 
		ctx.drawImage(that, 0, 0, w, h);
		// 图像质量
		if(obj.quality && obj.quality <= 1 && obj.quality > 0){
			quality = obj.quality;
		}
		// quality值越小，所绘制出的图像越模糊
		var base64 = canvas.toDataURL('image/jpeg');
		// 回调函数返回base64的值
		return base64;
	}
}