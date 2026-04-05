# Chess

## Índice
1. [Proyecto Chess](#proyecto-chess)
2. [Descripción control check a realizar](#descripción-control-check-a-realizar)
3. [Ejecución del backend](#ejecución-del-backend)
4. [Ejecución del frontend](#ejecución-del-frontend)

---

## Proyecto Chess
Pequeño proyecto basado en spring-petclinic para enseñar arquitecturas SPA con React, Java y Spring, desarrollado para la asignatura DP1 del Grado en Ingeniería del Software de la Universidad de Sevilla.

Este es un fork de https://github.com/spring-projects/spring-petclinic. Los principales cambios que se han realizado son:
- Recorte de varias partes de la aplicación para mantener el ejemplo simple.
- Reorganización de algunas partes del código siguiendo las buenas prácticas introducidas en el curso.
- Modificación de los Controladores para que funcionen como RestControllers, creando varios endpoints de API.
- Modificación de la configuración de seguridad para utilizar JWT.
- Creación de un frontend en React.

## Descripción control check a realizar

## Ejecución del backend

Para ejecutar el backend se deberá correr la clase `GameApplication.java`

Luego, puedes acceder al backend aquí: http://localhost:8080/

## Ejecución del frontend

El proyecto está implementado con un frontend en React situado en la carpeta llamada "frontend". Puedes iniciar el servidor de desarrollo para ver el frontend usando el comando (es posible que debas ejecutar el comando npm install antes de esto):
```
npm start 
```
Luego podrás acceder al frontend en http://localhost:3000