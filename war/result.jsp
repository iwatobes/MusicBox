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

		// Link to open the dialog
		$( "#dialog-link" ).click(function( event ) {
			$( "#dialog1" ).dialog( "open" );
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
	
	
		
			<% String result = (String) request.getAttribute("result");
				String[] listHP = (String[]) request.getAttribute("listhp");
				boolean[] listValidate = (boolean[]) request.getAttribute("listresult");
				int count =0;
			%>
			<%if (result.equalsIgnoreCase("true")) { %>
				<h2>Your request has been processed successfully.</h2>
			<%} else {%>
				<h2>Invalid handphone number :</h2>
				<%for (boolean s: listValidate) { %>
					<%if (!s) {%>
					<h3><%=listHP[count] %> is invalid phone number</h3>
					<%} %>
				<%
					count++;
					} %>
			<%} %>
			<p style="font-size: 14">Click <a href="<%=request.getContextPath()%>">here</a> to make another request</p>
		</div>



</body>
</html>
