import sqlite3
import jpysocket


# Creates a database table if one exists
# Needed to store users correctly
def create_table():
    conn = sqlite3.connect('users.db')
    c = conn.cursor()
    c.execute('''CREATE TABLE IF NOT EXISTS users
                 (username TEXT PRIMARY KEY, password TEXT)''')
    conn.commit()
    conn.close()

# Checks to see if the given login information is valid
def check_login(username, password):
    conn = sqlite3.connect('users.db')
    c = conn.cursor()
    c.execute("SELECT * FROM users WHERE username = ? AND password = ?", (username, password))

    user = c.fetchone()
    c.close()
    conn.close()

    if user:
        return True
    else:
        return False

# Inserts a new user into the database
def insert_user(username, password):

    conn = sqlite3.connect('users.db')
    c = conn.cursor()

    c = conn.cursor()
    c.execute("SELECT EXISTS(SELECT 1 FROM users WHERE username=?)", (username,))
    user_exists = c.fetchone()[0]

    if not user_exists:
        c.execute("INSERT INTO users (username, password) VALUES (?, ?)", (username, password))
        conn.commit()

    c.close()
    conn.close()

    return user_exists;


# Sends prompt to client to begin interacting with the server
def prompt_client(conn):
    prompt = jpysocket.jpyencode("Would you like to (A) Create an account or (B) Use an existing account")
    conn.send(prompt)


# Handles whether to log a user in, or have them create a profile
def account(request, conn):
    username = ""

    if request == 'A':
        username = create_account(conn)
    elif request == 'B':
        username = log_in(conn)
    else:
        message = jpysocket.jpyencode("Error: Incorrect option")
        conn.send(message)

    return username


# Functionality for creating an account
# The user is prompted to create a username and password
# The server then receives the client input and inserts it into a database
def create_account(conn):
    message1 = jpysocket.jpyencode("Create a Username: ")
    conn.send(message1)

    username = jpysocket.jpydecode(conn.recv(1024))

    message2 = jpysocket.jpyencode("Create a password: ")
    conn.send(message2)
    password = jpysocket.jpydecode(conn.recv(1024))

    user_exits = insert_user(username, password)

    if user_exits:
        message3 = jpysocket.jpyencode("User already exists")
    else:
        message3 = jpysocket.jpyencode("You are all registered!")

    conn.send(message3)

    return username


# Functionality for logging a user in
def log_in(conn):
    message1 = jpysocket.jpyencode("Please enter your Username: ")
    conn.send(message1)
    username = jpysocket.jpydecode(conn.recv(1024))

    message2 = jpysocket.jpyencode("Enter your Password: ")
    conn.send(message2)
    password = jpysocket.jpydecode(conn.recv(1024))

    if not check_login(username, password):
        conn.send(jpysocket.jpyencode("Incorrect username or password"))
        username = ""
    else:
        conn.send(jpysocket.jpyencode("You are all logged in"))
    return username


# Handles messaging on the global chat
def handle_global_message(conn, username, clients):
    sender = jpysocket.jpydecode(conn.recv(1024))
    print("Sender: " + sender)

    message = jpysocket.jpydecode(conn.recv(1024))
    print("Message: " + message)

    recipient = jpysocket.jpydecode(conn.recv(1024))
    print("Recipient: " + recipient)

    for client in clients:
        if client.username != sender:
            client.conn.send(jpysocket.jpyencode(sender))
            client.conn.send(jpysocket.jpyencode(message))


# Handles private messaging between two specific clients
def handle_private_message(conn, client):

    sender = jpysocket.jpydecode(conn.recv(1024))
    print("Sender: " + sender)

    message = jpysocket.jpydecode(conn.recv(1024))
    print("Message: " + message)

    recipient = jpysocket.jpydecode(conn.recv(1024))
    print("Recipient: " + recipient)

    # Find the recipient client

    client.conn.send(jpysocket.jpyencode(sender))
    client.conn.send(jpysocket.jpyencode(message))


# Receive a signal from the client for what type of messaging they would like
def connect(conn):
    option = jpysocket.jpydecode(conn.recv(1024))

    return option


# When the user enters the username of another client they want
# to connect to, this method connects to the database to see if the user
# does exist
def find_user(username):
    try:
        conn = sqlite3.connect("users.db")
        c = conn.cursor()
        c.execute("SELECT EXISTS(SELECT 1 FROM users WHERE username=?)", (username,))
        user_exists = c.fetchone()[0]

        c.close()
        conn.close()

        return bool(user_exists)
    except Exception as e:
        print("Error: ", e)
        return False
