import socket, threading
import jpysocket

from ServerMethods import *

# HOST = "172.31.44.231"
HOST = "10.0.0.94"  # Localhost IP, can and will be changed when needed
PORT = 65431  # Port number to listen to that isn't dedicated to other services
clients = []  # Global list of connected Clients


# A class that holds a Client's connection and username
class Client:
    def __init__(self, conn):
        self.conn = conn
        self.username = ""


# Sets up the functions and methods for handling clients
# and directing them to the right place
def handle_client(conn, addr):
    print("Connected by {addr}")

    prompt_client(conn)

    request = jpysocket.jpydecode(conn.recv(1024))

    username = account(request, conn)

    print("Username " + username)

    for client in clients:
        if client.conn == conn:
            client.username = username  # Sets username to Client object
            print("Client " + username)

    print()

    messaging = connect(conn)

    client = None

    if messaging == "P":
        # Sends messages to the user to see which other user they want
        # to chat with
        user_message = jpysocket.jpyencode("Enter the User you would like to Message")
        conn.send(user_message)
        user = jpysocket.jpydecode(conn.recv(1024))
        print(user)
        user_exits = find_user(user)
        print(user_exits)

        client_exits = False

        if user_exits:
            print ("Here?")
            for c in clients:  # Finds requested Client
                if c.username == user:
                    print(c.username + " = " + user)
                    client = c
                    client_exits = True

            if not client_exits:
                error_msg = jpysocket.jpyencode("Cannot locate user. Switching to Global Messaging")
                conn.send(error_msg)
                # Connect the user to global messaging if they cannot locate the user
                messaging = "G"

            else:
                success_msg = jpysocket.jpyencode("Found User! Connecting...")
                conn.send(success_msg)
        else:
            error_msg = jpysocket.jpyencode("Cannot locate user. Switching to Global Messaging")
            conn.send(error_msg)
            # Connect the user to global messaging if they cannot locate the user
            messaging = "G"


    # Handles global or private requests
    # Then applies needed functionality to get user connected
    # To whatever option they picked
    while True:
        if messaging == "G":
            handle_global_message(conn, username, clients)

        elif messaging == "P":
            handle_private_message(conn, client)


# Main handles creating the socket server and accepting clients
def main():
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.bind((HOST, PORT))
        s.listen()
        print("Server is listening...")
        while True:
            conn, addr = s.accept()  # Establishes connection

            client_thread = threading.Thread(target=handle_client, args=(conn, addr))
            client_thread.start()  # Creates and starts thread to handle each new connected client

            client = Client(conn)  # Creates client class object

            clients.append(client)  # Adds client to list


# From ServerMethods, creates a table for the sqlite database to use
# to store users if one does not already exist
create_table()

# Executes main
if __name__ == "__main__":
    main()
