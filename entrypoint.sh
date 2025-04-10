#!/bin/sh

echo "⏳ Esperando a que MySQL esté disponible en mysql-db:3306..."

# Espera hasta que MySQL esté listo
while ! nc -z mysql-db 3306; do
  echo "MySQL no disponible aún. Reintentando..."
  sleep 2
done

echo "✅ Base de datos disponible. Iniciando la aplicación Spring Boot..."
exec java -jar app.jar
