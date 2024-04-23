import socket
import threading
import random  # This import is for testing the multiple clients
from ServerMethods import *

HOST = "10.0.0.94"  # Localhost IP, can and will be changed when needed
PORT = 65431  # Port number to listen to that isn't dedicated to other services
clients = []


class Client:
    def __init__(self, conn):
        self.conn = conn
        self.username = ""


# Sets up the functions and methods for handling clients
# and directing them to the right place
def handle_client(conn, addr):
    print("Connected by {addr}")
    print(random.random())

    prompt_client(conn)

    request = jpysocket.jpydecode(conn.recv(1024))

    username = account(request, conn)

    print("Username " + username)

    for client in clients:
        if client.conn == conn:
            client.username = username
            print("Client: " + username)

    print(len(clients))
    for client in clients:
        print("Client: " + client.username)



    print()

    while True:
        handle_message(conn, username, clients)

    conn.close()
    print("Connection closed by {addr}")


# Main handles creating the socket server and accepting clients
def main():
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.bind((HOST, PORT))
        s.listen()
        print("Server is listening...")
        while True:
            conn, addr = s.accept()

            client_thread = threading.Thread(target=handle_client, args=(conn, addr))
            client_thread.start()

            client = Client(conn)

            clients.append(client)


# From ServerMethods, creates a table for the sqlite database to use
# to store users if one does not already exist
create_table()

# Executes main
if __name__ == "__main__":
    main()
