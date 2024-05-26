<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Task</title>
</head>
<body>
    <h1>Update Task</h1>
    <form action="/tasks/${task.taskId}/update" method="post">
        <input type="hidden" name="_method" value="post"/>
        <label for="taskName">Task Name:</label>
        <input type="text" id="taskName" name="taskName" value="${task.taskName}" required><br><br>

        <label for="description">Description:</label>
        <textarea id="description" name="description" required>${task.description}</textarea><br><br>

        <label for="status">Status:</label>
        <input type="text" id="status" name="status" value="${task.status}" required><br><br>

        <button type="submit">Update Task</button>
    </form>
    <a href="/tasks/${task.taskId}">Back to Task Details</a>
</body>
</html>