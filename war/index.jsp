<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="us">
<head>
	<meta charset="utf-8">
	<title>Music Box</title>
	<link href="css/start/jquery-ui-1.10.2.custom.css" rel="stylesheet">
	<script src="js/jquery-1.9.1.js"></script>
	<script src="js/jquery-ui-1.10.2.custom.js"></script>
	<script>
	
	
	$(function() {
		
		/*init the UI element  */
		
		$( "#submitSMS" ).button();
		$( "#submitVoice" ).button();
		$( "#radioset" ).buttonset();
		

		
		$( "#tabs" ).tabs();
		
		$( "#dialog1" ).dialog({
			autoOpen: false,
			width: 400,
			buttons: [
				{
					text: "Ok",
					click: function() {
						$( this ).dialog( "close" );
					}
				}
			]
		});
		
		$( "#dialog2" ).dialog({
			autoOpen: false,
			width: 400,
			buttons: [
				{
					text: "Ok",
					click: function() {
						$( this ).dialog( "close" );
					}
				}
			]
		});

		// Link to open the dialog
		$( "#dialog-link" ).click(function( event ) {
			$( "#dialog1" ).dialog( "open" );
			event.preventDefault();
		});
		
		$( "#dialog-link2" ).click(function( event ) {
			$( "#dialog2" ).dialog( "open" );
			event.preventDefault();
		});


		// Hover states on the static widgets
		$( "#dialog-link, #icons li" ).hover(
			function() {
				$( this ).addClass( "ui-state-hover" );
			},
			function() {
				$( this ).removeClass( "ui-state-hover" );
			}
		);
		
		$( "#dialog-link2, #icons li" ).hover(
				function() {
					$( this ).addClass( "ui-state-hover" );
				},
				function() {
					$( this ).removeClass( "ui-state-hover" );
				}
			);
		
		
		 var scntDiv = $('#p_scents');
	        var i = $('#p_scents p').size() + 1;
	        
	        $('#addScnt').on('click', function() {
	                $('<p><label for="p_scnts"><input type="text" id="p_scnt_'+i+'" size="20" name="p_scnt' +'" value="" placeholder="+6512345678" /></label> <a href="#" id="remScnt">Remove</a></p>').appendTo(scntDiv);
	                i++;
	                return false;
	        });
	        
	        $('#remScnt').on('click', function() { 
	                if( i > 2 ) {
	                        $(this).parents('p').remove();
	                        i--;
	                }
	                return false;
	        });
	        
	        
        var scntDiv = $('#p_voices');
        var i = $('#p_voices p').size() + 1;
        
        $('#addVoice').on('click', function() {
                $('<p><label for="p_vs"><input type="text" id="p_vs_'+i+'" size="20" name="p_vs' +'" value="" placeholder="+6512345678" /></label> <a href="#" id="remVs">Remove</a></p>').appendTo(scntDiv);
                i++;
                return false;
        });
        
        $('#remVs').on('click', function() { 
                if( i > 2 ) {
                        $(this).parents('p').remove();
                        i--;
                }
                return false;
        });
		
	});
	
	
	
	</script>
	<style>
	body{
		font: 62.5% "Trebuchet MS", sans-serif;
		margin: 50px;
	}
	.demoHeaders {
		margin-top: 2em;
	}
	#dialog-link {
		padding: .4em 1em .4em 20px;
		text-decoration: none;
		position: relative;
	}
	#dialog-link span.ui-icon {
		margin: 0 5px 0 0;
		position: absolute;
		left: .2em;
		top: 50%;
		margin-top: -8px;
	}
	#icons {
		margin: 0;
		padding: 0;
	}
	#icons li {
		margin: 2px;
		position: relative;
		padding: 4px 0;
		cursor: pointer;
		float: left;
		list-style: none;
	}
	#icons span.ui-icon {
		float: left;
		margin: 0 4px;
	}
	.fakewindowcontain .ui-widget-overlay {
		position: absolute;
	}
	
	#tabs .ui-tabs-panel {
    height: 400px;
    overflow: auto;
	}
	</style>
</head>
<body>

