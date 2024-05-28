<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Delete Project</title>
</head>
<body>
    <h1>Delete Project</h1>
    <p>Are you sure you want to delete the project: ${project.projectName}?</p>
    <form action="/projects/${project.projectId}/delete" method="post">
        <button type="submit">Delete Project</button>
    </form>
</body>
</html>