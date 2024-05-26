<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Task</title>
</head>
<body>
    <h1>Create Task</h1>
    <form action="/tasks/create" method="post">
        <label for="taskName">Task Name:</label>
        <input type="text" id="taskName" name="taskName" required>
        <br>
        <label for="description">Description:</label>
        <input type="text" id="description" name="description" required>
        <br>
        <label for="status">Status:</label>
        <input type="text" id="status" name="status" required>
        <br>
        <input type="hidden" name="projectId" value="${projectId}">
        <input type="hidden" name="userId" value="${userId}">
        <button type="submit">Create Task</button>
    </form>
    <h2>Tasks</h2>
        <ul>
            <#list tasks as task>
                <li>${task.taskName} - ${task.description} - ${task.status}</li>
            </#list>
        </ul>
</body>
</html>