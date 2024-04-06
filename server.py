import socket, threading
import random # This import is for testing the multiple clients
from ServerMethods import *


HOST = "127.0.0.1"  # Localhost IP, can and will be changed when needed
PORT = 65432        # Port number to listen to that isn't dedicated to other services


# Sets up the functions and methods for handling clients
# and directing them to the right place
def handle_client(conn, addr):
    print(f"Connected by {addr}")
    print(random.random())
    prompt_client(conn)
    while True:
        request = conn.recv(1024).decode()
        if not request:
            break
        account(request, conn)
    conn.close()
    print(f"Connection closed by {addr}")


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

# From ServerMethods, creates a table for the sqlite database to use
# to store users if one does not already exist
create_table()


# Executes main
if __name__ == "__main__":
    main()