
accessid = ''
accesskey = ''
host = ''
policyBase64 = ''
signature = ''
callbackbody = ''
filename = ''
key = ''
expire = 0
g_object_name = ''
g_object_name_type = 'random_name'
now = timestamp = Date.parse(new Date()) / 1000; 

function send_request(orgCode){
    var xmlhttp = null;
    if (window.XMLHttpRequest){
        xmlhttp=new XMLHttpRequest();
    }else if (window.ActiveXObject){
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    if (xmlhttp!=null){
        serverUrl = ctx + 'oss/getUploadOssPolicy?ossPolicyType='+$('#ossPolicyType').val()+"&orgCode="+orgCode;
        xmlhttp.open("GET", serverUrl, false );
        xmlhttp.send(null);
        var resStr = xmlhttp.responseText;
        if(null == resStr || ""==resStr){
//        	document.getElementById('console').appendChild(document.createTextNode("\n上传失败"));
        	alert("上传失败");
        	return;
        }
        return resStr
    }else{
        alert("Your browser does not support XMLHTTP.");
    }
};

function get_signature(orgCode){
    //可以判断当前expire是否超过了当前时间,如果超过了当前时间,就重新取一下.3s 做为缓冲
    now = timestamp = Date.parse(new Date()) / 1000; 
    if (expire < now + 3){
        body = send_request(orgCode)
        var obj = eval ("(" + body + ")");
        host = obj['host']
        policyBase64 = obj['policy']
        accessid = obj['accessid']
        signature = obj['signature']
        expire = parseInt(obj['expire'])
        callbackbody = obj['callback'] 
        key = obj['dir']
        return true;
    }
    return false;
};

function random_string(len) {
　　	len = len || 32;
　　	var chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';   
　　	var maxPos = chars.length;
　　	var pwd = '';
	for (i = 0; i < len; i++) {
    　　	pwd += chars.charAt(Math.floor(Math.random() * maxPos));
	}
    return pwd;
}

function get_suffix(filename) {
    pos = filename.lastIndexOf('.')
    suffix = ''
    if (pos != -1) {
        suffix = filename.substring(pos)
    }
    return suffix;
}

function calculate_object_name(filename){
    if (g_object_name_type == 'local_name'){
        g_object_name += "${filename}"
    }else if (g_object_name_type == 'random_name'){
        suffix = get_suffix(filename)
        g_object_name = key + random_string() + suffix
    }
    return ''
}

function get_uploaded_object_name(filename){
    if (g_object_name_type == 'local_name'){
        tmp_name = g_object_name;
        tmp_name = tmp_name.replace("${filename}", filename);
        return tmp_name;
    } else if(g_object_name_type == 'random_name'){
        return g_object_name;
    }
}

function set_upload_param(up, filename, ret, orgCode){
	orgCode = arguments[3]?arguments[3]:''; 
    if (ret == false){
        ret = get_signature(orgCode);
    }
    g_object_name = get_uploaded_object_name(filename);
    if (filename != '') {
        suffix = get_suffix(filename);
        calculate_object_name(filename);
    }
    new_multipart_params = {
        'key' : g_object_name,
        'policy': policyBase64,
        'OSSAccessKeyId': accessid, 
        'success_action_status' : '200', //让服务端返回200,不然，默认会返回204
        'callback' : callbackbody,
        'signature': signature
    };
    up.setOption({
        'url': host,
        'multipart_params': new_multipart_params
    });
    up.start();
}

function initSysUploader(ids,orgCode,isMulti){
	isMulti = arguments[2]?true:false; 
	$.each(ids,function(i,n){
		var self = this.toString();
		var uploader = new plupload.Uploader({
			runtimes : 'html5,flash,silverlight,html4',
			browse_button : self, 
			flash_swf_url : '/plugins/oss-h5-upload/lib/plupload-2.1.2/js/Moxie.swf',
			silverlight_xap_url : '/plugins/oss-h5-upload/lib/plupload-2.1.2/js/Moxie.xap',
		    url : 'https://redrbtoss.bytes-space.com',
		    filters: {
		        mime_types : [ //只允许上传图片和zip,rar文件
		        		{ title : "Image files", extensions : "jpg,jpeg,gif,png,bmp" }, 
		        		{ title : "Zip files", extensions : "zip,rar" },
		        		{ title : "attachment files", extensions : "doc,docx,xls,xlsx" },
		        		{ title : "Video files", extensions : "mp4,mkv,rmvb" }
		        	],
		        max_file_size : '30mb', //最大只能上传10mb的文件
		        prevent_duplicates : true //不允许选取重复文件
		    },
			init: {
				PostInit: function() {
					document.getElementById(self+'_ossfile').innerHTML = '';
					document.getElementById(self+'_upload').onclick = function() {
		            set_upload_param(uploader, '', false,orgCode);
		            return false;
					};
				},
		
				FilesAdded: function(up, files) {
					$("#"+self+'_console').addClass("hide");
					if(!isMulti && up.files.length>1) { // 最多上传1个文件
						$("#"+self+'_console').removeClass("hide");
	                    document.getElementById(self+'_console').appendChild(document.createTextNode("\n只能上传一个文件"));
	                    return;
	                }
					plupload.each(files, function(file) {
						document.getElementById(self+'_ossfile').innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ')<b></b>'
						+'<div class="progress"><div class="progress-bar" style="width: 0%"></div></div>'
						+'</div>';
					});
				},
		
				BeforeUpload: function(up, file) {
		            set_upload_param(up, file.name, true);
		        },
		
				UploadProgress: function(up, file) {
					var d = document.getElementById(file.id);
					d.getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
		            var prog = d.getElementsByTagName('div')[0];
					var progBar = prog.getElementsByTagName('div')[0]
					progBar.style.width= 2*file.percent+'px';
					progBar.setAttribute('aria-valuenow', file.percent);
				},
		
				FileUploaded: function(up, file, info) {
		            if (info.status == 200){
		                document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = ' 上传成功, 文件名:' + get_uploaded_object_name(file.name);
		                if(isMulti){//多文件上传
		                	 $("#"+self+"_pics").val($("#"+self+"_pics").val()+","+get_uploaded_object_name(file.name));
		                }else{
		                	$("#"+self+"_pics").val(get_uploaded_object_name(file.name));
		                }
		            }
		            else{
		                document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = info.response;
		            } 
				},
		
				Error: function(up, err) {
					$("#"+self+'_console').removeClass("hide");
		            if (err.code == -600) {
		                document.getElementById(self+'_console').appendChild(document.createTextNode("\n选择的文件太大了,可以根据应用情况，在upload.js 设置一下上传的最大大小"));
		            }
		            else if (err.code == -601) {
		                document.getElementById(self+'_console').appendChild(document.createTextNode("\n选择的文件后缀不对,可以根据应用情况，在upload.js进行设置可允许的上传文件类型"));
		            }
		            else if (err.code == -602) {
		                document.getElementById(self+'_console').appendChild(document.createTextNode("\n这个文件已经上传过一遍了"));
		            }
		            else {
		                document.getElementById(self+'_console').appendChild(document.createTextNode("\nError xml:" + err.response));
		            }
				}
			}
		});
		
		uploader.init();
	});
}

