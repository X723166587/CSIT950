CSIT950 assignment backend code


# API Documentation

This document provides examples and descriptions of the API endpoints available. Explore the various operations you can perform with our API, including retrieving lists of restaurants, orders, and customers, as well as placing new orders.

## Endpoints Overview

### Restaurants

#### List All Restaurants

**GET** `/restaurant/all`

Retrieves a list of all restaurants, including details such as ID, name, category, rating, revenue, address, phone number, and hero image.

```json
[
  {
    "restaurant_id": 1,
    "restaurant_name": "Tasty Treats",
    "restaurant_category": "Cafe",
    "restaurant_rating": 4,
    "restaurant_revenue": 10000,
    "restaurant_address": "123 Main St",
    "restaurant_phone": 412562170,
    "restaurant_hero_image": "image1.jpg"
  },
  {
    "restaurant_id": 2,
    "restaurant_name": "Savory Sensations",
    "restaurant_category": "Fine Dining",
    "restaurant_rating": 4,
    "restaurant_revenue": 20000,
    "restaurant_address": "456 Side St",
    "restaurant_phone": 923123522,
    "restaurant_hero_image": "image2.jpg"
  }
]
```

**GET** `/customer/all`
Retrieves a list of all customers. The information includes customer ID, name, VIP status, VIP expiration date, address, and phone number.

```json
[
  {
    "customer_id": 1,
    "customer_name": "Alice Smith",
    "vip_status": "active",
    "vip_expire": "2024-12-31",
    "customer_address": "789 Hill Rd",
    "customer_phone": "555-7890"
  },
  {
    "customer_id": 2,
    "customer_name": "Bob Johnson",
    "vip_status": "",
    "vip_expire": null,
    "customer_address": "321 Lake Ave",
    "customer_phone": "555-6543"
  },
  {
    "customer_id": 3,
    "customer_name": "John Doe",
    "vip_status": "active",
    "vip_expire": "2024-12-31",
    "customer_address": "123 Elm Street",
    "customer_phone": "555-1234"
  }
]
```


**GET** `/orders?order_id=1`
Obtains detailed information about a specific customer by their unique customer ID.
```json
{
  "customer_id": 1,
  "customer_name": "Alice Smith",
  "vip_status": "active",
  "vip_expire": "2024-12-31",
  "customer_address": "789 Hill Rd",
  "customer_phone": "555-7890"
}
```


**POST** `/orders`:
Create new order when customer submit order
```shell
curl -X POST http://localhost:8080/orders \
-H "Content-Type: application/json" \
-d '{
    "order_id": "1",
    "restaurant_id": "1",
    "customer_id": "1",
    "order_status": "confirmed",
    "order_subtotal": "27.5",
    "order_service_fee": "3.99"
}'

```

**GET** `/menu?restaurant_id=1`
Get all the item in menu from specific restaurant.
```json
[
  {
    "item_id": 1,
    "item_name": "Cappuccino",
    "item_price": 3.5,
    "item_image": "cappuccino.jpg",
    "item_description": "A classic Italian coffee drink.",
    "item_category": null
  },
  {
    "item_id": 2,
    "item_name": "Espresso",
    "item_price": 2.5,
    "item_image": "espresso.jpg",
    "item_description": "Strong and rich in flavor.",
    "item_category": null
  }
]
```

**POST** `/orders/updateStatus?order_id=?`:
Edit order status (include "confirmed", "accepted", "rejected", "delivering", "delivered")
```shell
curl -X PATCH http://localhost:8080/orders/updateStatus?order_id=1 \
-H "Content-Type: application/json" \
-d 'accepted'
```

**POST** `/orders/updateStatus?order_id=?`:
Customer provide feedback after meal
```shell
curl -X PATCH http://localhost:8080/orders/1/review \
-H "Content-Type: application/json" \
-d '"Excellent service and delicious food. Highly recommended!"'
```


**GET** `/orders/restaurant/{restaurantId}`
Restaurants view their own orders
```json
[
  {
    "order_id": "1",
    "restaurant_id": "1",
    "customer_id": "1",
    "order_status": "confirmed",
    "comment": "Please deliver quickly.",
    "order_subtotal": "20.00",
    "order_rating": "4.5",
    "order_review": "\"Excellent service and delicious food. Highly recommended!\"",
    "order_service_fee": "2.00"
  },
  {
    "order_id": "3",
    "restaurant_id": "1",
    "customer_id": "1",
    "order_status": "confirmed",
    "comment": null,
    "order_subtotal": "27.50",
    "order_rating": null,
    "order_review": null,
    "order_service_fee": "3.99"
  }
]
```

**GET** `/restaurant/{restaurantId}`
Get specific restaurant details
```json
{
  "restaurant_id": 1,
  "restaurant_name": "Tasty Treats",
  "restaurant_category": "Cafe",
  "restaurant_rating": 4,
  "restaurant_revenue": 10000,
  "restaurant_address": "123 Main St",
  "restaurant_phone": 412562170,
  "restaurant_hero_image": "image1.jpg"
}
```

**POST** `/customer`:
Create customer through register page
```shell
curl -X POST http://localhost:8080/customer \
-H "Content-Type: application/json" \
-d '{
"customer_name": "John Doe",
"vip_status": 1,
"vip_expire": "2024-12-31",
"customer_address": "123 Elm Street",
"customer_phone": "555-1234"
}'
```

**PUT** `/menu/update/{item_id}`
Update menu item details
```shell
curl -X PUT http://localhost:8080/menu/update/1 -H "Content-Type: application/json" -d '{
    "item_name": "Updated Cheese Pizza",
    "item_price": 10.99,
    "item_image": "url_to_updated_cheese_pizza_image.jpg",
    "item_description": "An updated description of the delicious cheese pizza.",
    "item_category": "Main Course"
}'
```

**POST** `/menu/update/{item_id}`
Restaurant mange their menu.
```shell
curl -X POST http://localhost:8080/menu -H "Content-Type: application/json" -d '{
    "item_name": "Cheese Pizza",
    "restaurant_id": 1,
    "item_price": 9.99,
    "item_image": "http://example.com/pizza.jpg",
    "item_description": "A delicious cheese pizza",
    "item_category": "Main Course"
}'
```

**DELETE** `/menu/{item_id}`
Delete data from menu
```shell
curl -X DELETE http://localhost:8080/menu/7
```

**GET** `/api/orderitems/1`
Get order items form specific order
```json
[
  {
    "orderId": 1,
    "itemId": 1,
    "quantity": 2
  },
  {
    "orderId": 1,
    "itemId": 2,
    "quantity": 1
  }
]
```



**POST** `/api/orderitems`
Insert order items to database
```shell
curl -X POST http://localhost:8080/api/orderitems \
> -H "Content-Type: application/json" \
> -d '[{"orderId": 2, "itemId": 2, "quantity": 1}, {"orderId": 2, "itemId": 13, "quantity": 1}]'
```






