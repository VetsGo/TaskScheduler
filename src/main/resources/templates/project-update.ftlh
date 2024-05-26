<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Project</title>
</head>
<body>
    <h1>Update Project</h1>
    <form action="/projects/${project.projectId}/update" method="post">
        <input type="hidden" name="_method" value="post"/>

        <label for="projectName">Project Name:</label>
        <input type="text" id="projectName" name="projectName" value="${project.projectName}" required><br><br>

        <label for="description">Description:</label>
        <textarea id="description" name="description" required>${project.description}</textarea><br><br>

        <label for="startDate">Start Date:</label>
        <input type="date" id="startDate" name="startDate" value="${project.startDate}" required><br><br>

        <label for="endDate">End Date:</label>
        <input type="date" id="endDate" name="endDate" value="${project.endDate}" required><br><br>

        <button type="submit">Update Project</button>
    </form>
    <a href="/projects/${project.projectId}">Back to Project Details</a>
</body>
</html>