var isSubmitting = false;
var displayAlertUser=true;


function getCurrentSchoolyear(){
	
	var d = new Date();
	var  currentYear = d.getFullYear();
	var currentMonth = d.getMonth();
	if(currentMonth < 9)
		return currentYear;
	else 
		return currentYear+1;
}


function getFormattedCurrentSchoolyear(){
	
	var d = new Date();
	var  currentYear = d.getFullYear();
	var currentMonth = d.getMonth();
	if(currentMonth < 9)
		return (currentYear-1)+'-'+currentYear;
	else 
		return currentYear+'-'+(currentYear+1);
}



$(document).ready(function() {

	
	var date = new Date();
	date.setTime(date.getTime() + (sessionTimeout * 1000));
	$.cookie("cap.timeout", date, { expires: date });
	$.timeoutDialog({timeout: sessionTimeout, countdown: sessionTimeoutCountdown*60, logout_redirect_url: '/cap/logout', restart_on_yes: true});
	
	$('[class^="col-sm-"]:has(.col-form-label.float-right.font-weight-bold)').css("padding-right", "0");
	textAreaInit();
	
	if (typeof(pageLoad) === "function") {
		pageLoad();
		}
	
	if(typeof(pageName)!="undefined"){
	$('.list-unstyled li').removeClass('active');
	$('#'+pageName).addClass('active');
	}
	
	$("form").on("submit", function(){
		var lastTime = $(this).data("lastSubmitTime");
		if (lastTime && typeof lastTime === "object") {
		    var now = new Date();
		    
		    if ((now - lastTime) < 30000) // 2000ms
		        return false;
		}
		
		$(this).data("lastSubmitTime", new Date());
		 isSubmitting = true;
		 displayAlertUser=false;
		 var date = new Date();
         date.setTime(date.getTime() + (sessionTimeout * 1000));
         $.cookie("cap.timeout", date, { expires: date });
 		 return true;
		 });
	
	if(timeoutUserNotice)
		$('#sessionTimeoutPolicy').show();

	 $('form').data('initial-state', $('form').serialize());

	 if(isReportsMenuActive)
		 $('#reportsMainMenu').click();
	 
	 
    });




function textAreaInit(){
	
	var isCtrlV=false;
	var originalTxt = [];

	
	if (typeof(autosize) === "function") {
	autosize($('textarea'));
	}
	
	
	if (typeof(autosize) === "function") {
		autosize($('textarea'));
		}
		
		$('textarea:not([maxLength])').attr('maxLength','4000');

		
		$('textarea').each(function( index ) {
			  if($(this).next('.countchar').length==0)
				 $(this).after('<div class="countchar" style="width:100%;text-align:left"></div>');
			});
		
		$('textarea').each(
				function(){
					var maxlen = this.maxLength==-1?4000:this.maxLength;
					var length = getByteLen($(this).val());
					var text=$(this).val();
					$(this).val(text);
			        $(this).next('.countchar').html('<span" >Count: '+(maxlen-length )+' chars left</span>');
			    }
		);
		
		$('textarea').on('keydown',function(e) {
			if(e.ctrlKey && e.keyCode==86)
				isCtrlV=true;
			originalTxt.push($(this).val());
		});
		
		$('textarea').on('keyup paste',function(e) 
			{
		
			if(e.type=='keyup'&&(isCtrlV && e.keyCode==86)||e.keyCode==17){
				isCtrlV=false;
				return;
			}
				
				var maxlen = this.maxLength==-1?4000:this.maxLength;
				
				
				
				var text=$(this).val();
				
				var length = getByteLen(text);
				
				var  copyText='';
				
				  if(e.type=='paste'){
				  copyText=e.originalEvent.clipboardData.getData('text').replace(/(\r\n|\n|\r)/g, "\r\n");
				  length=length+getByteLen(copyText);
				  }
				  if(length > (maxlen)){
					  $(this).next().css('font-weight','bold').css('color','red');
					  $(this).next().html('<span>Count: max length '+maxlen+' chars only!</span>')
					 					  
					  while (originalTxt.length>0) {
						  $(this).val(originalTxt.pop());
				        }
				 
					  e.preventDefault();
				  }
				  else
				    {
					  while (originalTxt.length>0) {
						  originalTxt.pop();
				        }
					  $(this).next().css('color','black').css('font-weight','normal');
					  $(this).next().html('<span>Count: '+(maxlen-length )+' chars left</span>');
				    }
				  
				  
				});
}


function loadGlobalMessages(message){
	$('#global-errors').append('<span class="error" >'+message+'</span>');
}

