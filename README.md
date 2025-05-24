# 🧾 Centralized Logging for Microservices with Filebeat, Logstash, Elasticsearch, and Kibana

This project sets up a centralized logging pipeline for Dockerized microservices (`user-service` and `order-service`) using the ELK stack with Filebeat.

---

## 🏗️ Architecture

+------------------+ +---------+ +---------+ +---------------+ +--------+
| user-service.log , order-service.log +-----> Filebeat +-----> | Logstash +-----> | Elasticsearch +-----> | Kibana |
+------------------+ +---------+ +---------+ +---------------+ +--------+

---

## ▶️ Running the Stack

docker-compose up --build

---

📊 Accessing Components

Kibana: http://localhost:5601
Elasticsearch: http://localhost:9200

---

🛠 Troubleshooting

Ensure order-service.log is actively written to the mounted path (./logs/order/order-service.log).
Use docker exec -it filebeat sh and cat /var/log/order-service/order-service.log to check log presence inside container.

---
📌 Indexes

microservice-logs-*


👤 Author
Burak Sarı
Senior Java Developer




