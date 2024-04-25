import sqlite3

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
    conn.send("Would you like to (A) Create an account or (B) Use an existing account".encode())

# Handles whether to log a user in, or have them create a profile
def account(request, conn):
    if request == 'A':
        create_account(conn)
    elif request == 'B':
        log_in(conn)
    else:
        conn.send("Error: Incorrect option".encode())

# Functionality for creating an account
# The user is prompted to create a username and password
# The server then receives the client input and inserts it into a database
def create_account(conn):
    conn.send("Create a Username: ".encode())
    username = conn.recv(1024).decode()
    conn.send("Create a password: ".encode())
    password = conn.recv(1024).decode()
    insert_user(username, password)
    conn.send("You are all registered".encode())

# Functionality for logging a user in
def log_in(conn):
    conn.send("Please enter your Username: ".encode())
    username = conn.recv(1024).decode()
    conn.send("Enter your Password: ".encode())
    password = conn.recv(1024).decode()

    if not check_login(username, password):
        conn.send("Incorrect username or password".encode())
    else:
        conn.send("You are all logged in".encode())