function resetFormData(path){

	var uri=path;
	
	$.ajax({
		type : "GET",
		url : uri,
		 async: false,
		success : function(response) {},
		error : function(msg, url, line) {
			targetElement.after('<span class="error" >Unknown System Exception</span>');
		}
	});
}

function loadDropDown(path,targetElementId) {		
		var targetElement = $('#' + targetElementId);
		var elementName=targetElement.attr("name");
		targetElement.val('');
		targetElement.children('option:not(:first)').remove();
		var uri=path;

		$.ajax({
			type : "GET",
			url : uri,
			 async: false,
			success : function(response) {
				$.each(response, function( key, value) {
					targetElement.append($("<option></option>").attr("value",
							key).text(value));
				});
			},
			error : function(msg, url, line) {
				targetElement.after('<span class="error" >Unknown System Exception</span>');
			}
		});
	}

function loadDropDownWithSortOrder(path,targetElementId) {		
	var targetElement = $('#' + targetElementId);
	var elementName=targetElement.attr("name");
	targetElement.val('');
	targetElement.children('option:not(:first)').remove();
	var uri=path;

	$.ajax({
		type : "GET",
		url : uri,
		 async: false,
		success : function(response) {
			$.each(response, function( index, map) {
				targetElement.append($("<option></option>").attr("value",
						map.key).text(map.value));
			});
		},
		error : function(msg, url, line) {
			targetElement.after('<span class="error" >Unknown System Exception</span>');
		}
	});
}



function loadDataTable(path,update,process,emptyMessage,errorSection){
	
	
	if(typeof(errorSection)==='undefined')
		errorSection='error-section';
	
	$('#'+errorSection).text("");
	
	var processElements = process.split(',');
	
	var parameters=[];
	
	for (element in processElements) {	
		parameter={name:$('#'+processElements[element]).attr("name"),value: $('#'+processElements[element]).val()}
		parameters.push(parameter);
    }
	
	var queryString=jQuery.param(parameters);
	
	if(queryString!='')
		queryString='?'+queryString;
	
	var uri= path +queryString;
	
	 jQuery.param(parameters);
	
	var elementName=$('#'+process).attr("name");
	
	
	jQuery.support.cors = true;
	$.ajax({
				type : "GET",
				url : uri,
				async : false,
				success : function(
						response) {

					$('#'+ update+'Wrapper' ).text("");
					
					result=$(response).find('#'+update+'Datatable');
					
					if(result.length==0){
						result=$(response).children()[0];
						$('#'+errorSection ).append(result);
					return;
					}
					
					$('#'+ update+'Wrapper' ).append(result);
					
					result.dataTable({																		
								"language": {
							      "emptyTable": emptyMessage
						    } });														
					
				},
				error : function(msg,
						url, line) {
					$('#table-section')
							.text(
									'Unknown Exception');
				}
			});
	
}




var originalVal = $.fn.val;

$.fn.val = function (value) {
    if (typeof value == 'undefined') {
        if ($(this).is("textarea")) {
            return originalVal.call(this)
                .replace(/(\r\n|\n|\r)/g, "\r\n");
        }

        return originalVal.call(this);
    }
    else {
        return originalVal.call(this, value);
    }
};


String.prototype.includes = function (str) {
	  var returnValue = false;

	  if (this.indexOf(str) !== -1) {
	    returnValue = true;
	  }

	  return returnValue;
	}


function getByteLen(normal_val) {
    // Force string type
    normal_val = String(normal_val);

    var byteLen = 0;
    for (var i = 0; i < normal_val.length; i++) {
        var c = normal_val.charCodeAt(i);
        byteLen += c < (1 <<  7) ? 1 :
                   c < (1 << 11) ? 2 :
                   c < (1 << 16) ? 3 :
                   c < (1 << 21) ? 4 :
                   c < (1 << 26) ? 5 :
                   c < (1 << 31) ? 6 : Number.NaN;
    }
    return byteLen;
}

function alertUser(){
	$(window).bind('beforeunload', function(e){
	    if(displayAlertUser&&($('form').serialize()!=$('form').data('initial-state') || (containsUnsavedUserChanges!='undefined'&&containsUnsavedUserChanges)))return true;
	    else e=null; // i.e; if form state change show warning box, else don't show it.
	});
}


/*
 * timeout-dialog.js v1.0.1, 01-03-2012
 * 
 * @author: Rodrigo Neri (@rigoneri)
 * 
 * (The MIT License)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE. 
 */


/* String formatting, you might want to remove this if you already use it.
 * Example:
 * 
 * var location = 'World';
 * alert('Hello {0}'.format(location));
 */
