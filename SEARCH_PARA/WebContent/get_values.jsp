<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link href="https://fonts.googleapis.com/css2?family=Sansita&display=swap" rel="stylesheet"> 
	<link rel="stylesheet" type="text/css" href="task.css">
	<title>Search Tab</title>
</head>

<body>

	<h1>Welcome to the Search Page !</h1>
	<h2>Enter your word and select a category:</h2>
	<p>Type your query about music in the search panel 
	and select the category you are looking into,<br>
	and you will get your results in an Excel spreadsheet</p>
	
	<form action="Get_Results" method="post">
		<label>Search Data:</label><br>
		<input id='dat' type="text" name="s_query" placeholder="Enter text here...">
		
		<select name="categ" id="dropdown" required>
			<option value="songfile">Songs</option>
			<option value="songsinger">Singers</option>
			<option value="songalbum">Albums</option>
			<option value="songcat">Genres</option>
			<option value="songwriter">Song Writers</option>
		</select>
		
		<input id='sub' type="submit" value="Search">
	</form>

</body>
</html>