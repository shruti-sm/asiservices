var common = (function() {
	
	var obj = {};
	
	obj.tokenKey = "authToken";
	obj.webPath = '/asi';
	obj.restservices = '/resources';
	
	obj.userTypeEmp = 'emp';
	obj.userTypeReports = 'reports';
	obj.userTypeSecurity = 'security';
	obj.empPath = '';
	obj.reportsPath = '/reports';
	obj.securityPath = '/security';
	
	/*
	 * http://tosbourn.com/a-fix-for-window-location-origin-in-internet-explorer/
	 */
	if (!window.location.origin) {
		window.location.origin = window.location.protocol + "//"
				+ window.location.hostname
				+ (window.location.port ? ':' + window.location.port : '');
	}
	
	obj.authorize = function(userType, redirectLoginUrl){
		
		if($.cookie(obj.tokenKey) != undefined) {
			var token = $.parseJSON($.cookie(obj.tokenKey));
			if(!token.userType == userType) {
				window.location.href = window.location.origin + obj.webPath + redirectLoginUrl;
			}
		} else {
			window.location.href = window.location.origin + obj.webPath + redirectLoginUrl;
		}
	};
	obj.authorized = function(userType){
		
		if($.cookie(obj.tokenKey) != undefined) {
			var token = $.parseJSON($.cookie(obj.tokenKey));
			if(token.userType == userType) {
				return true;
			}
		}
		return false;
	};
	
	
	obj.ajax = function(request, options) {
		options = defaultOptions(options);
		
		updateUrl(request);
		updateContentType(request);
		
		if(options.auth) {
			addAuthorization(request);
			//loginForInvalidToken(request);
		}
		/*
		 * This is a fix to prevent caching of custom ajax calls in IE.
		 * 
		 */
		request.cache = false;
		
		$.ajax(request);
	};
	
	function updateUrl(request) {
		request.url = obj.webPath + obj.restservices + request.url;
	}
		
	obj.queryString = function(){
		var url = document.URL;
		var arr = url.split("?");
		if(arr.length>1){
			return "?"+arr[1];
		}
		return "";
	};
	
	
	
	obj.generateUUID = function() {
	    var d = new Date().getTime();
	    var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
	        var r = (d + Math.random() * 16) % 16 | 0;
	        d = Math.floor(d / 16);
	        return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
	    });
	    return uuid;
	};
	obj.toURL = function(fileurl) {
		
		/*
		 * Some of the dynamic urls are already appended with /file (Though it should not be.)
		 */
		if(!startsWith(fileurl, '/file'))
			fileurl = '/file' + fileurl;
		
		return obj.restservices + fileurl;
	};
	
	obj.getContextUrl = function() {
		return window.location.origin + obj.webPath;
	};
	
	obj.redirect = function(url) {
		window.location.href = window.location.origin + obj.webPath + url;
	};
	
	obj.login = function(authToken, userType, redirectPage) {
		var token = JSON.stringify({
			token : authToken,
			userType: userType
		});
		alert(token);
		$.cookie(common.tokenKey, token, {expires : 7, path: '/'});
		window.location.href = window.location.origin + obj.webPath + redirectPage + common.queryString();
	};
	obj.logout = function(redirectPage) {
		var request = {
			type : 'GET',
			url : '/logout',
			success : function(response) {
				if(response.code != 1) {
					console.log(response.msg);
				}
				$.removeCookie( obj.tokenKey, { path: '/' } );
				window.location.href = window.location.origin + obj.webPath + redirectPage;
			},
			error : function(e){
				console.log(e);
			}
		};
		common.ajax(request);
	};

	obj.formatDate = function(d) {
		var month = '' + (d.getMonth() + 1);
        var day = '' + d.getDate();
        var year = d.getFullYear();

        if (month.length < 2) month = '0' + month;
        if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
	};
	
	obj.formatDateStandard = function(d) {
		
		return d.getFullYear() + "-" + 
		("00" + (d.getMonth() + 1)).slice(-2)+ "-" + 
	    ("00" + d.getDate()).slice(-2) + " " + 
	    ("00" + d.getHours()).slice(-2) + ":" + 
	    ("00" + d.getMinutes()).slice(-2) + ":" + 
	    ("00" + d.getSeconds()).slice(-2);
	};
	
	function updateContentType(request) {
		if(!request.contentType && request.contentType !== false)
			request.contentType = 'application/json';
	}

	

	function defaultOptions(options) {
		options = options || {};
		
		// Authorize if nothing is said
		if(!options.auth && options.auth !== false) {
			options.auth = true;
		}
		return options;
	}

	function addAuthorization(request) {
		if($.cookie(obj.tokenKey) != undefined) {
			var authToken = $.parseJSON($.cookie(obj.tokenKey));
			if(!request.headers) {
				request.headers = {};
			}
			request.headers[obj.tokenKey] = authToken.token;
		}
	}
	
	
	return obj;	
})();
