FROM node:20.15 as build
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build

FROM nginx
COPY --from=build /app/dist/penca-ucu/browser/ /usr/share/nginx/html
EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
