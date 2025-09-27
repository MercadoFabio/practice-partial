FROM node:20-alpine
WORKDIR /usr/src/app

# Instalar dependencias
COPY package*.json ./
RUN npm install -g @angular/cli && npm install

# Copiar el resto del código
COPY . .

# Exponer puerto de Angular
EXPOSE 4200

# Comando para ng serve con host 0.0.0.0 para permitir conexiones externas
CMD ["ng", "serve", "--port", "4200"]
