# Etapa 1: Build con Node
FROM node:20-alpine AS build
WORKDIR /app

# Copiar package.json y package-lock.json
COPY package*.json ./
RUN npm install

# Copiar código fuente y compilar
COPY . .
RUN npm run build

# Etapa 2: Servidor con NGINX
FROM nginx:alpine
# Copiar el archivo de configuración de nginx que creamos
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Copiar los archivos compilados de Angular desde la Etapa 1
COPY --from=build /app/dist/fisiohaven-frontend/browser /usr/share/nginx/html

# Exponer el puerto
EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