String.prototype.format = function() {
  var s = this,
      i = arguments.length;

  while (i--) {
    s = s.replace(new RegExp('\\{' + i + '\\}', 'gm'), arguments[i]);
  }
  return s;
};
/*
 * timeout-dialog.js v1.0.1, 01-03-2012
 * 
 * @author: Rodrigo Neri (@rigoneri)
 * 
 * (The MIT License)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE. 
 */


/* String formatting, you might want to remove this if you already use it.
 * Example:
 * 
 * var location = 'World';
 * alert('Hello {0}'.format(location));
 */
String.prototype.format = function() {
  var s = this,
      i = arguments.length;

  while (i--) {
    s = s.replace(new RegExp('\\{' + i + '\\}', 'gm'), arguments[i]);
  }
  return s;
};

!function($) {
  $.timeoutDialog = function(options) {

    var settings = {
      timeout: 3600,
      countdown: 300,
      title : 'Your session is about to expire!',
      message : 'You will be logged out in {0} seconds.',
      question: 'Do you want to stay signed in?',
      keep_alive_button_text: 'Yes, Keep me signed in',
      sign_out_button_text: 'No, Sign me out',
      keep_alive_url: '/cap/extendsession',
      logout_url: null,
      logout_redirect_url: '/logout',
      restart_on_yes: true,
      dialog_width: 350
    }    

    $.extend(settings, options);

    var TimeoutDialog = {
      init: function () {
        this.setupDialogTimer();
      }, 

      setupDialogTimer: function() {
        var self = this;
        self.destroyDialog();
        var expires=$.cookie("cap.timeout");
        var sessionExpire=new Date(expires).getTime();
        settings.timeout=Math.round((sessionExpire-new Date().getTime())/1000);

        window.setTimeout(function() {
           self.setupDialog();
        }, (settings.timeout - settings.countdown) * 1000);
      },

      setupDialog: function() {
        var self = this;
       
         $('#timeout-dialog').modal('show');
		   $('#keep-alive-button').click(function(){
				 self.keepAlive();
		});
		$('#sign-out-button').click(function(){
				 self.signOut(true);
		});       
       

        self.startCountdown();
      },

      destroyDialog: function() {
        $('#timeout-dialog').modal('hide');
      },

      startCountdown: function() {
        var self = this,
            counter = settings.countdown;

        this.countdown = window.setInterval(function() {
       
        var expires=$.cookie("cap.timeout");
        
        if(expires==undefined){
        	window.clearInterval(self.countdown);
            self.signOut(false);
        }
        
        var sessionExpire=new Date(expires).getTime();
        counter=Math.round((sessionExpire-new Date().getTime())/1000);
        
        var minutes = Math.floor(counter/60);
        var seconds = counter %60;
        var counterTxt='';
        
    
        if(minutes >0){
        	counterTxt =  (minutes < 10 ? "0" + minutes : minutes);
            counterTxt += ":" + (seconds  < 10 ? "0" + seconds : seconds) +' minutes';
        }else{
        	 counterTxt= (seconds  < 10 ? "0" + seconds : seconds) +' seconds';
        }
          
        
         $("#timeout-countdown").html(counterTxt);

          if (counter <= 0) {
            window.clearInterval(self.countdown);
            self.signOut(false);
          }
          
          if (counter > settings.countdown) {
              window.clearInterval(self.countdown);
              self.setupDialogTimer();
            }

        }, 1000);
      },

      keepAlive: function() {
        var self = this;
        this.destroyDialog();
        window.clearInterval(this.countdown);

        $.get(settings.keep_alive_url, function(data) {
          if (data == "OK") {
        	  displayAlertUser=true;
        	  var date = new Date();
              date.setTime(date.getTime() + (sessionTimeout * 1000));
              $.cookie("cap.timeout", date, { expires: date });
            if (settings.restart_on_yes) {           
             self.setupDialogTimer();
            }
          }
          else {
            self.signOut(false);
          }
        });
      },

      signOut: function(is_forced) {
    	$.removeCookie('cap.timeout');
        var self = this;
        displayAlertUser=false;
        this.destroyDialog();

        if (settings.logout_url != null) {
            $.post(settings.logout_url, function(data){
                self.redirectLogout(is_forced);
            });
        }
        else {
            self.redirectLogout(is_forced);
        }
      }, 

      redirectLogout: function(is_forced){
        var target = settings.logout_redirect_url + '?next=' + encodeURIComponent(window.location.pathname + window.location.search);
        if (!is_forced)
          target += '&timeout=t';
        window.location = target;
      }
    };

    TimeoutDialog.init();
  };
}(window.jQuery);