<h1>Welcome to Music Box!</h1>
<!-- Tabs -->
<div id="tabs">
	<ul>
		<li><a href="#tabs-1">Introduction</a></li>
		<li><a href="#tabs-2">Send a SMS</a></li>
		<li><a href="#tabs-3">Send a voice message</a></li>
		<li><a href="#tabs-4">Send a song</a></li>
		<li><a href="#tabs-5">How It Work</a></li>
	</ul>
	<div id="tabs-1">
		
		<div class="ui-widget">
			<p>You can use this service to send sms or recorded message to your beloved</p>
			<p>In the future , you will even be able to send to your friend(s) their favorite songs on their special occasion</p>
			<p>Click on How it Work to know more about our service</p>
			<br/>
			<br/>
			<p><h2>Disclaimer </h2>This application is in early state of development and to be viewed more as a demo then a complete service</p>
			<p>Future version will include mechanism for user login and address more security and privacy concerns</p>
			<p><h2>Don't abuse this service. We keep logs of your action and will provide to the authority if requested</h2></p>
		</div>
	</div>
	<div id="tabs-2">
	<form action="musicboxSMS/action" method="post">
				<p><a href="#" id="dialog-link" class="ui-state-default ui-corner-all"><span class="ui-icon ui-icon-newwin"></span>Help</a></p>
				<div id="dialog1" title="Help">
				This feature is a work in progress.
				</div>
		<fieldset title="SMS Content">
		<legend>SMS Content:</legend>
		 <p>
		 <textarea rows="5" name="smsContent" cols="40"></textarea><br>
		 </p>
		</fieldset>
		<br/>
		<fieldset title="SMS Content">
		<legend>Receiver Hand-phone number:</legend>
		 <!-- <a href="#" id="addScnt">Add More</a> -->

			<div id="p_scents">
			    <p>
			        <label for="p_scnts"><input type="text" id="p_scnt_1" size="20" name="p_scnt" value="" placeholder="+6512345678" /></label>
			    </p>
			</div>
		</fieldset>
				
			<!-- <input id="submitSMS" type="submit" value="Submit"/> -->
	</form>		
	</div>
	<div id="tabs-3">
	<form action="musicboxVoice/action" method="post">
				<p><a href="#" id="dialog-link2" class="ui-state-default ui-corner-all"><span class="ui-icon ui-icon-newwin"></span>Help</a></p>
				<div id="dialog2" title="Help">
				Key in the message you want to send together with the intended receiver phone number. <br/>You can send the same message to multiple recipient(s)
				<br/>Please read the disclaimer before using this service.
				</div>
			<fieldset title="Voice Message Config">
				<legend>Voice Message Config:</legend>
				 <p>
				 
				 </p>
			</fieldset>
			
			<fieldset title="Voice Content">
				<legend>Voice Message Content:</legend>
				 <p>
				 <textarea rows="5" name="voiceContent" cols="40"></textarea><br>
				 </p>
			</fieldset>
			<br/>
			<fieldset>
			<legend>Receiver Hand-phone number:</legend>
		 <a href="#" id="addVoice">Add More</a>

				<div id="p_voices">
				    <p>
				        <label for="p_vs"><input type="text" id="p_vs_1" size="20" name="p_vs" value="" placeholder="+6512345678" /></label>
				    </p>
				</div>
			</fieldset>
		
			<button id="submitVoice">Submit</button>	
	</form>	
	</div>
	<div id="tabs-4">
	This feature is a work in progress.
	</div>
	<div id="tabs-5">
	<h2>Services</h2>
	<h3>Send a SMS</h3>
	<div class="ui-widget">
			<p>You can use this message to send an anonymous sms to your friend(s)</p>
			<p>The SMS will be sent under our Program Name</p>
			<p>This feature is currently disabled</p>
		</div>
	<h3>Send a voice Message</h3>
	<div class="ui-widget">
			<p>You can use this message to send message to your beloved</p>
			<p>The message will be generated by text-to-speech audio</p>
			<p>Future expansion will involve option to choose gender and language.</p>
			<p>Click on How it Work to know more about our service</p>
		</div>
	<h3>Send a song </h3>
	<div class="ui-widget">
			<p>You can use this message to send a song from our selections to your friends on their special occasions</p>
			<p>This feature is a work in progress</p>
			<p>Click on How it Work to know more about our service</p>
		</div>		
</div>

	<h3>Powered by:</h3>
	<div class="ui-widget">
			<p>Google App Engine - Hoiio Java API -J-Query</p>
		</div>	
	</div>





<br>
</body>
</html>
