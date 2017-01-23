var common = (function() {
	var obj = {};
	
	obj.ajax = function(request, options) {
		options = defaultOptions(options);
		
		updateUrl(request);
		updateContentType(request);
		
		/*if(options.auth) {
			addAuthorization(request);
			loginForInvalidToken(request);
		}*/
		/*if(options.authorizedToAll) {
			addAuthorizedToAllHeader(request, "true");
		}*/
		/*
		 * This is a fix to prevent caching of custom ajax calls in IE.
		 * 
		 */
		request.cache = false;
		
		$.ajax(request);
	};
	
	function updateUrl(request) {
		request.url = '/asiservices/resources' + request.url;
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
	
	

	function updateContentType(request) {
		if(!request.contentType && request.contentType !== false)
			request.contentType = 'application/json';
	}

	

	function defaultOptions(options) {
		options = options || {};
		
		// Authorize if nothing is said
		if(!options.auth && options.auth !== false)
			options.auth = true;

		return options;
	}

	function addAuthorization(request) {
		var bearerToken = $.cookie(obj.tokenKey);
		
		if(bearerToken && bearerToken !== '') {
			if(!request.headers) {
				request.headers = {};
			}
			
			request.headers[obj.tokenKey] = bearerToken;
		}
	}
	
	function addAuthorizedToAllHeader(request, authValue) {
		if(!request.headers) {
			request.headers = {};
		}
		request.headers[obj.authorizedToAll] = authValue;
	}
	
	return obj;	
})();
