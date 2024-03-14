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
"customer_id": "1",
"customer_name": "Alice Smith",
"vip_status": "1",
"vip_expire": "2024-12-31",
"customer_address": "789 Hill Rd",
"customer_phone": "555-7890"
},
{
"customer_id": "2",
"customer_name": "Bob Johnson",
"vip_status": "0",
"vip_expire": null,
"customer_address": "321 Lake Ave",
"customer_phone": "555-6543"
}
]
```


**GET** `/orders?order_id=1`
Obtains detailed information about a specific customer by their unique customer ID.
```json
{
  "customer_id": "1",
  "customer_name": "Alice Smith",
  "vip_status": "1",
  "vip_expire": "2024-12-31",
  "customer_address": "789 Hill Rd",
  "customer_phone": "555-7890"
}

```


**POST** `/orders`:
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
