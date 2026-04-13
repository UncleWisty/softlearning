# Instrucciones manuales Postman - Order y OrderDetail

Este archivo solo contiene pasos y datos para ejecutar manualmente en Postman.

## Datos base

- Puerto backend: 8082
- Base URL: http://localhost:8082
- Endpoint base: /softlearning/orders
- Formato de fecha obligatorio: dd/MM/yyyy-HH:mm:ss
- OrderDetail se envia dentro del campo shopCart

Variables recomendadas en Environment:
- baseUrl = http://localhost:8082
- orderPath = /softlearning/orders
- orderId = 9001

## Body JSON para crear orden (con detalles)

```json
{
  "orderID": 9001,
  "operationRef": 9001,
  "clientID": 1,
  "description": "Pedido test Postman",
  "startDate": "13/04/2026-10:30:00",
  "finishDate": null,
  "receiverAddress": "Calle Test 123",
  "receiverPerson": "Ana QA",
  "paymentDate": "13/04/2026-11:00:00",
  "deliveryDate": "14/04/2026-09:15:00",
  "phoneContacts": "600111222,600333444",
  "packageWeight": 2.5,
  "packageHeight": 10.0,
  "packageWidth": 20.0,
  "packageDepth": 30.0,
  "status": "FORTHCOMING",
  "shopCart": [
    {
      "ref": "BOOK-001",
      "price": 25.5,
      "discount": 2.5,
      "amount": 2
    },
    {
      "ref": "COURSE-002",
      "price": 100.0,
      "discount": 10.0,
      "amount": 1
    }
  ]
}
```

## Flujo recomendado en Postman

1. Listar todas las ordenes
- Metodo: GET
- URL: {{baseUrl}}{{orderPath}}
- Header: Accept = application/json

2. Crear orden con OrderDetail
- Metodo: POST
- URL: {{baseUrl}}{{orderPath}}
- Headers:
  - Content-Type = application/json
  - Accept = application/json
- Body: usar el JSON base de arriba

3. Consultar orden por ID en JSON
- Metodo: GET
- URL: {{baseUrl}}{{orderPath}}/{{orderId}}
- Header: Accept = application/json

4. Consultar orden por ID en XML
- Metodo: GET
- URL: {{baseUrl}}{{orderPath}}/{{orderId}}
- Header: Accept = application/xml

5. Actualizar orden y detalle (ejemplo: amount)
- Metodo: PUT
- URL: {{baseUrl}}{{orderPath}}/{{orderId}}
- Headers:
  - Content-Type = application/json
  - Accept = application/json
- Body:

```json
{
  "orderID": 9001,
  "operationRef": 9001,
  "clientID": 1,
  "description": "Pedido actualizado",
  "startDate": "13/04/2026-10:30:00",
  "receiverAddress": "Calle Test 123",
  "receiverPerson": "Ana QA",
  "paymentDate": "13/04/2026-11:00:00",
  "deliveryDate": "14/04/2026-09:15:00",
  "phoneContacts": "600111222,600333444",
  "packageWeight": 2.5,
  "packageHeight": 10.0,
  "packageWidth": 20.0,
  "packageDepth": 30.0,
  "status": "FORTHCOMING",
  "shopCart": [
    {
      "ref": "BOOK-001",
      "price": 25.5,
      "discount": 2.5,
      "amount": 5
    }
  ]
}
```

6. Borrar orden
- Metodo: DELETE
- URL: {{baseUrl}}{{orderPath}}/{{orderId}}

7. Verificar que ya no existe
- Metodo: GET
- URL: {{baseUrl}}{{orderPath}}/{{orderId}}
- Header: Accept = application/json

## Casos negativos utiles (manuales)

1. Duplicado de orderID
- Repite el POST de crear con el mismo orderID (9001)

2. Fecha invalida
- En POST, cambia startDate por: 2026-04-13

3. Estado invalido
- En POST o PUT, usa status: NOT_A_STATUS

## Comportamiento actual a tener en cuenta

En PUT /softlearning/orders/{id}, el backend actualiza usando orderID del body.
Si envias:
- URL: /softlearning/orders/9999
- Body con orderID: 9001
se procesa la orden 9001.
