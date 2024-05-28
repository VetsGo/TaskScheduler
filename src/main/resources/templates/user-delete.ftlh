<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Delete User</title>
</head>
<body>
    <h1>Delete User</h1>
    <p>Are you sure you want to delete the user: ${user.name}?</p>
    <form action="/users/${user.userId}/delete" method="post">
        <button type="submit">Delete User</button>
    </form>
</body>
</html>