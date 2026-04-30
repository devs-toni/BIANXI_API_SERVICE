# Bianxi e-commerce

<b>This project is based on the page <a href="https://bianchistore.es">Bianchi Store</a> and mocks the e-commerce system implemented by this page, trying with this to improve my knowledge of React.js and related libraries</b>

<br />

![home](./static/home.png)
![home](./static/products.png)
![home](./static/cart.png)
<br />

This web application uses on <u><i>frontend</i></u>:

<b><ul>
  <li>React v18.2</li>
  <li>React router v6.4.5 for the route management</li>
  <li>Stripe/js for the payment</li>
  <li>React-uauth/google for google authentication</li>
  <li>Sass for styles</li>
</ul></b>

On the <u><i>backend:</i></u>

<b><ul>

  <li>SpringBoot</li>
  <li>MySql</li>
</ul></b>

# 🚀 BIANXI API Service

[![Spring Boot](https://img.shields.io/badge/Framework-Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Docker Hub](https://img.shields.io/badge/Docker%20Hub-Latest-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://hub.docker.com/r/devstoni/bianxi-api)
[![Architecture](https://img.shields.io/badge/Architecture-Hexagonal-orange?style=for-the-badge)](https://en.wikipedia.org/wiki/Hexagonal_architecture_(software))

Backend robusto para el e-commerce de bicicletas profesionales **Bianchi**. Diseñado bajo arquitectura hexagonal para garantizar escalabilidad y desacoplamiento.

---

## 🛠️ Entorno de Desarrollo

### 1. Base de Datos (MySQL)
Antes de arrancar la API, levanta la instancia de base de datos:
```bash
cd db/migrations
docker-compose -f docker-compose-develop.yml up -d
```

👩‍🚀 I hope you enjoy it! 🚀
