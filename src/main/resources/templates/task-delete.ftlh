<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Delete Task</title>
</head>
<body>
    <h1>Delete Task</h1>
    <p>Are you sure you want to delete the task: ${task.taskName}?</p>
    <form action="/tasks/${task.taskId}/delete" method="post">
        <input type="hidden" name="_method" value="delete"/>
        <button type="submit">Delete Task</button>
    </form>
    <a href="/tasks/${task.taskId}">Back to Task Details</a>
</body>
</html>