# stage 1 to buld the app
FROM node:latest as node
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build --prod

# stage 2 to run it on a more resource efficient container
FROM nginx:alpine
COPY --from=node /app/dist/phase-1 /usr/share/nginx/html
COPY default.conf /etc/nginx/conf.d/default.conf
EXPOSE 80