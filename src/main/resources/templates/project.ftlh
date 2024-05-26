<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Project</title>
</head>
<body>
    <h1>Create Project</h1>
    <form action="/projects/create" method="post">
        <label for="projectName">Project Name:</label>
        <input type="text" id="projectName" name="projectName" required>
        <br>
        <label for="description">Description:</label>
        <input type="text" id="description" name="description" required>
        <br>
        <label for="startDate">Start Date:</label>
        <input type="date" id="startDate" name="startDate" required>
        <br>
        <label for="endDate">End Date:</label>
        <input type="date" id="endDate" name="endDate" required>
        <br>
        <input type="hidden" name="userId" value="${userId}">
        <button type="submit">Create Project</button>
    </form>
    <h2>Projects</h2>
    <ul>
        <#list projects as project>
            <li>${project.projectName} - ${project.description} (Start: ${project.startDate?string("yyyy-MM-dd")}, End: ${project.endDate?string("yyyy-MM-dd")})</li>
        </#list>
    </ul>
</body>
</html>