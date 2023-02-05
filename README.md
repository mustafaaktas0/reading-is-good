# reading-is-good

## Project Definition
ReadingIsGood is an online books retail firm which operates only on internet. Main target of ReadingIsGood is deliver books to their customer within the same day. That is why stock consistency is the first priority for their vision operations.


## Used Technologies
-Java 11

-Spring-Boot

-H2 Database Engine

-Maven

-Docker


## Done
### Customer Controller
- Will persist new customers
- Will query all orders of the customer 
### Book Controller
- Will persist new book
- Will update book’s stock 
### Order Controller
- Will persist new order (statuses may used) 
- Will update stock records.
(Hint: what if it happens if 2 or more users tries to buy one last book at the same time)
- Will query order by Id
- List orders by date interval ( startDate - endDate )
