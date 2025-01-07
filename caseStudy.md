# Ticket Management System
## Case Study

The **Ticket Management System** is a desktop application designed to automate the ticket booking process. In this system:

- Each **Admin** manages the overall system, including movie or event details, and supervises the ticketing process. Admin details include their **Name, Contact Number, Admin Id (AID) and a unique serial number.**
- Multiple Movies or events can be managed, each identified by a unique **Movie ID (MID)** and having attributes such as **Name, Genre, Timing, and Price.**
- Customers can purchase tickets through Ticket seller or Admin, with each transaction recorded. A Customer is identified by a unique Customer ID (CID) and has details such as Name and Contact Number.
- Each **Ticket** is associated with a unique **Ticket ID (TID)** and includes details like the **Customer ID (CID)**, **Movie ID (MID)**, **Number of Tickets, and Total Price.**


## Entities and Attributes:

1. Admin / TicketSeller
    - Attributes:
        - Serial No > Primary Key
        - Admin ID (AID) / TicketSeller ID (TSID).
        - Name
        - Contact Number

2. Movie
    - Attributes:
        - Movie ID (MID) > Primary Key
        - Name
        - Genre
        - Timing
        - Price

3. Customer
    - Attributes:
        - Customer ID (CID) > Primary Key
        - Name
        - Contact Number

4. Ticket
    - Attributes:
        - Ticket ID (TID) > Primary Key
        - TicketSeller ID (TSID) > Foreign Key
        - Movie ID (MID) > Foreign Key
        - Number of Tickets
        - Total Price


## Introduction

Buying a ticket for a movie is quite an easy process for the normal consumer. But managing a system that stores information about every movie that goes on air and the seller information is a task that requires a very well managed software. A ticket seller can log into the website and manage the display the movies with available seats, time, date and other information. They can modify their dashboards accordingly. Our team built a software that serves the purpose to both the admin and seller. Our admin panel can easily add movies and register new ticket sellers. Admins can see, store and delete information about a seller and themselves. The dashboard system made it easier to access other frames. The ticket seller can control the seats that are available and provide time and limit the quantity for a customer to buy. Lastly it ends with a receipt where all the selected options with the poster and name of the movie is shown. The total price is calculated according to the seat quantity. To summarize, we solved the management issue of a ticket selling system with our software.