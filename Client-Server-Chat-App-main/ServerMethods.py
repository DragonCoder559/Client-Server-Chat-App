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


def insert_user(username, password):
    conn = sqlite3.connect('users.db')
    c = conn.cursor()
    c.execute("INSERT INTO users (username, password) VALUES (?, ?)", (username, password))
    conn.commit()

    c.close()
    conn.close()


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

    insert_user(username, password)

    message3 = jpysocket.jpydecode("You are all registered".encode())
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

def handle_message(conn, username, clients):

    sender = jpysocket.jpydecode(conn.recv(1024))
    print("Sender: " + sender)

    message = jpysocket.jpydecode(conn.recv(1024))
    print("Message: " + message)

    recipient = jpysocket.jpydecode(conn.recv(1024))
    print("Recipient: " + recipient)

    for client in clients:
        if client.username == recipient :

            print()
            print(username + " = " + client.username)
            print()

            client.conn.send(jpysocket.jpyencode(sender))
            client.conn.send(jpysocket.jpyencode(message))







